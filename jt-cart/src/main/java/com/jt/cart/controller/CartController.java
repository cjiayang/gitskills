package com.jt.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.cart.pojo.Cart;
import com.jt.cart.service.CartService;
import com.jt.common.vo.SysResult;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService cartService;
	
	@RequestMapping("/query/{userId}")
	@ResponseBody
	public SysResult myCart(@PathVariable Long userId){
		List<Cart> cartList = cartService.myCart(userId);
		return SysResult.oK(cartList);
	}
	@RequestMapping("/save")
	@ResponseBody
	public SysResult add(Cart cart){
		try {
			cartService.saveCart(cart);
			return SysResult.oK();
		} catch (Exception e) {
			return SysResult.build(201, "添加商品失败");
		}
	}
	
	@RequestMapping("update/num/{userId}/{itemId}/{num}")
	@ResponseBody
	public SysResult updateNum(Cart cart){
		try {
			cartService.updateNum(cart);
			return SysResult.oK();
		} catch (Exception e) {
			return SysResult.build(201, "商品数量更新失败");
		}
	}
	
	@RequestMapping("/delete/{userId}/{itemId}")
	@ResponseBody
	public SysResult deleteCart(Cart cart){
		try {
			cartService.deleteByWhere(cart);
			return SysResult.oK();
		} catch (Exception e) {
			return SysResult.build(201, "删除购物车失败");
		}
	}
	
}
