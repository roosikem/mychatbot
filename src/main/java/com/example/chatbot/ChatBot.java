package com.example.chatbot;

public class ChatBot {

    private CbpInstance cbpInstance;

    public void setCbpInstance(CbpInstance cbpInstance) {
        this.cbpInstance = cbpInstance;
    }

    public void onSessionActivity(ChatMessage chatMessage) {
        // Simulate API call for chatbot response
        Response response = callChatBotApi(chatMessage.getMessageText());

        // Send the response message
        cbpInstance.sendMessage(response.getMessageText(), chatMessage.getConversationId());
    }

    private Response callChatBotApi(String messageText) {
        // Simulated API call to get response from chatbot
        Response response = new Response();
        response.setMessageText("Response to: " + messageText);
        return response;
    }
}
