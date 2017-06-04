package com.yzj.sisbase.export.model;

public class SealInfo {

	private String sealId; // 印鉴的唯一id值
	private String cardImageId; // 印鉴所在的印鉴卡的图像
	private String seal; // 印鉴的2值化数据
	private int height; // 印鉴的高
	private int width; // 印鉴的宽
	private String litterInfo; // 印鉴上的小码信息
	private String litterSeal; // 印鉴上的细笔画信息
	private String litterFeature; // 印鉴上的细笔画特征信息
	private int sealType; // 印鉴类型（公章、私章等具体信息参见ZParam_SealType表）
	private int sealColor; // 印鉴的颜色（红、蓝等具体信息参见ZParam_SealColor表）
	private String startDate; // 印鉴的启用日期
	private String endDate; // 印鉴的注销日期
	private String noUseDate; // 印鉴的停用时间
	private String sealMemo; // 印鉴的备注
	private String sealCoord; // 印鉴针对印鉴卡图像的坐标x1,y1,x2,y2
	private short lossRegister; // 挂失类型(具体信息参见ZParam_LossRegister表)
	private String cardId; // 印鉴卡的id
	private short qualityLevel; // 是否为高质量印章
	private int sealForm; // 印鉴的形状（具体信息参见ZParam_SealForm表）
	private float scale; // 缩放因子，数据库里存的是印鉴相对于200dpi的缩放因子	
	private String sealLossMan;// 挂失操作员
	private String sealLossReason;// 挂失理由
	private String sealLossTime;// 挂失时间
	private String sealLossRemarks;// 挂失备注
	private String sealUnlossMan;// 解挂操作员
	private String sealUnlossReason;// 解挂理由
	private String sealUnlossTime;// 解挂时间
	private String sealUnlossRemarks;// 解挂备注
	
			
	public SealInfo(String sealId, String cardImageId, String seal, int height,
			int width, String litterInfo, String litterSeal,
			String litterFeature, int sealType, int sealColor,
			String startDate, String endDate, String noUseDate,
			String sealMemo, String sealCoord, short lossRegister,
			String cardId, short qualityLevel, int sealForm, float scale,
			String sealLossMan, String sealLossReason, String sealLossTime,
			String sealLossRemarks, String sealUnlossMan,
			String sealUnlossReason, String sealUnlossTime,
			String sealUnlossRemarks) {
		super();
		this.sealId = sealId;
		this.cardImageId = cardImageId;
		this.seal = seal;
		this.height = height;
		this.width = width;
		this.litterInfo = litterInfo;
		this.litterSeal = litterSeal;
		this.litterFeature = litterFeature;
		this.sealType = sealType;
		this.sealColor = sealColor;
		this.startDate = startDate;
		this.endDate = endDate;
		this.noUseDate = noUseDate;
		this.sealMemo = sealMemo;
		this.sealCoord = sealCoord;
		this.lossRegister = lossRegister;
		this.cardId = cardId;
		this.qualityLevel = qualityLevel;
		this.sealForm = sealForm;
		this.scale = scale;
		this.sealLossMan = sealLossMan;
		this.sealLossReason = sealLossReason;
		this.sealLossTime = sealLossTime;
		this.sealLossRemarks = sealLossRemarks;
		this.sealUnlossMan = sealUnlossMan;
		this.sealUnlossReason = sealUnlossReason;
		this.sealUnlossTime = sealUnlossTime;
		this.sealUnlossRemarks = sealUnlossRemarks;
	}


	public String getSealId() {
		return sealId;
	}


	public void setSealId(String sealId) {
		this.sealId = sealId;
	}


	public String getCardImageId() {
		return cardImageId;
	}


	public void setCardImageId(String cardImageId) {
		this.cardImageId = cardImageId;
	}


	public String getSeal() {
		return seal;
	}


	public void setSeal(String seal) {
		this.seal = seal;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public String getLitterInfo() {
		return litterInfo;
	}


	public void setLitterInfo(String litterInfo) {
		this.litterInfo = litterInfo;
	}


	public String getLitterSeal() {
		return litterSeal;
	}


	public void setLitterSeal(String litterSeal) {
		this.litterSeal = litterSeal;
	}


	public String getLitterFeature() {
		return litterFeature;
	}


	public void setLitterFeature(String litterFeature) {
		this.litterFeature = litterFeature;
	}


	public int getSealType() {
		return sealType;
	}


	public void setSealType(int sealType) {
		this.sealType = sealType;
	}


	public int getSealColor() {
		return sealColor;
	}


	public void setSealColor(int sealColor) {
		this.sealColor = sealColor;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public String getNoUseDate() {
		return noUseDate;
	}


	public void setNoUseDate(String noUseDate) {
		this.noUseDate = noUseDate;
	}


	public String getSealMemo() {
		return sealMemo;
	}


	public void setSealMemo(String sealMemo) {
		this.sealMemo = sealMemo;
	}


	public String getSealCoord() {
		return sealCoord;
	}


	public void setSealCoord(String sealCoord) {
		this.sealCoord = sealCoord;
	}


	public short getLossRegister() {
		return lossRegister;
	}


	public void setLossRegister(short lossRegister) {
		this.lossRegister = lossRegister;
	}


	public String getCardId() {
		return cardId;
	}


	public void setCardId(String cardId) {
		this.cardId = cardId;
	}


	public short getQualityLevel() {
		return qualityLevel;
	}


	public void setQualityLevel(short qualityLevel) {
		this.qualityLevel = qualityLevel;
	}


	public int getSealForm() {
		return sealForm;
	}


	public void setSealForm(int sealForm) {
		this.sealForm = sealForm;
	}


	public float getScale() {
		return scale;
	}


	public void setScale(float scale) {
		this.scale = scale;
	}


	public String getSealLossMan() {
		return sealLossMan;
	}


	public void setSealLossMan(String sealLossMan) {
		this.sealLossMan = sealLossMan;
	}


	public String getSealLossReason() {
		return sealLossReason;
	}


	public void setSealLossReason(String sealLossReason) {
		this.sealLossReason = sealLossReason;
	}


	public String getSealLossTime() {
		return sealLossTime;
	}


	public void setSealLossTime(String sealLossTime) {
		this.sealLossTime = sealLossTime;
	}


	public String getSealLossRemarks() {
		return sealLossRemarks;
	}


	public void setSealLossRemarks(String sealLossRemarks) {
		this.sealLossRemarks = sealLossRemarks;
	}


	public String getSealUnlossMan() {
		return sealUnlossMan;
	}


	public void setSealUnlossMan(String sealUnlossMan) {
		this.sealUnlossMan = sealUnlossMan;
	}


	public String getSealUnlossReason() {
		return sealUnlossReason;
	}


	public void setSealUnlossReason(String sealUnlossReason) {
		this.sealUnlossReason = sealUnlossReason;
	}


	public String getSealUnlossTime() {
		return sealUnlossTime;
	}


	public void setSealUnlossTime(String sealUnlossTime) {
		this.sealUnlossTime = sealUnlossTime;
	}


	public String getSealUnlossRemarks() {
		return sealUnlossRemarks;
	}


	public void setSealUnlossRemarks(String sealUnlossRemarks) {
		this.sealUnlossRemarks = sealUnlossRemarks;
	}
	
}
