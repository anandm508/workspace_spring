package com.demo.shoppingcart.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.shoppingcart.entity.Product;
import com.demo.shoppingcart.model.ProductInfo;
import com.demo.shoppingcart.repository.ProductRepository;

/**
 * Unit test class for ProductServiceImpl
 * @author 28883
 *
 */
@RunWith(SpringRunner.class)
public class ProductServiceImplTest {

	@Mock
	private ProductRepository productRepository;

    @Spy
    private Mapper mapper = new DozerBeanMapper();

	@InjectMocks
	private ProductServiceImpl service;
	

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Below test case will try to find a product with a criteria which will return non-zero results.
	 */
	@Test
	public void testFilterCase_1() {
		MockitoAnnotations.initMocks(this);
		List<Product> products = new ArrayList<Product>();
		Product product1 = new Product();
		product1.setName("Headfirst Java");
		product1.setPrice(200);
		products.add(product1);

		when(productRepository.findAll()).thenReturn(products);

		List<ProductInfo> result = service.filter("Java", 300);
		assertThat(result.size()).isEqualTo(1);
		assertThat(result.get(0).getName()).isEqualTo("Headfirst Java");
		assertThat(result.get(0).getPrice()).isEqualTo(200);
	}
	
	/**
	 * Below test case will try to find a product with a criteria which will return zero results.
	 */
	@Test
	public void testFilterCase_2() {
		List<Product> products = new ArrayList<Product>();
		Product product1 = new Product();
		product1.setName("Headfirst Java");
		product1.setPrice(200);
		products.add(product1);

		when(productRepository.findAll()).thenReturn(products);

		List<ProductInfo> result = service.filter("Java", 100);
		assertThat(result.size()).isEqualTo(0);
	}
	
	/**
	 * Checks the null product name handling.
	 */
	@Test
	public void testFilterCase_3() {
		List<Product> products = new ArrayList<Product>();
		Product product1 = new Product();
		product1.setName("Headfirst Java");
		product1.setPrice(200);
		products.add(product1);

		when(productRepository.findAll()).thenReturn(products);

		List<ProductInfo> result = service.filter(null, 100);
		assertThat(result).isNull();
	}
	
	/**
	 * Tests the list all products functionality
	 */
	@Test
	public void testList() {
		List<Product> products = new ArrayList<Product>();
		Product product1 = new Product();
		product1.setName("Headfirst Java");
		product1.setPrice(200);
		product1.setCode("S008");
		product1.setCreateDate(new Date());
		products.add(product1);

		when(productRepository.findAll()).thenReturn(products);

		List<ProductInfo> result = service.list();
		assertThat(result.size()).isGreaterThan(0);
	}
	
	/**
	 * Tests find product by code function using a valid value
	 */
	@Test
	public void testFindByValidCode() {
		Product product = new Product();
		product.setName("Headfirst Java");
		product.setPrice(200);
		product.setCode("S008");
		product.setCreateDate(new Date());

		when(productRepository.findByCode("S008")).thenReturn(product);

		ProductInfo result = service.findByCode("S008");
		assertThat(result.getCode()).isEqualTo("S008");
	}
	

	/**
	 * Tests find product by code function using a invalid value
	 */
	@Test
	public void testFindByInvalidCode() {
		Product product = new Product();
		product.setName("Headfirst Java");
		product.setPrice(200);
		product.setCode("S008");
		product.setCreateDate(new Date());

		when(productRepository.findByCode("S008")).thenReturn(product);

		ProductInfo result = service.findByCode("S009");
		assertNull(result);
	}

}
