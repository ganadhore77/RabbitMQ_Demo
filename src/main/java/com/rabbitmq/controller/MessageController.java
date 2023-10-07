package com.rabbitmq.controller;

import java.time.Instant;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.entity.UserEntity;
import com.rabbitmq.producer.JsonProducer;
import com.rabbitmq.producer.Producer;

@RestController
public class MessageController {

	@Autowired
	private Producer producer; 
	
	@Autowired
	private JsonProducer jsonProducer;
	
	@GetMapping("/publish")
	public ResponseEntity<String> getMsg(@RequestParam("msg") String msg){
		 producer.sendMessage(msg);
		 LocalTime time = LocalTime.now();
		return ResponseEntity.ok("Message sent to RabbitMQ ---- Time : " + time);
	}
	
	@GetMapping("/publishJsonMsg")
	public ResponseEntity<String> getJsonMsg(@RequestBody UserEntity user){
		 jsonProducer.sendJsonMsg(user);
		 LocalTime time = LocalTime.now();
		return ResponseEntity.ok("JSON Message sent to RabbitMQ ---- Time : " + time);
	}
	
}
