package com.lj.business.member.dto.chatroom;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class UpdateChatRoomMember implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = -6241120349253093945L;
	/**
     * CODE .
     */
    private String code;

    /**
     * 群CODE .
     */
    private String roomCode;

    /**
     * 中控微信号 .
     */
    private String noWxZk;

    /**
     * 群名 .
     */
    private String chatRoomName;

    /**
     * 群成员微信号 .
     */
    private String userName;

    /**
     * 群成员昵称 .
     */
    private String nickName;

    /**
     * 群成员头像 .
     */
    private String headUrl;

    /**
     * 状态：0初始化、1有效、2无效（删除） .
     */
    private Integer status;

    /**
     * 导购编号 .
     */
    private String memberNoGm;

    /**
     * 导购姓名 .
     */
    private String memberNameGm;

    /**
     * 客户编号 .
     */
    private String memberNo;

    /**
     * 客户名称 .
     */
    private String memberName;

    /**
     * 终端编号 .
     */
    

    /**
     * 终端名称 .
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
     * 版本号 .
     */
    private Long version;

    /**
     * 创建人 .
     */
    private String createId;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 备注 .
     */
    private String remark;

    /**
     * 备注 .
     */
    private String remark2;

    /**
     * 备注 .
     */
    private String remark3;

    /**
     * 备注 .
     */
    private String remark4;
    /**
     * 
     */
    private String[] userNames;
    
    
    public String[] getUserNames() {
		return userNames;
	}

	public void setUserNames(String[] userNames) {
		this.userNames = userNames;
	}
    /**
     * CODE .
     *
     */
    public String getCode() {
        return code;
    }

    /**
     * CODE .
     *
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 群CODE .
     *
     */
    public String getRoomCode() {
        return roomCode;
    }

    /**
     * 群CODE .
     *
     */
    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode == null ? null : roomCode.trim();
    }

    /**
     * 中控微信号 .
     *
     */
    public String getNoWxZk() {
        return noWxZk;
    }

    /**
     * 中控微信号 .
     *
     */
    public void setNoWxZk(String noWxZk) {
        this.noWxZk = noWxZk == null ? null : noWxZk.trim();
    }

    /**
     * 群名 .
     *
     */
    public String getChatRoomName() {
        return chatRoomName;
    }

    /**
     * 群名 .
     *
     */
    public void setChatRoomName(String chatRoomName) {
        this.chatRoomName = chatRoomName == null ? null : chatRoomName.trim();
    }

    /**
     * 群成员微信号 .
     *
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 群成员微信号 .
     *
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 群成员昵称 .
     *
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 群成员昵称 .
     *
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * 群成员头像 .
     *
     */
    public String getHeadUrl() {
        return headUrl;
    }

    /**
     * 群成员头像 .
     *
     */
    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl == null ? null : headUrl.trim();
    }

    /**
     * 状态：0初始化、1有效、2无效（删除） .
     *
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态：0初始化、1有效、2无效（删除） .
     *
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 导购编号 .
     *
     */
    public String getMemberNoGm() {
        return memberNoGm;
    }

    /**
     * 导购编号 .
     *
     */
    public void setMemberNoGm(String memberNoGm) {
        this.memberNoGm = memberNoGm == null ? null : memberNoGm.trim();
    }

    /**
     * 导购姓名 .
     *
     */
    public String getMemberNameGm() {
        return memberNameGm;
    }

    /**
     * 导购姓名 .
     *
     */
    public void setMemberNameGm(String memberNameGm) {
        this.memberNameGm = memberNameGm == null ? null : memberNameGm.trim();
    }

    /**
     * 客户编号 .
     *
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * 客户编号 .
     *
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo == null ? null : memberNo.trim();
    }

    /**
     * 客户名称 .
     *
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * 客户名称 .
     *
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    /**
     * 商户编号 .
     *
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * 商户编号 .
     *
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    /**
     * 商户名称 .
     *
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * 商户名称 .
     *
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    /**
     * 版本号 .
     *
     */
    public Long getVersion() {
        return version;
    }

    /**
     * 版本号 .
     *
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * 创建人 .
     *
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * 创建人 .
     *
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    /**
     * 创建时间 .
     *
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间 .
     *
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 备注 .
     *
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark4() {
        return remark4;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark4(String remark4) {
        this.remark4 = remark4 == null ? null : remark4.trim();
    }

	@Override
	public String toString() {
		return "UpdateChatRoomMember [code=" + code + ", roomCode=" + roomCode + ", noWxZk=" + noWxZk
				+ ", chatRoomName=" + chatRoomName + ", userName=" + userName + ", nickName=" + nickName + ", headUrl="
				+ headUrl + ", status=" + status + ", memberNoGm=" + memberNoGm + ", memberNameGm=" + memberNameGm
				+ ", memberNo=" + memberNo + ", memberName=" + memberName + ", merchantNo=" + merchantNo
				+ ", merchantName=" + merchantName + ", version=" + version + ", createId=" + createId + ", createDate="
				+ createDate + ", remark=" + remark + ", remark2=" + remark2 + ", remark3=" + remark3 + ", remark4="
				+ remark4 + ", userNames=" + Arrays.toString(userNames) + "]";
	}

}
