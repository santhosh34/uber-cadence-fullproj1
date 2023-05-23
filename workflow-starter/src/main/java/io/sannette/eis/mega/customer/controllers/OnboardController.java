package io.sannette.eis.mega.customer.controllers;

import io.sannette.eis.mega.customer.models.Customer;
import io.sannette.eis.mega.customer.services.OnboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController()
@RequestMapping("/api")
public class OnboardController {

    @Autowired
    private OnboardService onboardService;

    @PostMapping(path = "/customers")
    public ResponseEntity createCustomer(Customer customer){

        onboardService.createCustomer(customer);

        return ResponseEntity.ok().build();
    }


}
