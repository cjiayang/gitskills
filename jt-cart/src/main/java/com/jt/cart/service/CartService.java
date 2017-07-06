package com.jt.cart.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.cart.mapper.CartMapper;
import com.jt.cart.pojo.Cart;
import com.jt.common.service.BaseService;

@Service
public class CartService extends BaseService<Cart>{
	@Autowired
	private CartMapper cartMapper;
	
	public List<Cart> myCart(Long userId){
		Cart params = new Cart();
		params.setUserId(userId);
		return cartMapper.select(params);
	}
	
	//加入购物车
	public void saveCart(Cart cart){
		Cart params = new Cart();
		params.setUserId(cart.getUserId());
		params.setItemId(cart.getItemId());
		Cart oldCart = super.queryByWhere(cart);
		if(null==oldCart){//购物车中没有该商品
			//新增
			cart.setCreated(new Date());
			cart.setUpdated(cart.getCreated());
			cartMapper.insertSelective(cart);
		}else{
			//修改商品数量
			cart.setId(oldCart.getId());
			cart.setNum(oldCart.getNum()+cart.getNum());
			cartMapper.updateByPrimaryKeySelective(cart);
		}
	}
	//修改商品数量，与页面上的数量一致
	public void updateNum(Cart cart){
		cartMapper.updateNum(cart);
	}
	
	
	
}
