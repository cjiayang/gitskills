package com.jt.sso.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.BaseService;
import com.jt.common.service.RedisService;
import com.jt.sso.mapper.UserMapper;
import com.jt.sso.pojo.User;

@Service
public class UserService extends BaseService<User>{
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RedisService redisService;
	private static final ObjectMapper MAPPER = new ObjectMapper();
	//用户监测
	public Boolean check(String param, Integer type){
		Map<String, Object> map = new HashMap<String, Object>();
		if(type==1){
			map.put("colname", "username");
		}else if(type==2){
			map.put("colname", "phone");
		}else {
			map.put("colname", "email");
		}
		map.put("param", param);
		
		Integer i = userMapper.check(map);
		if(i==0){
			return false;
		}else{
			return true;
		}
		
	}
	
	//注册
	public String saveRegister(User user){
		user.setCreated(new Date());
		user.setUpdated(user.getCreated());
	    //因为页面上不用填写，为了唯一性而准备的
		user.setEmail("temp_"+user.getPhone());
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		userMapper.insertSelective(user);
		return user.getUsername();
	}
	//登录
	public String saveLogin(String username,String passwd) throws JsonProcessingException{
		//1、先根据用户名查询，取出对象后和密码进行比较
		String ticket = "";
		User param = new User();
		param.setUsername(username);
		User curUser = super.queryByWhere(param);
		if(null!=curUser){
			String newPasswd = DigestUtils.md5Hex(passwd);
			if(newPasswd.equals(curUser.getPassword())){
				//2、生成ticket:唯一性，动态性，混淆
				ticket = DigestUtils.md2Hex(System.currentTimeMillis()+curUser.getUsername()+curUser.getId());
				//3、把当前用户信息放入redis,设置一个定期自动删除，设置过期时间：7天，10天
				redisService.set(ticket, MAPPER.writeValueAsString(curUser),60*60*24*7);
			}
		}
		return ticket;
	}

}
