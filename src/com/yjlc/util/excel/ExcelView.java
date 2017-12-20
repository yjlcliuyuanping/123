package com.yjlc.util.excel;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

public class ExcelView extends AbstractXlsView{
	
	private String name;
	private ExcelCallBack callBack;
	
	

	/*model：表示数据模型
	 * workbook：表示一个Excel对象
	 */
	
	public ExcelView(String name, ExcelCallBack callBack) {
		super();
		this.name = name;
		this.callBack = callBack;
	}

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			Workbook workbook, 
			HttpServletRequest requerst,
			HttpServletResponse response) throws Exception {
		
		//处理标题 
		response.setHeader("Content-disposition", "attachment;fileName="+URLEncoder.encode(name, "utf-8"));
		callBack.bindExcel( model, workbook);
		
	}
	
	//设置Excel的回调接口
	public static interface ExcelCallBack {
		void bindExcel(Map<String, Object> model,Workbook workbook);
	}
	
	

}

  
