/**  
 * All rights Reserved, Designed By www.kehuzhitongche.com
 * @Title:  OrthodonticsProcessResultDto.java   
 * @Package com.ye.business.hx.dto   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 贾光朝     
 * @date:   2019-12-12 18:38   
 * @version V1.0 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
package com.ye.business.hx.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**   
 * @ClassName:  OrthodonticsProcessResultDto   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 贾光朝 
 * @date:   2019-12-12 18:38   
 *     
 * @Copyright: 2019 www.kehuzhitongche.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
public class OrthodonticsProcessResultDto implements Serializable {

	 /**
     * 创建日期 .
     */
    private Date createDate;
	
    private List<OrthodonticsProcessReturnDto> list;

	/**  
	 * @Title:  getCreateDate <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**  
	 * @Title:  setCreateDate <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**  
	 * @Title:  getList <BR>  
	 * @Description: please write your description <BR>  
	 * @return: List<OrthodonticsProcessReturnDto> <BR>  
	 */
	public List<OrthodonticsProcessReturnDto> getList() {
		return list;
	}

	/**  
	 * @Title:  setList <BR>  
	 * @Description: please write your description <BR>  
	 * @return: List<OrthodonticsProcessReturnDto> <BR>  
	 */
	public void setList(List<OrthodonticsProcessReturnDto> list) {
		this.list = list;
	}
    
    
   
	
}
