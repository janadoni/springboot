package com.example.kafkaexample.config;

import com.example.kafkaexample.bean.order;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class consumerconfig {
//    @Value("${spring.kafka.bootstrap-servers}")
//    private String bootStrapServer;
//
//    public Map<String,Object> consumerConfig(){
//
//        Map<String,Object> props= new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrapServer);
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, com.fasterxml.jackson.databind.JsonDeserializer.class);
//        return props;
//    }
//    @Bean
//    public ConsumerFactory<String,order> consumerFactory(){
//        JsonDeserializer<order> deserializer = new JsonDeserializer<>(order.class);
//        deserializer.addTrustedPackages("*");
//        return new DefaultKafkaConsumerFactory<>(consumerConfig(),new StringDeserializer(),deserializer);
//    }
//    @Bean
//    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String,order>> factory(
//            ConsumerFactory<String,order> consumerFactory
//    ){
//        ConcurrentKafkaListenerContainerFactory<String,order> factory=new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory);
//        return factory;
//    }
@Bean
public ConsumerFactory<String, order> orderConsumerFactory() {
    JsonDeserializer<order> deserializer = new JsonDeserializer<>(order.class);
    deserializer.setRemoveTypeHeaders(false);
    deserializer.addTrustedPackages("*");
    deserializer.setUseTypeMapperForKey(true);

    Map<String, Object> config = new HashMap<>();
    config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    config.put(ConsumerConfig.GROUP_ID_CONFIG, "groupid");
    config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//    config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);

    return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
}

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, order> kafkaListenerContainerFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, order>();
        factory.setConsumerFactory(orderConsumerFactory());
        return factory;
    }
}
