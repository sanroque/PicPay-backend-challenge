package local.picpay.picpay_backend.domain.transaction;

import jakarta.persistence.*;
import local.picpay.picpay_backend.domain.user.User;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transactions")
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name= "sender_id")
    private User sender;
    @ManyToOne
    @JoinColumn(name = "reciever_id")
    private User reciever;
    private LocalDateTime timestamp;
}
