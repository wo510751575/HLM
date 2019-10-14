package com.lj.business.member.service.impl;
import java.util.List;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lj.base.core.util.AssertUtils;
import com.lj.business.member.dao.IProjectCircleDao;
import com.lj.business.member.domain.ProjectCircle;
import com.lj.business.member.service.IProjectCircleService;

/**
 * 
 * 
 * 类说明：服务项目周期实现类
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
@Service
public class ProjectCircleServiceImpl implements IProjectCircleService {
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(ProjectCircleServiceImpl.class);
    
    @Autowired
    private IProjectCircleDao projectCircleDao;

    @Override
    public List<ProjectCircle> findByProjectCode(String projectCode) {
        logger.debug("findByProjectCode(String projectCode={}) - start", projectCode); 

        AssertUtils.notNullAndEmpty(projectCode, "项目CODE不能为空");
        
        ProjectCircle projectCircle = new ProjectCircle();
        projectCircle.setProjectCode(projectCode);
        
        List<ProjectCircle> projectCircles = projectCircleDao.selectBySelective(projectCircle);

        logger.debug("findByProjectCode(String) - end - return value={}", projectCircles); 
        return projectCircles;
    }

}
