package com.model;

public class Price {
	private String pid;//��id��ʾ�ȼ�1-5��
	private String pricelow;
	private String pricehigh;
	private Category category;
	private Product product;//����Ʒһ��һ
	
	public Price() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPricelow() {
		return pricelow;
	}
	public void setPricelow(String pricelow) {
		this.pricelow = pricelow;
	}
	public String getPricehigh() {
		return pricehigh;
	}
	public void setPricehigh(String pricehigh) {
		this.pricehigh = pricehigh;
	}
	
}
