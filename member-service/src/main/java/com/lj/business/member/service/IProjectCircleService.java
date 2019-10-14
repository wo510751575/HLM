package com.lj.business.member.service;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.domain.ProjectCircle;

/**
 * 
 * 
 * 类说明：
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
public interface IProjectCircleService {
    /**
     * 
     *
     * 方法说明：根据服务项目code查询项目周期列表
     *
     * @param projectCode
     * @return
     * @throws TsfaServiceException
     *
     * @author 许新龙 CreateDate: 2017年12月5日
     *
     */
    List<ProjectCircle> findByProjectCode(String projectCode);
}
