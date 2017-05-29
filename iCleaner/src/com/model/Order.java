package com.model;

import java.util.List;


public class Order {
	private String orderId;
	private String totalPrice;//总价
	private CleanStatus cleanStatus;
	private PayStatus payStatus;
	private Product product;//和产品表一对一
	private Station station;//要获取站点
	private PickUp pickUp;
	private String orderAddr;
	private String customer_order_id;
	public String getCustomer_order_id() {
		return customer_order_id;
	}

	public void setCustomer_order_id(String customer_order_id) {
		this.customer_order_id = customer_order_id;
	}
	private List<Items> itemsList;
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PickUp getPickUp() {
		return pickUp;
	}

	public void setPickUp(PickUp pickUp) {
		this.pickUp = pickUp;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getOrderAddr() {
		return orderAddr;
	}

	public void setOrderAddr(String orderAddr) {
		this.orderAddr = orderAddr;
	}

	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public List<Items> getItemsList() {
		return itemsList;
	}
	public void setItemsList(List<Items> itemsList) {
		this.itemsList = itemsList;
	}
	public CleanStatus getCleanStatus() {
		return cleanStatus;
	}
	public void setCleanStatus(CleanStatus cleanStatus) {
		this.cleanStatus = cleanStatus;
	}
	public PayStatus getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(PayStatus payStatus) {
		this.payStatus = payStatus;
	}
	
}
