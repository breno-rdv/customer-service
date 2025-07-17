package br.com.breno.customerservice.service;

import br.com.breno.customerservice.dto.CustomerDto;
import br.com.breno.customerservice.dto.CustomerMapper;
import br.com.breno.customerservice.model.Customer;
import br.com.breno.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

}
