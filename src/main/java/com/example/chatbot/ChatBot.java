package com.example.chatbot;

public class ChatBot {

    public Response processMessage(String messageText) {
        // Simulate API call to get response from chatbot
        Response response = new Response();
        response.setMessageText("Response to: " + messageText);
        return response;
    }
}
