package com.example.chatbot;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChatBotManager {
    private final ConcurrentHashMap<String, ChatBot> chatBots = new ConcurrentHashMap<>();

    public ChatBot getChatBot(String conversationId) {
        return chatBots.computeIfAbsent(conversationId, id -> new ChatBot());
    }
}
