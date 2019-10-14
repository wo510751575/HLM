package com.lj.business.member.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.domain.ShopTerminal;
import com.lj.business.member.dto.couponmultipush.FindShopTerminalByWxList;
import com.lj.business.member.dto.shopterminal.AddShopTerminal;
import com.lj.business.member.dto.shopterminal.AddShopTerminalReturn;
import com.lj.business.member.dto.shopterminal.FindShopTerminal;
import com.lj.business.member.dto.shopterminal.FindShopTerminalPage;
import com.lj.business.member.dto.shopterminal.FindShopTerminalPageReturn;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.dto.shopterminal.FindShopTidFromWeb;
import com.lj.business.member.dto.shopterminal.FindShopTidFromWebReturn;
import com.lj.business.member.dto.shopterminal.PmFlowQcode;
import com.lj.business.member.dto.shopterminal.TerminalSign;
import com.lj.business.member.dto.shopterminal.TerminalSignReturn;
import com.lj.business.member.dto.shopterminal.UpdateShopTerminal;
import com.lj.business.member.dto.shopterminal.UpdateShopTerminalReturn;
import com.lj.business.member.dto.shopterminal.UpdateTerminalWxPwd;
import com.lj.business.member.dto.shopterminal.UpdateWorkKey;


/**
 * 
 * 
 * 类说明：接口类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年11月1日
 */
public interface IShopTerminalService {
	
	
	/**
	 * 方法说明：根据微信查终端信息
	 */
	public FindShopTerminalReturn findShopTerminalByWx(String noWx) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：添加终端终端信息
	 *
	 * @param addShopTerminal
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月1日
	 *
	 */
	public AddShopTerminalReturn addShopTerminal(AddShopTerminal addShopTerminal) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找终端终端信息
	 *
	 * @param findShopTerminal
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月1日
	 *
	 */
	public FindShopTerminalReturn findShopTerminal(FindShopTerminal findShopTerminal) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：根据imei查找门店终端信息
	 *
	 * @param findShopTerminal
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月1日
	 *
	 */
//	public FindShopTerminalReturn findShopTerminalByImei(String imei) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：修改终端终端信息
	 *
	 * @param updateShopTerminal
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月1日
	 *
	 */
	public UpdateShopTerminalReturn updateShopTerminal(UpdateShopTerminal updateShopTerminal)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找终端终端信息
	 *
	 * @param findShopTerminalPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月1日
	 *
	 */
	public Page<FindShopTerminalPageReturn> findShopTerminalPage(FindShopTerminalPage findShopTerminalPage) throws TsfaServiceException;

	/**
	 *
	 * 方法说明：查找终端终端信息所有微信号
	 *
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月20日
	 *
	 */
	public List<String> findAllNoWx();

	/**
	 *
	 * 方法说明：查找终端终端信息所有手机传号
	 *
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月20日
	 *
	 */
	public List<String> findAllImei();

	/**
	 *
	 * 方法说明：查找终端终端信息微信号是否重复
	 *
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月27日
	 * @param string 
	 *
	 */
	public int hasNoWx(String noWx);

	/**
	 *
	 * 方法说明：查找终端终端信息手机串号是否重复
	 *
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月27日
	 * @param string 
	 *
	 */
	public int hasImei(String imei);
	
	/**
	 * 
	 *
	 * 方法说明：导购助手-终端终端（微信）列表查询
	 *
	 * @param findShopTidFromWeb
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月1日
	 *
	 */
	public List<FindShopTidFromWebReturn> findShopTerminalFromWeb(FindShopTidFromWeb findShopTidFromWeb) ;
	
	/**
	 * 
	 *
	 * 方法说明：根据shopNo查找终端终端信息
	 *
	 * @param findShopTerminal
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年12月9日
	 *
	 */
	public List<FindShopTerminalReturn> findShopTerminalByShopNo(String shopNo) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找终端终端信息
	 *
	 * @param findShopTerminal
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年12月11日
	 *
	 */
	public List<FindShopTerminalReturn> findShopTerminalSelect(FindShopTerminal findShopTerminal) throws TsfaServiceException;
	
	
	/**
	 * 通过微信号查询终端信息
	 * @param noWx
	 * @return
	 */
	public FindShopTerminalReturn findShopTerminalNormal(String noWx);
	/**
	 * 
	 *
	 * 方法说明：更新终端信息
	 *
	 * @param shopTerminal
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2018年1月6日
	 *
	 */
	public int updateShopTerminalShopName(UpdateShopTerminal updateShopTerminal) throws TsfaServiceException ;

	/**
	 * 
	 *
	 * 方法说明：检查并更新终端版本
	 *
	 * @param code
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月10日
	 * @return true 有版本更新，并已向中控发起更新请求
	 * 			false 已是最新版本
	 *
	 */
	public boolean checkAndUpdateVersion(String code);

	/**
	 * 
	 *
	 * 方法说明：校验工作微信号是否为终端有效微信号
	 *
	 * @param findShopTerminalPage
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月22日
	 * @return true  有效
	 * 		   false 无效
	 *
	 */
	public boolean validNoWx(FindShopTerminalPage findShopTerminalPage);


	
	/**
	 * 
	 * 方法说明：查询所有未绑定的终端
	 *
	 * @param @return    设定文件 
	 * @return List<ShopTerminal>    返回类型 
	 * @throws Exception
	 *
	 * @author 林进权
	 *         CreateDate: 2018年1月22日
	 */
	List<FindShopTerminalPageReturn> findShopTerminalsByNotBind(FindShopTerminalPage findShopTerminalPage);

	/**
	 * 
	 * 方法说明：查询所有已绑定的终端
	 *
	 * @param @return    设定文件 
	 * @return List<ShopTerminal>    返回类型 
	 * @throws Exception
	 *
	 * @author 林进权
	 *         CreateDate: 2018年1月22日
	 */
	public List<FindShopTerminalPageReturn> findShopTerminalsByBind(FindShopTerminalPage findShopTerminalPage);
	
	/**
	 * 
	 *
	 * 方法说明：更新终端微信账户余额（根据CODE或noWx更新）
	 *
	 * @param code
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月26日
	 *
	 */
	public void updateWxAccountBalance(UpdateShopTerminal updateShopTerminal);
	
	/**
	 * 
	 *
	 * 方法说明：更新工作密钥
	 *
	 * @param updateWorkKey
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月26日
	 *
	 */
	public void updateWorkKey(UpdateWorkKey updateWorkKey);
	
	/**
	 * 
	 *
	 * 方法说明：终端签到
	 *
	 * @param terminalSign
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月26日
	 *
	 */
	public TerminalSignReturn sign(TerminalSign terminalSign);
	
	/**
	 * 
	 *
	 * 方法说明：向终端下发签到命令
	 *
	 * @param code		终端CODE
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月27日
	 *
	 */
	public void sendTerminalSignCommand(String code);
	
	/**
	 * 
	 *
	 * 方法说明：设置终端微信支付密码
	 *
	 * @param updateTerminalWxPwd
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月27日
	 *
	 */
	public void updateTerminalWxPwd(UpdateTerminalWxPwd updateTerminalWxPwd);

	/**
	 * 
	 *
	 * 方法说明：根据选中的多个终端微信查询终端编号集合
	 *
	 * @param findShopTerminalByWxList
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2018年1月29日
	 *
	 */
    List<String> findShopTerminalByWxList(
            FindShopTerminalByWxList findShopTerminalByWxList);

    /**
     * 
     *
     * 方法说明：导购助手-好友有预报名的终端终端（微信）列表查询
     *
     * @param findShopTidFromWeb
     * @return
     *
     * @author 许新龙 CreateDate: 2018年3月21日
     *
     */
    List<FindShopTidFromWebReturn> findShopTerminalForecastName(
            FindShopTidFromWeb findShopTidFromWeb);
			
			
			
			   /**
     * 
    *
    * 方法说明：查找商户指定招聘类型的中控机
    *
    * @param findShopTerminal
    * @return
    *
    * @author 罗书明 CreateDate: 2018年3月26日
    *
    */
    public  List<FindShopTerminalReturn> findAllShopTerminalByWeiXin(FindShopTerminal findShopTerminal);
    
    /**
     * 
     *
     * 方法说明：开关终端上传朋友圈功能
     *
     * @param updateShopTerminal
     *
     * @author 曾垂瑜 CreateDate: 2018年8月15日
     *
     */
    public void updateUploadFriendsFlag(UpdateShopTerminal updateShopTerminal);
    
    
    
    /**
     * 根据导购微信和商户号查询终端信息
     * @param merchantNo
     * @param noWxGm
     * @return
     * @throws TsfaServiceException
     */
    public FindShopTerminalReturn findShopTerminalByMerchantNoAndNoWxGm(String merchantNo, String noWxGm) throws TsfaServiceException;
    
    
	   /**
		* 
		*
		* 方法说明：查找商户终端
		*
		* @param findShopTerminal
		* @return

		*
		*/
		public  List<FindShopTerminalReturn> findAllShopTerminalByMerchantNo(String merchantNo);
		
		/**
		 * 查询分流二维码组列表
		 * @param pmFlowQcode
		 */
		public List<PmFlowQcode> findFlowWxByMerchantNo(String merchantNo);
		
		/**
		 * 增加分流二维码组
		 * @param pmFlowQcode
		 */
		public void addFlowWxByMerchantNo(PmFlowQcode pmFlowQcode);
		
		/**
		 * 更新分流二维码组
		 * @param pmFlowQcode
		 */
		public void updateFlowWxByMerchantNo(PmFlowQcode pmFlowQcode);
		
		/**
		 * 查询分流二维码by code
		 * @param pmFlowQcode
		 */
		public List<PmFlowQcode> findFlowWxByCode(String code);
		
		/**
	     * 
	     *
	     * 方法说明：根据商户编号，获取微信总数
	     *
	     * @param updateShopTerminal
	     *
	     * @author gongwenxue CreateDate: 2018年8月15日
	     *
	     */
	    public Integer getShopTerminalCount(ShopTerminal shopTerminalEntity);

		/**
		 *@Desc 删除终端
		 *@param code
		 *@return void
		 *@author 贾光朝
		 *@createDate 2019年5月6日下午2:55:57
		 */
		public void delete(String code);
}
