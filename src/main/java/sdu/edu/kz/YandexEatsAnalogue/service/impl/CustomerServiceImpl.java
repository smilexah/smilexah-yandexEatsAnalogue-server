package sdu.edu.kz.YandexEatsAnalogue.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sdu.edu.kz.YandexEatsAnalogue.entity.Account;
import sdu.edu.kz.YandexEatsAnalogue.repository.CustomerRepository;
import sdu.edu.kz.YandexEatsAnalogue.service.CustomerService;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public Optional<Account> getAccountById(Long id) {
        return customerRepository.findById(id);
    }
}
