package sdu.edu.kz.YandexEatsAnalogue.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sdu.edu.kz.YandexEatsAnalogue.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);
    Optional<Account> findByEmailAndPassword(String email, String password);
}
