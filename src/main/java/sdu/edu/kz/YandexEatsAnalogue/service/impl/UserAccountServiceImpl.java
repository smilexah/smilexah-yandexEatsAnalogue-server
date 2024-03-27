package sdu.edu.kz.YandexEatsAnalogue.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import sdu.edu.kz.YandexEatsAnalogue.dto.UserAccountDTO;
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
    public void saveUserAccount(UserAccountDTO userAccountDTO) {
        UserAccount userAccount = new UserAccount();

        userAccount.setUserId(userAccount.getUserId());

        userAccount.setEmail(userAccountDTO.getEmail());
        userAccount.setPassword(userAccountDTO.getPassword());
        userAccount.setRole(userAccountDTO.getRole());
        userAccount.setIsActive(true);

        userAccountRepository.save(userAccount);
    }

    @Override
    public void updateUserAccount(UserAccountDTO userAccountDTO, Long id) {
        Optional<UserAccount> userAccountOptional = userAccountRepository.findById(id);
        if (userAccountOptional.isEmpty()) {
            throw new EntityNotFoundException("User account not found");
        }
        UserAccount userAccount = userAccountOptional.get();

        userAccount.setEmail(userAccountDTO.getEmail());
        userAccount.setPassword(userAccountDTO.getPassword());
        userAccount.setRole(userAccountDTO.getRole());
        userAccount.setIsActive(userAccountDTO.getIsActive());

        userAccountRepository.save(userAccount);
    }

    @Override
    public void deleteUserAccount(Long id) {
        userAccountRepository.deleteById(id);
    }
}
