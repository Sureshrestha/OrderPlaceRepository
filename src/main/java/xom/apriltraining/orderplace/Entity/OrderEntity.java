package xom.apriltraining.orderplace.Entity;

import java.util.List;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orders.order")
public class OrderEntity {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	
	@Column(name = "orderID", unique = true)
	private String orderId;

	
	@Column(name = "customerID")
	private String customerId;
	
	@Column(name = "productIDs")
	@ElementCollection(targetClass = String.class)
	private List<String> productIds;
	
	@Column(name = "Amount")
	private float paidAmount;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "TimeCreated")
	private Date timecreated;
}
