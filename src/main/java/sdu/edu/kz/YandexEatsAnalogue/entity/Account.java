package sdu.edu.kz.YandexEatsAnalogue.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sdu.edu.kz.YandexEatsAnalogue.utils.constants.Roles;

@Entity
@Table(name = "user_accounts")
@Getter
@Setter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Email(message = "Invalid email")
    @NotEmpty(message = "Email is missing!")
    private String email;

    @NotEmpty(message = "Password is missing!")
    private String password;

    @Enumerated(EnumType.STRING)
    @NotEmpty(message = "Role is missing!")
    private Roles role;

    @NotNull(message = "Active status is missing!")
    private Boolean isActive;
}