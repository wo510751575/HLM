package com.lj.business.supcon.service;

import com.lj.business.supcon.dto.friends.AddRedPackMessage;

public interface IRedPackFacade {
	
	
	/**
	 * 发送红包
	 */
	public void sendRedPackRequest(AddRedPackMessage redPackMessage);

}
