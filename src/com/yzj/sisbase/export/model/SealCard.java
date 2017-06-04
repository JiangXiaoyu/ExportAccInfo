package com.yzj.sisbase.export.model;


public class SealCard {

	private String sid;
	private String cardId; // 印鉴卡的唯一id值
	private String accId; // 印鉴卡关联的账户信息数据库字段体现为accid
	private int sealCount; // 印鉴的数量
	private String startDate; // 印鉴卡的启用日期
	private String endDate; // 印鉴卡的注销日期
	private String usage; // 印鉴卡的用途
	private String memoInfo; // 印鉴卡的备注
	
		
	public SealCard(String sid, String cardId, String accId, int sealCount,
			String startDate, String endDate, String usage, String memoInfo) {
		super();
		this.sid = sid;
		this.cardId = cardId;
		this.accId = accId;
		this.sealCount = sealCount;
		this.startDate = startDate;
		this.endDate = endDate;
		this.usage = usage;
		this.memoInfo = memoInfo;
	}


	public String getSid() {
		return sid;
	}


	public String getCardId() {
		return cardId;
	}


	public String getAccId() {
		return accId;
	}


	public int getSealCount() {
		return sealCount;
	}


	public String getStartDate() {
		return startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public String getUsage() {
		return usage;
	}


	public String getMemoInfo() {
		return memoInfo;
	}

}
