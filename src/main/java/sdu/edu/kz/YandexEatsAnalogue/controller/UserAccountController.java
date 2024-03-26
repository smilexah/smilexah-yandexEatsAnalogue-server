package sdu.edu.kz.YandexEatsAnalogue.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdu.edu.kz.YandexEatsAnalogue.dto.UserAccountDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.UserAccount;
import sdu.edu.kz.YandexEatsAnalogue.service.UserAccountService;
import sdu.edu.kz.YandexEatsAnalogue.utils.ModelMapperUtil;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/userAccounts")
@RequiredArgsConstructor
public class UserAccountController {

    @Autowired
    private final UserAccountService userAccountService;

    @Autowired
    private final ModelMapperUtil modelMapperUtil;

    @GetMapping
    public ResponseEntity<List<UserAccountDTO>> getAllUserAccounts() {
        List<UserAccount> userAccounts = userAccountService.findAllUserAccounts();
        List<UserAccountDTO> userAccountDTOs = userAccounts.stream()
                .map(userAccount -> modelMapperUtil.map(userAccount, UserAccountDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(userAccountDTOs);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserAccountDTO> getUserAccountById(@PathVariable Long userId) {
        return userAccountService.findUserAccountById(userId)
                .map(userAccount -> ResponseEntity.ok(modelMapperUtil.map(userAccount, UserAccountDTO.class)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<UserAccountDTO> createUserAccount(@RequestBody UserAccountDTO userAccountDTO) {
        UserAccount userAccountRequest = modelMapperUtil.map(userAccountDTO, UserAccount.class);
        UserAccount newUserAccount = userAccountService.saveUserAccount(userAccountRequest);
        return new ResponseEntity<>(modelMapperUtil.map(newUserAccount, UserAccountDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserAccountDTO> updateUserAccount(@PathVariable Long userId, @RequestBody UserAccountDTO userAccountDTO) {
        return userAccountService.findUserAccountById(userId)
                .map(userAccount -> {
                    userAccount.setEmail(userAccountDTO.getEmail());
                    userAccount.setPassword(userAccountDTO.getPassword());
                    userAccount.setRole(userAccountDTO.getRole());
                    userAccount.setIsActive(userAccountDTO.getIsActive());

                    UserAccount updatedUserAccount = userAccountService.saveUserAccount(userAccount);
                    return new ResponseEntity<>(modelMapperUtil.map(updatedUserAccount, UserAccountDTO.class), HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserAccount(@PathVariable Long userId) {
        if (userAccountService.findUserAccountById(userId).isPresent()) {
            userAccountService.deleteUserAccount(userId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}