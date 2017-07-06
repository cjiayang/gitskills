package com.jt.manage.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

//代表一条记录，代表一级分类或者二级分类
public class ItemCatData {
	@JsonProperty("u")
	private String url;
	@JsonProperty("n")
	private String name;
	@JsonProperty("i")
	private List<?> items; //?代表二级菜单，三级菜单
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<?> getItems() {
		return items;
	}
	public void setItems(List<?> items) {
		this.items = items;
	}
	
}
