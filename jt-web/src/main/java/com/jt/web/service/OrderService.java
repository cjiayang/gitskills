package com.jt.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.web.pojo.Cart;
import com.jt.web.pojo.Order;

@Service
public class OrderService {
	@Autowired
	private HttpClientService httpClientService;
	private static final ObjectMapper MAPPER = new ObjectMapper();
	public List<Cart> getCartList(Long userId) throws Exception{
		String url = "http://cart.jt.com/cart/query/"+userId;
		String jsonData = httpClientService.doGet(url);
		JsonNode data = MAPPER.readTree(jsonData).get("data");
		Object obj = null;
		if(data.isArray() && data.size()>0){
			obj = MAPPER.readValue(data.traverse(), 
					MAPPER.getTypeFactory().constructCollectionType(List.class, Cart.class));
		}
		return (List<Cart>)obj;
	}
	
	public String createOrder(Order order) throws Exception{
		String url = "http://order.jt.com/order/create";
		String orderId = httpClientService.doPostJson(url, MAPPER.writeValueAsString(order));
		return orderId;
	}
	
	public Order getOrderById(String orderId) throws Exception{
		String url  = "http://order.jt.com/query/"+orderId;
		String jsonData = httpClientService.doGet(url);
		return MAPPER.readValue(jsonData, Order.class);
	}
	

}
