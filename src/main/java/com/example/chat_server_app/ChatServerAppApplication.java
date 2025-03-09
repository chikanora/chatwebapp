package com.example.chat_server_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  run the chatserverappapplication to run the backend
 * 'npm start' on /api/chat-server-app/chatclient/chat-app to start the application
 * ctrl-c to stop the application
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
			@Override
			public void addCorsMappings(CorsRegistry registry)
			{
				registry.addMapping("/**")
						.allowedHeaders("http://localhost:3000")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
						.allowedHeaders("*")
						.allowCredentials(true);
			}
		};
	}
}
