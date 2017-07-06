package com.jt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Cart;
import com.jt.web.pojo.Order;
import com.jt.web.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@RequestMapping("/create")
	public String getCartList(Model model) throws Exception{
		Long userId = 7L;
		List<Cart> carts = orderService.getCartList(userId);
		model.addAttribute("carts", carts);
		return "order-cart";
	}
	
	@RequestMapping("/submit")
	@ResponseBody
	public SysResult submit(Order order) throws Exception{
		Long userId = 7L;
		order.setUserId(userId);
		String orderId = orderService.createOrder(order);
		return SysResult.oK(orderId);
	}
	
	@RequestMapping("/success")
	public String success(String orderId, Model model) throws Exception{
		Order order = orderService.getOrderById(orderId);
		model.addAttribute("order", order);
		return "success";
	}
	
	
}