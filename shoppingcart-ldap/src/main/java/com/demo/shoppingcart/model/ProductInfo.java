package com.demo.shoppingcart.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ProductInfo {
	
	@ApiModelProperty(notes="Product Code", dataType="String")
	private String code;
	@ApiModelProperty(notes="Product Name", dataType="String")
	private String name;
	@ApiModelProperty(notes="Product Price", dataType="double")
	private double price;
	
	public ProductInfo() {}
	
	public ProductInfo(String code, String name, double price) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}