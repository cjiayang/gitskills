package com.jt.manage.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jt.common.service.BaseService;
import com.jt.common.vo.EasyUIResult;
import com.jt.manage.mapper.ItemDescMapper;
import com.jt.manage.mapper.ItemMapper;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;
@Service
public class ItemService extends BaseService<Item>{
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;
	public EasyUIResult queryItemList(Integer page, Integer rows){
		//标识分页开始
		PageHelper.startPage(page, rows);
		/*PageHelper pageHelper = new PageHelper();
		pageHelper.startPage(pageNum, pageSize);*/
		List<Item> itemList = itemMapper.queryItemList();
		//封装当前页和记录总数对象
		PageInfo<Item> pageInfo = new PageInfo<Item>(itemList);
		return new EasyUIResult(pageInfo.getTotal(),pageInfo.getList());
	}
	public void saveItem(Item item,String desc) {
		item.setStatus(1);
		item.setCreated(new Date());
		item.setUpdated(item.getCreated());
		itemMapper.insertSelective(item);
		
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(item.getCreated());
		itemDesc.setUpdated(item.getCreated());
		itemDescMapper.insertSelective(itemDesc);
	}
	public void updateItem(Item item, String desc) {
		item.setUpdated(new Date());
		itemMapper.updateByPrimaryKeySelective(item);
		
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDesc.setUpdated(new Date());
		itemDescMapper.updateByPrimaryKeySelective(itemDesc);
	}
	public void deleteItems(Long[] ids) {
		itemMapper.deleteByIDS(ids);
		itemDescMapper.deleteByIDS(ids);
	}
	public void updateStatus(Integer status,Long[] ids) {
		//两个参数，可以在service层封装成Map，然后传到数据库中访问
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("status", status);
		params.put("ids", ids);
 		itemMapper.updateStatus(params);
	}
	public ItemDesc getItemDescByItemId(Long itemId){
		return itemDescMapper.selectByPrimaryKey(itemId);
	}
}
