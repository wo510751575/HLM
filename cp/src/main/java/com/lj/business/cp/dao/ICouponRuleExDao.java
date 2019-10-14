package com.lj.business.cp.dao;

import java.util.List;

import com.lj.business.cp.domain.CouponRuleEx;
import com.lj.business.cp.dto.couponRuleEx.FindCouponRuleExPage;
import com.lj.business.cp.dto.couponRuleEx.FindCouponRuleExPageReturn;
import com.lj.business.cp.dto.couponRuleEx.UpdateCouponRuleEx;

public interface ICouponRuleExDao {
    int deleteByPrimaryKey(String code);

    int insert(CouponRuleEx record);

    int insertSelective(CouponRuleEx record);

    CouponRuleEx selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(CouponRuleEx record);

    int updateByPrimaryKey(CouponRuleEx record);

	List<FindCouponRuleExPageReturn> findCouponRuleExPage(FindCouponRuleExPage findCouponRuleExPage);

	int findCouponRuleExPageCount(FindCouponRuleExPage findCouponRuleExPage);
	
	int  updateCouponUseNum(CouponRuleEx couponRuleEx);
	
	CouponRuleEx findCouponRuleExByRuleCode(String ruleCode);
	
	int updateCouponCv(UpdateCouponRuleEx updateCouponRuleEx);
	 
	int updateCouponRuleExData(UpdateCouponRuleEx couponRuleEx);
	 
	
	
}