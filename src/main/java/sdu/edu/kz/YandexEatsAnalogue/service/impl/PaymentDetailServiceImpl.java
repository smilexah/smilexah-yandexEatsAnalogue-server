package sdu.edu.kz.YandexEatsAnalogue.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sdu.edu.kz.YandexEatsAnalogue.dto.PaymentDetailDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.Order;
import sdu.edu.kz.YandexEatsAnalogue.entity.PaymentDetail;
import sdu.edu.kz.YandexEatsAnalogue.repository.PaymentDetailRepository;
import sdu.edu.kz.YandexEatsAnalogue.service.PaymentDetailService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentDetailServiceImpl implements PaymentDetailService {
    private final PaymentDetailRepository paymentDetailRepository;

    @Override
    public List<PaymentDetail> findAllPaymentDetails() {
        return paymentDetailRepository.findAll();
    }

    @Override
    public Optional<PaymentDetail> findPaymentDetailById(Long id) {
        return paymentDetailRepository.findById(id);
    }

    @Override
    public void savePaymentDetail(PaymentDetailDTO paymentDetailDTO) {
        PaymentDetail paymentDetail = new PaymentDetail();

        Order order = new Order();
        order.setOrderId(paymentDetailDTO.getOrderId());
        paymentDetail.setOrder(order);

        paymentDetail.setAmount(paymentDetailDTO.getAmount());
        paymentDetail.setPaymentMethod(paymentDetailDTO.getPaymentMethod());
        paymentDetail.setPaymentStatus(paymentDetailDTO.getPaymentStatus());
        paymentDetail.setTransactionID(paymentDetailDTO.getTransactionID());
        paymentDetail.setPaymentDateTime(paymentDetailDTO.getPaymentDateTime());

        paymentDetailRepository.save(paymentDetail);
    }

    @Override
    public void updatePaymentDetail(PaymentDetailDTO paymentDetailDTO, Long id) {
        Optional<PaymentDetail> paymentDetailOptional = paymentDetailRepository.findById(id);
        if (paymentDetailOptional.isEmpty()) {
            throw new EntityNotFoundException("Payment detail not found");
        }
        PaymentDetail paymentDetail = paymentDetailOptional.get();

        Order order = new Order();
        order.setOrderId(paymentDetailDTO.getOrderId());
        paymentDetail.setOrder(order);

        paymentDetail.setAmount(paymentDetailDTO.getAmount());
        paymentDetail.setPaymentMethod(paymentDetailDTO.getPaymentMethod());
        paymentDetail.setPaymentStatus(paymentDetailDTO.getPaymentStatus());
        paymentDetail.setTransactionID(paymentDetailDTO.getTransactionID());
        paymentDetail.setPaymentDateTime(paymentDetailDTO.getPaymentDateTime());

        paymentDetailRepository.save(paymentDetail);
    }

    @Override
    public void deletePaymentDetail(Long id) {
        paymentDetailRepository.deleteById(id);
    }
}
