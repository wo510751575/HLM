/**  
 * All rights Reserved, Designed By www.kehuzhitongche.com
 * @Title:  OrthodonticsProcessReturnDto.java   
 * @Package com.ye.business.hx.dto   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 贾光朝     
 * @date:   2019-12-12 17:06   
 * @version V1.0 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
package com.ye.business.hx.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ye.business.hx.domain.OrthodonticsProcess;

/**   
 * @ClassName:  OrthodonticsProcessReturnDto   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 贾光朝 
 * @date:   2019-12-12 17:06   
 *     
 * @Copyright: 2019 www.kehuzhitongche.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
public class OrthodonticsProcessReturnDto implements Serializable {

	private List<OrthodonticsProcessDto> mangement;
	
	private List<OrthodonticsProcessDto> check;
	
	private List<OrthodonticsProcessDto> doctorAdvice;
	
	 /**
     * 患者编号 .
     */
    private String patientNo;

    /**
     * 经治医生 .
     */
    private String attendingDoctor;

    /**
     * 护士 .
     */
    private String nurse;

    /**
     * 创建日期 .
     */
    private Date createDate;

	/**  
	 * @Title:  getMangement <BR>  
	 * @Description: please write your description <BR>  
	 * @return: List<OrthodonticsProcessDto> <BR>  
	 */
	public List<OrthodonticsProcessDto> getMangement() {
		return mangement;
	}

	/**  
	 * @Title:  setMangement <BR>  
	 * @Description: please write your description <BR>  
	 * @return: List<OrthodonticsProcessDto> <BR>  
	 */
	public void setMangement(List<OrthodonticsProcessDto> mangement) {
		this.mangement = mangement;
	}

	/**  
	 * @Title:  getCheck <BR>  
	 * @Description: please write your description <BR>  
	 * @return: List<OrthodonticsProcessDto> <BR>  
	 */
	public List<OrthodonticsProcessDto> getCheck() {
		return check;
	}

	/**  
	 * @Title:  setCheck <BR>  
	 * @Description: please write your description <BR>  
	 * @return: List<OrthodonticsProcessDto> <BR>  
	 */
	public void setCheck(List<OrthodonticsProcessDto> check) {
		this.check = check;
	}

	/**  
	 * @Title:  getDoctorAdvice <BR>  
	 * @Description: please write your description <BR>  
	 * @return: List<OrthodonticsProcessDto> <BR>  
	 */
	public List<OrthodonticsProcessDto> getDoctorAdvice() {
		return doctorAdvice;
	}

	/**  
	 * @Title:  setDoctorAdvice <BR>  
	 * @Description: please write your description <BR>  
	 * @return: List<OrthodonticsProcessDto> <BR>  
	 */
	public void setDoctorAdvice(List<OrthodonticsProcessDto> doctorAdvice) {
		this.doctorAdvice = doctorAdvice;
	}

	/**  
	 * @Title:  getPatientNo <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getPatientNo() {
		return patientNo;
	}

	/**  
	 * @Title:  setPatientNo <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setPatientNo(String patientNo) {
		this.patientNo = patientNo;
	}

	/**  
	 * @Title:  getAttendingDoctor <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getAttendingDoctor() {
		return attendingDoctor;
	}

	/**  
	 * @Title:  setAttendingDoctor <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setAttendingDoctor(String attendingDoctor) {
		this.attendingDoctor = attendingDoctor;
	}

	/**  
	 * @Title:  getNurse <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getNurse() {
		return nurse;
	}

	/**  
	 * @Title:  setNurse <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setNurse(String nurse) {
		this.nurse = nurse;
	}

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

	
	
	
}
