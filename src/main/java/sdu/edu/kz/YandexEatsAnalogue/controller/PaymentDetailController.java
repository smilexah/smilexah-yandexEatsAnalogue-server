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

    @GetMapping
    public ResponseEntity<?> getAllPaymentDetails() {
        paymentDetailService.findAllPaymentDetails();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentDetailDTO> getPaymentDetailById(@PathVariable Long paymentId) {
        paymentDetailService.findPaymentDetailById(paymentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PaymentDetailDTO> createPaymentDetail(@RequestBody PaymentDetailDTO paymentDetailDTO) {
        paymentDetailService.savePaymentDetail(modelMapperUtil.map(paymentDetailDTO, PaymentDetailDTO.class));
        return new ResponseEntity<>(paymentDetailDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity<PaymentDetailDTO> updatePaymentDetail(@PathVariable Long paymentId,
            @RequestBody PaymentDetailDTO paymentDetailDTO) {
        paymentDetailService.updatePaymentDetail(paymentDetailDTO, paymentId);
        return new ResponseEntity<>(paymentDetailDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Void> deletePaymentDetail(@PathVariable Long paymentId) {
        paymentDetailService.deletePaymentDetail(paymentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}