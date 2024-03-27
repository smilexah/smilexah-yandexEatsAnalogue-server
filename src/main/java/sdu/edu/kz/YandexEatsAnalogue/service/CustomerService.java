package sdu.edu.kz.YandexEatsAnalogue.service;

import org.springframework.stereotype.Service;
import sdu.edu.kz.YandexEatsAnalogue.dto.CustomerDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.Customer;

import java.util.List;
import java.util.Optional;

@Service
public interface CustomerService {
    List<Customer> findAllCustomers();
    Optional<Customer> findCustomerById(Long id);
    Customer saveCustomer(CustomerDTO customerDTO);
    void deleteCustomer(Long id);
    void updateCustomer(CustomerDTO customerDTO, Long userId);
}
