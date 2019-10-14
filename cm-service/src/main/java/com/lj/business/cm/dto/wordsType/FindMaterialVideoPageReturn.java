package com.lj.business.cm.dto.wordsType;

import java.io.Serializable;
import java.util.Date;

/**
 * 类说明:视频库
 *@author 贾光朝
 *@createDate 2019年4月22日下午2:08:35
 */
public class FindMaterialVideoPageReturn implements Serializable{

	private static final long serialVersionUID = 7060781623603352188L;
	
	private String code;
	private String videoName;
	private String folderName;
	private String parentId;
	private String parentIds;
	private Double size;
	private String videoLocation;
	private Date updateTime;
	private String videoLength;
	private String firstView;
	private int count;
	private int length;
	private int width;
	/**
     * 商户号
     */
    private String merchantNo;
    /**
     * 机构名
     */
    private String officeName;
    
    
    
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getFirstView() {
		return firstView;
	}
	public void setFirstView(String firstView) {
		this.firstView = firstView;
	}
	public String getVideoLength() {
		return videoLength;
	}
	public void setVideoLength(String videoLength) {
		this.videoLength = videoLength;
	}
	public String getMerchantNo() {
		return merchantNo;
	}
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
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
	public String getFolderName() {
		return folderName;
	}
	public void setFolderName(String folderName) {
		this.folderName = folderName;
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
	public Double getSize() {
		return size;
	}
	public void setSize(Double size) {
		this.size = size;
	}
	public String getVideoLocation() {
		return videoLocation;
	}
	public void setVideoLocation(String videoLocation) {
		this.videoLocation = videoLocation;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	

}
