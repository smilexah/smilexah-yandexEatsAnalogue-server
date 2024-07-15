package sdu.edu.kz.YandexEatsAnalogue.service;

import java.util.Optional;
import sdu.edu.kz.YandexEatsAnalogue.entity.Account;

public interface CustomerService {
    Optional<Account> getAccountById(Long id);
}
