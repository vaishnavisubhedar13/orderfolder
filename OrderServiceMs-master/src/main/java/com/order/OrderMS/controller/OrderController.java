package com.order.OrderMS.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.core.env.Environment;

import com.order.OrderMS.dto.CartDTO;

import com.order.OrderMS.dto.OrderDetailsDTO;
import com.order.OrderMS.dto.PlaceOrderDTO;
import com.order.OrderMS.dto.ProductDTO;
import com.order.OrderMS.dto.ProductIDsDTO;
import com.order.OrderMS.dto.ProductsOrderedDTO;
import com.order.OrderMS.entity.ProductsEntity;
import com.order.OrderMS.service.OrdersService;


@RestController
@CrossOrigin
public class OrderController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	OrdersService orderService;
	
	@Autowired
	Environment environment;
	
//	@Value("${cart.uri}")
//	String carturi;

	
	
	//fetch values based on BuyerId from orderdetails
	@GetMapping(value = "/order/{BUYERID}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderDetailsDTO>> getOrderDetails(@PathVariable int BUYERID) {
		try {
		logger.info("order details {}", BUYERID);

		List<OrderDetailsDTO> orderDetailsDTOs= orderService.getOrderDetails(BUYERID);
		return new ResponseEntity<List<OrderDetailsDTO>>(orderDetailsDTOs,HttpStatus.FOUND);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
		}
	}
	

	
	
	
// add values to productsordered and orderdetails
	@PostMapping(value = "/placedorders",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String productsordered(@RequestBody PlaceOrderDTO placeOrderDTO) {
		logger.info("fetching products from Cart");
		ResponseEntity<CartDTO[]> carts =new RestTemplate().getForEntity("http://localhost:8200/product/fetchCart/"+placeOrderDTO.getBuyerid(),CartDTO[].class);
		CartDTO[] cart=carts.getBody();
		List<CartDTO>  cartDTOs=new ArrayList<CartDTO>();
		for(int i=0;i<cart.length;i++) {
			cartDTOs.add(cart[i]);
		}
		List<Integer> prodIds=new ArrayList<Integer>();
		for(CartDTO cartDTO:cartDTOs) {
			prodIds.add(cartDTO.getProductId());
		}
		ProductIDsDTO productIDsDTO=new ProductIDsDTO();
		productIDsDTO.setProductIds(prodIds);
		logger.info("fetching products from Product table");
		ResponseEntity<ProductDTO[]> products=new RestTemplate().postForEntity("http://localhost:8100/productByIds", productIDsDTO, ProductDTO[].class);
		ProductDTO[] product=products.getBody();
		List<ProductDTO>  productDTOs=new ArrayList<ProductDTO>();
		for(int i=0;i<product.length;i++) {
			productDTOs.add(product[i]);
		}
		
		OrderDetailsDTO odd = placeOrderDTO.convertToOrderDetailsDTO(placeOrderDTO,cartDTOs,productDTOs);
		logger.info("request for order with buyer{}",placeOrderDTO.getBuyerid());
		int orderId = orderService.addToOrderDetailsTable(odd);
		logger.info("order saved in OrderDetails Table");
		logger.info("request for saving all orders in product order table");
		List<ProductsOrderedDTO> POD = placeOrderDTO.convertToProductsOrderedDTO(cartDTOs,productDTOs,orderId);
		List<ProductsEntity> peList=orderService.addToproductsorderedTable(POD);
		String message=null;
		if(!peList.isEmpty()) {
			new RestTemplate().delete("http://localhost:8200/deleteCart/"+placeOrderDTO.getBuyerid());
			message=environment.getProperty("ORDER_PLACED");
		}
		else {
			message=environment.getProperty("ORDER_NOTPLACED");
		}
		return message;
	}
	
	//reorder products
	@PostMapping(value = "/order/reorder/{orderId:.+}/{buyerId}")
	public String reorderProducts(@PathVariable("orderId") Integer orderId,@PathVariable("buyerId") Integer buyerId) {
		List<OrderDetailsDTO> buyerOrders=orderService.getOrderDetails(buyerId);
		OrderDetailsDTO previousOrder=new OrderDetailsDTO();
		for(OrderDetailsDTO order:buyerOrders) {
			if(order.getOrderid()==orderId) {
				previousOrder=order;
			}
		}
		String message=null;
		List<ProductsEntity> ordered=orderService.reorder(previousOrder);
		if(!ordered.isEmpty()) {
			message=environment.getProperty("REORDER_PLACED");
		}
		else {
		message=environment.getProperty("REORDER_NOTPLACED");
		}
		return message;
		
	}
	
	
	
	
	
	@PostMapping(value="/product/addproductcart", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addProductToCart(@RequestBody CartDTO cartDTO){
		try {
			logger.info("request for add product to cart");
			String responseCart=new RestTemplate().postForObject("http://localhost:8200/product/addcart",cartDTO, String.class);
			return new ResponseEntity<String>(responseCart,HttpStatus.CREATED);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
		}
	}
	@PostMapping(value="/product/removeproductcart", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> removeProductToWishList(@RequestBody CartDTO cartDTO){
		try {
			logger.info("request for remove product to cart");
			String responseCart=new RestTemplate().postForObject("http://localhost:8200/product/removeCart",cartDTO, String.class);
			return new ResponseEntity<String>(responseCart,HttpStatus.CREATED);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
		}
	}
	
	
	
	
	



}
