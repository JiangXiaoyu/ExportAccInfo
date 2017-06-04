package com.yzj.sisbase.export.model;

public class SealCombine {
	private String combineId; // 印鉴组合的唯一id值
	private String accId; // 印鉴组合关联的账户信息
	private String condition; // 印鉴组合的条件表达式
	private String seal; // 印鉴组合的印鉴关系表达式
	private String startDate; // 印鉴组合的开始时间
	private String endDate; // 印鉴组合的注销日期
	private String showName; // 印鉴组合的名称
	private String combineMemo;// 印鉴组合的备注
	
	
	public SealCombine(String combineId, String accId, String condition,
			String seal, String startDate, String endDate, String showName,
			String combineMemo) {
		super();
		this.combineId = combineId;
		this.accId = accId;
		this.condition = condition;
		this.seal = seal;
		this.startDate = startDate;
		this.endDate = endDate;
		this.showName = showName;
		this.combineMemo = combineMemo;
	}


	public String getCombineId() {
		return combineId;
	}


	public String getAccId() {
		return accId;
	}


	public String getCondition() {
		return condition;
	}


	public String getSeal() {
		return seal;
	}


	public String getStartDate() {
		return startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public String getShowName() {
		return showName;
	}


	public String getCombineMemo() {
		return combineMemo;
	}

	
}
