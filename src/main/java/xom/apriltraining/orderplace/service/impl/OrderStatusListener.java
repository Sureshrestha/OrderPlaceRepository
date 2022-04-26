package xom.apriltraining.orderplace.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import xom.apriltraining.orderplace.Entity.OrderEntity;
import xom.apriltraining.orderplace.dao.OrderPlacerepository;
import xom.apriltraining.orderplace.models.Order;

@Service
public class OrderStatusListener {

	private OrderPlacerepository orderPlacerepository;
	
	private ObjectMapper objectMapper;
	
	private KafkaTemplate<String, String>kafkaTemplate;
	
	
	public OrderStatusListener(@Autowired OrderPlacerepository orderPlacerepository,
			@Autowired KafkaTemplate<String, String>kafkaTemplate
			, @Autowired ObjectMapper objectMapper) {
		this.orderPlacerepository = orderPlacerepository;
		this.kafkaTemplate = kafkaTemplate;
		this.objectMapper = objectMapper;
	}
	
	
	@KafkaListener(topics = "topic-2", groupId = "custom_group")
	public void listenGroup(String message) throws JsonMappingException, JsonProcessingException {
	    System.out.println("Received Message in order place service: " + message);
	    
	    Order order = objectMapper.readValue(message,Order.class);
	    OrderEntity entity = orderPlacerepository.findByOrderId(order.getOrderId());
	    
	    orderPlacerepository.save(entity);
	    
	}
	
}
