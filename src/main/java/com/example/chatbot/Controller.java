package com.example.chatbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private Service service;

    @RequestMapping("/sendMessage")
    public void sendMessage(@RequestParam String conversationId, @RequestParam String message) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMessageText(message);
        chatMessage.setConversationId(conversationId);

        service.handleMessage(chatMessage);
    }
}
