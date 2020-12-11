package com.order.OrderMS.dto;

import com.order.OrderMS.entity.ProductsEntity;

public class ProductsOrderedDTO {

	private Integer orderid;
	private Integer prodid;
	private Integer sellerid;
	private Integer quantity;
	private String status;
	private Long price;
	
	public ProductsOrderedDTO() {
		super();
	}
	
	
	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public Integer getProdid() {
		return prodid;
	}

	public void setProdid(Integer prodid) {
		this.prodid = prodid;
	}

	public Integer getSellerid() {
		return sellerid;
	}

	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}
	// Converts Entity into DTO
	public static ProductsOrderedDTO valueOf(ProductsEntity productsOrderedEntity){
		
		ProductsOrderedDTO psd =new ProductsOrderedDTO();
		
		psd.setOrderid(productsOrderedEntity.getOrderid());
		psd.setProdid(productsOrderedEntity.getProdid());
		psd.setSellerid(productsOrderedEntity.getSellerid());
		psd.setQuantity(productsOrderedEntity.getQuantity());
		psd.setStatus(productsOrderedEntity.getStatus());
		psd.setPrice(productsOrderedEntity.getPrice());
		
		return psd;
		
	}

	// Converts DTO into Entity
	public ProductsEntity createEntity() {
		ProductsEntity pe=new ProductsEntity();
	
		pe.setOrderid(this.orderid);
		pe.setProdid(this.prodid);
		pe.setSellerid(this.sellerid);
		pe.setQuantity(this.quantity);
		pe.setStatus(this.status);
		pe.setPrice(this.price);
		
		return pe;
	}
	
}
