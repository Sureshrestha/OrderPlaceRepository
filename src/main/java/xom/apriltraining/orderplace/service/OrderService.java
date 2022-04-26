package xom.apriltraining.orderplace.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import xom.apriltraining.orderplace.models.*;

public interface OrderService {
	
	Order placeOrder(CreateOrderRequest createOrderRequest) throws JsonProcessingException;
	
	Order getOrder(String orderId);

}
