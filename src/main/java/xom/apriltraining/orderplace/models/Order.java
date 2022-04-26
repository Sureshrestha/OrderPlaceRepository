package xom.apriltraining.orderplace.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Order {
	private String orderId;
	private List<String> productIds;
	private String customerId;
	private float paidAmount;
	private String status;
	private Date timecreated;
}
