package com.lj.business.cm.dto.wordsType;

import java.util.Date;

import com.lj.base.core.pagination.PageParamEntity;

/**
 * 类说明:视频库
 *@author 贾光朝
 *@createDate 2019年4月22日下午2:08:35
 */
public class FindMaterialVideoPage extends PageParamEntity{

	private static final long serialVersionUID = 7060781623603352188L;
	
	private String code;
	private String videoName;
	private String folderName;
	private String parentId;
	private String parentIds;
	private Date updateTime;
	private String videoLocation;

	/**
     * 商户号
     */
    private String merchantNo;
	
    
    
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getMerchantNo() {
		return merchantNo;
	}
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
	public String getFolderName() {
		return folderName;
	}
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getParentIds() {
		return parentIds;
	}
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	public String getVideoLocation() {
		return videoLocation;
	}
	public void setVideoLocation(String videoLocation) {
		this.videoLocation = videoLocation;
	}
	

}
