package sdu.edu.kz.YandexEatsAnalogue.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdu.edu.kz.YandexEatsAnalogue.dto.CustomerDTO;
import sdu.edu.kz.YandexEatsAnalogue.service.CustomerService;
import sdu.edu.kz.YandexEatsAnalogue.utils.ModelMapperUtil;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final ModelMapperUtil modelMapperUtil;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return new ResponseEntity<>(customerService.findAllCustomers().stream()
                .map(customer -> modelMapperUtil.map(customer, CustomerDTO.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        return customerService.findCustomerById(id)
                .map(customer -> new ResponseEntity<>(modelMapperUtil.map(customer, CustomerDTO.class), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO customer = modelMapperUtil.map(customerDTO, CustomerDTO.class);
        return new ResponseEntity<>(modelMapperUtil.map(customerService.saveCustomer(customer), CustomerDTO.class),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.findCustomerById(id)
                .map(customer -> {
                    customerService.updateCustomer(customerDTO, id);
                    return new ResponseEntity<>(HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        return customerService.findCustomerById(id)
                .map(customer -> {
                    customerService.deleteCustomer(id);
                    return new ResponseEntity<Void>(HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}