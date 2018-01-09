package com.testvagrant.services.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelpController {

    @GetMapping("/ping")
    public String checkServiceRunning() {
        return "PONG";
    }
}
