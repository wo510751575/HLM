package com.lj.business.st.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 * <p>
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 */

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.dto.CfCountAnalyze.AddCfCountAnalyze;
import com.lj.business.st.dto.CfCountAnalyze.FindCfCountAnalyze;
import com.lj.business.st.dto.CfCountAnalyze.FindCfCountAnalyzeReturn;

import java.util.List;


/**
 * 类说明：接口类
 *
 *
 * <p>
 * 详细描述：.
 *
 * @author 彭阳
 *
 *
 * CreateDate: 2017-06-14
 */
public interface ICfCountAnalyzeService {

    /**
     *
     *
     * 方法说明：添加跟进次数分析表信息
     *
     * @param addCfCountAnalyze
     * @return
     * @throws TsfaServiceException
     *
     * @author 彭阳 CreateDate: 2017年07月10日
     *
     */
    public void addCfCountAnalyze(AddCfCountAnalyze addCfCountAnalyze) throws TsfaServiceException;

    /**
     *
     *
     * 方法说明：查找客户跟进次数
     *
     * @param findCfCountAnalyze
     * @return
     * @throws TsfaServiceException
     *
     */
    public List<FindCfCountAnalyzeReturn> findCfCountAnalyzeList(FindCfCountAnalyze findCfCountAnalyze) throws TsfaServiceException;

}
