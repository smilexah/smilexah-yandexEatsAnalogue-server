package sdu.edu.kz.YandexEatsAnalogue.service;

import org.springframework.stereotype.Service;
import sdu.edu.kz.YandexEatsAnalogue.entity.DeliveryPartner;

import java.util.List;
import java.util.Optional;

@Service
public interface DeliveryPartnerService {
    List<DeliveryPartner> findAllDeliveryPartners();
    Optional<DeliveryPartner> findDeliveryPartnerById(Long id);
    DeliveryPartner saveDeliveryPartner(DeliveryPartner deliveryPartner);
    void deleteDeliveryPartner(Long id);
}
