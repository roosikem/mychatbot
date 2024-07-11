package com.example.chatbot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RefreshController {

    private final Service service;

    public RefreshController(Service service) {
        this.service = service;
    }

    @RequestMapping("/refresh")
    public String refresh(@RequestParam String conversationId) {
        return service.getResponse(conversationId);
    }
}
