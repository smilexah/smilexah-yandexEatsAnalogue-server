package sdu.edu.kz.YandexEatsAnalogue.service;

import org.springframework.stereotype.Service;
import sdu.edu.kz.YandexEatsAnalogue.dto.UserAccountDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.UserAccount;

import java.util.List;
import java.util.Optional;

@Service
public interface UserAccountService {
    List<UserAccount> findAllUserAccounts();
    Optional<UserAccount> findUserAccountById(Long id);
    UserAccount saveUserAccount(UserAccount userAccount);
    void deleteUserAccount(Long id);
}
