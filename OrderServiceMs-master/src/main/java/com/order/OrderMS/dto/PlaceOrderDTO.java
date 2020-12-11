package com.order.OrderMS.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class PlaceOrderDTO {
	
	private int buyerid ;
	
	private String address ;
	
	
	public int getBuyerid() {
		return buyerid;
	}
	public void setBuyerid(int buyerid) {
		this.buyerid = buyerid;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	// convert PlaceOrderDTO to OrderDetailsDTO
	public OrderDetailsDTO convertToOrderDetailsDTO(PlaceOrderDTO placeOrderDTO,List<CartDTO> cartDTOs,List<ProductDTO> productDTOS) {
		Double amount=0.0;
		for(CartDTO cartDTO:cartDTOs) {
			for(ProductDTO productDTO:productDTOS) {
				if(cartDTO.getProductId()==productDTO.getProdid()) {
					amount+=productDTO.getPrice()*cartDTO.getQuantity();
				}
			}
		}
		OrderDetailsDTO oDD =new OrderDetailsDTO();
		oDD.setBuyerid(placeOrderDTO.getBuyerid());
		oDD.setDate(LocalDate.now());
		oDD.setAddress(placeOrderDTO.getAddress());
		oDD.setStatus("ORDER PLACED");
		oDD.setAmount(amount);
		
		return oDD;
		
	}
	
	// convert PlaceOrderDTO to ProductsOrderedDTO
		public List<ProductsOrderedDTO> convertToProductsOrderedDTO(List<CartDTO> cartDTOs,List<ProductDTO> productDTOs,Integer orderId) {
			List<ProductsOrderedDTO> productsOrderedDTOs=new ArrayList<ProductsOrderedDTO>();
			for(CartDTO cartDTO:cartDTOs) {
				for(ProductDTO productDTO:productDTOs) {
					if(productDTO.getProdid()==cartDTO.getProductId()) {
						ProductsOrderedDTO pdd =new ProductsOrderedDTO();
						pdd.setOrderid(orderId);
						pdd.setSellerid(productDTO.getSellerid());
						pdd.setProdid(cartDTO.getProductId());
						pdd.setStatus("ORDER PLACED");
						pdd.setPrice(productDTO.getPrice());
						pdd.setQuantity(cartDTO.getQuantity());
						productsOrderedDTOs.add(pdd);
					}
				}
			}
			return productsOrderedDTOs;
			
		}
	
	
	

}
