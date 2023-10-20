package ru.relex.solution.chatroom.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync(proxyTargetClass = true)
@Configuration
public class AsyncConfig  {
}
