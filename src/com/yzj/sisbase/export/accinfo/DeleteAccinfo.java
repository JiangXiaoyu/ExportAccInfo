package com.yzj.sisbase.export.accinfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.yzj.sisbase.export.repository.ConnectionPool;
import com.yzj.sisbase.export.repository.QueryRepository;

public class DeleteAccinfo {
	
	public QueryRepository queryRepository = new QueryRepository();

	public String deleteAccinfo(String accno){
		String feedback = "";
		feedback = validateAccno(accno);		
		if(feedback.isEmpty()){		
			feedback = delete(accno);
		}
			
		return feedback;
	}
	
	public String delete(String accno){
		String feedback = "";
		Connection conn = null;
		PreparedStatement ps = null;		
		try {
			conn = ConnectionPool.getConnection();
			conn.setAutoCommit(false);		
			ps = conn.prepareStatement("delete from SIS_AccAdm_SealInfo where cardimageid in (select c.cardimageid from SIS_AccAdm_AccInfo a,SIS_AccAdm_SealCard b,SIS_AccAdm_CardImage c where a.accId=b.accId and b.sid=c.sealCardSid and a.accno=?)");
			ps.setString(1, accno);
			ps.executeUpdate();
			
			ps = conn.prepareStatement("delete from SIS_AccAdm_CardImage where sealcardsid in (select sid from SIS_AccAdm_SealCard b,SIS_AccAdm_AccInfo a where a.accId=b.accId and a.accno=?)");
			ps.setString(1, accno);
			ps.executeUpdate();
			
			ps = conn.prepareStatement("delete from SIS_AccAdm_SealCard where accId in (select b.accId from SIS_AccAdm_AccInfo b where b.accno=?)");
			ps.setString(1, accno);
			ps.executeUpdate();
			
			ps = conn.prepareStatement("delete from SIS_AccAdm_SealCombine where accId in (select b.accId from SIS_AccAdm_AccInfo b where b.accno=?)");
			ps.setString(1, accno);
			ps.executeUpdate();
			
			ps = conn.prepareStatement("delete from SIS_AccAdm_AccInfo where accno = ?");
			ps.setString(1, accno);
			ps.executeUpdate();
			feedback = "账户" + accno + "信息删除成功!";
		} catch (Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}			
			feedback = e.getMessage();
			e.printStackTrace();
		} finally{
			try {
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ConnectionPool.close(conn,ps,null);
		}
		
		return feedback;
	}
	
	public String validateAccno(String accno){
		String errormessage = "";
		if(accno==null || accno.isEmpty()){
			errormessage = "请输入账号!";
		} else if(queryRepository.queryAccInfobyAccno(accno).size() == 0){
			errormessage = "账户" + accno + "不存在,请重新输入！";
		} else {
			QueryRepository queryRepository = new QueryRepository();
			String accnoList = queryRepository.getAccnolistBymainaccno(accno);
			if(accnoList!=null && !accnoList.isEmpty()){
				errormessage = "账户" + accno + "存在以下从账户：" + accnoList + "请先删除!";
			}
		}
		
		return errormessage;
	}
}
