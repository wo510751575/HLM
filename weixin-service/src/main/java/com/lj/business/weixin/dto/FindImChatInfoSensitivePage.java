package com.lj.business.weixin.dto;


import com.lj.base.core.pagination.PageParamEntity;


public class FindImChatInfoSensitivePage extends PageParamEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8892685501478658996L;


    /**
     * 分店名称 .
     */
    

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;

    /**
     * 消息来源 .
     */
    private Integer msgSource;

    /**
     * 聊天助手名称 .
     */
    private String chatAssistantName;
    
    /**
     * 导购名称
     */
    private String memberGmName;
    
    /**
     * 发送人
     */
    private String chatAssistant;
    
    

	public String getChatAssistant() {
		return chatAssistant;
	}

	public void setChatAssistant(String chatAssistant) {
		this.chatAssistant = chatAssistant;
	}

	public String getMemberGmName() {
		return memberGmName;
	}

	public void setMemberGmName(String memberGmName) {
		this.memberGmName = memberGmName;
	}


	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public Integer getMsgSource() {
		return msgSource;
	}

	public void setMsgSource(Integer msgSource) {
		this.msgSource = msgSource;
	}

	public String getChatAssistantName() {
		return chatAssistantName;
	}

	public void setChatAssistantName(String chatAssistantName) {
		this.chatAssistantName = chatAssistantName;
	}
  
    



}