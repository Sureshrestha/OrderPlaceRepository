package xom.apriltraining.orderplace.api;

import java.net.http.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import xom.apriltraining.orderplace.models.CreateOrderRequest;
import xom.apriltraining.orderplace.service.OrderService;


/**
 * Creating rest controllers to place order 
 * and get order status
 * 
 * */
@RestController
@RequestMapping(value = "/api/v1")
public class OrderPlaceController 
{
	private OrderService orderService;
	
	public OrderPlaceController(@Autowired OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping("/value")
	ResponseEntity<?> test()
	{
		return ResponseEntity.ok("Hey what the fuck");
	}

@PostMapping(value = "/order/action/place-order")
ResponseEntity<?> placeorder(@RequestHeader(required = false) HttpHeaders headers,
		@RequestBody  CreateOrderRequest createOrderRequest) throws JsonProcessingException
{
	return ResponseEntity.ok(orderService.placeOrder(createOrderRequest));
}

@GetMapping("/order/action/get-order-status")
ResponseEntity<?> getOrderStatus(@RequestHeader(required = false) HttpHeaders headers,
		@RequestParam (defaultValue = "", required = true)String orderId)
{
return ResponseEntity.ok(orderService.getOrder(orderId));	
}

}
