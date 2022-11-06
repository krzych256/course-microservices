package com.in28minutes.microservices.currencyexchangeservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CircutBreakerController {

    @GetMapping("/sample-api")
    public String sampleApi() {
        return "Sample API";
    }
}
