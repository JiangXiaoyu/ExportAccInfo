package com.yzj.sisbase.export.repository;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class ExcelUtility {
	
	public final static String AccInfo = "AccInfo";
	public final static String SealCard = "SealCard";
	public final static String CardImage = "CardImage";
	public final static String SealInfo = "SealInfo";
	public final static String SealCombine = "SealCombine";

	public static void createExcel(List<Object> modelList, HSSFWorkbook workbook, String table){
		
		HSSFSheet sheet = null;
		if(QueryRepository.SIS_AccAdm_AccInfo.equals(table)){
			 sheet = workbook.createSheet(AccInfo);
		} else if(QueryRepository.SIS_AccAdm_SealCard.equals(table)){
			 sheet = workbook.createSheet(SealCard);
		} else if(QueryRepository.SIS_AccAdm_CardImage.equals(table)){
			 sheet = workbook.createSheet(CardImage);
		} else if(QueryRepository.SIS_AccAdm_SealInfo.equals(table)){
			 sheet = workbook.createSheet(SealInfo);
		} else if(QueryRepository.SIS_AccAdm_SealCombine.equals(table)){
			 sheet = workbook.createSheet(SealCombine);
		}
		
		setRowValue(sheet, modelList);				
	}
	
	public static void setRowValue(HSSFSheet sheet, List<Object> modelList){
		int lastrow = sheet.getLastRowNum();
		int column = 0;
		
		for(Object data : modelList){
		Field[] fields = data.getClass().getDeclaredFields();
		
		if(lastrow == 0){
			HSSFRow row = sheet.createRow((short)lastrow++);
			 column = 0;
			for(Field field : fields){
				row.createCell(column++).setCellValue(field.getName());
			}
		} 

		column = 0;
		HSSFRow row = sheet.createRow((short)lastrow++);
		for(Field field : fields){
			String fieldValue = null;			
			try {
				field.setAccessible(true);
				if(field.get(data) != null){
				 fieldValue = field.get(data).toString();
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			row.createCell(column++).setCellValue(fieldValue);
		}	
		}
	}	
	
	public static String isExcelFile(String fileName){
		String error = "";
		if(!fileName.endsWith(".xls")){
			if(fileName.endsWith(".xlsx")){
				error = "请选择有效的03版excel文件!" + "\n";
			} else {
				error = "请选择有效的excel文件!" + "\n";
			}
		}
		
		return error;
	}
}
