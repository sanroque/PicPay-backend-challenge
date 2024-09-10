package local.picpay.picpay_backend.domain.user;

import jakarta.persistence.*;
import local.picpay.picpay_backend.dto.UserDTO;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Entity(name ="users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;
    private String password;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private UserType userType;

  public User(UserDTO data){
      BeanUtils.copyProperties(data, this);
  }
}
