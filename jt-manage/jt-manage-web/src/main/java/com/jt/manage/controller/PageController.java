package com.jt.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {
	//通用转向页面，任何一个jsp
	@RequestMapping("/{pageName}")
	public String goHome(@PathVariable String pageName){
		return pageName;
	}
}
