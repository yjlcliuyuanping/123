package com.yjlc.entity;

import java.util.List;

public class Excel {
	
	private String phone;
	
	private String name;
	
	private List<Data> data;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Excel [phone=" + phone + "]";
	}
	
	

}
