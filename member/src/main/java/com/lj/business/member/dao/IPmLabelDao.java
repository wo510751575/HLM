package com.lj.business.member.dao;

import java.util.List;
import java.util.Map;

import com.lj.business.member.domain.PmLabel;
import com.lj.business.member.dto.AddPmLabel;
import com.lj.business.member.dto.FindPmLabelPage;
import com.lj.business.member.dto.FindPmLabelPageReturn;
import com.lj.business.member.dto.FindPmLabelReturnList;
import com.lj.business.member.dto.PmLabelDto;

public interface IPmLabelDao {
    int deleteByPrimaryKey(String code);

    int insert(PmLabel record);

    int insertSelective(PmLabel record);

    PmLabel selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(PmLabel record);

    int updateByPrimaryKey(PmLabel record);

	List<FindPmLabelPageReturn> findPmLabelPage(FindPmLabelPage findPmLabelPage);

	int findPmLabelPageCount(FindPmLabelPage findPmLabelPage);
	
	/**
	 * 
	 *
	 * 方法说明：导购维度统计
	 *
	 * @param findPmLabel
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年8月4日
	 *
	 */
	List<FindPmLabelReturnList> findPmlabelGuidMember();
	/**
	 * 
	 *
	 * 方法说明：商户维度统计
	 *
	 * @param findPmLabel
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年8月4日
	 *
	 */
	List<FindPmLabelReturnList> findPmlabelMerchantNo();
	/**
	 * 
	 *
	 * 方法说明：终端维度统计
	 *
	 * @param findPmLabel
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年8月4日
	 *
	 */
	List<FindPmLabelReturnList> findPmlabelShop();
	/**
	 * 
	 *
	 * 方法说明：区域维度统计
	 *
	 * @param findPmLabel
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年8月4日
	 *
	 */
	List<FindPmLabelReturnList> findPmlabelAreaCode();
	
	/**
	 * 
	 *
	 * 方法说明：根据商户号查询标签列表
	 *
	 * @param merchantNo
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2017年12月8日
	 *
	 */
	List<PmLabelDto> findPmLabelByMerchantNo(String merchantNo);
	
	/**
	 *
	 * 方法说明：根据商户号/上次更新时间查询标签列表
	 * @param merchantNo
	 * @param lastTime 上次最后更新时间
	 * @author 李端强 2018年1月6日18:35:13
	 * @return
	 */
	List<PmLabelDto> findPmLabelByLastTime(Map<String, Object> paramMap);

	/**
	 *@Desc 
	 *@param addPmLabel
	 *@return
	 *@return int
	 *@author 贾光朝
	 *@createDate 2019年5月29日下午2:09:13
	 */
	int selectCountByMerchantNo(AddPmLabel addPmLabel);
	
   
}