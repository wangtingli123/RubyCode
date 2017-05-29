package com.model;

public class SendPeople {
	private String sendId;//工号
	private String sendName;
	private String sendTel;
	private String sendHomeAddr;
	private String sendPeoplePwd;
	private Station station;//这里理想化：一个取送人员仅绑定一个地址
	
	public SendPeople() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getSendId() {
		return sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	public String getSendTel() {
		return sendTel;
	}

	public void setSendTel(String sendTel) {
		this.sendTel = sendTel;
	}

	public String getSendHomeAddr() {
		return sendHomeAddr;
	}

	public void setSendHomeAddr(String sendHomeAddr) {
		this.sendHomeAddr = sendHomeAddr;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public String getSendPeoplePwd() {
		return sendPeoplePwd;
	}

	public void setSendPeoplePwd(String sendPeoplePwd) {
		this.sendPeoplePwd = sendPeoplePwd;
	}
	
	
}
