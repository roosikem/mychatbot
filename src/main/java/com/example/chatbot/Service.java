package com.example.chatbot;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Queue;

@org.springframework.stereotype.Service
public class Service {

    private final ConcurrentHashMap<String, Queue<ChatMessage>> messageMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Queue<String>> responseMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Boolean> processingMap = new ConcurrentHashMap<>();
    private final ChatBotManager chatBotManager;

    public Service(ChatBotManager chatBotManager) {
        this.chatBotManager = chatBotManager;
    }

    public void handleMessage(ChatMessage chatMessage) {
        String conversationId = chatMessage.getConversationId();
        messageMap.putIfAbsent(conversationId, new ConcurrentLinkedQueue<>());
        responseMap.putIfAbsent(conversationId, new ConcurrentLinkedQueue<>());
        processingMap.putIfAbsent(conversationId, false);

        Queue<ChatMessage> messageQueue = messageMap.get(conversationId);
        messageQueue.add(chatMessage);

        if (!processingMap.get(conversationId)) {
            processNextMessage(conversationId);
        }
    }

    private void processNextMessage(String conversationId) {
        Queue<ChatMessage> messageQueue = messageMap.get(conversationId);

        if (messageQueue.isEmpty()) {
            processingMap.put(conversationId, false);
            return;
        }

        processingMap.put(conversationId, true);
        ChatMessage chatMessage = messageQueue.poll();

        // Process message using ChatBot
        ChatBot chatBot = chatBotManager.getChatBot(conversationId);
        chatBot.onSessionActivity(chatMessage);

        // Continue processing the next message in the queue
        processNextMessage(conversationId);
    }

    public String getResponse(String conversationId) {
        Queue<String> responses = responseMap.get(conversationId);
        return (responses != null && !responses.isEmpty()) ? responses.poll() : "No new messages";
    }
}
