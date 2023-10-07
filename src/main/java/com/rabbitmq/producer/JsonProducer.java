package com.rabbitmq.producer;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.RabbitConverterFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabbitmq.entity.UserEntity;

@Service
public class JsonProducer {

	@Autowired
	public AsyncRabbitTemplate rabbitTemplate;
	
	@Autowired
	private TopicExchange exchange_one; 

	public void sendJsonMsg(UserEntity user) {

		RabbitConverterFuture<String> receive = rabbitTemplate.convertSendAndReceive(exchange_one.getName(), "producer.two", user);
		
		System.out.println(receive);
		
		receive.whenComplete((receivedMsg, exception) -> {
			if (exception != null) {
				System.out.println(exception);
			} else {
				System.out.println(receivedMsg);
			}

		});
	}

}
