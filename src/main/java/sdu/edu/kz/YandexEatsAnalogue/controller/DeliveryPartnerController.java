package sdu.edu.kz.YandexEatsAnalogue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdu.edu.kz.YandexEatsAnalogue.dto.DeliveryPartnerDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.DeliveryPartner;
import sdu.edu.kz.YandexEatsAnalogue.service.DeliveryPartnerService;
import sdu.edu.kz.YandexEatsAnalogue.utils.ModelMapperUtil;

@RestController
@RequestMapping("/deliveryPartners")
@RequiredArgsConstructor
public class DeliveryPartnerController {

    @Autowired
    private final DeliveryPartnerService deliveryPartnerService;

    // Assume CustomMapper contains static methods for converting between entities and DTOs
    @Autowired
    private final ModelMapperUtil modelMapperUtil;

    @PutMapping("/{partnerId}")
    public ResponseEntity<DeliveryPartnerDTO> updateDeliveryPartner(@PathVariable Long partnerId, @RequestBody DeliveryPartnerDTO deliveryPartnerDTO) {
        return deliveryPartnerService.findDeliveryPartnerById(partnerId)
                .map(partner -> {
                    // Here, you would manually set fields from deliveryPartnerDTO to partner
                    partner.setName(deliveryPartnerDTO.getName());
                    partner.setPhone(deliveryPartnerDTO.getPhone());
                    partner.setIsActive(deliveryPartnerDTO.getIsActive());

                    DeliveryPartner updatedPartner = deliveryPartnerService.saveDeliveryPartner(partner);
                    return ResponseEntity.ok(modelMapperUtil.toDeliveryPartnerDTO(updatedPartner));
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
