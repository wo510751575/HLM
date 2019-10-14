package com.lj.business.member.dto;

import java.util.Date;
import java.util.List;

import com.lj.base.core.pagination.PageParamEntity;

public class FindImIndexPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1613687660457949355L;
	
	private String memberNoGm;
	
	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	/**
	 * 终端微信，noWx与noWxList不能同时为空
	 */
    private String noWx;
    /**
     * 多个微信号
     */
    private String noWxs;
    
    /**
     * 终端微信号列表，noWx与noWxList不能同时为空
     */
    private List<String> noWxList;
    
    /**
	 * 关键字
	 */
	private String keyWord;
	
	/**
	 * 类型编号
	 */
	private String typeCode;
	
	/**
     * 会员号  .
     */
    private String memberNo; 
    
    /**
     * 性别:
             男：male
             女：female
             未知：unknown .
     */
    private String sex;
    
    /**
     * 是否全选.
     */
    private Boolean selectAll;
    
    /**
     * 开始时间
     */
    private Date startTime;
    
    /**
     * 结束时间
     */
    private Date endTime;
    
    /**
     * 置顶类型
     */
    private String setUpUser;
    public String getSetUpUser() {
		return setUpUser;
	}

	public void setSetUpUser(String setUpUser) {
		this.setUpUser = setUpUser;
	}

	/**
     * 查询群聊信息
     * queryChatRoom == null or queryChatRoom == 'true'
     */
    private String queryChatRoom;
    
	public String getQueryChatRoom() {
		return queryChatRoom;
	}

	public void setQueryChatRoom(String queryChatRoom) {
		this.queryChatRoom = queryChatRoom;
	}


	public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Boolean getSelectAll() {
        return selectAll;
    }

    public void setSelectAll(Boolean selectAll) {
        this.selectAll = selectAll;
    }

	public String getNoWx() {
		return noWx;
	}

	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	/**
	 * @return the noWxList
	 */
	public List<String> getNoWxList() {
		return noWxList;
	}

	/**
	 * @param noWxList the noWxList to set
	 */
	public void setNoWxList(List<String> noWxList) {
		this.noWxList = noWxList;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

    public String getNoWxs() {
        return noWxs;
    }

    public void setNoWxs(String noWxs) {
        this.noWxs = noWxs;
    }

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindImIndexPage [noWx=");
		builder.append(noWx);
		builder.append(", noWxs=");
		builder.append(noWxs);
		builder.append(", noWxList=");
		builder.append(noWxList);
		builder.append(", keyWord=");
		builder.append(keyWord);
		builder.append(", typeCode=");
		builder.append(typeCode);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", selectAll=");
		builder.append(selectAll);
		builder.append(", startTime=");
		builder.append(startTime);
		builder.append(", endTime=");
		builder.append(endTime);
		builder.append(", queryChatRoom=");
		builder.append(queryChatRoom);
		builder.append("]");
		return builder.toString();
	}

}
