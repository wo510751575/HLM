package com.lj.business.weixin.dao;

import com.lj.business.weixin.domain.FriendPointCycle;
/**
 * 
 * 类说明：客户朋友圈提示周期
 * <p>
 * 详细描述：客户朋友圈提示周期(导购助手端)
 * @Company: 扬恩科技有限公司
 * @author 李端强
 * CreateDate: 2018年1月26日16:29:39
 */
public interface IFriendPointCycleDao {
    
    int deleteByPrimaryKey(String code);

    int insert(FriendPointCycle record);

    int insertSelective(FriendPointCycle record);

    FriendPointCycle selectByPrimaryKey(String code);
    /**
     * 根据客户编号查询朋友圈提示周期
     * @param memberNo
     * @return
     * @author 李端强
     */
    FriendPointCycle selectByMemberNo(String memberNo);
    
    int updateByPrimaryKeySelective(FriendPointCycle record);

    int updateByPrimaryKey(FriendPointCycle record);
}