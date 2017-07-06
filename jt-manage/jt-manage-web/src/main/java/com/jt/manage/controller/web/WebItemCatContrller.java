package com.jt.manage.controller.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.manage.pojo.ItemCatResult;
import com.jt.manage.service.ItemCatService;

@Controller //后台系统为前台系统实现的controller
@RequestMapping("web/itemcat")
public class WebItemCatContrller {
	@Autowired
	private ItemCatService itemCatService;
	@RequestMapping("/all")
	@ResponseBody
	public ItemCatResult getItemCatList(){
		return itemCatService.getItemCatList();
	}
}
