package sdu.edu.kz.YandexEatsAnalogue.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdu.edu.kz.YandexEatsAnalogue.entity.UserAccount;
import sdu.edu.kz.YandexEatsAnalogue.repository.UserAccountRepository;
import sdu.edu.kz.YandexEatsAnalogue.service.UserAccountService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {
    @Autowired
    private final UserAccountRepository userAccountRepository;
    @Override
    public List<UserAccount> findAllUserAccounts() {
        return userAccountRepository.findAll();
    }

    @Override
    public Optional<UserAccount> findUserAccountById(Long id) {
        return userAccountRepository.findById(id);
    }

    @Override
    public UserAccount saveUserAccount(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }

    @Override
    public void deleteUserAccount(Long id) {
        userAccountRepository.deleteById(id);
    }
}
