package xom.apriltraining.orderplace.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import xom.apriltraining.orderplace.Entity.OrderEntity;
import xom.apriltraining.orderplace.dao.OrderPlacerepository;
import xom.apriltraining.orderplace.models.CreateOrderRequest;
import xom.apriltraining.orderplace.models.Order;
import xom.apriltraining.orderplace.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	
	private OrderPlacerepository orderPlacerepository;
	
	private KafkaTemplate<String, String>kafkaTemplate;
	
	private ObjectMapper objectMapper;
	
	
	public OrderServiceImpl(@Autowired OrderPlacerepository orderPlacerepository,
			@Autowired KafkaTemplate<String, String>kafkaTemplate
			, @Autowired ObjectMapper objectMapper) {
		this.orderPlacerepository = orderPlacerepository;
		this.kafkaTemplate = kafkaTemplate;
		this.objectMapper = objectMapper;
	}
	
	@Override
	public Order placeOrder(CreateOrderRequest createOrderRequest) throws JsonProcessingException {
		OrderEntity entity = OrderEntity.builder().
				customerId(createOrderRequest.getCustomerId())
				.orderId(UUID.randomUUID().toString())
				.productIds(createOrderRequest.getProductIds())
				.paidAmount(createOrderRequest.getPaidAmount())
				.status("PENDING")
				.timecreated(new Date())
				.build();
		
		orderPlacerepository.save(entity);
		
		Order order = 
				 Order.builder()
						.orderId(entity.getOrderId())
						.customerId(entity.getCustomerId())
						.productIds(entity.getProductIds())
						.paidAmount(entity.getPaidAmount())
						.status(entity.getStatus())
						.timecreated(entity.getTimecreated())
						.build();
		kafkaTemplate.send("topic-temp",objectMapper.writeValueAsString(order));
		return order;		
	}

	@Override
	public Order getOrder(String orderId) {
	
		OrderEntity entity =  orderPlacerepository.findByOrderId(orderId);
		
		return Order.builder()
				.orderId(entity.getOrderId())
				.customerId(entity.getCustomerId())
				.productIds(entity.getProductIds())
				.paidAmount(entity.getPaidAmount())
				.status(entity.getStatus())
				.timecreated(entity.getTimecreated())
				.build();		
	}

}
