package com.example.chatbot;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Queue;

public class CbpInstance {

    private final ConcurrentHashMap<String, Queue<String>> responseMap = new ConcurrentHashMap<>();

    public Integer sendMessage(String message, String conversationId) {
        // Logic to send message
        System.out.println("Sending message to conversation " + conversationId + ": " + message);

        // Store the response in the map
        responseMap.putIfAbsent(conversationId, new ConcurrentLinkedQueue<>());
        responseMap.get(conversationId).add(message);

        return 1; // Return status or any relevant information
    }

    public String getResponse(String conversationId) {
        Queue<String> responses = responseMap.get(conversationId);
        return (responses != null && !responses.isEmpty()) ? responses.poll() : "No new messages";
    }
}
