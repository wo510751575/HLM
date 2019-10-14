package com.lj.business.api.dto.hc;

import java.io.Serializable;
import java.util.List;

import com.lj.business.api.dto.FindPmTypeIndexAllReturn;

/**
 * 
 * 
 * 类说明：顾客管理首页：分组信息及其明细查询
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年9月21日
 */
public class ClientMgrFirstPageReturn implements Serializable{

	private static final long serialVersionUID = -1865987282726489996L;

	/**
	 * 顾客总数
	 */
	private int clientTotal = 0;
	
	/**
	 * 顾客分组及分组明细
	 */
	private List<FindPmTypeIndexAllReturn> clientList;
	
	/**
	 * 顾客消费次数MAP，key=memberNo会员编号, value=消费次数
	 */
//	private Map<String, Integer> countMap;

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
	public List<FindPmTypeIndexAllReturn> getClientList() {
		return clientList;
	}


	/**
	 * @param clientList the clientList to set
	 */
	public void setClientList(List<FindPmTypeIndexAllReturn> clientList) {
		this.clientList = clientList;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClientMgrFirstPageReturn [clientTotal=");
		builder.append(clientTotal);
		builder.append(", clientList=");
		builder.append(clientList);
		builder.append("]");
		return builder.toString();
	}

	
}
