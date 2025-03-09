package com.example.chat_server_app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer
{
    // Method to register STOMP endpoints for WebSocket communication
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry)
    {
        // Register the '/ws' (WebSocket) endpoint, enabling SockJS as a fallback option for browsers that don't support WebSockets
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }

    // Method to configure the message broker settings
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry)
    {
        // Define the prefix for messages that are sent to application endpoints (used for sending messages from client to server)
        registry.setApplicationDestinationPrefixes("/app");

        // Enable a simple message broker that will route messages to the specified destinations
        registry.enableSimpleBroker("/chatroom", "/user");

        // Set the user-specific destination prefix for private message (e.g., '/user/{username}/private')
        registry.setUserDestinationPrefix("/user");
    }
}
