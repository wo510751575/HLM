/**  
 * All rights Reserved, Designed By www.kehuzhitongche.com
 * @Title:  GumCheckVo.java   
 * @Package com.ye.business.hx.dto   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 贾光朝     
 * @date:   2019-11-01 11:19   
 * @version V1.0 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
package com.ye.business.hx.dto;

import java.util.List;

/**   
 * @ClassName:  GumCheckVo   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 贾光朝 
 * @date:   2019-11-01 11:19   
 *     
 * @Copyright: 2019 www.kehuzhitongche.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
public class GumCheckVo {

	private String location;
	
	private List<GumCheckDto> list;

	/**  
	 * @Title:  getLocation <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getLocation() {
		return location;
	}

	/**  
	 * @Title:  setLocation <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**  
	 * @Title:  getList <BR>  
	 * @Description: please write your description <BR>  
	 * @return: List<GumCheckDto> <BR>  
	 */
	public List<GumCheckDto> getList() {
		return list;
	}

	/**  
	 * @Title:  setList <BR>  
	 * @Description: please write your description <BR>  
	 * @return: List<GumCheckDto> <BR>  
	 */
	public void setList(List<GumCheckDto> list) {
		this.list = list;
	}

	
	
}
