package com.lj.business.cm.dto;

import java.util.Date;

import com.lj.base.core.pagination.PageParamEntity;
/**
 * 
 * 类说明：朋友圈图片素材
 * <p>
 * 详细描述：FRIENDS_IMAGE_MATERIAL表传参实体
 * @Company: 扬恩科技有限公司
 * @author 李端强
 * CreateDate: 2017年12月20日
 */
public class FriendsImageMaterialDto extends PageParamEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7138169106103462498L;

	/**
     * CODE  UUID
     */
    private String code;

    /**
     * 商户编号
     */
    private String merchantNo;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 图片地址（多个时以逗号分隔）
     */
    private String imgAddr;

    /**
     * 素材类型 1.朋友圈广告素材 2.朋友圈维护素材
     */
    private String materialType;

    /**
     * 是否自动评论：0否、1是
     */
    private Integer autoComment;

    /**
     * 自动评论内容
     */
    private String commentContent;

    /**
     * 删除标识：0未删除、1已删除
     */
    private Integer deleteFlag;

    /**
     * 创建人ID
     */
    private String createId;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 备注2
     */
    private String remark2;

    /**
     * 备注3
     */
    private String remark3;

    /**
     * 备注4
     */
    private String remark4;
    
    /**
     * 起始时间 yyyy-MM-dd HH:mm:ss
     */
    private String startDate;
    
    /**
     * 截止时间 yyyy-MM-dd HH:mm:ss
     */
    private String endDate;
    
    /**
     * 名称,变量值,内容等等
     */
    private String conditionStr;

    /**
     * CODE  UUID
     */
    public String getCode() {
        return code;
    }

    /**
     * CODE  UUID
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 商户编号
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * 商户编号
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    /**
     * 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 图片地址（多个时以逗号分隔）
     */
    public String getImgAddr() {
        return imgAddr;
    }

    /**
     * 图片地址（多个时以逗号分隔）
     */
    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr == null ? null : imgAddr.trim();
    }

    /**
     * 素材类型 1.朋友圈广告素材 2.朋友圈维护素材
     */
    public String getMaterialType() {
        return materialType;
    }

    /**
     * 素材类型 1.朋友圈广告素材 2.朋友圈维护素材
     */
    public void setMaterialType(String materialType) {
        this.materialType = materialType == null ? null : materialType.trim();
    }

    /**
     * 是否自动评论：0否、1是
     */
    public Integer getAutoComment() {
        return autoComment;
    }

    /**
     * 是否自动评论：0否、1是
     */
    public void setAutoComment(Integer autoComment) {
        this.autoComment = autoComment;
    }

    /**
     * 自动评论内容
     */
    public String getCommentContent() {
        return commentContent;
    }

    /**
     * 自动评论内容
     */
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    /**
     * 删除标识：0未删除、1已删除
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * 删除标识：0未删除、1已删除
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * 创建人
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * 创建人
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    /**
     * 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 备注2
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * 备注2
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    /**
     * 备注3
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     * 备注3
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    /**
     * 备注4
     */
    public String getRemark4() {
        return remark4;
    }

    /**
     * 备注4
     */
    public void setRemark4(String remark4) {
        this.remark4 = remark4;
    }
    /**
     * 起始时间 yyyy-MM-dd HH:mm:ss
     */
    public String getStartDate() {
		return startDate;
	}
    /**
     * 起始时间 yyyy-MM-dd HH:mm:ss
     */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
     * 截止时间 yyyy-MM-dd HH:mm:ss
     */
	public String getEndDate() {
		return endDate;
	}
	/**
     * 截止时间 yyyy-MM-dd HH:mm:ss
     */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
     * 名称,变量值,内容等等
     */
	public String getConditionStr() {
		return conditionStr;
	}
	/**
     * 名称,变量值,内容等等
     */
	public void setConditionStr(String conditionStr) {
		this.conditionStr = conditionStr;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friends_image_material
     *
     * @mbggenerated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        FriendsImageMaterialDto other = (FriendsImageMaterialDto) that;
        return (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getMerchantNo() == null ? other.getMerchantNo() == null : this.getMerchantNo().equals(other.getMerchantNo()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getImgAddr() == null ? other.getImgAddr() == null : this.getImgAddr().equals(other.getImgAddr()))
            && (this.getMaterialType() == null ? other.getMaterialType() == null : this.getMaterialType().equals(other.getMaterialType()))
            && (this.getAutoComment() == null ? other.getAutoComment() == null : this.getAutoComment().equals(other.getAutoComment()))
            && (this.getCommentContent() == null ? other.getCommentContent() == null : this.getCommentContent().equals(other.getCommentContent()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getCreateId() == null ? other.getCreateId() == null : this.getCreateId().equals(other.getCreateId()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getRemark2() == null ? other.getRemark2() == null : this.getRemark2().equals(other.getRemark2()))
            && (this.getRemark3() == null ? other.getRemark3() == null : this.getRemark3().equals(other.getRemark3()))
            && (this.getRemark4() == null ? other.getRemark4() == null : this.getRemark4().equals(other.getRemark4()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friends_image_material
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getMerchantNo() == null) ? 0 : getMerchantNo().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getImgAddr() == null) ? 0 : getImgAddr().hashCode());
        result = prime * result + ((getMaterialType() == null) ? 0 : getMaterialType().hashCode());
        result = prime * result + ((getAutoComment() == null) ? 0 : getAutoComment().hashCode());
        result = prime * result + ((getCommentContent() == null) ? 0 : getCommentContent().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getCreateId() == null) ? 0 : getCreateId().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getRemark2() == null) ? 0 : getRemark2().hashCode());
        result = prime * result + ((getRemark3() == null) ? 0 : getRemark3().hashCode());
        result = prime * result + ((getRemark4() == null) ? 0 : getRemark4().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friends_image_material
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", code=").append(code);
        sb.append(", merchantNo=").append(merchantNo);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", imgAddr=").append(imgAddr);
        sb.append(", materialType=").append(materialType);
        sb.append(", autoComment=").append(autoComment);
        sb.append(", commentContent=").append(commentContent);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", createId=").append(createId);
        sb.append(", createDate=").append(createDate);
        sb.append(", remark=").append(remark);
        sb.append(", remark2=").append(remark2);
        sb.append(", remark3=").append(remark3);
        sb.append(", remark4=").append(remark4);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}