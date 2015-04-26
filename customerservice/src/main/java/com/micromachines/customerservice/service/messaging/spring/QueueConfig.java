package com.micromachines.customerservice.service.messaging.spring;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.micromachines.customerservice.service.messaging.receivers.CustomerCommandReceiver;

@Configuration
public class QueueConfig {
	
	
	private static final String CUSTOMER_CMD_QUEUE = "customer-cmd-queue";
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Bean
	Queue queue() {
		return new Queue(CUSTOMER_CMD_QUEUE, false);
	}
	
	@Bean
	TopicExchange exchange() {
		return new TopicExchange("microservices-exchange");
	}
	
	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("cmd.customer.*");
	}
	
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(CUSTOMER_CMD_QUEUE);
		container.setMessageListener(listenerAdapter);
		return container;
	}
	
	@Bean
    CustomerCommandReceiver receiver() {
        return new CustomerCommandReceiver();
    }

	@Bean
	MessageListenerAdapter listenerAdapter(CustomerCommandReceiver receiver) {
		return new MessageListenerAdapter(receiver, "execute");
	}
	
	@PostConstruct
	void setReveiveTimeout() {
		rabbitTemplate.setReplyTimeout(5000);
	}
	
	
}
