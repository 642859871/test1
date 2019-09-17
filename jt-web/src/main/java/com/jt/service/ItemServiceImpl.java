package com.jt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.until.HttpClientService;
import com.jt.util.ObjectMapperUtil;
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private HttpClientService httpClientService;
	@Override
	public Item findItemById(Long itemId) {
		String url = "http://manage.jt.com/web/item/findItemById/"+itemId;
		String itemJson = httpClientService.doGet(url);
		return ObjectMapperUtil.toObject(itemJson, Item.class);
	}
	@Override
	public ItemDesc findItemDescById(Long itemId) {
		String url = "http://manage.jt.com/web/item/findItemDescById/"+itemId;
		String itemDescJson = httpClientService.doGet(url);
		return ObjectMapperUtil.toObject(itemDescJson, ItemDesc.class);
	}

}
