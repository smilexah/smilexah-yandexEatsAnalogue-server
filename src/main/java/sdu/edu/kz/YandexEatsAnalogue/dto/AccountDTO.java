package sdu.edu.kz.YandexEatsAnalogue.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private String email;
    private String password;
    private String role;
    private Boolean isActive;
}
