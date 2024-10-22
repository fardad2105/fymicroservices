package com.fy.customer.services;

import com.fy.customer.model.Customer;
import com.fy.customer.model.dto.CustomerRequest;
import com.fy.customer.model.dto.FraudCheckResponse;
import com.fy.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository customerRepository,
                              RestTemplate restTemplate
) {

    public void registerCustomer(CustomerRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        customerRepository.saveAndFlush(customer);

        // check if fraudster
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );

        assert fraudCheckResponse != null;
        if (Boolean.TRUE.equals(fraudCheckResponse.isFraudster())) {
            throw new IllegalStateException("fraudster");
        }
    }
}
