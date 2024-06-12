package sdu.edu.kz.YandexEatsAnalogue.service;

import org.springframework.stereotype.Service;
import sdu.edu.kz.YandexEatsAnalogue.dto.AccountDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.Account;

import java.util.List;
import java.util.Optional;

@Service
public interface AccountService {
    List<Account> findAllUserAccounts();
    Optional<Account> findUserAccountById(Long id);
    void saveUserAccount(AccountDTO accountDTO);
    void updateUserAccount(AccountDTO accountDTO, Long id);
    void deleteUserAccount(Long id);
    Optional<Account> login(String email, String password);
}
