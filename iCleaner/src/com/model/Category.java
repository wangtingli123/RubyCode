package com.model;

import java.util.List;

public class Category {
	private String cid;
	private String cname;
	private Price price;//和价格一对一
	private List<Product> product;//和商品：一对多
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Price getPrice() {
		return price;
	}
	public void setPrice(Price price) {
		this.price = price;
	}
	
	
}
