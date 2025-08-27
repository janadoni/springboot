package com.example.kafkaexample.service;

import com.example.kafkaexample.bean.order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class orderconsumer {
    @KafkaListener(topics = "pratice",groupId = "groupid")
    public void receiver(order order){
        System.out.println(order.getOrderId());
        System.out.println(order.getItem());
        System.out.println(order.getName());
        System.out.println(order.getQuantity());
    }

}
