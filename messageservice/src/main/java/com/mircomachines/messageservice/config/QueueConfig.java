package com.mircomachines.messageservice.config;

import java.util.HashMap;
import java.util.Map;

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

import com.mircomachines.messageservice.service.queue.CommandReceiver;

@Configuration
public class QueueConfig {
	
	
	private static final String MAIL_MESSAGE_CMD_QUEUE = "mail-message-cmd-queue";
	private static final String DELAY_QUEUE = "mail-message-delay-queue";
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Bean
	Queue queue() {
		return new Queue(MAIL_MESSAGE_CMD_QUEUE, false);
	}
	
	@Bean
	public Queue delayQueue() {
	  Map<String, Object> args = new HashMap<String, Object>();
	  args.put("x-dead-letter-exchange", "");
	  args.put("x-dead-letter-routing-key", MAIL_MESSAGE_CMD_QUEUE);
	  args.put("x-message-ttl", 10000);

	  return new Queue(DELAY_QUEUE, false, false, false, args);
	}
	
	@Bean
	TopicExchange delayExchange() {
		return new TopicExchange("microservices-delay-exchange");
	}

	
	@Bean
	TopicExchange exchange() {
		return new TopicExchange("microservices-exchange");
	}
	
	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("cmd.message.#");
	}
	
	@Bean
	Binding delayBinding(Queue delayQueue, TopicExchange delayExchange) {
		return BindingBuilder.bind(delayQueue).to(delayExchange).with("#");
	}
	
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(MAIL_MESSAGE_CMD_QUEUE);
		container.setMessageListener(listenerAdapter);
			
		return container;
	}
	
	@Bean
	CommandReceiver receiver() {
        return new CommandReceiver();
    }

	@Bean
	MessageListenerAdapter listenerAdapter(CommandReceiver receiver) {
		return new MessageListenerAdapter(receiver, "execute");
	}
	
	@PostConstruct
	void setReveiveTimeout() {
		rabbitTemplate.setReplyTimeout(5000);
	}
	
	
}
