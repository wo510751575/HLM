package com.lj.business.weixin.service;

import com.lj.base.exception.TsfaServiceException;

public interface IWXJobHandler {
	
	
	public void toHandler(String code)throws TsfaServiceException;
	
	
	
	public void reHandler(String detailCode)throws TsfaServiceException;

}
