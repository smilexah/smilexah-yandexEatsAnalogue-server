package sdu.edu.kz.YandexEatsAnalogue.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdu.edu.kz.YandexEatsAnalogue.entity.PaymentDetail;
import sdu.edu.kz.YandexEatsAnalogue.repository.PaymentDetailRepository;
import sdu.edu.kz.YandexEatsAnalogue.service.PaymentDetailService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentDetailServiceImpl implements PaymentDetailService{
    @Autowired
    private PaymentDetailRepository paymentDetailRepository;

    @Override
    public List<PaymentDetail> findAllPaymentDetails() {
        return paymentDetailRepository.findAll();
    }

    @Override
    public Optional<PaymentDetail> findPaymentDetailById(Long id) {
        return paymentDetailRepository.findById(id);
    }

    @Override
    public PaymentDetail savePaymentDetail(PaymentDetail paymentDetail) {
        return paymentDetailRepository.save(paymentDetail);
    }

    @Override
    public void deletePaymentDetail(Long id) {
        paymentDetailRepository.deleteById(id);
    }
}
