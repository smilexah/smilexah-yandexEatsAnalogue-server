package sdu.edu.kz.YandexEatsAnalogue.service;

import org.springframework.stereotype.Service;

import sdu.edu.kz.YandexEatsAnalogue.dto.DeliveryPartnerDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.DeliveryPartner;

import java.util.List;
import java.util.Optional;

@Service
public interface DeliveryPartnerService {
    List<DeliveryPartner> findAllDeliveryPartners();
    Optional<DeliveryPartner> findDeliveryPartnerById(Long id);
    void saveDeliveryPartner(DeliveryPartnerDTO deliveryPartnerDTO);
    void updateDeliveryPartner(DeliveryPartnerDTO deliveryPartnerDTO, Long id);
    void deleteDeliveryPartner(Long id);
}
