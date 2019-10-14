package com.lj.business.dao;

import java.util.List;
import java.util.Map;

import com.lj.business.ai.dto.MerchantAutoReplyDto;

public interface MerchantAutoReplyDao {
    
	
	public void  insertGmAutoStatus(Map<String ,String > map);
	
	List<MerchantAutoReplyDto> selectAutoReplyList(Map<String ,String > map);
	
	public void deleteAutoReplyList(Map<String ,String > map);
}
