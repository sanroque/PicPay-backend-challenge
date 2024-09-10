package local.picpay.picpay_backend.services;

import local.picpay.picpay_backend.domain.transaction.Transaction;
import local.picpay.picpay_backend.domain.user.User;
import local.picpay.picpay_backend.dto.TransactionDTO;
import local.picpay.picpay_backend.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;

    public Transaction createTransaction(TransactionDTO transaction) throws Exception {
        User sender = this.userService.findUserById(transaction.senderId());
        User reciever = this.userService.findUserById(transaction.recieverId());

        userService.validateTransaction(sender, transaction.value());

        boolean isAuthorized =this.authorizeTransaction(sender, transaction.value());
        if (!isAuthorized){
            throw new Exception("Transaction not authorized!");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setReciever(reciever);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        reciever.setBalance(reciever.getBalance().add(transaction.value()));

        this.transactionRepository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(reciever);

        this.notificationService.sendNotification(sender, "Transaction sent successfully");
        this.notificationService.sendNotification(reciever, "Transaction recieved successfully");

        return newTransaction;
    }

    public boolean authorizeTransaction(User sender, BigDecimal value){
        ResponseEntity<Map> authorizeResponse = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);

        if (authorizeResponse.getStatusCode() == HttpStatus.OK){
            String message = (String) authorizeResponse.getBody().get("status");
            return "success".equalsIgnoreCase(message);
        }else return false;
    }

}
