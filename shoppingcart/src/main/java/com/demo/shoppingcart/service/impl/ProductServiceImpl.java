package com.demo.shoppingcart.service.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.shoppingcart.entity.Product;
import com.demo.shoppingcart.model.ProductInfo;
import com.demo.shoppingcart.repository.ProductRepository;
import com.demo.shoppingcart.service.ProductService;

/**
 * Implementation of ProductService interface.
 * Hosts functionality to cater operations with products.
 * 
 * @author 28883
 *
 */
@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private Mapper mapper;
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<ProductInfo> filter(final String name, final double price) {
		List<ProductInfo> filteredProduct = null;
		if(name != null) {
			final Iterable<Product> products = productRepository.findAll();
			filteredProduct = StreamSupport.stream(products.spliterator(), true).filter((product)-> product.getName().contains(name)).filter((product)-> product.getPrice() < price).map(product->mapper.map(product, ProductInfo.class)).collect(Collectors.toList());
		}		
		return filteredProduct;
	}

	@Override
	public ProductInfo findByCode(final String code) {
		ProductInfo productInfo = null;
		if(code != null) {
			final Product product = productRepository.findByCode(code);
			if(product != null) {
				productInfo = mapper.map(product, ProductInfo.class);
			}
		}
		return productInfo;
		
	}

	@Override
	public List<ProductInfo> list() {
		Function<Product, ProductInfo> func = (product) -> {
			return mapper.map(product, ProductInfo.class);
		};
		final Iterable<Product> products = productRepository.findAll();
		List<ProductInfo> filteredProduct = StreamSupport.stream(products.spliterator(), true).map(func).collect(Collectors.toList());
		return filteredProduct;
	}

}
