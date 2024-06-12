package sdu.edu.kz.YandexEatsAnalogue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdu.edu.kz.YandexEatsAnalogue.dto.AccountDTO;
import sdu.edu.kz.YandexEatsAnalogue.service.AccountService;
import sdu.edu.kz.YandexEatsAnalogue.utils.mapper.ModelMapperUtil;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final ModelMapperUtil modelMapperUtil;

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllUserAccounts() {
        return new ResponseEntity<>(accountService.findAllUserAccounts().stream()
                .map(account -> modelMapperUtil.map(account, AccountDTO.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<AccountDTO> getUserAccountById(@PathVariable Long userId) {
        return accountService.findUserAccountById(userId)
                .map(userAccount -> new ResponseEntity<>(modelMapperUtil.map(userAccount, AccountDTO.class),
                        HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<AccountDTO> createUserAccount(@RequestBody AccountDTO accountDTO) {
        accountService.saveUserAccount(modelMapperUtil.map(accountDTO, AccountDTO.class));
        return new ResponseEntity<>(accountDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<AccountDTO> updateUserAccount(@PathVariable Long userId,
                                                        @RequestBody AccountDTO accountDTO) {
        return accountService.findUserAccountById(userId)
                .map(existingUserAccount -> {
                    accountService.updateUserAccount(accountDTO, userId);
                    return new ResponseEntity<>(modelMapperUtil.map(existingUserAccount, AccountDTO.class),
                            HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserAccount(@PathVariable Long userId) {
        return accountService.findUserAccountById(userId)
                .map(existingUserAccount -> {
                    accountService.deleteUserAccount(userId);
                    return new ResponseEntity<Void>(HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping("/login")
    public ResponseEntity<AccountDTO> login(@RequestBody AccountDTO accountDTO) {
        return accountService.login(accountDTO.getEmail(), accountDTO.getPassword())
                .map(userAccount -> new ResponseEntity<>(modelMapperUtil.map(userAccount, AccountDTO.class),
                        HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}