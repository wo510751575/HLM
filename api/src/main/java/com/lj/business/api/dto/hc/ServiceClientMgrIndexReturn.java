package com.lj.business.api.dto.hc;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 
 * 类说明：服务管理-客情管理-首页 返回参数
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年9月23日
 */
public class ServiceClientMgrIndexReturn implements Serializable{

	private static final long serialVersionUID = 7946333942797602065L;

	/**
	 * 顾客总数
	 */
	private int clientTotal;
	
	/**
	 * 分组及顾客明细
	 */
	private List<ServiceClientPmTypeIndexReturn> clientList;
	
	/**
	 * @return the clientTotal
	 */
	public int getClientTotal() {
		return clientTotal;
	}


	/**
	 * @param clientTotal the clientTotal to set
	 */
	public void setClientTotal(int clientTotal) {
		this.clientTotal = clientTotal;
	}


	/**
	 * @return the clientList
	 */
	public List<ServiceClientPmTypeIndexReturn> getClientList() {
		return clientList;
	}


	/**
	 * @param clientList the clientList to set
	 */
	public void setClientList(List<ServiceClientPmTypeIndexReturn> clientList) {
		this.clientList = clientList;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServiceClientMgrIndexReturn [clientTotal=");
		builder.append(clientTotal);
		builder.append(", clientList=");
		builder.append(clientList);
		builder.append("]");
		return builder.toString();
	}

}
