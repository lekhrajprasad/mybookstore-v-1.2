package com.lpras.placeorder;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import brave.sampler.Sampler;



@SpringBootApplication
public class PlaceOrderConfig {
	

	/*
	 * @Bean(name = "myOrderQueue") Queue createOrderQueue() { return
	 * QueueBuilder.durable(ORDER_QUEUE).build(); }
	 * 
	 * @Bean(name = "myOrderExchange") Exchange createOrderExchange() { return
	 * ExchangeBuilder.topicExchange(ORDER_EXCHANGE).build(); }
	 * 
	 * @Bean Binding orderBinding(Queue myOrderQueue, TopicExchange myOrderExchange)
	 * { return
	 * BindingBuilder.bind(myOrderQueue).to(myOrderExchange).with(ORDER_QUEUE); }
	 * 
	 * @Bean(name = "myInventoryQueue") Queue createInventoryQueue() { return
	 * QueueBuilder.durable(INVENTORY_QUEUE).build(); }
	 * 
	 * @Bean(name = "myInventoryExchange") Exchange createInventoryExchange() {
	 * return ExchangeBuilder.topicExchange(INVENTORY_EXCHANGE).build(); }
	 * 
	 * @Bean Binding inventoryBinding(Queue myInventoryQueue, TopicExchange
	 * myInventoryExchange) { return
	 * BindingBuilder.bind(myInventoryQueue).to(myInventoryExchange).with(
	 * INVENTORY_QUEUE); }
	 */
    @Bean
    public Sampler defaultSampler() {
	return Sampler.ALWAYS_SAMPLE;
    }

}
