package com.example.chatbot;

import java.util.concurrent.ConcurrentHashMap;

public class ChatBotManager {
    private ConcurrentHashMap<String, ChatBot> chatBots = new ConcurrentHashMap<>();

    public ChatBot getChatBot(String conversationId) {
        return chatBots.computeIfAbsent(conversationId, id -> {
            ChatBot chatBot = new ChatBot();
            chatBot.setCbpInstance(new CbpInstance());
            return chatBot;
        });
    }
}
