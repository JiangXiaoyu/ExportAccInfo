package com.yzj.sisbase.export.repository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yzj.sisbase.export.accinfo.ExportAccinfo;
import com.yzj.sisbase.export.model.AccInfo;
import com.yzj.sisbase.export.model.CardImage;
import com.yzj.sisbase.export.model.SealCard;
import com.yzj.sisbase.export.model.SealCombine;
import com.yzj.sisbase.export.model.SealInfo;

public class QueryRepository {
	
	public final static String SIS_AccAdm_AccInfo   = "SIS_AccAdm_AccInfo";
	public final static String SIS_AccAdm_SealCard  = "SIS_AccAdm_SealCard";
	public final static String SIS_AccAdm_CardImage = "SIS_AccAdm_CardImage";
	public final static String SIS_AccAdm_SealInfo  = "SIS_AccAdm_SealInfo";
	public final static String SIS_AccAdm_SealCombine  = "SIS_AccAdm_SealCombine";
	
	public List<Object> queryObject(String table, String column, String values) {
		List<Object> accInfoList = new ArrayList<Object>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		if(!"()".equals(values)){
		try {
			conn = ConnectionPool.getConnection();
			ps = conn.prepareStatement("select * from " + table + " where " + column + " in " + values);
			res = ps.executeQuery();
			if(SIS_AccAdm_AccInfo.equals(table)){
				while (res.next()) {
					accInfoList.add(parseAccInfo(res));
				}
		   } else if(SIS_AccAdm_SealCard.equals(table)){
			   while (res.next()) {
					accInfoList.add(parseSealCard(res));
				}
		   } else if(SIS_AccAdm_CardImage.equals(table)){
			   while (res.next()) {
					accInfoList.add(parseCardImage(res));
				}
		   } else if(SIS_AccAdm_SealInfo.equals(table)){
			   while (res.next()) {
					accInfoList.add(parseSealInfo(res));
				}
		   } else if(SIS_AccAdm_SealCombine.equals(table)){
			   while(res.next()){
				   accInfoList.add(parseSealCombine(res));
			   }
		   }
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			ConnectionPool.close(conn,ps,res);
		}
		}

		return accInfoList;
	}
	
	public String getAccNoBysid(String sidList){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		String accnoList = "";
		try {
			conn = ConnectionPool.getConnection();
			ps = conn.prepareStatement("select a.accno from SIS_AccAdm_AccInfo a,SIS_AccAdm_SealCard b where a.accId = b.accId and b.sid in" + sidList);
			res = ps.executeQuery();
			while(res.next()){
				String accno = res.getString("accno");
				if(accnoList.indexOf(accno) > -1){
					accnoList += res.getString("accno");
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally{
			ConnectionPool.close(conn,ps,res);
		}
		return accnoList;
	}
	
	public static String getAccNoByCardImageId(String cardImageIdList){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		String accnoList = "";
		try {
			conn = ConnectionPool.getConnection();
			ps = conn.prepareStatement("select a.accno from SIS_AccAdm_AccInfo a,SIS_AccAdm_SealCard b,SIS_AccAdm_CardImage c where a.accId=b.accId and b.sid=c.sealcardsid and c.cardImageId in "+ cardImageIdList);
			res = ps.executeQuery();
			while(res.next()){
				String accno = res.getString("accno");
				if(accnoList.indexOf(accno) > -1){
					accnoList += res.getString("accno");
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally{
			ConnectionPool.close(conn,ps,res);
		}
		return accnoList;
	}
	
	public static String getAccNoBySealId(String sealIdList){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		String accnoList = "";
		try {
			conn = ConnectionPool.getConnection();
			ps = conn.prepareStatement("select a.accno from SIS_AccAdm_AccInfo a,SIS_AccAdm_SealCard b,SIS_AccAdm_CardImage c,SIS_AccAdm_SealInfo d where a.accId=b.accId and b.sid=c.sealcardsid and c.cardImageId=d.cardImageId and d.sealId in "+ sealIdList);
			res = ps.executeQuery();
			while(res.next()){
				String accno = res.getString("accno");
				if(accnoList.indexOf(accno) > -1){
					accnoList += res.getString("accno");
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally{
			ConnectionPool.close(conn,ps,res);
		}
		return accnoList;
	}
	
	public List<AccInfo> queryAccInfobyAccno(String accno){
		List<AccInfo> accInfoList = new ArrayList<AccInfo>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		try{
			conn = ConnectionPool.getConnection();
			ps = conn.prepareStatement("select * from SIS_AccAdm_AccInfo where accno=?");
			ps.setString(1, accno);
			res = ps.executeQuery();
			while(res.next()){
				AccInfo accInfo = new AccInfo(accno);
				accInfoList.add(accInfo);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			ConnectionPool.close(conn,ps,res);
		}
		
		return accInfoList;
	}
	
	public String getMainaccnoByAccno(String accno){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		String mainAccno = "";
		try {
			conn = ConnectionPool.getConnection();
			ps = conn.prepareStatement("select accno from SIS_AccAdm_AccInfo where accid in(select mainaccid from SIS_AccAdm_AccInfo b where b.accno =?)");
			ps.setString(1, accno);
			res = ps.executeQuery();
			if(res.next()){
				mainAccno = res.getString("accno");
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally{
			ConnectionPool.close(conn,ps,res);
		}
		return mainAccno;
	}
	
	
	public String getAccnolistBymainaccno(String mainAccno){
		String accnoList = "";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		try {
			String sql = "select a.accno from SIS_AccAdm_AccInfo a,SIS_AccAdm_AccInfo b where a.mainaccid = b.accid and b.accno =?";
			conn = ConnectionPool.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, mainAccno);
			res = ps.executeQuery();
			while (res.next()) {
				accnoList += res.getString("accno") + ",";
			}
			if(!accnoList.isEmpty()){
				accnoList = accnoList.substring(0, accnoList.length()-1) + ".";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(conn, ps, res);
		}
		return accnoList;
	}
			
	public static AccInfo parseAccInfo(ResultSet res) {
		AccInfo accInfo = null;
		try {
			accInfo = new AccInfo(res.getString("accId"),
					res.getString("mainAccId"), res.getString("customerId"),
					res.getString("accno"), res.getString("accName"),
					res.getString("openDate"), res.getString("startDate"),res.getString("endDate"), 
					res.getString("createDate"), res.getInt("currencyType"),res.getString("pointNo"), 
					res.getString("pointName"), res.getString("linkMan"),res.getString("address"), 
					res.getString("code"), res.getString("telephone"),res.getShort("ifCombine"), 
					res.getInt("withDrawFlag"), res.getString("extend"),res.getString("lastChangeDate"), 
					res.getString("inputOp"), res.getString("checkOp"),res.getString("lastMainAccId"),					
					res.getShort("accFlag"), res.getShort("attentionFlag"),res.getInt("accType"), 	
					res.getInt("accCategory"), res.getShort("ifFantasy"),res.getString("officeAddress"), 						
					res.getString("mobilephone"), res.getString("linkMan2"),res.getString("mobilephone2"), 	
					res.getString("telephone2"), res.getString("address2"),res.getString("memo"), 	
					res.getInt("sleepFlag"), res.getString("lastPointNo"),res.getString("migratePeople"), 	
					res.getString("migrateAuthPeople"),res.getString("migrateDate"));					
								
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accInfo;

	}
	
	public static SealCard parseSealCard(ResultSet res) {
		SealCard sealCard = null;
		try {
			sealCard = new SealCard(res.getString("sid"),
					res.getString("cardId"), res.getString("accId"),
					res.getInt("sealCount"), res.getString("startDate"),
					res.getString("endDate"),res.getString("usage"),res.getString("memoInfo"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sealCard;

	}
	
	public static CardImage parseCardImage(ResultSet res) {
		CardImage cardImage = null;
		try {
			cardImage = new CardImage(res.getString("cardImageId"),
					res.getString("sealCardSid"), res.getString("sealCard"),
					res.getString("imageId"), res.getShort("imageFlag"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cardImage;
	}
	
	public static SealInfo parseSealInfo(ResultSet res) {
		SealInfo sealInfo = null;
		try {						
				generateImageFile(res.getBytes(3), res.getString("sealId"));
				String seal = "见" + res.getString("sealId") + "文件";
				sealInfo = new SealInfo(res.getString("sealId"),res.getString("cardImageId"), seal,res.getInt("height"),
						res.getInt("width"), res.getString("litterInfo"), res.getString("litterSeal"),
						 res.getString("litterFeature"), res.getInt("sealType"), res.getInt("sealColor"),
						 res.getString("startDate"), res.getString("endDate"),res.getString("noUseDate"),
						 res.getString("sealMemo"), res.getString("sealCoord"),res.getShort("lossRegister"),
						 res.getString("cardId"), res.getShort("qualityLevel"),res.getInt("sealForm"),res.getFloat("scale"),
						 res.getString("sealLossMan"), res.getString("sealLossReason"),res.getString("sealLossTime"),
						 res.getString("sealLossRemarks"), res.getString("sealUnlossMan"),res.getString("sealUnlossReason"),
						 res.getString("sealUnlossTime"), res.getString("sealUnlossRemarks"));				
			} catch (Exception e) {
				e.printStackTrace();
			} 
	
		return sealInfo;
	}
	
	public SealCombine parseSealCombine(ResultSet res){
		SealCombine sealCombine = null;
		try {
			sealCombine = new SealCombine(res.getString("combineId"), res.getString("accId"), 
					res.getString("condition"),res.getString("seal"), res.getString("startDate"), 
					res.getString("endDate"), res.getString("showName"),res.getString("combineMemo"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return sealCombine;
	}
	
	private static void generateImageFile(byte[] fileBytes, String sealId) {		
		String imagePath = ExportAccinfo.rootPath + "/" + sealId + ".txt";
		OutputStream targetFile = null;
		try {		
				targetFile =  new FileOutputStream(imagePath);				
				targetFile.write(fileBytes);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				targetFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
