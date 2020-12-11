package com.order.OrderMS.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import com.order.OrderMS.entity.OrdersEntity;

import org.springframework.stereotype.Repository;
@Repository
public interface OrdersRepo extends JpaRepository<OrdersEntity,Integer>{
	

}
