package com.simplepicpay.domain.user;
import com.simplepicpay.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "users")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private BigDecimal balance;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User(UserDTO data){
        firstName = data.firstName();
        lastName = data.lastName();
        balance = data.balance();
        document = data.document();
        email = data.email();
        password = data.password();
        userType = data.type();
    }
}