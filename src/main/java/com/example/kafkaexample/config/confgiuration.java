package com.example.kafkaexample.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class confgiuration {
    @Bean
    public NewTopic kafkaTopic(){
        return TopicBuilder.name("pratice")
                .build();
    }
    @Bean
    public NewTopic kafkaTopic1(){
        return TopicBuilder.name("pratice1")
                .build();
    }

}
