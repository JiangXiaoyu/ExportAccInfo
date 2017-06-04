package com.yzj.sisbase.export.accinfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.yzj.sisbase.export.repository.ExcelUtility;
import com.yzj.sisbase.export.repository.QueryRepository;

public class ExportAccinfo {
	public static final String rootPath = System.getProperty("user.dir") + "/AccInfo";
	public String accIdList = "";
	public QueryRepository queryRepository = new QueryRepository();
	
//	public static void main(String[] arg){
//		ExportAccinfo exportAccinfo = new ExportAccinfo();
//		exportAccinfo.exportAccinfo("zuhe1");
//	}
	
	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public String exportAccinfo(String accno) {	
		String feedback = validateAccno(accno);
		String filename = rootPath + "/accInfo.xls";
		HSSFWorkbook workbook = new HSSFWorkbook();	
		
		if(feedback.isEmpty()) {		
			File folder = new File(rootPath);
			if(!folder.exists()){
				folder.mkdir();
			}									
			String table = "";
			String column = "";//Used for query
			String nextId = "";//Used for getting the param of next table
			List<Object> objectList = new ArrayList<Object>();
			String para = getAccnoPara(accno);
			
			List<String> parasList = Arrays.asList("SIS_AccAdm_AccInfo,accno,accId", "SIS_AccAdm_SealCard,accId,sid", "SIS_AccAdm_CardImage,sealcardsid,cardImageId", 
					"SIS_AccAdm_SealInfo,cardImageId,cardImageId", "SIS_AccAdm_SealCombine,accId,accId");
			for(String paras : parasList){
				String[] pstr =  paras.split(",");
				table = pstr[0];  
				column = pstr[1];							
				if(!para.isEmpty()){
					objectList = queryRepository.queryObject(table,column, para);				
					if(objectList.size()==0){
						if(!"SIS_AccAdm_SealCombine".equals(table)){
							feedback = "账户" + accno + ":" + pstr[0] + "表内容为空!\n导出数据请查看" + filename;
						} 
					} else {
						ExcelUtility.createExcel(objectList,workbook,table);						
					}	
				}
				para = getPara(objectList, table, pstr[2]);
			}	
			String errormessage = write(filename,workbook);
			feedback = errormessage.isEmpty() ? feedback : errormessage;
			if(feedback.isEmpty()){
				feedback = "账户" + accno + ",数据导出成功！请查看" + filename;
			}
		}		
		
		return feedback;
	}
	
	public String write(String filename, HSSFWorkbook workbook){
		String errormessage = "";
		FileOutputStream fileout = null;
		try {
			fileout = new FileOutputStream(filename);
			workbook.write(fileout);
		} catch (IOException e) {
			errormessage = e.getMessage();
			e.printStackTrace();
		} finally{
			try {
				if(fileout!=null){
					fileout.close();
			    }
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
		return errormessage;
	}
	
	
	/**
	 * 校验输入账号
	 * @param accno
	 * @return
	 */
	public String validateAccno(String accno){
		String errormessage = "";
		if(accno==null || accno.isEmpty()){
			errormessage = "请输入账号!";
		} else if(queryRepository.queryAccInfobyAccno(accno).size() == 0){
			errormessage = "账户" + accno + "不存在,请重新输入！";
		}
		
		return errormessage;
	}
	
	/**
	 * 输入账号，其主账号一起查出来
	 * @param accno
	 * @return
	 */
	public String getAccnoPara(String accno){
		String accnoList = "('" + accno + "',";
		String mainAccno = "";
		do{
			mainAccno = queryRepository.getMainaccnoByAccno(accno);
			if(!mainAccno.isEmpty()){
				accnoList += ("'" + mainAccno + "',");
			}
			accno = mainAccno;
		} while(mainAccno!=null && !mainAccno.isEmpty());
		
		accnoList = accnoList.substring(0, accnoList.length()-1) + ")";
		
		return accnoList;
	}

	/**
	 * 获取查询下个表的参数
	 * 
	 */
	public String getPara(List<Object> resultList, String table, String column){
		String para = "(";
		if("SIS_AccAdm_SealInfo".equals(table)){
			//循环到SIS_AccAdm_SealCombine时,传入accIdList
			para = accIdList;
		} else {
		for(Object result : resultList){
			Field[] fields = result.getClass().getDeclaredFields();
			for(Field field :fields){
				try {
					field.setAccessible(true);
					if(field.getName().equals(column) && field.get(result) != null){
						para += "'" + field.get(result).toString() + "',";
						break;
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		if(para.length()<2){
			para = "";
		} else {
			para = para.substring(0, para.length()-1) + ")";
		}
		}		
		if("SIS_AccAdm_AccInfo".equals(table)){
			//把accId存下来
			accIdList = para;
		}
		
		return para;
	}
	
}
