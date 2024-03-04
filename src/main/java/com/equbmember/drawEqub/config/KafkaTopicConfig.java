package com.equbmember.drawEqub.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic winnerTopic(){
        return TopicBuilder.name("WINNER_TOPIC")
                .build();
    }
    @Bean
    public NewTopic memberTopic(){
        return TopicBuilder.name("MEMBER_TOPIC").build();
    }
    @Bean
    public NewTopic employeeTopic(){
        return TopicBuilder.name("EMPLOYEE_TOPIC").build();
    }
}
