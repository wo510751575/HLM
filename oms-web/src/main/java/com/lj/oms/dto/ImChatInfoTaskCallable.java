package com.lj.oms.dto;

import java.util.concurrent.Callable;

import com.lj.business.weixin.dto.imchat.FindImChatInfo;
import com.lj.business.weixin.dto.imchat.FindImChatInfoReturn;
import com.lj.business.weixin.service.IImChatInfoService;

public class ImChatInfoTaskCallable implements Callable<FindImChatInfoReturn>{
	
	private IImChatInfoService imChatInfoService;
	private String code;
   
	public ImChatInfoTaskCallable(IImChatInfoService imChatInfoService, String code) {
		super();
		this.imChatInfoService = imChatInfoService;
		this.code = code;
	}

	@Override
    public FindImChatInfoReturn call() throws Exception {
		FindImChatInfo findImChatInfo = new FindImChatInfo();
		findImChatInfo.setCode(code);
		FindImChatInfoReturn findImChatInfoReturn= imChatInfoService.findImChatInfo(findImChatInfo);
        return findImChatInfoReturn;
    }

}
