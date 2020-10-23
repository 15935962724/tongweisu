package com.tongweisu.entity;

import java.io.Serializable;

/**
 * 系统设置
 * 
 * @author HaoKangHu Team
 * @version 1.0
 */
public class Setting implements Serializable {

	private static final long serialVersionUID = -1478999889661796840L;

	/** 缓存名称 */
	public static final String CACHE_NAME = "setting";

	/** 缓存Key */
	public static final Integer CACHE_KEY = 0;

	/** 分隔符 */
	private static final String SEPARATOR = ",";

	/*短信接口*/
	private String shortMessageUrl;

	/*开发者主账号（ACCOUNT SID）。开发者账号唯一标识符*/
	private String accountSid;

	/*子账户ID，具体查看用户中心 短信配置中值，验证码通知和营销不一样*/
	private String accountId;

	/*短信接口秘钥*/
	private String authToken;

	/*会员注册赠送积分*/
	private String memberRegisterPoint;

	/*购买软件赠送积分*/
	private String purchaseProductPoint;

	/*投票访问次数*/
	private Integer toupiaoCount;

	/*有效投票数*/
	private Integer toupiaoSum;

	/*投票结束时间*/
	private Long date;

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public String getShortMessageUrl() {
		return shortMessageUrl;
	}

	public void setShortMessageUrl(String shortMessageUrl) {
		this.shortMessageUrl = shortMessageUrl;
	}

	public String getAccountSid() {
		return accountSid;
	}

	public void setAccountSid(String accountSid) {
		this.accountSid = accountSid;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getMemberRegisterPoint() {
		return memberRegisterPoint;
	}

	public void setMemberRegisterPoint(String memberRegisterPoint) {
		this.memberRegisterPoint = memberRegisterPoint;
	}

	public String getPurchaseProductPoint() {
		return purchaseProductPoint;
	}

	public void setPurchaseProductPoint(String purchaseProductPoint) {
		this.purchaseProductPoint = purchaseProductPoint;
	}


	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public Integer getToupiaoSum() {
		return toupiaoSum;
	}

	public void setToupiaoSum(Integer toupiaoSum) {
		this.toupiaoSum = toupiaoSum;
	}

	public Integer getToupiaoCount() {
		return toupiaoCount;
	}

	public void setToupiaoCount(Integer toupiaoCount) {
		this.toupiaoCount = toupiaoCount;
	}
}