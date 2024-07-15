package sdu.edu.kz.YandexEatsAnalogue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sdu.edu.kz.YandexEatsAnalogue.entity.Account;

public interface CustomerRepository extends JpaRepository<Account, Long> {
}
