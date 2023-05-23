package io.sannette.eis.mega.customer.controllers;

import io.sannette.eis.mega.customer.models.Action;
import io.sannette.eis.mega.customer.models.Customer;
import io.sannette.eis.mega.customer.services.OnboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/api")
public class OnboardController {

    @Autowired
    private OnboardService onboardService;

    @PostMapping(path = "/customers")
    public ResponseEntity createCustomer(Customer customer) {
        String newCustomerId = onboardService.createCustomer(customer);
        return ResponseEntity.ok().body(newCustomerId);
    }

    @PostMapping(path = "/customers/{customerId}/actions")
    public ResponseEntity updateCustomer(@PathVariable String customerId, @RequestBody Action action) {
        System.out.println(action);
        System.out.println(action.getCommand());
        String status = switch (action.getCommand()) {
            case "APPROVE" -> onboardService.approveCustomer(customerId);
            case "REJECT" -> onboardService.rejectCustomer(customerId);
            default -> throw new IllegalStateException("Unexpected value");
        };
        System.out.println(status);
        return ResponseEntity.ok().body(status);
    }

    @GetMapping(path = "/customers/{customerId}/status")
    public ResponseEntity updateCustomer(@PathVariable String customerId) {
        String queryResponse = onboardService.getCustomerOnboardStatus(customerId);
        // Return the query status....
        return ResponseEntity.ok().body(queryResponse);
    }
}
