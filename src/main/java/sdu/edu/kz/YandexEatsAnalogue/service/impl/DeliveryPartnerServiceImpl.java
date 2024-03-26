package sdu.edu.kz.YandexEatsAnalogue.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdu.edu.kz.YandexEatsAnalogue.entity.DeliveryPartner;
import sdu.edu.kz.YandexEatsAnalogue.repository.DeliveryPartnerRepository;
import sdu.edu.kz.YandexEatsAnalogue.service.DeliveryPartnerService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryPartnerServiceImpl implements DeliveryPartnerService {
    @Autowired
    private final DeliveryPartnerRepository deliveryPartnerRepository;

    public List<DeliveryPartner> findAllDeliveryPartners() {
        return deliveryPartnerRepository.findAll();
    }

    public Optional<DeliveryPartner> findDeliveryPartnerById(Long id) {
        return deliveryPartnerRepository.findById(id);
    }

    public DeliveryPartner saveDeliveryPartner(DeliveryPartner deliveryPartner) {
        return deliveryPartnerRepository.save(deliveryPartner);
    }

    public void deleteDeliveryPartner(Long id) {
        deliveryPartnerRepository.deleteById(id);
    }
}
