package com.example.zadanie221;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    private final MailService mailService;

    public PageController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/")
    String home() {
        return "home";
    }

    @GetMapping("/contact")
    String contact() {
        return "send-email";
    }

    @PostMapping("/send")
    String sendEmail(@RequestParam String name,
                     @RequestParam String email,
                     @RequestParam String description,
                     @RequestParam(required = false) boolean confirmation,
                     @Value("${support.mail}") String supportEmail) {
        mailService.send(name, email, description, confirmation, supportEmail);
        return "confirm-form";
    }

}
