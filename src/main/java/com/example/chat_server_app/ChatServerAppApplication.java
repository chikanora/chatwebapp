package com.example.chat_server_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Simple Chat Web App created using Springboot (Backend) and React (Frontend)
 * Made by Ronald Tran
 * Sole purpose for this app is to learn more about implementing chat communication between two different
 * clients and experimenting on additional features that I could use to implement for future projects.
 *
 * As of this version, only private messaging and storing chat history after ending the program is broken/half-completed
 *
 * HOW TO RUN:
 *  run the chatserverappapplication to run the backend
 * 'npm start' on /api/chat-server-app/chatclient/chat-app to start the application
 * ctrl-c to stop the frontend
 * press the stop button for the backend
 */

@SpringBootApplication
public class ChatServerAppApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(ChatServerAppApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer()
	{
		return new WebMvcConfigurer()
		{
			// Adding CORs mappings to allow cross-origins requests
			@Override
			public void addCorsMappings(CorsRegistry registry)
			{
				// Allow requests from the frontend (localhost:3000)
				registry.addMapping("/**") // Allow requests to all endpoints
						.allowedHeaders("http://localhost:3000") // Frontend host
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow HTTP methods
						.allowedHeaders("*") // Allow all headers
						.allowCredentials(true); // Allowsending cookies with the request
			}
		};
	}
}
