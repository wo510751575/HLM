package com.lj.business.st.service.impl;

import java.util.Date;
import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.Test;
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.st.dto.wxPmFollow.AddWxPmFollowReportShop;
import com.lj.business.st.dto.wxPmFollow.DelWxPmFollowReportShop;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportShop;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportShopPage;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportShopPageReturn;
import com.lj.business.st.dto.wxPmFollow.UpdateWxPmFollowReportShop;
import com.lj.business.st.service.IWxPmFollowReportShopService;


/**
 * 
 * 
 * 类说明：门店微信客户跟踪日报测试类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 许新龙
 *   
 * CreateDate: 2018年08月06日
 */
public class WxPmFollowReportShopServiceImplTest extends SpringTestCase{

	@Resource
	private IWxPmFollowReportShopService wxPmFollowReportShopService;

	/**
	 * 
	 *
	 * 方法说明：添加门店微信客户跟踪日报信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void addWxPmFollowReportShop() throws TsfaServiceException{
		AddWxPmFollowReportShop addWxPmFollowReportShop = new AddWxPmFollowReportShop();
		//add数据录入
		addWxPmFollowReportShop.setCode("Code");
		addWxPmFollowReportShop.setReportDate(new Date());
		addWxPmFollowReportShop.setMerchantNo("MerchantNo");
		addWxPmFollowReportShop.setMerchantName("MerchantName");
		addWxPmFollowReportShop.setCompanyNo("CompanyNo");
		addWxPmFollowReportShop.setCompanyName("CompanyName");
		addWxPmFollowReportShop.setDealerCode("DealerCode");
		addWxPmFollowReportShop.setShopNo("ShopNo");
		addWxPmFollowReportShop.setShopName("ShopName");
		addWxPmFollowReportShop.setShopCode("ShopCode");
		addWxPmFollowReportShop.setNumPmNew(0L);
		addWxPmFollowReportShop.setNumPmNewNotFollow(0L);
		addWxPmFollowReportShop.setNumPmNewFollow(0L);
		addWxPmFollowReportShop.setNumPmOld(0L);
		addWxPmFollowReportShop.setNumPmOldNotFollow(0L);
		addWxPmFollowReportShop.setNumPmOldFollow(0L);
		addWxPmFollowReportShop.setNumPmTotal(0L);
		addWxPmFollowReportShop.setCreateDate(new Date());
		addWxPmFollowReportShop.setUpdateDate(new Date());
		addWxPmFollowReportShop.setRemark("Remark");
		
		Assert.assertNotNull(wxPmFollowReportShopService.addWxPmFollowReportShop(addWxPmFollowReportShop ));
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改门店微信客户跟踪日报信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void updateWxPmFollowReportShop() throws TsfaServiceException{
		UpdateWxPmFollowReportShop updateWxPmFollowReportShop = new UpdateWxPmFollowReportShop();
		//update数据录入
		updateWxPmFollowReportShop.setCode("Code");
		updateWxPmFollowReportShop.setReportDate(new Date());
		updateWxPmFollowReportShop.setMerchantNo("MerchantNo");
		updateWxPmFollowReportShop.setMerchantName("MerchantName");
		updateWxPmFollowReportShop.setCompanyNo("CompanyNo");
		updateWxPmFollowReportShop.setCompanyName("CompanyName");
		updateWxPmFollowReportShop.setDealerCode("DealerCode");
		updateWxPmFollowReportShop.setShopNo("ShopNo");
		updateWxPmFollowReportShop.setShopName("ShopName");
		updateWxPmFollowReportShop.setShopCode("ShopCode");
		updateWxPmFollowReportShop.setNumPmNew(0L);
		updateWxPmFollowReportShop.setNumPmNewNotFollow(0L);
		updateWxPmFollowReportShop.setNumPmNewFollow(0L);
		updateWxPmFollowReportShop.setNumPmOld(0L);
		updateWxPmFollowReportShop.setNumPmOldNotFollow(0L);
		updateWxPmFollowReportShop.setNumPmOldFollow(0L);
		updateWxPmFollowReportShop.setNumPmTotal(0L);
		updateWxPmFollowReportShop.setCreateDate(new Date());
		updateWxPmFollowReportShop.setUpdateDate(new Date());
		updateWxPmFollowReportShop.setRemark("Remark");

		wxPmFollowReportShopService.updateWxPmFollowReportShop(updateWxPmFollowReportShop );
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找门店微信客户跟踪日报信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void findWxPmFollowReportShop() throws TsfaServiceException{
		FindWxPmFollowReportShop findWxPmFollowReportShop = new FindWxPmFollowReportShop();
		findWxPmFollowReportShop.setCode("");
		wxPmFollowReportShopService.findWxPmFollowReportShop(findWxPmFollowReportShop);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找门店微信客户跟踪日报信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void findWxPmFollowReportShopPage() throws TsfaServiceException{
		FindWxPmFollowReportShopPage findWxPmFollowReportShopPage = new FindWxPmFollowReportShopPage();
		Page<FindWxPmFollowReportShopPageReturn> page = wxPmFollowReportShopService.findWxPmFollowReportShopPage(findWxPmFollowReportShopPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除门店微信客户跟踪日报信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void delWxPmFollowReportShop() throws TsfaServiceException{
		DelWxPmFollowReportShop delWxPmFollowReportShop = new DelWxPmFollowReportShop();
		delWxPmFollowReportShop.setCode("");
		Assert.assertNotNull(wxPmFollowReportShopService.delWxPmFollowReportShop(delWxPmFollowReportShop));
		
	}	
}
