package sdu.edu.kz.YandexEatsAnalogue.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sdu.edu.kz.YandexEatsAnalogue.service.CustomerService;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/account")
    public String getAccountById(Long id) {
        return customerService.getAccountById(id).toString();
    }
}
