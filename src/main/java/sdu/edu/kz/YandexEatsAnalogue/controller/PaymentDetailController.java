package sdu.edu.kz.YandexEatsAnalogue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdu.edu.kz.YandexEatsAnalogue.dto.PaymentDetailDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.PaymentDetail;
import sdu.edu.kz.YandexEatsAnalogue.service.PaymentDetailService;
import sdu.edu.kz.YandexEatsAnalogue.utils.ModelMapperUtil;

@RestController
@RequestMapping("/paymentDetails")
@RequiredArgsConstructor
public class PaymentDetailController {

    @Autowired
    private final PaymentDetailService paymentDetailService;

    @Autowired
    private final ModelMapperUtil modelMapperUtil;

    @PutMapping("/{paymentId}")
    public ResponseEntity<PaymentDetailDTO> updatePaymentDetail(@PathVariable Long paymentId, @RequestBody PaymentDetailDTO paymentDetailDTO) {
        return paymentDetailService.findPaymentDetailById(paymentId)
                .map(paymentDetail -> {
                    // Manual mapping fields
                    paymentDetail.setAmount(paymentDetailDTO.getAmount());
                    paymentDetail.setPaymentMethod(paymentDetailDTO.getPaymentMethod());
                    paymentDetail.setPaymentStatus(paymentDetailDTO.getPaymentStatus());
                    paymentDetail.setTransactionID(paymentDetailDTO.getTransactionID());
                    paymentDetail.setPaymentDateTime(paymentDetailDTO.getPaymentDateTime());

                    PaymentDetail updatedPaymentDetail = paymentDetailService.savePaymentDetail(paymentDetail);
                    return ResponseEntity.ok(modelMapperUtil.toPaymentDetailDTO(updatedPaymentDetail));
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}