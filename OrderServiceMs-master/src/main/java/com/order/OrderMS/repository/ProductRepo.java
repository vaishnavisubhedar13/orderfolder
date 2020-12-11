package com.order.OrderMS.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.order.OrderMS.entity.ProductsEntity;

@Repository
public interface ProductRepo  extends JpaRepository<ProductsEntity, Integer> {
	
	

	

}
