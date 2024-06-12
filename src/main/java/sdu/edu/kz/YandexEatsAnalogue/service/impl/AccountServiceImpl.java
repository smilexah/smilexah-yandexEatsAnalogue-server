package sdu.edu.kz.YandexEatsAnalogue.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sdu.edu.kz.YandexEatsAnalogue.dto.AccountDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.Account;
import sdu.edu.kz.YandexEatsAnalogue.repository.AccountRepository;
import sdu.edu.kz.YandexEatsAnalogue.service.AccountService;
import sdu.edu.kz.YandexEatsAnalogue.utils.constants.Roles;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder; // Inject PasswordEncoder

    @Override
    public List<Account> findAllUserAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> findUserAccountById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public void saveUserAccount(AccountDTO accountDTO) {
        Account userAccount = new Account();

        userAccount.setUserId(userAccount.getUserId());

        userAccount.setEmail(accountDTO.getEmail());
        userAccount.setRole(Roles.valueOf(accountDTO.getRole()));
        userAccount.setPassword(passwordEncoder.encode(accountDTO.getPassword())); // Encode the password
        userAccount.setIsActive(accountDTO.getIsActive());

        accountRepository.save(userAccount);
    }

    @Override
    public void updateUserAccount(AccountDTO accountDTO, Long id) {
        Optional<Account> userAccountOptional = accountRepository.findById(id);
        if (userAccountOptional.isEmpty()) {
            throw new EntityNotFoundException("User account not found");
        }
        Account userAccount = userAccountOptional.get();

        userAccount.setEmail(accountDTO.getEmail());

        userAccount.setPassword(accountDTO.getPassword());
        userAccount.setRole(Roles.valueOf(accountDTO.getRole()));
        userAccount.setIsActive(accountDTO.getIsActive());

        accountRepository.save(userAccount);
    }

    @Override
    @Transactional
    public void deleteUserAccount(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Optional<Account> login(String email, String password) {
        return accountRepository.findByEmailAndPassword(email, password);
    }
}
