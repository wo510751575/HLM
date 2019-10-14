package com.lj.business.api.dto.hc;

import java.io.Serializable;
import java.util.List;

import com.lj.business.api.dto.FindUnContactReturn;

/**
 * 
 * 
 * 类说明：服务管理-首页
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年9月25日
 */
public class ServiceMgrIndexPageReturn implements Serializable {

	private static final long serialVersionUID = 2788034755632055626L;

	/**
	 * 当日新增客户数
	 */
	private int newPmCount;
	
	/**
	 * 微信提醒数
	 */
	private long wxMsgCount;
	
	/**
	 * 客户数
	 */
	private int clientCount;
	
	/**
	 * 客户提醒
	 */
	private List<FindUnContactReturn> unContactList;

	/**
	 * @return the newPmCount
	 */
	public int getNewPmCount() {
		return newPmCount;
	}

	/**
	 * @param newPmCount the newPmCount to set
	 */
	public void setNewPmCount(int newPmCount) {
		this.newPmCount = newPmCount;
	}

	/**
	 * @return the wxMsgCount
	 */
	public long getWxMsgCount() {
		return wxMsgCount;
	}

	/**
	 * @param wxMsgCount the wxMsgCount to set
	 */
	public void setWxMsgCount(long wxMsgCount) {
		this.wxMsgCount = wxMsgCount;
	}

	/**
	 * @return the clientCount
	 */
	public int getClientCount() {
		return clientCount;
	}

	/**
	 * @param clientCount the clientCount to set
	 */
	public void setClientCount(int clientCount) {
		this.clientCount = clientCount;
	}

	/**
	 * @return the unContactList
	 */
	public List<FindUnContactReturn> getUnContactList() {
		return unContactList;
	}

	/**
	 * @param unContactList the unContactList to set
	 */
	public void setUnContactList(List<FindUnContactReturn> unContactList) {
		this.unContactList = unContactList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServiceMgrIndexPageReturn [newPmCount=");
		builder.append(newPmCount);
		builder.append(", wxMsgCount=");
		builder.append(wxMsgCount);
		builder.append(", clientCount=");
		builder.append(clientCount);
		builder.append(", unContactList=");
		builder.append(unContactList);
		builder.append("]");
		return builder.toString();
	}
	
}
