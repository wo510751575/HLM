package com.lj.business.member.dao;

import java.util.List;

import com.lj.business.member.dto.shopterminal.PmFlowQcode;

public interface IFlowQcodeDao {
	 public List<PmFlowQcode> findFlowWxByMerchantNo(String merchantNo);
	 
	 public void addFlowWxByMerchantNo(PmFlowQcode pmFlowQcode);
	 
	 public void updateFlowWxByMerchantNo(PmFlowQcode pmFlowQcode);
	 
	 List<PmFlowQcode> findFlowWxByCode(String code);
}
