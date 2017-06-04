package com.yzj.sisbase.export.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.yzj.sisbase.export.accinfo.ExportAccinfo;
import com.yzj.sisbase.export.model.AccInfo;
import com.yzj.sisbase.export.model.CardImage;
import com.yzj.sisbase.export.model.SealCard;
import com.yzj.sisbase.export.model.SealCombine;
import com.yzj.sisbase.export.model.SealInfo;

public class InsertRepository {
	
	public static List<AccInfo> accInfoList = new ArrayList<AccInfo>();
	public static List<CardImage> cardImageList = new ArrayList<CardImage>();
	public static List<SealCard> sealCardList = new ArrayList<SealCard>();
	public static List<SealInfo> sealInfoList = new ArrayList<SealInfo>();
	public QueryRepository modelRepository = new QueryRepository();
	
	public String checkFrimaryKey(List<AccInfo> accInfoList, List<SealCard> sealCardList, 
			List<CardImage> cardImageList, List<SealInfo> sealInfoList){
		String errormessage = "";
		String paraAccInfo = "";
		String paraSealCard = "";
		String paraCardImage = "";
		String paraSealInfo = "";
		
		for(AccInfo accInfo : accInfoList){
			if(!"".equals(accInfo.getAccId()) && accInfo.getAccId() != null){
				paraAccInfo += ("'" + accInfo.getAccId() + "',");
			}
		}
		if(!paraAccInfo.isEmpty()){
			paraAccInfo = "(" + paraAccInfo.substring(0, paraAccInfo.length() - 1) + ")";
		}
		
		for(SealCard sealCard : sealCardList){
			if(!"".equals(sealCard.getSid()) && sealCard.getSid() != null){
				paraSealCard += ("'" + sealCard.getSid() + "',");
			}
		}
		if(!paraSealCard.isEmpty()){
			paraSealCard = "(" + paraSealCard.substring(0, paraSealCard.length() - 1) + ")";
		}
		
		for(CardImage cardImage : cardImageList){
			if(!"".equals(cardImage.getCardImageId()) && cardImage.getCardImageId() != null){
				paraCardImage += ("'" + cardImage.getCardImageId() + "',");
			}
		}
		if(!paraCardImage.isEmpty()){
			paraCardImage = "(" + paraCardImage.substring(0, paraCardImage.length() - 1) + ")";
		}
		
		for(SealInfo sealInfo : sealInfoList){
			if(!"".equals(sealInfo.getSealId()) && sealInfo.getSealId() != null){
				paraSealInfo += ("'" + sealInfo.getSealId() + "',");
			}
		}
		if(!paraSealInfo.isEmpty()){
			paraSealInfo = "(" + paraSealInfo.substring(0, paraSealInfo.length() - 1) + ")";
		}

		String conflictKey = "";
		List<Object> modelList = new ArrayList<Object>();
		String column = "SIS_AccAdm_AccInfo.accId";
		String accnoList = "";
		
		modelList = modelRepository.queryObject("SIS_AccAdm_AccInfo", "accId", paraAccInfo);
		if(modelList.size() > 0){
			accnoList = getAccNo(modelList);
		}else if(modelList.size() == 0){ 
			modelList = modelRepository.queryObject("SIS_AccAdm_SealCard", "sid", paraSealCard);
			column = "SIS_AccAdm_SealCard.sid";
			accnoList = modelRepository.getAccNoBysid(paraSealCard);
		} else if(modelList.size() == 0){
			modelList = modelRepository.queryObject("SIS_AccAdm_CardImage", "cardImageId", paraCardImage);
			column = "SIS_AccAdm_CardImage.cardImageId";
			accnoList = QueryRepository.getAccNoByCardImageId(paraCardImage);
		} else if(modelList.size() == 0){
			modelList = modelRepository.queryObject("SIS_AccAdm_SealInfo", "sealId", paraSealInfo);
			column = "SIS_AccAdm_SealInfo.sealId";
			accnoList = QueryRepository.getAccNoBySealId(paraSealInfo);
		}
		
		conflictKey = getKey(modelList);		
		if(modelList.size() > 0){
			errormessage = "主键冲突: " + column + "(" + conflictKey + ").\n" + "相关联AccNo为:" + accnoList;
		}
		
		return errormessage;
	}
	
	/**
	 * 根据accinfo List ,查询想关联的accno
	 * @return
	 */
	public static String getAccNo(List<Object> modelList){
		String accnoList = ""; 
		
		if(modelList.size() > 0){
		for(Object model : modelList){
			if(model instanceof AccInfo){
				accnoList += ((AccInfo) model).getAccno() + ",";
			}
		}
		accnoList = accnoList.substring(0, accnoList.length() - 1);
		}
		return accnoList;
	}
	
	public static String getKey(List<Object> modelList){
		String key = "";
		if(modelList.size() > 0){
			for(Object model : modelList){
				if(model instanceof AccInfo){
					key += ((AccInfo) model).getAccId() + ",";
				} else if(model instanceof SealCard){
					key += ((SealCard) model).getSid() + ",";
				} else if (model instanceof CardImage){
					key += ((CardImage) model).getCardImageId() + ",";
				} else if (model instanceof SealInfo){
					key += ((SealInfo) model).getSealId() + ",";
				}
			}
			key = key.substring(0, key.length()-1);
		}
		
		return key;
	}

	public String insertTable(List<AccInfo> accInfoList, List<CardImage> cardImageList, List<SealCard> sealCardList,
			List<SealInfo> sealInfoList, List<SealCombine> sealCombineList) {
	
		String errormessage = "";
		Connection conn = null;
		PreparedStatement ps = null;	
		
		/*写表之前，检测主键冲突 */		
		errormessage = checkFrimaryKey(accInfoList, sealCardList, cardImageList, sealInfoList);
		
		if(errormessage.isEmpty()){
			String sqlInsert = "";
		try {
			conn = ConnectionPool.getConnection();		
			conn.setAutoCommit(false);
			sqlInsert = "INSERT INTO SIS_AccAdm_AccInfo (AccID, MainAccID, CustomerID, " +
					"Accno, AccName, OpenDate, StartDate, EndDate, CreateDate, " +
					"CurrencyType, PointNo, PointName, LinkMan, Address, Code, Telephone, " +
					"IfCombine, WithDrawFlag, Extend, LastChangeDate, InputOp, CheckOp, " +
					"LastMainAccID, AccFlag, AttentionFlag, AccType, AccCategory, " +
					"IfFantasy, OfficeAddress, Mobilephone, LinkMan2, Mobilephone2, " +
					"Telephone2, Address2, Memo, SleepFlag, lastPointNo, migratePeople, " +
					"migrateAuthPeople, migrateDate) VALUES (?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?)";	
			ps = conn.prepareStatement(sqlInsert);
			for(AccInfo accInfo :accInfoList){					
				ps.setString(1, accInfo.getAccId());				
				String mainaccId = accInfo.getMainAccId();
				if(mainaccId==null || mainaccId.isEmpty()){
					ps.setNull(2, Types.NULL);
				} else {
				   ps.setString(2, accInfo.getMainAccId());
				}
				String customerID = accInfo.getCustomerId();
				if(customerID==null || customerID.isEmpty()){
					ps.setNull(3, Types.NULL);
				} else {
					ps.setString(3, accInfo.getCustomerId());
				}				
				ps.setString(4, accInfo.getAccno());
				ps.setString(5, accInfo.getAccName());
				ps.setString(6, accInfo.getOpenDate());
				ps.setString(7, accInfo.getStartDate());
				ps.setString(8, accInfo.getEndDate());
				ps.setString(9, accInfo.getCreateDate());
				ps.setInt(10, accInfo.getCurrencyType());
				ps.setString(11, accInfo.getPointNo());
				ps.setString(12, accInfo.getPointName());
				ps.setString(13, accInfo.getLinkMan());
				ps.setString(14, accInfo.getAddress());
				ps.setString(15, accInfo.getCode());
				ps.setString(16, accInfo.getTelephone());
				ps.setShort(17, accInfo.getIfCombine());
				ps.setInt(18, accInfo.getWithDrawFlag());
				ps.setString(19, accInfo.getExtend());
				ps.setString(20, accInfo.getLastChangeDate());
				ps.setString(21, accInfo.getInputOp());
				ps.setString(22, accInfo.getCheckOp());
				ps.setString(23, accInfo.getLastMainAccId());
				ps.setShort(24, accInfo.getAccFlag());
				ps.setShort(25, accInfo.getAttentionFlag());
				ps.setInt(26, accInfo.getAccType());
				ps.setInt(27, accInfo.getAccCategory());
				ps.setShort(28, accInfo.getIfFantasy());
				ps.setString(29, accInfo.getOfficeAddress());
				ps.setString(30, accInfo.getMobilephone());
				ps.setString(31, accInfo.getLinkMan2());
				ps.setString(32, accInfo.getMobilephone2());
				ps.setString(33, accInfo.getTelephone2());
				ps.setString(34, accInfo.getAddress2());
				ps.setString(35, accInfo.getMemo());
				ps.setInt(36, accInfo.getSleepFlag());
				ps.setString(37, accInfo.getLastPointNo());
				ps.setString(38, accInfo.getMigratePeople());
				ps.setString(39, accInfo.getMigrateAuthPeople());
				ps.setString(40, accInfo.getMigrateDate());
				ps.addBatch();
			}
			ps.executeBatch();
			
			sqlInsert = "INSERT INTO SIS_AccAdm_SealCard (SID, CardID, AccID, SealCount, StartDate, EndDate, Usage, MemoInfo) VALUES (?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sqlInsert);
			for(SealCard sealCard : sealCardList){				
				ps.setString(1, sealCard.getSid());
				ps.setString(2, sealCard.getCardId());
				ps.setString(3, sealCard.getAccId());
				ps.setInt(4, sealCard.getSealCount());
				ps.setString(5, sealCard.getStartDate());
				ps.setString(6, sealCard.getEndDate());
				ps.setString(7, sealCard.getUsage());
				ps.setString(8, sealCard.getMemoInfo());
				ps.addBatch();
			}
			ps.executeBatch();
			
			sqlInsert = "INSERT INTO SIS_AccAdm_CardImage (CardImageID, SealCardSid, SealCard, ImageID, ImageFlag) VALUES (?,?,?,?,?)";
			ps = conn.prepareStatement(sqlInsert);
			for(CardImage cardImage : cardImageList){				 
				 ps.setString(1, cardImage.getCardImageId());
				 ps.setString(2, cardImage.getSealCardSid());
				 ps.setString(3, cardImage.getSealCard());
				 ps.setString(4, cardImage.getImageId());
				 ps.setShort(5, cardImage.getImageFlag());
				 ps.addBatch();
			}
			ps.executeBatch();		
						
			
			List<InputStream> streamList = new ArrayList<InputStream>();
			sqlInsert = "INSERT INTO SIS_AccAdm_SealInfo (SealID, CardImageID, Seal, Height, Width, LitterInfo, " +
					"LitterSeal, LitterFeature, SealType, SealColor, StartDate, EndDate, NoUseDate, SealMemo, " +
					"sealCoord, LossRegister, CardID, QualityLevel, SealForm, Scale, SealLossMan, SealLossReason, " +
					"SealLossTime, SealLossRemarks, SealUnlossMan, SealUnlossReason, SealUnlossTime, " +
					"SealUnlossRemarks) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sqlInsert);

			for(SealInfo sealInfo : sealInfoList){				
				InputStream inputStream = null;
				String filePath = ExportAccinfo.rootPath + "/" + sealInfo.getSealId() + ".txt";
				File file = new File(filePath);				
				if(!file.exists()){					
					errormessage = "找不到印鉴文件" + sealInfo.getSealId() + "！请将印鉴文件和excel文件放于同一目录下!";
					conn.rollback();
					break;
				} else {
					inputStream = new FileInputStream(file);
					streamList.add(inputStream);
				}				
				ps.setString(1, sealInfo.getSealId());
				ps.setString(2, sealInfo.getCardImageId());
				ps.setBlob(3, inputStream);
				ps.setInt(4, sealInfo.getHeight());
				ps.setInt(5, sealInfo.getWidth());
				ps.setString(6, sealInfo.getLitterInfo());
				ps.setNull(7, Types.NULL);
				ps.setNull(8, Types.NULL);
				ps.setInt(9, sealInfo.getSealType());
				ps.setInt(10, sealInfo.getSealColor());
				
				ps.setString(11, sealInfo.getStartDate());
				ps.setString(12, sealInfo.getEndDate());
				ps.setString(13, sealInfo.getNoUseDate());
				ps.setString(14, sealInfo.getSealMemo());
				ps.setString(15, sealInfo.getSealCoord());
				ps.setShort(16, sealInfo.getLossRegister());
				ps.setString(17, sealInfo.getCardId());
				ps.setShort(18, sealInfo.getQualityLevel());
				ps.setInt(19, sealInfo.getSealForm());
				ps.setFloat(20, sealInfo.getScale());
				
				ps.setString(21, sealInfo.getSealLossMan());
				ps.setString(22, sealInfo.getSealLossReason());
				ps.setString(23, sealInfo.getSealLossTime());
				ps.setString(24, sealInfo.getSealLossRemarks());
				ps.setString(25, sealInfo.getSealUnlossMan());
				ps.setString(26, sealInfo.getSealUnlossReason());
				ps.setString(27, sealInfo.getSealUnlossTime());
				ps.setString(28, sealInfo.getSealUnlossRemarks());
				ps.addBatch();
				
			}
			ps.executeBatch();
			closeInputstream(streamList);
			
			
			sqlInsert = "INSERT INTO SIS_AccAdm_SealCombine (CombineID, AccID, Condition, Seal, StartDate, EndDate, ShowName, CombineMemo) VALUES (?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sqlInsert);
			for(SealCombine sealCombine : sealCombineList){				 
				 ps.setString(1, sealCombine.getCombineId());
				 ps.setString(2, sealCombine.getAccId());
				 ps.setString(3, sealCombine.getCondition());
				 ps.setString(4, sealCombine.getSeal());
				 ps.setString(5, sealCombine.getStartDate());
				 ps.setString(6, sealCombine.getEndDate());
				 ps.setString(7, sealCombine.getShowName());
				 ps.setString(8, sealCombine.getCombineMemo());
				 ps.addBatch();
			}
			ps.executeBatch();			
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			errormessage = e.getMessage();
			e.printStackTrace();
		} finally{	
			try {				
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ConnectionPool.close(conn,ps,null);
		}
		}
		
		return errormessage;
	}
	
	public void closeInputstream(List<InputStream> streamList) {
		for(InputStream stream : streamList){
			if(stream != null){
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}