package com.order.OrderMS.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "productsordered")
@IdClass(ProductsEntity.class)
public class ProductsEntity implements Serializable{

	@Id
	
	@Column(name="ORDERID",nullable=false)
	private Integer orderid;
	
	@Id

	@Column(name="PRODID",nullable = false)
	private Integer prodid;
	
	@Column(name="SELLERID")
	private Integer sellerid;
	
	@Column(name="QUANTITY")
	private Integer quantity;
	
	@Column(name="STATUS",nullable = false,length=60)
	private String status;
	
	@Column(name="price",precision = 10,scale = 2)
	private long price;

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

	
	
}
