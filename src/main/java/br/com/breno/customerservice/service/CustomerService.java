package br.com.breno.customerservice.service;

import br.com.breno.customerservice.dto.CustomerDto;
import br.com.breno.customerservice.dto.CustomerMapper;
import br.com.breno.customerservice.model.Customer;
import br.com.breno.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public Optional<CustomerDto> getCustomerById(String customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        return customer.map(customerMapper::customerToCustomerDto);
    }

    public Iterable<CustomerDto> getAllCustomers() {
        return StreamSupport.stream(customerRepository.findAll().spliterator(), false)
                .map(customerMapper::customerToCustomerDto)
                .collect(Collectors.toList());
    }

    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.customerDtoToCustomer(customerDto);
        customer.setCustomerId(UUID.randomUUID().toString());
        customerRepository.save(customer);
        return customerMapper.customerToCustomerDto(customer);
    }

    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
