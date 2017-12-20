package com.yjlc.entity;

public class Data {
	
	private String invName;
	
	private Integer yjcount;

	public String getInvName() {
		return invName;
	}

	public void setInvName(String invName) {
		this.invName = invName;
	}

	public Integer getYjcount() {
		return yjcount;
	}

	public void setYjcount(Integer yjcount) {
		this.yjcount = yjcount;
	}

	@Override
	public String toString() {
		return "Data [invName=" + invName + ", yjcount=" + yjcount + "]";
	}
	
	
		
}
