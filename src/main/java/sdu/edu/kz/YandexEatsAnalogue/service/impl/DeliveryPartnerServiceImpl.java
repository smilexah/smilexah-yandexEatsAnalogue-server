package sdu.edu.kz.YandexEatsAnalogue.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sdu.edu.kz.YandexEatsAnalogue.dto.DeliveryPartnerDTO;
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

    @Override
    public List<DeliveryPartner> findAllDeliveryPartners() {
        return deliveryPartnerRepository.findAll();
    }

    @Override
    public Optional<DeliveryPartner> findDeliveryPartnerById(Long id) {
        return deliveryPartnerRepository.findById(id);
    }

    @Override
    public void saveDeliveryPartner(DeliveryPartnerDTO deliveryPartnerDTO) {
        DeliveryPartner deliveryPartner = new DeliveryPartner();
        deliveryPartner.setName(deliveryPartnerDTO.getName());
        deliveryPartner.setPhone(deliveryPartnerDTO.getPhone());
        deliveryPartner.setIsActive(Boolean.TRUE);
        deliveryPartnerRepository.save(deliveryPartner);
    }

    @Override
    public void updateDeliveryPartner(DeliveryPartnerDTO deliveryPartnerDTO, Long id) {
        Optional<DeliveryPartner> deliveryPartnerOptional = deliveryPartnerRepository.findById(id);
        if (deliveryPartnerOptional.isEmpty()) {
            throw new EntityNotFoundException("Delivery partner not found");
        }
        DeliveryPartner deliveryPartner = deliveryPartnerOptional.get();
        deliveryPartner.setName(deliveryPartnerDTO.getName());
        deliveryPartner.setPhone(deliveryPartnerDTO.getPhone());
        deliveryPartner.setIsActive(deliveryPartnerDTO.getIsActive());
        deliveryPartnerRepository.save(deliveryPartner);
    }

    @Override
    public void deleteDeliveryPartner(Long id) {
        deliveryPartnerRepository.deleteById(id);
    }
}
