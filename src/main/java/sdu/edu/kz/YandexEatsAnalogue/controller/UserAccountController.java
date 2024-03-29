package sdu.edu.kz.YandexEatsAnalogue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdu.edu.kz.YandexEatsAnalogue.dto.UserAccountDTO;
import sdu.edu.kz.YandexEatsAnalogue.service.UserAccountService;
import sdu.edu.kz.YandexEatsAnalogue.utils.ModelMapperUtil;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/userAccounts")
@RequiredArgsConstructor
public class UserAccountController {
    private final UserAccountService userAccountService;
    private final ModelMapperUtil modelMapperUtil;

    @GetMapping
    public ResponseEntity<List<UserAccountDTO>> getAllUserAccounts() {
        return new ResponseEntity<>(userAccountService.findAllUserAccounts().stream()
                .map(userAccount -> modelMapperUtil.map(userAccount, UserAccountDTO.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserAccountDTO> getUserAccountById(@PathVariable Long userId) {
        return userAccountService.findUserAccountById(userId)
                .map(userAccount -> new ResponseEntity<>(modelMapperUtil.map(userAccount, UserAccountDTO.class),
                        HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<UserAccountDTO> createUserAccount(@RequestBody UserAccountDTO userAccountDTO) {
        userAccountService.saveUserAccount(modelMapperUtil.map(userAccountDTO, UserAccountDTO.class));
        return new ResponseEntity<>(userAccountDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserAccountDTO> updateUserAccount(@PathVariable Long userId,
            @RequestBody UserAccountDTO userAccountDTO) {
        return userAccountService.findUserAccountById(userId)
                .map(existingUserAccount -> {
                    userAccountService.updateUserAccount(userAccountDTO, userId);
                    return new ResponseEntity<>(modelMapperUtil.map(existingUserAccount, UserAccountDTO.class),
                            HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserAccount(@PathVariable Long userId) {
        return userAccountService.findUserAccountById(userId)
                .map(existingUserAccount -> {
                    userAccountService.deleteUserAccount(userId);
                    return new ResponseEntity<Void>(HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}