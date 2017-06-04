package com.yzj.sisbase.export.model;

public class CardImage {

	private String cardImageId; // 印鉴卡图像的id	
	private String sealCardSid;// 印鉴卡的对象信息 （数据库中体现为cardSid）
	private String sealCard; // 印鉴卡号
	private String imageId; // 图像的id
	private short imageFlag; // 图像的正反面标识（0正 1反 2粘单）
	
	
	public CardImage(String cardImageId, String sealCardSid, String sealCard,
			String imageId, short imageFlag) {
		super();
		this.cardImageId = cardImageId;
		this.sealCardSid = sealCardSid;
		this.sealCard = sealCard;
		this.imageId = imageId;
		this.imageFlag = imageFlag;
	}


	public String getCardImageId() {
		return cardImageId;
	}


	public String getSealCardSid() {
		return sealCardSid;
	}


	public String getSealCard() {
		return sealCard;
	}


	public String getImageId() {
		return imageId;
	}


	public short getImageFlag() {
		return imageFlag;
	}
			
}
