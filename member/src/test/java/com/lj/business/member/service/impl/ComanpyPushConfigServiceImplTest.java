package com.lj.business.member.service.impl;

import java.util.Date;
import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.Test;
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.member.dto.company.AddComanpyPushConfig;
import com.lj.business.member.dto.company.DelComanpyPushConfig;
import com.lj.business.member.dto.company.FindComanpyPushConfig;
import com.lj.business.member.dto.company.FindCompanyPushConfigPage;
import com.lj.business.member.dto.company.FindComanpyPushConfigPageReturn;
import com.lj.business.member.dto.company.UpdateComanpyPushConfig;
import com.lj.business.member.service.IComanpyPushConfigService;


/**
 * 
 * 
 * 类说明：经销商推送配置测试类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年03月22日
 */
public class ComanpyPushConfigServiceImplTest extends SpringTestCase{

	@Resource
	private IComanpyPushConfigService comanpyPushConfigService;

	/**
	 * 
	 *
	 * 方法说明：添加经销商推送配置信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void addComanpyPushConfig() throws TsfaServiceException{
		AddComanpyPushConfig addComanpyPushConfig = new AddComanpyPushConfig();
		//add数据录入
		addComanpyPushConfig.setCode("Code");
		addComanpyPushConfig.setCompanyNo("CompanyNo");
		addComanpyPushConfig.setCompanyName("CompanyName");
		addComanpyPushConfig.setStatus("Status");
		addComanpyPushConfig.setSort(1);
		addComanpyPushConfig.setType("Type");
		addComanpyPushConfig.setContent("Content");
		addComanpyPushConfig.setMerchantNo("MerchantNo");
		addComanpyPushConfig.setCreateDate(new Date());
		addComanpyPushConfig.setUpdateDate(new Date());
		addComanpyPushConfig.setRemark("Remark");
		
		Assert.assertNotNull(comanpyPushConfigService.addComanpyPushConfig(addComanpyPushConfig ));
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改经销商推送配置信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void updateComanpyPushConfig() throws TsfaServiceException{
		UpdateComanpyPushConfig updateComanpyPushConfig = new UpdateComanpyPushConfig();
		//update数据录入
		updateComanpyPushConfig.setCode("Code");
		updateComanpyPushConfig.setCompanyNo("CompanyNo");
		updateComanpyPushConfig.setCompanyName("CompanyName");
		updateComanpyPushConfig.setStatus("Status");
		updateComanpyPushConfig.setSort(1);
		updateComanpyPushConfig.setType("Type");
		updateComanpyPushConfig.setContent("Content");
		updateComanpyPushConfig.setMerchantNo("MerchantNo");
		updateComanpyPushConfig.setCreateDate(new Date());
		updateComanpyPushConfig.setUpdateDate(new Date());
		updateComanpyPushConfig.setRemark("Remark");

		comanpyPushConfigService.updateComanpyPushConfig(updateComanpyPushConfig );
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找经销商推送配置信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void findComanpyPushConfig() throws TsfaServiceException{
		FindComanpyPushConfig findComanpyPushConfig = new FindComanpyPushConfig();
		findComanpyPushConfig.setCode("");
		comanpyPushConfigService.findComanpyPushConfig(findComanpyPushConfig);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找经销商推送配置信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void findComanpyPushConfigPage() throws TsfaServiceException{
		FindCompanyPushConfigPage findComanpyPushConfigPage = new FindCompanyPushConfigPage();
		Page<FindComanpyPushConfigPageReturn> page = comanpyPushConfigService.findComanpyPushConfigPage(findComanpyPushConfigPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除经销商推送配置信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void delComanpyPushConfig() throws TsfaServiceException{
		DelComanpyPushConfig delComanpyPushConfig = new DelComanpyPushConfig();
		delComanpyPushConfig.setCode("");
		Assert.assertNotNull(comanpyPushConfigService.delComanpyPushConfig(delComanpyPushConfig));
		
	}	
}
