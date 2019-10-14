package com.lj.business.weixin.service;

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
public interface IFriendPointCycleService {
    
    public int deleteByPrimaryKey(String code);

    public int insert(FriendPointCycle record);

    public int insertSelective(FriendPointCycle record);

    public FriendPointCycle selectByPrimaryKey(String code);
    /**
     * 根据客户编号查询朋友圈提示周期
     * @param memberNo
     * @return
     * @author 李端强
     */
    public FriendPointCycle selectByMemberNo(String memberNo);
    /**
     * 根据客户编号修改朋友圈提示周期
     * @param memberNo
     * @return
     * @author 李端强
     */
    public int updateByMemberNo(FriendPointCycle memberNo);

    public int updateByPrimaryKey(FriendPointCycle record);
}