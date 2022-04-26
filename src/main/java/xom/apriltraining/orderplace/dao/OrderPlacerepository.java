package xom.apriltraining.orderplace.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xom.apriltraining.orderplace.Entity.OrderEntity;

@Repository
public interface OrderPlacerepository extends JpaRepository<OrderEntity, Long>{

	OrderEntity findByOrderId(String orderId);
	
	
	
}
