package com.lj.business.cm.dto.wordsType;

import java.util.Date;

import com.lj.base.core.pagination.PageParamEntity;

/**
 * 类说明:视频库
 *@author 贾光朝
 *@createDate 2019年4月22日下午2:08:35
 */
public class FindMaterialPhotoPage extends PageParamEntity{

	private static final long serialVersionUID = 7060781623603352188L;
	
	private String code;
	private String photoName;
	private String folderName;
	private String parentId;
	private String parentIds;
	private String smallPhotoLocation;
	private String photoLocation;
	private Date updateTime;
	private Double size;
	private int count;
	private int length;
	private int width;
	private String officeName;
	/**
     * 商户号
     */
    private String merchantNo;
	

	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
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
	public Double getSize() {
		return size;
	}
	public void setSize(Double size) {
		this.size = size;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getSmallPhotoLocation() {
		return smallPhotoLocation;
	}
	public void setSmallPhotoLocation(String smallPhotoLocation) {
		this.smallPhotoLocation = smallPhotoLocation;
	}
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	public String getPhotoLocation() {
		return photoLocation;
	}
	public void setPhotoLocation(String photoLocation) {
		this.photoLocation = photoLocation;
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
	

}
