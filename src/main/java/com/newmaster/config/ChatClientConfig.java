package com.newmaster.config;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import com.alibaba.cloud.ai.memory.jdbc.MysqlChatMemoryRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class ChatClientConfig {
	private static final String DEFAULT_PROMPT = "你是一个博学的智能聊天助手，请根据用户的提问进行回答";
	
	@Bean
	public ChatClient chatClient(JdbcTemplate jdbcTemplate, ChatClient.Builder builder) {
		ChatMemoryRepository chatMemoryRepository = MysqlChatMemoryRepository.mysqlBuilder()
				.jdbcTemplate(jdbcTemplate).build();
		
		ChatMemory chatMemory = MessageWindowChatMemory.builder()
				.chatMemoryRepository(chatMemoryRepository).build();
		
		return builder.defaultSystem(DEFAULT_PROMPT)
				.defaultAdvisors(new SimpleLoggerAdvisor())//实现Logger的advisor
				.defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())//注册带记忆的advisor
				.defaultOptions(DashScopeChatOptions.builder().withTopP(0.7).build())//设置ChatModel中的option参数
				.build();
	}
}
