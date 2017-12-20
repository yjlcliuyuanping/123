package com.yjlc.dao;

import java.util.List;

import com.yjlc.entity.Data;

public interface Dao {
	
	public List<Data> query(String phone);
		

}
