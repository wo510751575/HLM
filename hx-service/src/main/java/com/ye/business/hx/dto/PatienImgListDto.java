/**  
 * All rights Reserved, Designed By www.kehuzhitongche.com
 * @Title:  PatienImgListDto.java   
 * @Package com.ye.business.hx.dto   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 贾光朝     
 * @date:   2019-10-26 18:01   
 * @version V1.0 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
package com.ye.business.hx.dto;

import java.io.Serializable;
import java.util.List;

import com.ye.business.hx.domain.PatientImg;

/**   
 * @ClassName:  PatienImgListDto   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 贾光朝 
 * @date:   2019-10-26 18:01   
 *     
 * @Copyright: 2019 www.kehuzhitongche.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
public class PatienImgListDto implements Serializable {

	  /**
	   * 预约时间 .
     */
    private String reservationDate;
    
    private List<PatientImg> list;

	/**  
	 * @Title:  getReservationDate <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getReservationDate() {
		return reservationDate;
	}

	/**  
	 * @Title:  setReservationDate <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	/**  
	 * @Title:  getList <BR>  
	 * @Description: please write your description <BR>  
	 * @return: List<PatientImg> <BR>  
	 */
	public List<PatientImg> getList() {
		return list;
	}

	/**  
	 * @Title:  setList <BR>  
	 * @Description: please write your description <BR>  
	 * @return: List<PatientImg> <BR>  
	 */
	public void setList(List<PatientImg> list) {
		this.list = list;
	}

	
    
    
}
