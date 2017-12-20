package com.yjlc.util.excel;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.multipart.MultipartFile;



public class ExcelUtil<T> {
	
	private MultipartFile empExcel;
	private CallBack<T> callBack;
	private String sheetName;
		
	public ExcelUtil(MultipartFile empExcel, CallBack<T> callBack,String sheetName) {
		super();
		this.empExcel = empExcel;
		this.callBack = callBack;
		this.sheetName=sheetName;
	}
	
	public List<T> getList(){		
		HSSFWorkbook workbook;
		try {
			workbook = new HSSFWorkbook(empExcel.getInputStream());
			HSSFSheet sheet = workbook.getSheet(sheetName);
			List<T> list = new ArrayList<>();
			// 总行数
			int sows = sheet.getPhysicalNumberOfRows();
			for (int i = 0; i < sows; i++) {
				// 获得行
				Row row = sheet.getRow(i + 1);
				if (row != null) {
					// 获得总列数
					int cells = row.getPhysicalNumberOfCells();
					String value = "";
					for (int j = 0; j < cells; j++) {
						// 获得列
						Cell cell = row.getCell(j);
						if (cell != null) {
							int type = cell.getCellType();
							switch (type) {
							// 数字类型
							case HSSFCell.CELL_TYPE_NUMERIC:
								value += new DecimalFormat("0").format(cell.getNumericCellValue()) + ",";
								break;
							// String 类型
							case HSSFCell.CELL_TYPE_STRING:
								value += cell.getStringCellValue() + ",";
								break;

							}

						}
					}
					String[] values = value.split(",");
					T t= callBack.bindExcel(values);
					list.add(t);
				}
				
			}
			return list;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}








	public static interface CallBack<T>{
		T bindExcel(String[] values);
	};

}
