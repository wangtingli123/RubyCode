package com.model;

public class Station {
	
	private String station_name="";
	private String latitude="";
	private String longitude="";
	private String station_id="";
	private SendPeople sendPeople;
	private Order order;
	private PickUp pickUp;
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public PickUp getPickUp() {
		return pickUp;
	}
	public void setPickUp(PickUp pickUp) {
		this.pickUp = pickUp;
	}
	public SendPeople getSendPeople() {
		return sendPeople;
	}
	public void setSendPeople(SendPeople sendPeople) {
		this.sendPeople = sendPeople;
	}
	public String getStation_name() {
		return station_name;
	}
	public void setStation_name(String station_name) {
		this.station_name = station_name;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getStation_id() {
		return station_id;
	}
	public void setStation_id(String station_id) {
		this.station_id = station_id;
	}

}
