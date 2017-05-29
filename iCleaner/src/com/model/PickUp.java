package com.model;

public class PickUp {
	private String pickid;
	private SendOrderStatus pickStatus;
	private String expectedTime;
	private String realTime;
	private Order order;
	private Station station;
	public SendOrderStatus getPickStatus() {
		return pickStatus;
	}
	public void setPickStatus(SendOrderStatus pickStatus) {
		this.pickStatus = pickStatus;
	}
	public String getPickid() {
		return pickid;
	}
	public void setPickid(String pickid) {
		this.pickid = pickid;
	}
	public String getExpectedTime() {
		return expectedTime;
	}
	public void setExpectedTime(String expectedTime) {
		this.expectedTime = expectedTime;
	}
	public String getRealTime() {
		return realTime;
	}
	public void setRealTime(String realTime) {
		this.realTime = realTime;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Station getStation() {
		return station;
	}
	public void setStation(Station station) {
		this.station = station;
	}
	
}
