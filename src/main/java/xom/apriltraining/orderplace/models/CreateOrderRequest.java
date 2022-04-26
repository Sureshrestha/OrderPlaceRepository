package xom.apriltraining.orderplace.models;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOrderRequest {

	private List<String> productIds;
	private String customerId;
	private float paidAmount;

}
