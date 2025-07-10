package com.newmaster.config;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {
	private static final String DEFAULT_PROMPT = "你是一个博学的智能聊天助手，请根据用户的提问进行回答";
	
	@Bean
	public ChatClient chatClient(ChatClient.Builder builder) {
		return builder.defaultSystem(DEFAULT_PROMPT)
				.defaultAdvisors(new SimpleLoggerAdvisor())//实现Logger的advisor
				.defaultOptions(DashScopeChatOptions.builder().withTopP(0.7).build())//设置ChatModel中的option参数
				.build();
	}
}
