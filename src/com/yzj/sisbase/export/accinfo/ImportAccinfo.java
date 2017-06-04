package com.yzj.sisbase.export.accinfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import com.yzj.sisbase.export.model.AccInfo;
import com.yzj.sisbase.export.model.CardImage;
import com.yzj.sisbase.export.model.SealCard;
import com.yzj.sisbase.export.model.SealCombine;
import com.yzj.sisbase.export.model.SealInfo;
import com.yzj.sisbase.export.repository.ExcelUtility;
import com.yzj.sisbase.export.repository.QueryRepository;
import com.yzj.sisbase.export.repository.ValidateRepository;
import com.yzj.sisbase.export.repository.InsertRepository;

public class ImportAccinfo {

	List<List<String>> excelFile = new ArrayList<List<String>>();
	public List<AccInfo> accInfoList = new ArrayList<AccInfo>();
	public List<CardImage> cardImageList = new ArrayList<CardImage>();
	public List<SealCard> sealCardList = new ArrayList<SealCard>();
	public List<SealInfo> sealInfoList = new ArrayList<SealInfo>();
	public List<SealCombine> sealCombineList = new ArrayList<SealCombine>();
	
	public ImportAccinfo(){
		
	}
//	public static void main(String[] arg){
//		String pathFile = "E:/WTools/MyEclipse Blue Edition/workspace7/ExportAccInfo/AccInfo/accInfo.xls";
//		ImportAccinfo importAccinfo = new ImportAccinfo();
//		importAccinfo.importAccinfo(pathFile);
//	}
	
	public String importAccinfo(String pathFile) {
		String errormessage = "";
		String[] sheetStr = new String[]{"AccInfo","SealCard","CardImage","SealInfo","SealCombine"};		
		
		File file = new File(pathFile);
		try {
			InputStream input = new FileInputStream(file);
			HSSFWorkbook workbook = new HSSFWorkbook(input);			
			for(String sheetName : sheetStr){
				HSSFSheet sheet = workbook.getSheet(sheetName);
				if(sheet != null && sheet.getLastRowNum() > 0){
				Iterator<Row> iter = sheet.iterator();
				while(iter.hasNext()){
					Row row = iter.next();
					if(row.getRowNum() == 0){
						 validHeaderRow(sheetName, row);
					} else {
						parse(sheetName, row);
					}		
				}
			  } 
//				else {
//				  errormessage = "上传文件" + sheetName + " sheet为空!";
//			  }
			}			
			//写表
			if(errormessage.isEmpty()){
				InsertRepository insertTool = new InsertRepository();
				//resort accInfoList,put the acc first which is not other's mainaccno
				accInfoList = resort(accInfoList);
				errormessage = insertTool.insertTable(accInfoList, cardImageList, sealCardList, sealInfoList, sealCombineList);
			}
		} catch (Exception e) {
			errormessage = e.getMessage();
		} finally {
			System.out.println(errormessage);
			if(errormessage.isEmpty()){
				errormessage = "账户文件导入成功!";
			}
		}
		return errormessage;
	}
	
	public List<AccInfo> resort(List<AccInfo> accInfoList) throws Exception{
		List<AccInfo> finalList = new ArrayList<AccInfo>();
		List<AccInfo> blank = new ArrayList<AccInfo>();
		List<AccInfo> notBlank = new ArrayList<AccInfo>();
		
		for(AccInfo accInfo : accInfoList){
			//查询数据库，是否存在相同名称账号
			QueryRepository query = new QueryRepository();
			String accno = accInfo.getAccno();
			if(query.queryAccInfobyAccno(accno).size() > 0){
				throw new Exception("账户导入失败,数据库已存在账户" + accno + "!");
			}
			if(accInfo.getMainAccId() == null || "".equals(accInfo.getMainAccId())){
				blank.add(accInfo);
			} else {
				notBlank.add(accInfo);
			}
		}
		finalList.addAll(blank);
		finalList.addAll(notBlank);
		
		return finalList;
	}
	
	public static String getCellValue(Cell cell) {  
        String value = null;
        if(cell != null){
        switch (cell.getCellType()) {  
        case Cell.CELL_TYPE_BLANK:  
            value = "";  
            break;  
        case Cell.CELL_TYPE_BOOLEAN:  
            value = String.valueOf(cell.getBooleanCellValue());  
            break;  
        case Cell.CELL_TYPE_ERROR:  
            value = null;  
            break;  
        case Cell.CELL_TYPE_FORMULA:  
            Workbook wb = cell.getSheet().getWorkbook();  
            CreationHelper crateHelper = wb.getCreationHelper();  
            FormulaEvaluator evaluator = crateHelper.createFormulaEvaluator();  
            value = getCellValue(evaluator.evaluateInCell(cell));  
            break;  
        case Cell.CELL_TYPE_STRING:  
            value = cell.getRichStringCellValue().getString();  
            break;  
        default:  
            value = null;  
        }  
        } 
        return value;  
    }  
	
	public void validHeaderRow(String sheetName,Row row) throws Exception{
		String errormessage = "";		
		List<String> FIELD_ALL= new ArrayList<String>();
		ValidateRepository acc = new ValidateRepository();
		if(ExcelUtility.AccInfo.equals(sheetName)){
			FIELD_ALL = acc.FIELDS_ACCINFO_ALL;
		} else if(ExcelUtility.CardImage.equals(sheetName)){
			FIELD_ALL = acc.FIELDS_CARDIMAGE_ALL;
		} else if(ExcelUtility.SealCard.equals(sheetName)){
			FIELD_ALL = acc.FIELD_SEALCARD_ALL;
		} else if(ExcelUtility.SealInfo.equals(sheetName)){
			FIELD_ALL = acc.FIELD_SEALINFO_ALL;
		} else if(ExcelUtility.SealCombine.equals(sheetName)){
			FIELD_ALL = acc.FIELD_SEALCOMBINE_ALL;
		}		
		
		for(Cell cell : row){
			int cellColumn = cell.getColumnIndex();		
				String fieldname = FIELD_ALL.get(cellColumn);
						if(fieldname != null && !fieldname.equalsIgnoreCase(cell.toString())){
							cellColumn++;
							errormessage = "第" + cellColumn + "列" + ",字段名称 " + cell.toString() + " 应为" + fieldname;
							break;
						}				
		}
		
		if(!errormessage.isEmpty()){
			throw new Exception(errormessage);
		}
	}
	
	public void parse(String sheetName, Row row){
		if(ExcelUtility.AccInfo.equals(sheetName)){
			  String accId = getCellValue(row.getCell(0));
			  String mainAccId = getCellValue(row.getCell(1)); 
			  String customerId = getCellValue(row.getCell(2)); 
			  String accno = getCellValue(row.getCell(3)); 
			  String accName = getCellValue(row.getCell(4)); 
			  String openDate = getCellValue(row.getCell(5)); 
			  String startDate = getCellValue(row.getCell(6)); 
			  String endDate = getCellValue(row.getCell(7)); 
			  String createDate = getCellValue(row.getCell(8)); 
			  int currencyType = Integer.parseInt(getCellValue(row.getCell(9))); 
			  String pointNo = getCellValue(row.getCell(10)); 
			  String pointName = getCellValue(row.getCell(11));
			  String linkMan = getCellValue(row.getCell(12)); 
			  String address = getCellValue(row.getCell(13));
			  String code = getCellValue(row.getCell(14)); 
			  String telephone = getCellValue(row.getCell(15));
			  short ifCombine = Short.parseShort(getCellValue(row.getCell(16))); 
			  int withDrawFlag = Integer.parseInt(getCellValue(row.getCell(17))); 
			  String extend = getCellValue(row.getCell(18));
			  String lastChangeDate = getCellValue(row.getCell(19)); 
			  String inputOp = getCellValue(row.getCell(20)); 
			  String checkOp = getCellValue(row.getCell(21)); 
			  String lastMainAccId = getCellValue(row.getCell(22)); 
			  short accFlag = Short.parseShort(getCellValue(row.getCell(23))); 
			  short attentionFlag = Short.parseShort(getCellValue(row.getCell(24))); 
			  int accType = Integer.parseInt(getCellValue(row.getCell(25))); 
			  int accCategory = Integer.parseInt(getCellValue(row.getCell(26))); 
			  short ifFantasy = Short.parseShort(getCellValue(row.getCell(27))); 
			  String officeAddress = getCellValue(row.getCell(28)); 
			  String mobilephone = getCellValue(row.getCell(29)); 
			  String linkMan2 = getCellValue(row.getCell(30)); 
			  String mobilephone2 = getCellValue(row.getCell(31)); 
			  String telephone2 = getCellValue(row.getCell(32));
			  String address2 = getCellValue(row.getCell(33)); 
			  String memo = getCellValue(row.getCell(34)); 
			  int sleepFlag = Integer.parseInt(getCellValue(row.getCell(35)));
			  String lastPointNo = getCellValue(row.getCell(36));
			  String migratePeople = getCellValue(row.getCell(37));
			  String migrateAuthPeople = getCellValue(row.getCell(38));
			  String migrateDate = getCellValue(row.getCell(39));
			
			AccInfo accinfo = new AccInfo(accId, mainAccId, customerId,
					accno, accName, openDate, startDate,
					endDate, createDate, currencyType,
					pointNo, pointName, linkMan, address,
					code, telephone, ifCombine, withDrawFlag,
					extend, lastChangeDate, inputOp,
					checkOp, lastMainAccId, accFlag,
					attentionFlag, accType, accCategory, ifFantasy,
					officeAddress, mobilephone, linkMan2,
					mobilephone2, telephone2, address2,
					memo, sleepFlag, lastPointNo,
					migratePeople, migrateAuthPeople, migrateDate);
			accInfoList.add(accinfo);
		} else if(ExcelUtility.CardImage.equals(sheetName)){
			String cardImageId = getCellValue(row.getCell(0));
			String sealCardSid = getCellValue(row.getCell(1)); 
			String sealCard = getCellValue(row.getCell(2));
			String imageId = getCellValue(row.getCell(3));
			short imageFlag = Short.parseShort(getCellValue(row.getCell(4)));
			CardImage cardImage = new CardImage(cardImageId, sealCardSid, sealCard, imageId, imageFlag);
			cardImageList.add(cardImage);
		} else if(ExcelUtility.SealCard.equals(sheetName)){
			String sid = getCellValue(row.getCell(0));
			String cardId = getCellValue(row.getCell(1));
			String accId = getCellValue(row.getCell(2));
			int sealCount = Integer.parseInt(getCellValue(row.getCell(3)));
			String startDate = getCellValue(row.getCell(4));
			String endDate = getCellValue(row.getCell(5));
			String usage = getCellValue(row.getCell(6));
			String memoInfo = getCellValue(row.getCell(7));
			
			SealCard sealCard = new SealCard(sid, cardId, accId, sealCount, startDate, endDate, usage, memoInfo);
			sealCardList.add(sealCard);
		} else if(ExcelUtility.SealInfo.equals(sheetName)){
			String sealId = getCellValue(row.getCell(0));
			String cardImageId = getCellValue(row.getCell(1));
			String seal = getCellValue(row.getCell(2));
			int height = Integer.parseInt(getCellValue(row.getCell(3)));
			int width = Integer.parseInt(getCellValue(row.getCell(4))); 
			String litterInfo = getCellValue(row.getCell(5));
			String litterSeal = getCellValue(row.getCell(6));
			String litterFeature = getCellValue(row.getCell(7)); 
			int sealType = Integer.parseInt(getCellValue(row.getCell(8)));
			int sealColor = Integer.parseInt(getCellValue(row.getCell(9)));
			String startDate = getCellValue(row.getCell(10));
			String endDate = getCellValue(row.getCell(11));
			String noUseDate = getCellValue(row.getCell(12));
			String sealMemo = getCellValue(row.getCell(13));
			String sealCoord = getCellValue(row.getCell(14));
			short lossRegister = Short.parseShort(getCellValue(row.getCell(15)));
			String cardId = getCellValue(row.getCell(16));
			short qualityLevel = Short.parseShort(getCellValue(row.getCell(17)));
			int sealForm = Integer.parseInt(getCellValue(row.getCell(18)));
			float scale = Float.parseFloat(getCellValue(row.getCell(19)));
			String sealLossMan = getCellValue(row.getCell(20));
			String sealLossReason = getCellValue(row.getCell(21));
			String sealLossTime = getCellValue(row.getCell(22));
			String sealLossRemarks = getCellValue(row.getCell(23));
			String sealUnlossMan = getCellValue(row.getCell(24));
			String sealUnlossReason = getCellValue(row.getCell(25));
			String sealUnlossTime = getCellValue(row.getCell(26));
			String sealUnlossRemarks = getCellValue(row.getCell(27));
			
			SealInfo sealInfo = new SealInfo(sealId, cardImageId, seal, height,
					width, litterInfo, litterSeal,
					litterFeature, sealType, sealColor,
					startDate, endDate, noUseDate,
					sealMemo, sealCoord, lossRegister,
					cardId, qualityLevel, sealForm, scale,
					sealLossMan, sealLossReason, sealLossTime,
					sealLossRemarks, sealUnlossMan,
					sealUnlossReason, sealUnlossTime,
					sealUnlossRemarks);
			sealInfoList.add(sealInfo);
		} else if(ExcelUtility.SealCombine.equals(sheetName)){			
			String combineId = getCellValue(row.getCell(0));
			String accId = getCellValue(row.getCell(1));
			String condition = getCellValue(row.getCell(2));
			String seal = getCellValue(row.getCell(3));
			String startDate = getCellValue(row.getCell(4));
			String endDate = getCellValue(row.getCell(5));
			String showName = getCellValue(row.getCell(6));
			String combineMemo = getCellValue(row.getCell(6));
			
			SealCombine sealCombine = new SealCombine(combineId, accId, condition, seal, startDate, endDate, showName, combineMemo);
			sealCombineList.add(sealCombine);
		} 			
	}
	
}
