package local.picpay.picpay_backend.services;

import local.picpay.picpay_backend.domain.user.User;
import local.picpay.picpay_backend.domain.user.UserType;
import local.picpay.picpay_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if (sender.getUserType() == UserType.MERCHANT){
            throw new Exception("User type merchant has no permissions to make transactions!");
        }

        if (sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Insufficient funds to make transactions!");
        }
    }

    public User findUserById(Long id) throws Exception {
        return this.userRepository.findById(id).orElseThrow(() -> new Exception("User not found!"));
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }
}
