package local.picpay.picpay_backend.repositories;

import local.picpay.picpay_backend.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
