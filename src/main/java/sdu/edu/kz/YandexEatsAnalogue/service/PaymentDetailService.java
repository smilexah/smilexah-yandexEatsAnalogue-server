package sdu.edu.kz.YandexEatsAnalogue.service;

import org.springframework.stereotype.Service;
import sdu.edu.kz.YandexEatsAnalogue.entity.PaymentDetail;

import java.util.List;
import java.util.Optional;

@Service
public interface PaymentDetailService {
    List<PaymentDetail> findAllPaymentDetails();
    Optional<PaymentDetail> findPaymentDetailById(Long id);
    PaymentDetail savePaymentDetail(PaymentDetail paymentDetail);
    void deletePaymentDetail(Long id);
}
