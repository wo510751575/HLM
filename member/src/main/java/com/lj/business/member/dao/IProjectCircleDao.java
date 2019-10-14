package com.lj.business.member.dao;

import java.util.List;
import com.lj.business.member.domain.ProjectCircle;

/**
 * 
 * 
 * 类说明：项目周期dao类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 许新龙
 *   
 * CreateDate: 2017年12月5日
 */
public interface IProjectCircleDao {
    int deleteByPrimaryKey(String code);

    int insert(ProjectCircle record);

    int insertSelective(ProjectCircle record);

    List<ProjectCircle> selectBySelective(ProjectCircle record);

    ProjectCircle selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ProjectCircle record);

    int updateByPrimaryKey(ProjectCircle record);
}