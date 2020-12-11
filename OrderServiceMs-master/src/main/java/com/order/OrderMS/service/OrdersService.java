package com.order.OrderMS.service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import com.order.OrderMS.dto.OrderDetailsDTO;


import com.order.OrderMS.dto.ProductsOrderedDTO;
import com.order.OrderMS.entity.OrdersEntity;
import com.order.OrderMS.entity.ProductsEntity;
import com.order.OrderMS.repository.OrdersRepo;
import com.order.OrderMS.repository.ProductRepo;

@Service
public class OrdersService {
	
Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductRepo ProductsRepo;
	
	@Autowired
	OrdersRepo OrderRepo;
	
	
			
	
	
	public List<OrderDetailsDTO> getOrderDetails(@PathVariable Integer buyerid) {
		
		List<OrdersEntity> OrderedEntities =OrderRepo.findAll();
		List<ProductsEntity> productsEntities=ProductsRepo.findAll();
		List<OrderDetailsDTO> orderedProducts=new ArrayList<OrderDetailsDTO>();
		OrderDetailsDTO orderedProduct=new OrderDetailsDTO();
		for(OrdersEntity ordersEntity:OrderedEntities) {
			
			List<ProductsEntity> sameOrderId=new ArrayList<ProductsEntity>();
			if(ordersEntity.getBuyerid()==buyerid) {
				
				for(ProductsEntity productsEntity:productsEntities) {
					if(productsEntity.getOrderid()==ordersEntity.getOrderid()) {
						sameOrderId.add(productsEntity);
					}
				}
				if(!sameOrderId.isEmpty()) {
				orderedProduct=OrderDetailsDTO.valueOf(ordersEntity, sameOrderId);
				orderedProducts.add(orderedProduct);
				}
				}
			}
		return orderedProducts;
	}
	
	public int addToOrderDetailsTable(OrderDetailsDTO orderDetailsDTO) {
		
		OrdersEntity oe=orderDetailsDTO.createEntity();
		OrderRepo.save(oe);
		return oe.getOrderid();
		
	}
	
	public List<ProductsEntity> addToproductsorderedTable(List<ProductsOrderedDTO> productsOrderedDTOs) {
		List<ProductsEntity> productsEntities=new ArrayList<ProductsEntity>();	
		for(ProductsOrderedDTO prod:productsOrderedDTOs) {
			ProductsEntity pe=prod.createEntity();
			productsEntities.add(pe);
		}
		List<ProductsEntity> prodOrdered=ProductsRepo.saveAll(productsEntities);
			return prodOrdered;
	}
	public List<ProductsEntity> reorder(OrderDetailsDTO preOrder) {
		preOrder.setOrderid(0);
		preOrder.setDate(LocalDate.now());
		int orderId=addToOrderDetailsTable(preOrder);
		List<ProductsOrderedDTO> preProducts=preOrder.getProductsOrderedDTO();
		for(ProductsOrderedDTO preDto:preProducts) {
			preDto.setOrderid(orderId);
		}
		List<ProductsEntity> prodOrdered=addToproductsorderedTable(preProducts);
		return prodOrdered;
	}
	
	
	
	
	
	
	

	
}
