package com.ye.business.hx.dto;

import java.util.List;

public class PatientMedicalTemplateListVo extends PatientMedicalTemplateListDto { 

   private List<PatientMedicalTemplateListVo> children;

	/**  
	 * @Title:  getChildren <BR>  
	 * @Description: please write your description <BR>  
	 * @return: List<PatientMedicalTemplateListVo> <BR>  
	 */
	public List<PatientMedicalTemplateListVo> getChildren() {
		return children;
	}
	
	/**  
	 * @Title:  setChildren <BR>  
	 * @Description: please write your description <BR>  
	 * @return: List<PatientMedicalTemplateListVo> <BR>  
	 */
	public void setChildren(List<PatientMedicalTemplateListVo> children) {
		this.children = children;
	}

	
   
   
}
