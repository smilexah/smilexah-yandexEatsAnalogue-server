package sdu.edu.kz.YandexEatsAnalogue.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdu.edu.kz.YandexEatsAnalogue.dto.CustomerDTO;
import sdu.edu.kz.YandexEatsAnalogue.dto.UserAccountDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.Customer;
import sdu.edu.kz.YandexEatsAnalogue.entity.UserAccount;
import sdu.edu.kz.YandexEatsAnalogue.service.CustomerService;
import sdu.edu.kz.YandexEatsAnalogue.utils.ModelMapperUtil;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    @Autowired
    private final CustomerService customerService;
    @Autowired
    private final ModelMapperUtil modelMapperUtil;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<Customer> customers = customerService.findAllCustomers();
        List<CustomerDTO> customerDTOs = customers.stream()
                .map(customer -> modelMapperUtil.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(customerDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        return customerService.findCustomerById(id)
                .map(customer -> ResponseEntity.ok(modelMapperUtil.map(customer, CustomerDTO.class)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

//    @PostMapping
//    public ResponseEntity<Void> createCustomer(@RequestBody CustomerDTO customerDTO) {
//        Customer customer = modelMapperUtil.map(customerDTO, Customer.class);
//        UserAccount userAccount = modelMapperUtil.map(customerDTO.getUserId(), UserAccount.class);
//
//        customer.setUserAccount(userAccount);
//        userAccount.setCustomer(customer);
//
//        customerService.saveCustomer(customer);
//
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        // Convert DTO to entity (pseudo-code, implement this according to your setup)
        Customer customer = convertToEntity(customerDTO);
        customer = customerService.saveCustomer(customer);

        // Convert entity back to DTO (pseudo-code)
        CustomerDTO savedCustomerDTO = convertToDto(customer);
        return new ResponseEntity<>(savedCustomerDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.findCustomerById(id)
                .map(customer -> {
                    // Map updated fields from CustomerDTO to existing customer entity
                    customer.setName(customerDTO.getName());
                    customer.setPhone(customerDTO.getPhone());
                    customer.setAddress(customerDTO.getAddress());

                    // Save the updated customer entity
                    Customer updatedCustomer = customerService.saveCustomer(customer);

                    // Return the updated customer data
                    return new ResponseEntity<>(modelMapperUtil.map(updatedCustomer, CustomerDTO.class), HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    private Customer convertToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setCustomerId(customerDTO.getCustomerId());
        customer.setName(customerDTO.getName());

        // Assuming UserAccountDTO is part of CustomerDTO and also needs to be converted
        UserAccount userAccount = new UserAccount();
        if (customerDTO.getUserAccount() != null) {
            userAccount.setUserId(customerDTO.getUserAccount().getUserId());
            userAccount.setEmail(customerDTO.getUserAccount().getEmail());
            // Map other fields as necessary
        }

        customer.setUserAccount(userAccount);
        userAccount.setCustomer(customer); // Setting the back reference

        return customer;
    }

    private CustomerDTO convertToDto(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setName(customer.getName());

        // Assuming Customer has a UserAccount and it also needs to be converted
        UserAccountDTO userAccountDTO = new UserAccountDTO();
        if (customer.getUserAccount() != null) {
            userAccountDTO.setUserId(customer.getUserAccount().getUserId());
            userAccountDTO.setEmail(customer.getUserAccount().getEmail());
            // Map other fields as necessary
        }

        customerDTO.setUserAccount(userAccountDTO);

        return customerDTO;
    }
}