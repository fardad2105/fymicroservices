package com.fy.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * generate main customer module
 * created by: fardad yadegar
 *
 */
@SpringBootApplication
@EnableFeignClients(
        basePackages = "com.fy.clients"
)
public class CustomerApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(CustomerApplication.class, args);
    }
}
