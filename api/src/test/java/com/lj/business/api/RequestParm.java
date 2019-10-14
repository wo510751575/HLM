package com.lj.business.api;

import com.alibaba.fastjson.JSON;
import com.lj.business.cm.dto.wordsType.FindWordsTypePage;

public class RequestParm {

	public static void main(String[] args) {
		FindWordsTypePage type = new FindWordsTypePage();
		type.setLimit(10);
		type.setCode(null);
		type.setMerchantNo("1");
		String json = JSON.toJSONString(type);
		
		System.out.println("请求参数:"+json);
	}

}
