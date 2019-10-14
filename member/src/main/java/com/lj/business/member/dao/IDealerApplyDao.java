package com.lj.business.member.dao;

import java.util.List;
import com.lj.business.member.domain.DealerApply;
import com.lj.business.member.dto.company.FindDealerApplyDetailReturn;
import com.lj.business.member.dto.company.FindDealerApplyPage;
import com.lj.business.member.dto.company.FindDealerApplyPageReturn;

public interface IDealerApplyDao {
    int deleteByPrimaryKey(String code);

    int insert(DealerApply record);

    int insertSelective(DealerApply record);

    DealerApply selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(DealerApply record);

    int updateByPrimaryKey(DealerApply record);

    int findDealerApplyPageCount(FindDealerApplyPage findDealerApplyPage);

    /**
     * 
     *
     * 方法说明：分页查询经销商申请列表
     *
     * @param findDealerApplyPage
     * @return
     *
     * @author 许新龙 CreateDate: 2018年5月8日
     *
     */
    List<FindDealerApplyPageReturn> findDealerApplyPage(FindDealerApplyPage findDealerApplyPage);

    /**
     * 
     *
     * 方法说明：经销商入驻申请详情
     *
     * @param dealerApplyCode
     * @return
     *
     * @author 许新龙 CreateDate: 2018年5月24日
     *
     */
    FindDealerApplyDetailReturn findDealerApplyDetail(String dealerApplyCode);
}