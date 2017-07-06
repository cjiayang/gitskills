package com.jt.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.service.RedisService;
import com.jt.common.vo.SysResult;
import com.jt.sso.pojo.User;
import com.jt.sso.service.UserService;
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RedisService redisService;
	
	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public SysResult check(@PathVariable String param, @PathVariable Integer type){
		try {
			Boolean data = userService.check(param, type);
			return SysResult.oK(data);
		} catch (Exception e) {                             
			return SysResult.build(201, "检测失败");
		}
	}
	@RequestMapping("/register")
	@ResponseBody
	public SysResult saveRegister(User user){
		try {
			String username = userService.saveRegister(user);
			return SysResult.oK(username);
		} catch (Exception e) {
			return SysResult.build(201, "注册失败");
		}
	}
	
	//登录
	@RequestMapping("/login")
	@ResponseBody
	public SysResult login(String username, String password){
		try {
			String ticket = userService.saveLogin(username, password);
			return SysResult.oK(ticket);
		} catch (Exception e) {
			return SysResult.build(201, "用户登录失败");
		}
	}
	
	@RequestMapping("/query/{ticket}")
	@ResponseBody
	public SysResult queryByTicket(@PathVariable String ticket){
		try {
			String userJson = redisService.get(ticket);
			return SysResult.oK(userJson);
		} catch (Exception e) {
			return SysResult.build(201, "查询失败");
		}
		
	}
	
	
	
	
}
