package com.order.OrderMS.dto;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import com.order.OrderMS.entity.OrdersEntity;
import com.order.OrderMS.entity.ProductsEntity;

public class OrderDetailsDTO {
	
	private int orderid;
	private int buyerid ;
	private double amount ;
	private LocalDate date;
	private String address ;
	private String status;
	private List<ProductsOrderedDTO> productsOrderedDTOs;
	
	
	
	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public int getBuyerid() {
		return buyerid;
	}

	public void setBuyerid(int buyerid) {
		this.buyerid = buyerid;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ProductsOrderedDTO> getProductsOrderedDTO() {
		return productsOrderedDTOs;
	}

	public void setProductsOrderedDTO(List<ProductsOrderedDTO> productsOrderedDTOs) {
		this.productsOrderedDTOs = productsOrderedDTOs;
	}

	public OrderDetailsDTO() {
		super();
	}
	
//	public OrderDetailsDTO(int orderid,int buyerid,double amount,Date date,String address,String status) {
//		this();
//	}

	

	//convert Entity to DTO
	public static OrderDetailsDTO valueOf(OrdersEntity orderDetailsEntity,List<ProductsEntity> productsEntities){
		
		OrderDetailsDTO ordersDetailsDTO =new OrderDetailsDTO();
		ordersDetailsDTO.setOrderid(orderDetailsEntity.getOrderid());
		ordersDetailsDTO.setBuyerid(orderDetailsEntity.getBuyerid());
		ordersDetailsDTO.setAmount(orderDetailsEntity.getAmount());
		ordersDetailsDTO.setDate(orderDetailsEntity.getDate());
		ordersDetailsDTO.setAddress(orderDetailsEntity.getAddress());
		ordersDetailsDTO.setStatus(orderDetailsEntity.getStatus());
		List<ProductsOrderedDTO> pDtos=new ArrayList<ProductsOrderedDTO>();
		for(ProductsEntity productsEntity:productsEntities) {
			ProductsOrderedDTO productsOrderedDTO= ProductsOrderedDTO.valueOf(productsEntity);
			pDtos.add(productsOrderedDTO);
		}
		
		ordersDetailsDTO.setProductsOrderedDTO(pDtos);
		
		return ordersDetailsDTO;
		
	}
	
	// Converts DTO into Entity
		public OrdersEntity createEntity() {
			OrdersEntity ordersEntity=new OrdersEntity();
			ordersEntity.setOrderid(this.orderid);
			ordersEntity.setBuyerid(this.buyerid);
			ordersEntity.setAmount(this.amount);
			ordersEntity.setDate(this.date);
			ordersEntity.setAddress(this.address);
			ordersEntity.setStatus(this.status);
			
			return ordersEntity;
			
		}
		
		
		
		
		
	

}
