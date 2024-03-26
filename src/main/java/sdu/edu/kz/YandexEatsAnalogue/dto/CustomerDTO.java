package sdu.edu.kz.YandexEatsAnalogue.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private Long customerId;
    private UserAccountDTO userAccount;
    private String name;
    private String phone;
    private String address;
}
