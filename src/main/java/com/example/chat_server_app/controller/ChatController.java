package com.example.chat_server_app.controller;

import com.example.chat_server_app.controller.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000") // Allow frontend to access API
@RestController
@RequestMapping("/chatroom")
public class ChatController
{
    // Used to send messages to WebSocket clients
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    // Viewing previous messages
    private List<Message> chatHistory = new ArrayList<>();

    @MessageMapping("/receive-message") // /app/message
    @SendTo("/chatroom/public")
    public Message receivePublicMessage(@Payload Message message)
    {
        return message; // Return the received message to be sent to the registered users
    }

    @MessageMapping("/send-message")
    @SendTo("/chatroom")
    public Message sendMessage(@Payload Message message)
    {
        System.out.println("Received message: " + message); // Debugging log
        return message; // Return the received message to be broadcasted to all users
    }

    @MessageMapping("/receive-private-message")
    public Message receivePrivateMessage(@Payload Message message)
    {
        // Send the private message to the user using SimpMessagingTemplate
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message); // /user/(USER)/private
        System.out.println(message.toString()); // Debugging log
        return message; // Returns the message to the other recipient
    }

    @MessageMapping("/send-private-message")
    @SendToUser("/user/private")
    public Message sendPrivateMessage(@Payload Message message)
    {
        System.out.println("Received private message: " + message); // Debugging log
        return message; // Return the previous message to be sent to the selected user chatting
    }

    // Endpoint to get chat history
    @GetMapping("/history")
    public List<Message> getChatHistory()
    {
        return chatHistory; // Return the stored chat history
    }
}