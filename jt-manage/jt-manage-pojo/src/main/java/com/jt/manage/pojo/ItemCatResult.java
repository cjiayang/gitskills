package com.jt.manage.pojo;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
//按前台系统的要求，...
public class ItemCatResult {
	@JsonProperty("data")//缩减传输的长度
	private List<ItemCatData> itemCatDataList = new ArrayList<ItemCatData>();

	public List<ItemCatData> getItemCatDataList() {
		return itemCatDataList;
	}

	public void setItemCatDataList(List<ItemCatData> itemCatDataList) {
		this.itemCatDataList = itemCatDataList;
	}
	
}
