package com.jt.manage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.BaseService;
import com.jt.common.service.RedisService;
import com.jt.manage.mapper.ItemCatMapper;
import com.jt.manage.pojo.ItemCat;
import com.jt.manage.pojo.ItemCatData;
import com.jt.manage.pojo.ItemCatResult;


@Service
public class ItemCatService extends BaseService<ItemCat>{
	@Autowired
	private ItemCatMapper itemCatMapper;
	@Autowired
	private RedisService redisService;
	//jackson
	private static final ObjectMapper MAPPER = new ObjectMapper();
	private static final Logger log =Logger.getLogger(ItemCatService.class);
	
	
	/*//到后台查询商品分类，返回java对象，列表
	public List<ItemCat> list(Long id){
		ItemCat params = new ItemCat();
		params.setStatus(1);	//1正常2删除
		params.setParentId(id);
		
		List<ItemCat> itemCatList = itemCatMapper.select(params);
		return itemCatList;
	}
*/

	
	public List<ItemCat> treeList(Long id) {
		ItemCat itemCat = new ItemCat();
		itemCat.setStatus(1);
		itemCat.setParentId(id);
		List<ItemCat> itemCatList = null;
		String ITEMCAT_KEY = "ITEMCAT_"+id;
		try {
			String jsonItemCat = redisService.get(ITEMCAT_KEY);
			//判断缓存中是否有数据
			if(StringUtils.isNotEmpty(jsonItemCat)){//有数据
				//将json串转为jsonNode
				JsonNode jsonNode = MAPPER.readTree(jsonItemCat);
				//利用jackson提供的方法，将jsonNode转为java对象
				itemCatList = MAPPER.readValue(jsonNode.traverse(), MAPPER.getTypeFactory().constructCollectionType(List.class, ItemCat.class));
				return itemCatList;
			}
			//写数据到缓存，把java对象变成字符串；缓存如果出错不能抛异常，要让业务继续运行。
			itemCatList = itemCatMapper.select(itemCat);
			jsonItemCat = MAPPER.writeValueAsString(itemCatList);
			redisService.set(ITEMCAT_KEY,jsonItemCat);
			return itemCatList;
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public ItemCatResult getItemCatList(){
		ItemCat params = new ItemCat();
		params.setStatus(1);
		List<ItemCat> itemCateList = super.queryListByWhere(params);
		
		//获取每个节点下的所有的子节点，<Long,List<ItemCat>> id,当前节点下的所有数据
		Map<Long, List<ItemCat>> map = new HashMap<Long, List<ItemCat>>();
		for (ItemCat itemCat : itemCateList) {
			//map中如果没有父节点就创建父节点
			if(!map.containsKey(itemCat.getParentId())){
				map.put(itemCat.getParentId(), new ArrayList<ItemCat>());
			}
			//map中已经有父节点了就见该itemCat加入该父节点中
			map.get(itemCat.getParentId()).add(itemCat);
		}
		//组织ItemCatResult的结构
		ItemCatResult result = new ItemCatResult();
		//遍历一级目录
		for (ItemCat itemCat1 : map.get(0L)) {
			ItemCatData data1 = new ItemCatData();
			String url = "/products/"+itemCat1.getId()+".html";
			data1.setUrl(url);
			data1.setName("<a href=\""+url+"\">" + itemCat1.getName() + "</a>");
			List<ItemCatData> list1 = new ArrayList<ItemCatData>();
			for(ItemCat itemCat2 : map.get(itemCat1.getId())){//遍历二级目录
				ItemCatData data2 = new ItemCatData();
				data2.setUrl("/products/"+itemCat2.getId()+".html");
				data2.setName(itemCat2.getName());
				List<String> list2 = new ArrayList<String>();
				for(ItemCat itemCat3 : map.get(itemCat2.getId())){//遍历三级目录
					list2.add("/products/"+itemCat3.getId()+".html|"+itemCat3.getName());
				}
				data2.setItems(list2);
				list1.add(data2);
			}
			data1.setItems(list1);
			if(result.getItemCatDataList().size()>14){
				break;
			}
			result.getItemCatDataList().add(data1);
		}
		return result;
	}
}
