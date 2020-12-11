package com.order.OrderMS.dto;

public class ProductDTO {
	private Integer prodid;
	private String brand;
	private String category;
	private String description;
	private String image;
	private String productname;
	private String subcategory;
	private long price;
	private Integer rating;
	private Integer sellerid;
	private Integer stock;
	 
	public ProductDTO() {
		super();
	}
	
	
	public Integer getProdid() {
		return prodid;
	}


	public void setProdid(Integer prodid) {
		this.prodid = prodid;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getProductname() {
		return productname;
	}


	public void setProductname(String productname) {
		this.productname = productname;
	}


	public String getSubcategory() {
		return subcategory;
	}


	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}


	public long getPrice() {
		return price;
	}


	public void setPrice(long price) {
		this.price = price;
	}


	public Integer getRating() {
		return rating;
	}


	public void setRating(Integer rating) {
		this.rating = rating;
	}


	public Integer getSellerid() {
		return sellerid;
	}


	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}


	public Integer getStock() {
		return stock;
	}


	public void setStock(Integer stock) {
		this.stock = stock;
	}
}
