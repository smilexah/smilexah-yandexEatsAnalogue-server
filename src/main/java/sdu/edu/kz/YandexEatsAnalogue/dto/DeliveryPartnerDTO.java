package sdu.edu.kz.YandexEatsAnalogue.dto;

import lombok.Data;

@Data
public class DeliveryPartnerDTO {
    private Long partnerId;
    private String name;
    private String phone;
    private Boolean isActive;
}
