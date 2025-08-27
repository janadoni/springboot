package com.example.kafkaexample.controller;

import com.example.kafkaexample.bean.order;
import com.example.kafkaexample.service.Orderproducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafkaapi")
public class orderController {
    @Autowired
    private Orderproducer orderProducer;


    @PostMapping("/producer")
    public ResponseEntity<?> orderplace(@RequestBody order order){
        orderProducer.send(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
