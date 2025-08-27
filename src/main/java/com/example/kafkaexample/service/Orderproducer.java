package com.example.kafkaexample.service;

import com.example.kafkaexample.bean.order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Orderproducer {
    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;
    public  void send(order order){
        kafkaTemplate.send("pratice",order);
        System.out.println("Order sent : "+order.getOrderId());
    }

}
