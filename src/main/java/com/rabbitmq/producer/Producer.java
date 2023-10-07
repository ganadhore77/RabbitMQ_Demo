package com.rabbitmq.producer;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.RabbitConverterFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Producer {
	
	@Autowired
	private TopicExchange exchange_one; 
	
	@Autowired
	public AsyncRabbitTemplate rabbitTemplate;
	
	public void sendMessage(String msg) {
		
		RabbitConverterFuture<String> receive  = rabbitTemplate.convertSendAndReceive(exchange_one.getName(), "producer.one", msg);
		
		System.out.println(receive);
		
		receive.whenComplete((receivedMsg, exception)  ->{
		    if(exception!=null) {		    	
		    	System.out.println(exception);
		    } else {
		    	System.out.println(receivedMsg);
		    }
			
		    
		} );

	}
	
}
