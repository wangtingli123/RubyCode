package com.model;

public class CleanStatus {
	private String cleanStatusId;
	private String cleanStatus;
	private Order order;
	public CleanStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCleanStatusId() {
		return cleanStatusId;
	}
	public void setCleanStatusId(String cleanStatusId) {
		this.cleanStatusId = cleanStatusId;
	}
	public String getCleanStatus() {
		return cleanStatus;
	}
	public void setCleanStatus(String cleanStatus) {
		this.cleanStatus = cleanStatus;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
}
