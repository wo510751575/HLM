package com.lj.business.cm.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class AddMaterial.
 */
public class AddMaterialPhoto implements Serializable { 

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3067755891871003459L;

	private String code;
	private String photoName;
	private String folderName;
	private String parentId;
	private String parentIds;
	private Double size;
	private String photoLocation;
	private String smallPhotoLocation;
	private Date updateTime;
	private int count;
	private int length;
	private int width;
	/**
     * 商户号
     */
    private String merchantNo;
    
    
    
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
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
	public String getPhotoLocation() {
		return photoLocation;
	}
	public void setPhotoLocation(String photoLocation) {
		this.photoLocation = photoLocation;
	}
	public String getSmallPhotoLocation() {
		return smallPhotoLocation;
	}
	public void setSmallPhotoLocation(String smallPhotoLocation) {
		this.smallPhotoLocation = smallPhotoLocation;
	}
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
	
    
    
}
