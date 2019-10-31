/**  
 * All rights Reserved, Designed By www.kehuzhitongche.com
 * @Title:  PatientSymptomVo.java   
 * @Package com.ye.business.hx.dto   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 贾光朝     
 * @date:   2019-10-30 11:40   
 * @version V1.0 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
package com.ye.business.hx.dto;

import java.util.List;

/**   
 * @ClassName:  PatientSymptomVo   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 贾光朝 
 * @date:   2019-10-30 11:40   
 *     
 * @Copyright: 2019 www.kehuzhitongche.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
public class PatientSymptomVo extends PatientSymptomDto {

	private List<PatientSymptomVo> children;

	/**  
	 * @Title:  getChildren <BR>  
	 * @Description: please write your description <BR>  
	 * @return: List<PatientSymptomVo> <BR>  
	 */
	public List<PatientSymptomVo> getChildren() {
		return children;
	}

	/**  
	 * @Title:  setChildren <BR>  
	 * @Description: please write your description <BR>  
	 * @return: List<PatientSymptomVo> <BR>  
	 */
	public void setChildren(List<PatientSymptomVo> children) {
		this.children = children;
	}

	

	
	
	
}
