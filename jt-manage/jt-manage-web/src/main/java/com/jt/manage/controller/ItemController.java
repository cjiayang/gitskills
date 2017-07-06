package com.jt.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;
import com.jt.manage.service.ItemService;
@Controller
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private ItemService itemService;
	@RequestMapping("/query")
	@ResponseBody
	public EasyUIResult queryItemList(Integer page,Integer rows){
		return itemService.queryItemList(page,rows);
	}
	//新增
	@RequestMapping("/save")
	@ResponseBody
	public SysResult saveItem(Item item,String desc){
		try {
			itemService.saveItem(item,desc);
			return SysResult.oK();
		} catch (Exception e) {
			return SysResult.build(201, "新增商品出错");
		}
	}
	//修改
	@RequestMapping("/update")
	@ResponseBody
	public SysResult updateItem(Item item,String desc){
		try {
			itemService.updateItem(item, desc);
			return SysResult.oK();
		} catch (Exception e) {
			return SysResult.build(201, "更新商品出错");
		}
	}
	//删除
	@RequestMapping("/delete")
	@ResponseBody
	public SysResult deleteItems(Long[] ids){
		try {
			itemService.deleteItems(ids);
			return SysResult.oK();
		} catch (Exception e) {
			return SysResult. build(201, "删除商品出错");
		}
	}
	//商品下架
	@RequestMapping("/instock")
	@ResponseBody
	public SysResult updateStatus(Long[] ids){
		try {
			itemService.updateStatus(2,ids);
			return SysResult.oK();
		} catch (Exception e) {
			return SysResult.build(201, "下架商品成功");
		}
	}
	//商品上架
	@RequestMapping("/reshelf")
	@ResponseBody
	public SysResult updateStadus(Long[] ids){
		try {
			itemService.updateStatus(1,ids);
			return SysResult.oK();
		} catch (Exception e) {
			return SysResult.build(201, "上架商品成功");
		}
	}
	@RequestMapping("/query/item/desc/{itemId}")
	@ResponseBody
	public SysResult getItemDescByItemId(@PathVariable Long itemId){
		try {
			ItemDesc itemDesc = itemService.getItemDescByItemId(itemId);
			return SysResult.oK(itemDesc);
		} catch (Exception e) {
			return SysResult.build(201, "商品详情回显失败");
		}
	}
	
	
	
	
	
	
	
}
