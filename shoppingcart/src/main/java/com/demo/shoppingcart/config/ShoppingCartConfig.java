package com.demo.shoppingcart.config;

import java.util.Arrays;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShoppingCartConfig {
	
	@Bean(name = "org.dozer.Mapper")
	public DozerBeanMapper dozerBean(){
		//Can provide mapping files here
		List<String> mappingFiles = Arrays.asList();
		
		DozerBeanMapper dozerBean = new DozerBeanMapper();
		dozerBean.setMappingFiles(mappingFiles);
		return dozerBean;
	}
}
