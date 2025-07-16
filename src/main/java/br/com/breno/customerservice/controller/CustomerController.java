package br.com.breno.customerservice.controller;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @GetMapping("/customers/{id}")
    public ResponseEntity<String> getCustomerById(@PathVariable String id) {
        return ResponseEntity.status(201).body(id);
    }
}
