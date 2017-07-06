package com.jt.web.controller;

import java.util.List;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicInterface2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Cart;
import com.jt.web.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService cartService;
	@RequestMapping("/show")
	public String show(Model model) throws Exception{
		Long userId =7L;
		List<Cart> cartList = cartService.show(userId);
		model.addAttribute("cartList", cartList);
		return "cart";
	}
	
	@RequestMapping("/add/{itemId}")
	public String saveCart(Cart cart) throws Exception{
	    Long userId =7L;
	    cart.setUserId(userId);
		cartService.saveCart(cart);
		return "redirect:/cart/show.html";
	}
	
	//  http://www.jt.com/service/cart/update/num/562379/3
	@RequestMapping("/update/num/{itemId}/{num}")
	public String updateNum(@PathVariable Long itemId,@PathVariable Integer num) throws Exception{
		Long userId =7L;
		cartService.updateNum(userId, itemId, num);
		return "";
	}
	@RequestMapping("/delete/{itemId}")
	public String deleteCart(@PathVariable Long itemId) throws Exception{
		Long userId = 7L;
		cartService.deleteCart(userId, itemId);
		return "redirect:/cart/show.html";
	}
	
	
	
}
