package io.sannette.eis.mega.customer.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/worker/api")
public class OnboardWorkerController {

    @GetMapping("/e2ehealth")
    public String getHealth(){
        return "fine";
    }
}
