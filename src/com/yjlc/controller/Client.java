package com.yjlc.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.yjlc.dao.Dao;
import com.yjlc.entity.Data;
import com.yjlc.entity.Excel;
import com.yjlc.util.excel.ExcelUtil;
import com.yjlc.util.excel.ExcelUtil.CallBack;
import com.yjlc.util.excel.ExcelView;
import com.yjlc.util.excel.ExcelView.ExcelCallBack;

@Controller
@RequestMapping("/data")
public class Client {
	
	@Autowired
	private Dao dao;
	
	@RequestMapping("/query")
	public ModelAndView queryAll(MultipartFile file,ModelAndView model){	     
	     ExcelUtil<Excel> excelUtil = new ExcelUtil<Excel>(file,getphone(),"sheet1"); 	     
	     List<Excel> lists = excelUtil.getList();
	     
	     List<Excel> list2=new ArrayList<>();
	     for(int i=0;i<lists.size();i++){ 
	    	 //查询数据
	    	 List<Data> datas= dao.query(lists.get(i).getPhone());
	    	 lists.get(i).setData(datas);
	    	 list2.add(lists.get(i));
	     }
	     
	     model.addObject("data",list2);
	     
		//设置非逻辑视图
		ExcelCallBack callBack = getEmpCallBack();
		model.setView(new ExcelView("财富中心数据.xls", callBack));
		
		return model;
	    
	}
	
	//构建导入信息
	public CallBack<Excel> getphone(){				
		return new CallBack<Excel>(){
			@Override
			public Excel bindExcel(String[] values) {
				Excel excel=new Excel();								
			    excel.setPhone(values[0]);	
			    excel.setName(values[1]);
				return excel;
			}
			
		};
		
	}
	//构建导出信息
	
	public ExcelCallBack getEmpCallBack(){
		return new ExcelCallBack(){

			@Override
			public void bindExcel(Map<String, Object> model, Workbook workbook) {
				@SuppressWarnings("unchecked")
				List<Excel> lists= (List<Excel>) model.get("data");				
				//构建excel
				Sheet sheet = workbook.createSheet("12月4日");
				//创建行
				Row row= sheet.createRow(0);
				//创建列
				row.createCell(0).setCellValue("总投资");
				row.createCell(1).setCellValue("总注册");
				row.createCell(2).setCellValue("新增投资");
				row.createCell(3).setCellValue("12月4日新增投资");
				row.createCell(4).setCellValue("12月4日新增注册");
				row.createCell(5).setCellValue("电话号码");
				row.createCell(6).setCellValue("推荐人");
				
				for(int i=0;i<lists.size();i++){
					Row r= sheet.createRow(i+1);
					List<Data> data=lists.get(i).getData();
					if(data.get(0).getYjcount()==null||data.get(0).getYjcount()==0){
						r.createCell(0).setCellValue("0");
					}else{
						r.createCell(0).setCellValue(data.get(0).getYjcount());
					}
					
					if(data.get(1).getYjcount()==null||data.get(1).getYjcount()==0){
						r.createCell(1).setCellValue("0");
					}else{
						r.createCell(1).setCellValue(data.get(1).getYjcount());
					}
					
					if(data.get(2).getYjcount()==null||data.get(2).getYjcount()==0){
						r.createCell(2).setCellValue("0");
					}else{
						r.createCell(2).setCellValue(data.get(2).getYjcount());
					}
					
					if(data.get(3).getYjcount()==null||data.get(3).getYjcount()==0){
						r.createCell(3).setCellValue("0");
					}else{
						r.createCell(3).setCellValue(data.get(3).getYjcount());
					}
					
					if(data.get(4).getYjcount()==null){
						r.createCell(4).setCellValue("0");
					}else{
						r.createCell(4).setCellValue(data.get(4).getYjcount());
					}


					r.createCell(5).setCellValue(lists.get(i).getPhone());
					r.createCell(6).setCellValue(lists.get(i).getName());
					
				}
			}
			
			
			
		  };
		}
	
	

}
