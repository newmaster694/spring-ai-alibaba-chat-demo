package com.newmaster.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hello-world")
public class ChatController {
	
	private final ChatClient chatClient;
	
	@GetMapping("/simple/chat")
	public String chat(@RequestParam(value = "query", defaultValue = "你好，很高兴认识你，能简单介绍一下自己吗？") String query) {
		return chatClient.prompt(query).call().content();
	}
	
	@GetMapping("/stream/chat")
	public Flux<String> streamChat(
			@RequestParam(value = "query", defaultValue = "你好，很高兴认识你，能简单介绍一下自己吗？") String query,
			HttpServletResponse response) {
		
		response.setCharacterEncoding("UTF-8");
		
		return chatClient.prompt(query).stream().content();
	}
}

