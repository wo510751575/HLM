package com.lj.business.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lj.business.member.domain.PmLabelPm;
import com.lj.business.member.dto.ChangePmLabel;
import com.lj.business.member.dto.PmLabelDto;

public interface IPmLabelPmDao {
	
    int deleteByPrimaryKey(PmLabelPm pmLabelPm);

    int insertOrUpdate(PmLabelPm pmLabelPm);
    
    /**
     * 根据条件查询
     * @param parmap
     * @return
     */
    List<PmLabelDto> findPmLabelByCond(Map<String,String> parmap);
    
    /**
     * 
     *
     * 方法说明：根据客户编号和商户编号删除标签关联数据
     *
     * @param changePmLabel
     *
     * @author 许新龙 CreateDate: 2017年12月8日
     *
     */
    void deleteByMemberNoAndMerchantNo(ChangePmLabel changePmLabel);

    
    /**
     * 根据商户号And终端微信获取标签列表（包含统计数和客户基本信息）
     * @param parmap
     * @return
     */
    List<Map<String,String>> findPmLabelByMerchantNoAndShopWx(Map<String,String> parmap);
    
    /**
     * 同步标签名
     * @param oldLabelName
     * @param labelName
     * @return
     */
    int synPmLabelName(@Param("labelCode") String labelCode,@Param("labelName")  String labelName);
}