package com.order.OrderMS.entity;


import java.time.LocalDate;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name = "orderdetails")
public class OrdersEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ORDERID")
	private int orderid;
	
	@Column(name="BUYERID",nullable = false)
	private int buyerid;
	
	@Column(name="AMOUNT",nullable=false,precision = 10,scale=2)
	private double amount;
	
	@Column(name="DATE")
	private LocalDate date;
	
	@Column(name="ADDRESS",nullable=false,length=10)
	private String address;
	
	@Column(name="STATUS",nullable = false,length=60)
	private String status;

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

	

	
}
