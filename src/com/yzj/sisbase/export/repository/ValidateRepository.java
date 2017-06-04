package com.yzj.sisbase.export.repository;

import java.util.ArrayList;
import java.util.List;

public class ValidateRepository {

	public static final String  FIELD_accId = "accId";
	public static final String  FIELD_mainAccId = "mainAccId";
	public static final String  FIELD_customerId = "customerId";
	public static final String  FIELD_accno = "accno";
	public static final String  FIELD_accName = "accName";
	public static final String  FIELD_openDate = "openDate";
	public static final String  FIELD_startDate = "startDate";
	public static final String  FIELD_endDate = "endDate";
	public static final String  FIELD_createDate = "createDate";
	public static final String  FIELD_currencyType = "currencyType";	
	public static final String  FIELD_pointNo = "pointNo";
	public static final String  FIELD_pointName = "pointName";
	public static final String  FIELD_linkMan = "linkMan";
	public static final String  FIELD_address = "address";
	public static final String  FIELD_code = "code";
	public static final String  FIELD_telephone = "telephone";
	public static final String  FIELD_ifCombine = "ifCombine";
	public static final String  FIELD_withDrawFlag = "withDrawFlag";
	public static final String  FIELD_extend = "extend";
	public static final String  FIELD_lastChangeDate = "lastChangeDate";	
	public static final String  FIELD_inputOp = "inputOp";
	public static final String  FIELD_checkOp = "checkOp";
	public static final String  FIELD_lastMainAccId = "lastMainAccId";
	public static final String  FIELD_accFlag = "accFlag";
	public static final String  FIELD_attentionFlag = "attentionFlag";
	public static final String  FIELD_accType = "accType";
	public static final String  FIELD_accCategory = "accCategory";
	public static final String  FIELD_ifFantasy = "ifFantasy";
	public static final String  FIELD_officeAddress = "officeAddress";
	public static final String  FIELD_mobilephone = "mobilephone";	
	public static final String  FIELD_linkMan2 = "linkMan2";
	public static final String  FIELD_mobilephone2 = "mobilephone2";
	public static final String  FIELD_telephone2 = "telephone2";
	public static final String  FIELD_address2 = "address2";
	public static final String  FIELD_memo = "memo";
	public static final String  FIELD_sleepFlag = "sleepFlag";
	public static final String  FIELD_lastPointNo = "lastPointNo";
	public static final String  FIELD_migratePeople = "migratePeople";
	public static final String  FIELD_migrateAuthPeople = "migrateAuthPeople";
	public static final String  FIELD_migrateDate = "migrateDate";	
	public final List<String> FIELDS_ACCINFO_ALL = new ArrayList<String>();	
	{
		FIELDS_ACCINFO_ALL.add(FIELD_accId);
		FIELDS_ACCINFO_ALL.add(FIELD_mainAccId);
		FIELDS_ACCINFO_ALL.add(FIELD_customerId);
		FIELDS_ACCINFO_ALL.add(FIELD_accno);
		FIELDS_ACCINFO_ALL.add(FIELD_accName);
		FIELDS_ACCINFO_ALL.add(FIELD_openDate);
		FIELDS_ACCINFO_ALL.add(FIELD_startDate);
		FIELDS_ACCINFO_ALL.add(FIELD_endDate);
		FIELDS_ACCINFO_ALL.add(FIELD_createDate);
		FIELDS_ACCINFO_ALL.add(FIELD_currencyType);
		FIELDS_ACCINFO_ALL.add(FIELD_pointNo);
		FIELDS_ACCINFO_ALL.add(FIELD_pointName);
		FIELDS_ACCINFO_ALL.add(FIELD_linkMan);
		FIELDS_ACCINFO_ALL.add(FIELD_address);
		FIELDS_ACCINFO_ALL.add(FIELD_code);
		FIELDS_ACCINFO_ALL.add(FIELD_telephone);
		FIELDS_ACCINFO_ALL.add(FIELD_ifCombine);
		FIELDS_ACCINFO_ALL.add(FIELD_withDrawFlag);
		FIELDS_ACCINFO_ALL.add(FIELD_extend);
		FIELDS_ACCINFO_ALL.add(FIELD_lastChangeDate);
		FIELDS_ACCINFO_ALL.add(FIELD_inputOp);
		FIELDS_ACCINFO_ALL.add(FIELD_checkOp);
		FIELDS_ACCINFO_ALL.add(FIELD_lastMainAccId);
		FIELDS_ACCINFO_ALL.add(FIELD_accFlag);
		FIELDS_ACCINFO_ALL.add(FIELD_attentionFlag);
		FIELDS_ACCINFO_ALL.add(FIELD_accType);
		FIELDS_ACCINFO_ALL.add(FIELD_accCategory);
		FIELDS_ACCINFO_ALL.add(FIELD_ifFantasy);
		FIELDS_ACCINFO_ALL.add(FIELD_officeAddress);
		FIELDS_ACCINFO_ALL.add(FIELD_mobilephone);
		FIELDS_ACCINFO_ALL.add(FIELD_linkMan2);
		FIELDS_ACCINFO_ALL.add(FIELD_mobilephone2);
		FIELDS_ACCINFO_ALL.add(FIELD_telephone2);
		FIELDS_ACCINFO_ALL.add(FIELD_address2);
		FIELDS_ACCINFO_ALL.add(FIELD_memo);
		FIELDS_ACCINFO_ALL.add(FIELD_sleepFlag);
		FIELDS_ACCINFO_ALL.add(FIELD_lastPointNo);
		FIELDS_ACCINFO_ALL.add(FIELD_migratePeople);
		FIELDS_ACCINFO_ALL.add(FIELD_migrateAuthPeople);
		FIELDS_ACCINFO_ALL.add(FIELD_migrateDate);
	}
	
	public final static String FIELD_cardImageId = "cardImageId";
	public final static String FIELD_sealCardSid = "sealCardSid";
	public final static String FIELD_sealCard = "sealCard";
	public final static String FIELD_imageId = "imageId";
	public final static String FIELD_imageFlag = "imageFlag";
	public final List<String> FIELDS_CARDIMAGE_ALL = new ArrayList<String>();
	{
		FIELDS_CARDIMAGE_ALL.add(FIELD_cardImageId);
		FIELDS_CARDIMAGE_ALL.add(FIELD_sealCardSid);
		FIELDS_CARDIMAGE_ALL.add(FIELD_sealCard);
		FIELDS_CARDIMAGE_ALL.add(FIELD_imageId);
		FIELDS_CARDIMAGE_ALL.add(FIELD_imageFlag);
	}
	
	public final static String FIELD_sid = "sid";
	public final static String FIELD_cardId = "cardId";
	public final static String FIELD_SEALCARD_accId = "accId";
	public final static String FIELD_sealCount = "sealCount";
	public final static String FIELD_SEALCARD_startDate = "startDate";
	public final static String FIELD_SEALCARD_endDate = "endDate";
	public final static String FIELD_usage = "usage";
	public final static String FIELD_memoInfo = "memoInfo";
	public final List<String> FIELD_SEALCARD_ALL = new ArrayList<String>();
	{
		FIELD_SEALCARD_ALL.add(FIELD_sid);
		FIELD_SEALCARD_ALL.add(FIELD_cardId);
		FIELD_SEALCARD_ALL.add(FIELD_SEALCARD_accId);
		FIELD_SEALCARD_ALL.add(FIELD_sealCount);
		FIELD_SEALCARD_ALL.add(FIELD_SEALCARD_startDate);
		FIELD_SEALCARD_ALL.add(FIELD_SEALCARD_endDate);
		FIELD_SEALCARD_ALL.add(FIELD_usage);
		FIELD_SEALCARD_ALL.add(FIELD_memoInfo);
	}
		
	public final static String FIELD_sealId = "sealId";
	public final static String FIELD_SealInfo_cardImageId = "cardImageId";
	public final static String FIELD_seal = "seal";
	public final static String FIELD_height = "height";
	public final static String FIELD_width = "width";
	public final static String FIELD_litterInfo = "litterInfo";
	public final static String FIELD_litterSeal = "litterSeal";
	public final static String FIELD_litterFeature = "litterFeature";
	public final static String FIELD_sealType = "sealType";
	public final static String FIELD_sealColor = "sealColor";	
	public final static String FIELD_SEALINFO_startDate = "startDate";
	public final static String FIELD_SEALINFO_endDate = "endDate";
	public final static String FIELD_noUseDate = "noUseDate";
	public final static String FIELD_sealMemo = "sealMemo";
	public final static String FIELD_sealCoord = "sealCoord";
	public final static String FIELD_lossRegister = "lossRegister";
	public final static String FIELD_SEALINFO_cardId = "cardId";
	public final static String FIELD_qualityLevel = "qualityLevel";
	public final static String FIELD_sealForm = "sealForm";
	public final static String FIELD_scale = "scale";	
	public final static String FIELD_sealLossMan = "sealLossMan";
	public final static String FIELD_sealLossReason = "sealLossReason";
	public final static String FIELD_sealLossTime = "sealLossTime";
	public final static String FIELD_sealLossRemarks = "sealLossRemarks";
	public final static String FIELD_sealUnlossMan = "sealUnlossMan";
	public final static String FIELD_sealUnlossReason = "sealUnlossReason";
	public final static String FIELD_sealUnlossTime = "sealUnlossTime";
	public final static String FIELD_sealUnlossRemarks = "sealUnlossRemarks";
	public final List<String> FIELD_SEALINFO_ALL = new ArrayList<String>();
	{
		FIELD_SEALINFO_ALL.add(FIELD_sealId);
		FIELD_SEALINFO_ALL.add(FIELD_SealInfo_cardImageId);
		FIELD_SEALINFO_ALL.add(FIELD_seal);
		FIELD_SEALINFO_ALL.add(FIELD_height);
		FIELD_SEALINFO_ALL.add(FIELD_width);
		FIELD_SEALINFO_ALL.add(FIELD_litterInfo);
		FIELD_SEALINFO_ALL.add(FIELD_litterSeal);
		FIELD_SEALINFO_ALL.add(FIELD_litterFeature);
		FIELD_SEALINFO_ALL.add(FIELD_sealType);
		FIELD_SEALINFO_ALL.add(FIELD_sealColor);		
		FIELD_SEALINFO_ALL.add(FIELD_SEALINFO_startDate);
		FIELD_SEALINFO_ALL.add(FIELD_SEALINFO_endDate);
		FIELD_SEALINFO_ALL.add(FIELD_noUseDate);
		FIELD_SEALINFO_ALL.add(FIELD_sealMemo);
		FIELD_SEALINFO_ALL.add(FIELD_sealCoord);
		FIELD_SEALINFO_ALL.add(FIELD_lossRegister);
		FIELD_SEALINFO_ALL.add(FIELD_SEALINFO_cardId);
		FIELD_SEALINFO_ALL.add(FIELD_qualityLevel);
		FIELD_SEALINFO_ALL.add(FIELD_sealForm);
		FIELD_SEALINFO_ALL.add(FIELD_scale);		
		FIELD_SEALINFO_ALL.add(FIELD_sealLossMan);
		FIELD_SEALINFO_ALL.add(FIELD_sealLossReason);
		FIELD_SEALINFO_ALL.add(FIELD_sealLossTime);
		FIELD_SEALINFO_ALL.add(FIELD_sealLossRemarks);
		FIELD_SEALINFO_ALL.add(FIELD_sealUnlossMan);
		FIELD_SEALINFO_ALL.add(FIELD_sealUnlossReason);
		FIELD_SEALINFO_ALL.add(FIELD_sealUnlossTime);		
		FIELD_SEALINFO_ALL.add(FIELD_sealUnlossRemarks);
	}
	
	
	public final static String FIELD_combineId = "combineId";
	public final static String FIELD_SEALCOMBINE_accId = "accId";
	public final static String FIELD_condition = "condition";
	public final static String FIELD_SEALCOMBINE_seal = "seal";
	public final static String FIELD_SEALCOMBINE_startDate = "startDate";
	public final static String FIELD_SEALCOMBINE_endDate = "endDate";
	public final static String FIELD_showName = "showName";
	public final static String FIELD_combineMemo = "combineMemo";
	public final List<String> FIELD_SEALCOMBINE_ALL = new ArrayList<String>();
	{
		FIELD_SEALCOMBINE_ALL.add(FIELD_combineId);
		FIELD_SEALCOMBINE_ALL.add(FIELD_SEALCOMBINE_accId);
		FIELD_SEALCOMBINE_ALL.add(FIELD_condition);
		FIELD_SEALCOMBINE_ALL.add(FIELD_SEALCOMBINE_seal);
		FIELD_SEALCOMBINE_ALL.add(FIELD_SEALCOMBINE_startDate);
		FIELD_SEALCOMBINE_ALL.add(FIELD_SEALCOMBINE_endDate);
		FIELD_SEALCOMBINE_ALL.add(FIELD_showName);
		FIELD_SEALCOMBINE_ALL.add(FIELD_combineMemo);
	}

}
