package com.rabbitmq.configuration;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RabbitMQ_Config {
	// Spring bean for RBTMQ Queue
	@Bean
	public Queue queue1() {
		return new Queue("simple_msg_queue");
	}
	
	@Bean
	public Queue jsonQueue2() {
		return new Queue("json_msg_queue");
	}

	// Spring bean for RBTMQ Exchange
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange("exchange_one");
	}

	// Binding between Queue and Exchange using Routing Key
	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue1()).to(exchange()).with("producer.one");
	}

	
	@Bean
	public Binding jsonBinding() {
		return BindingBuilder.bind(jsonQueue2()).to(exchange()).with("producer.two");
	}
	
	@Bean
	public Jackson2JsonMessageConverter msgConverter() {
		return new Jackson2JsonMessageConverter();
	}
			
	@Bean
	public AsyncRabbitTemplate asyncRabbitTemplate(RabbitTemplate rabbitTemplate){
		return new AsyncRabbitTemplate(rabbitTemplate);
	}
}
