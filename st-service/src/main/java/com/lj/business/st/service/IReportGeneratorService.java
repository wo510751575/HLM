package com.lj.business.st.service;

import java.util.Date;

import com.lj.base.exception.TsfaServiceException;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */

/**
 * 
 * 
 * 类说明：报表生成SERVICE
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年8月2日
 */
public interface IReportGeneratorService {

	
	
	
	/**
	 * 
	 *
	 * 方法说明：生成导购工作统计
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 梅宏博  CreateDate: 2017年8月19日
	 *
	 */
	public void generatorWorkRatioGm(Date date) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：生成门店工作统计
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 梅宏博  CreateDate: 2017年8月21日
	 *
	 */
	public void generatorWorkRatioShop(Date date) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：生成到店体验统计
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年8月2日
	 *
	 */
	public void generatorClientExpTotal(Date date) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：生成员工画像
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年8月2日
	 *
	 */
	public void generatorEmployeeAnalyze() throws TsfaServiceException; //2 T
	
	/**
	 * 
	 *
	 * 方法说明：生成客户标签统计
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年8月7日
	 *
	 */
	public void generatorPmLabelTotal() throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：素材中心统计
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 梅宏博 CreateDate: 2017年8月18日
	 *
	 */
	public void generatorMaterialTotal() throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：统计跟踪分析
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年8月7日
	 *
	 */
	public void generatorCfCountAnalyze()throws TsfaServiceException; //2 T
	/**
	 * 
	 *
	 * 方法说明：跟进分析摘要
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年8月7日
	 *
	 */
    public void generatorCfAnalyze(Date date)throws TsfaServiceException; //2  T
    
    /**
     * 
     
     * 方法说明：区域订单统计
     *
     * @throws TsfaServiceException
     *
     * @author 罗书明 CreateDate: 2017年8月7日
     *
     */
    public void generatorAreaOrderAnalyze()throws TsfaServiceException; //2 T
    
    /**
     * 
     *
     * 方法说明：区域工作统计
     *
     * @throws TsfaServiceException
     *
     * @author 罗书明 CreateDate: 2017年8月7日
     *
     */
    public void generatorWorkRatioArea()throws TsfaServiceException; //1 
    /**
     * 
     *
     * 方法说明：运营分析摘要统计
     *
     * @throws TsfaServiceException
     *
     * @author 罗书明 CreateDate: 2017年8月7日
     *
     */
   public void generatorOperationAnalysisDayBrief(Date date) throws TsfaServiceException; //2T
	
    /**
     * 
     *
     * 方法说明：客户咨询统计
     *
     * @throws TsfaServiceException
     *
     * @author 罗书明 CreateDate: 2017年8月7日
     *
     */
    public void generatorPmTalkTotal() throws TsfaServiceException; //2 T
    
    
    /**
     * 
     *
     * 方法说明：社交分析统计
     *
     * @throws TsfaServiceException
     *
     * @author 刘培 CreateDate: 2017年8月15日
     *
     */
    public void generatorSocialAnalyze(Date date) throws TsfaServiceException; //2 T
    
    /**
     * 
     *
     * 方法说明：未联系客户分析统计
     *
     * @throws TsfaServiceException
     *
     * @author 刘培 CreateDate: 2017年8月19日
     *
     */
    void generatorUnContactAnalyze() throws TsfaServiceException;//T
    
	/**
	 * 
	 *
	 * 方法说明：客户分类
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年8月21日
	 *
	 */
    public void  generatorPmTypeTotal()throws TsfaServiceException; //2 T

    
   /**
    * 
    *
    * 方法说明：增加或更新客户画像统计
    *
    *
    * @return
    * @throws TsfaServiceException
    *
    * @author 彭阳 CreateDate: 2017年8月29日
    *
    */
	int addOrUpdateClientAnalyzeStatistics() throws TsfaServiceException;;
	
    
}
