package sdu.edu.kz.YandexEatsAnalogue.service;

import org.springframework.stereotype.Service;
import sdu.edu.kz.YandexEatsAnalogue.entity.Customer;

import java.util.List;
import java.util.Optional;

@Service
public interface CustomerService {
    //    void createCustomer(CustomerDTO customerDTO);
//    void updateCustomer(CustomerDTO customerDTO);
//    void deleteCustomer(String id);
//    CustomerDTO getCustomer(String id);
    List<Customer> findAllCustomers();

    Optional<Customer> findCustomerById(Long id);

    Customer saveCustomer(Customer customer);

    void deleteCustomer(Long id);

    void createOrUpdateCustomer(Customer customer, Long userId);
}
