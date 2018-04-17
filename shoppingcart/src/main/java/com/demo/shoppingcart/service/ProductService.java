package com.demo.shoppingcart.service;

import java.util.List;

import com.demo.shoppingcart.model.ProductInfo;

public interface ProductService {
	
	public List<ProductInfo> filter(final String name, final double price);
	public ProductInfo findByCode(final String code);	
	public List<ProductInfo> list();	

}
	