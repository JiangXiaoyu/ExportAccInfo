package com.yzj.sisbase.export.model;

public class AccInfo {
	
	private String accId; 
	private String mainAccId; 
	private String customerId; 
	private String accno; 
	private String accName; 
	private String openDate; 
	private String startDate; 
	private String endDate; 
	private String createDate; 
	private int currencyType; 
	private String pointNo; 
	private String pointName;
	private String linkMan; 
	private String address;
	private String code; 
	private String telephone;
	private short ifCombine; 
	private int withDrawFlag; 
	private String extend;
	private String lastChangeDate; 
	private String inputOp; 
	private String checkOp; 
	private String lastMainAccId; 
	private short accFlag; 
	private short attentionFlag; 
	private int accType; 
	private int accCategory; 
	private short ifFantasy; 
	private String officeAddress; 
	private String mobilephone; 
	private String linkMan2; 
	private String mobilephone2; 
	private String telephone2;
	private String address2; 
	private String memo; 
	private int sleepFlag;
	private String lastPointNo;
	private String migratePeople;
	private String migrateAuthPeople;
	private String migrateDate;
	
	//该类不要添加SIS_AccAdm_AccInfo表中以外的字段
		
	public AccInfo(String accno){
		this.accno = accno;
	}
	
	public AccInfo(String accId, String mainAccId, String customerId,
			String accno, String accName, String openDate, String startDate,
			String endDate, String createDate, int currencyType,
			String pointNo, String pointName, String linkMan, String address,
			String code, String telephone, short ifCombine, int withDrawFlag,
			String extend, String lastChangeDate, String inputOp,
			String checkOp, String lastMainAccId, short accFlag,
			short attentionFlag, int accType, int accCategory, short ifFantasy,
			String officeAddress, String mobilephone, String linkMan2,
			String mobilephone2, String telephone2, String address2,
			String memo, int sleepFlag, String lastPointNo,
			String migratePeople, String migrateAuthPeople, String migrateDate) {
		super();
		this.accId = accId;
		this.mainAccId = mainAccId;
		this.customerId = customerId;
		this.accno = accno;
		this.accName = accName;
		this.openDate = openDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createDate = createDate;
		this.currencyType = currencyType;
		this.pointNo = pointNo;
		this.pointName = pointName;
		this.linkMan = linkMan;
		this.address = address;
		this.code = code;
		this.telephone = telephone;
		this.ifCombine = ifCombine;
		this.withDrawFlag = withDrawFlag;
		this.extend = extend;
		this.lastChangeDate = lastChangeDate;
		this.inputOp = inputOp;
		this.checkOp = checkOp;
		this.lastMainAccId = lastMainAccId;
		this.accFlag = accFlag;
		this.attentionFlag = attentionFlag;
		this.accType = accType;
		this.accCategory = accCategory;
		this.ifFantasy = ifFantasy;
		this.officeAddress = officeAddress;
		this.mobilephone = mobilephone;
		this.linkMan2 = linkMan2;
		this.mobilephone2 = mobilephone2;
		this.telephone2 = telephone2;
		this.address2 = address2;
		this.memo = memo;
		this.sleepFlag = sleepFlag;
		this.lastPointNo = lastPointNo;
		this.migratePeople = migratePeople;
		this.migrateAuthPeople = migrateAuthPeople;
		this.migrateDate = migrateDate;
	}

	public AccInfo() {
		// TODO Auto-generated constructor stub
	}

	public String getAccId() {
		return accId;
	}

	public String getMainAccId() {
		return mainAccId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getAccno() {
		return accno;
	}

	public String getAccName() {
		return accName;
	}

	public String getOpenDate() {
		return openDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public String getCreateDate() {
		return createDate;
	}

	public int getCurrencyType() {
		return currencyType;
	}

	public String getPointNo() {
		return pointNo;
	}

	public String getPointName() {
		return pointName;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public String getAddress() {
		return address;
	}

	public String getCode() {
		return code;
	}

	public String getTelephone() {
		return telephone;
	}

	public short getIfCombine() {
		return ifCombine;
	}

	public int getWithDrawFlag() {
		return withDrawFlag;
	}

	public String getExtend() {
		return extend;
	}

	public String getLastChangeDate() {
		return lastChangeDate;
	}

	public String getInputOp() {
		return inputOp;
	}

	public String getCheckOp() {
		return checkOp;
	}

	public String getLastMainAccId() {
		return lastMainAccId;
	}

	public short getAccFlag() {
		return accFlag;
	}

	public short getAttentionFlag() {
		return attentionFlag;
	}

	public int getAccType() {
		return accType;
	}

	public int getAccCategory() {
		return accCategory;
	}

	public short getIfFantasy() {
		return ifFantasy;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public String getLinkMan2() {
		return linkMan2;
	}

	public String getMobilephone2() {
		return mobilephone2;
	}

	public String getTelephone2() {
		return telephone2;
	}

	public String getAddress2() {
		return address2;
	}

	public String getMemo() {
		return memo;
	}

	public int getSleepFlag() {
		return sleepFlag;
	}

	public String getLastPointNo() {
		return lastPointNo;
	}

	public String getMigratePeople() {
		return migratePeople;
	}

	public String getMigrateAuthPeople() {
		return migrateAuthPeople;
	}

	public String getMigrateDate() {
		return migrateDate;
	}

}