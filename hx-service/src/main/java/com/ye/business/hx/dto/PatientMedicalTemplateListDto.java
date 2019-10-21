package com.ye.business.hx.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PatientMedicalTemplateListDto implements Serializable { 

    /**
     *  .
     */
    private String code;

    /**
     * 名称 .
     */
    private String name;

    /**
     * 父 .
     */
    private String parentCode;

    /**
     * 父名称 .
     */
    private String parentName;

    /**
     * 排序（数值越大越后） .
     */
    private Integer orderNo;

    /**
     * 创建人 .
     */
    private String creater;

    /**
     * 创建时间 .
     */
    private Date createTime;
    
    
    private Integer levelCode;
    
    private String parentCodes;
    //类型:1-模板目录;2-模板
    private String type;
    
    

    
    /**  
	 * @Title:  getType <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getType() {
		return type;
	}

	/**  
	 * @Title:  setType <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**  
	 * @Title:  getParentCodes <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getParentCodes() {
		return parentCodes;
	}

	/**  
	 * @Title:  setParentCodes <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setParentCodes(String parentCodes) {
		this.parentCodes = parentCodes;
	}

	/**  
	 * @Title:  getLevelCode <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	public Integer getLevelCode() {
		return levelCode;
	}

	/**  
	 * @Title:  setLevelCode <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	public void setLevelCode(Integer levelCode) {
		this.levelCode = levelCode;
	}

	public static String getRootId() {
		return "1";
	}
    
    public static void sortList(List<PatientMedicalTemplateListDto> list, List<PatientMedicalTemplateListDto> sourcelist, String parentId, boolean cascade){
		for (int i=0; i<sourcelist.size(); i++){
			PatientMedicalTemplateListDto e = sourcelist.get(i);
			if (e.getParentCode()!=null&& e.getParentCode().equals(parentId)){
				list.add(e);
				if (cascade){
					// 判断是否还有子节点, 有则继续获取子节点
					for (int j=0; j<sourcelist.size(); j++){
						PatientMedicalTemplateListDto child = sourcelist.get(j);
						if (child.getParentCode()!=null && child.getParentCode().equals(e.getCode())){
							sortList(list, sourcelist, e.getCode(), true);
							break;
						}
					}
				}
			}
		}
	}
    
    /**
     *  .
     *
     */
    public String getCode() {
        return code;
    }

    /**
     *  .
     *
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 名称 .
     *
     */
    public String getName() {
        return name;
    }

    /**
     * 名称 .
     *
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 父 .
     *
     */
    public String getParentCode() {
        return parentCode;
    }

    /**
     * 父 .
     *
     */
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }

    /**
     * 父名称 .
     *
     */
    public String getParentName() {
        return parentName;
    }

    /**
     * 父名称 .
     *
     */
    public void setParentName(String parentName) {
        this.parentName = parentName == null ? null : parentName.trim();
    }

    /**
     * 排序（数值越大越后） .
     *
     */
    public Integer getOrderNo() {
        return orderNo;
    }

    /**
     * 排序（数值越大越后） .
     *
     */
    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 创建人 .
     *
     */
    public String getCreater() {
        return creater;
    }

    /**
     * 创建人 .
     *
     */
    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    /**
     * 创建时间 .
     *
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间 .
     *
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PatientMedicalTemplateList [code=").append(code);
        builder.append(",name=").append(name); 
        builder.append(",parentCode=").append(parentCode); 
        builder.append(",parentName=").append(parentName); 
        builder.append(",orderNo=").append(orderNo); 
        builder.append(",creater=").append(creater); 
        builder.append(",createTime=").append(createTime); 
        builder.append("]");
        return builder.toString();
    }
}
