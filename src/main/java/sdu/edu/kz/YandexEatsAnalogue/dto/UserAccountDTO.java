package sdu.edu.kz.YandexEatsAnalogue.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountDTO {
    private Long userId;
    private String email;
    private String password;
    private String role;
    private Boolean isActive;
}
