package com.lj.business.st.service.impl;

import java.util.Date;
import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.Test;
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.st.dto.wxPmFollow.AddWxPmFollowReportGm;
import com.lj.business.st.dto.wxPmFollow.DelWxPmFollowReportGm;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportGm;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportGmPage;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportGmPageReturn;
import com.lj.business.st.dto.wxPmFollow.UpdateWxPmFollowReportGm;
import com.lj.business.st.service.IWxPmFollowReportGmService;


/**
 * 
 * 
 * 类说明：导购微信客户跟踪日报测试类
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
public class WxPmFollowReportGmServiceImplTest extends SpringTestCase{

	@Resource
	private IWxPmFollowReportGmService wxPmFollowReportGmService;

	/**
	 * 
	 *
	 * 方法说明：添加导购微信客户跟踪日报信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void addWxPmFollowReportGm() throws TsfaServiceException{
		AddWxPmFollowReportGm addWxPmFollowReportGm = new AddWxPmFollowReportGm();
		//add数据录入
		addWxPmFollowReportGm.setCode("Code");
		addWxPmFollowReportGm.setReportDate(new Date());
		addWxPmFollowReportGm.setMerchantNo("MerchantNo");
		addWxPmFollowReportGm.setMerchantName("MerchantName");
		addWxPmFollowReportGm.setCompanyNo("CompanyNo");
		addWxPmFollowReportGm.setCompanyName("CompanyName");
		addWxPmFollowReportGm.setDealerCode("DealerCode");
		addWxPmFollowReportGm.setShopNo("ShopNo");
		addWxPmFollowReportGm.setShopName("ShopName");
		addWxPmFollowReportGm.setShopCode("ShopCode");
		addWxPmFollowReportGm.setMemberNoGm("MemberNoGm");
		addWxPmFollowReportGm.setMemberNameGm("MemberNameGm");
		addWxPmFollowReportGm.setHeadAddress("HeadAddress");
		addWxPmFollowReportGm.setNumPmNew(0L);
		addWxPmFollowReportGm.setNumPmNewNotFollow(0L);
		addWxPmFollowReportGm.setNumPmNewFollow(0L);
		addWxPmFollowReportGm.setNumPmOld(0L);
		addWxPmFollowReportGm.setNumPmOldNotFollow(0L);
		addWxPmFollowReportGm.setNumPmOldFollow(0L);
		addWxPmFollowReportGm.setNumPmTotal(0L);
		addWxPmFollowReportGm.setCreateDate(new Date());
		addWxPmFollowReportGm.setUpdateDate(new Date());
		addWxPmFollowReportGm.setRemark("Remark");
		
		Assert.assertNotNull(wxPmFollowReportGmService.addWxPmFollowReportGm(addWxPmFollowReportGm ));
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改导购微信客户跟踪日报信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void updateWxPmFollowReportGm() throws TsfaServiceException{
		UpdateWxPmFollowReportGm updateWxPmFollowReportGm = new UpdateWxPmFollowReportGm();
		//update数据录入
		updateWxPmFollowReportGm.setCode("Code");
		updateWxPmFollowReportGm.setReportDate(new Date());
		updateWxPmFollowReportGm.setMerchantNo("MerchantNo");
		updateWxPmFollowReportGm.setMerchantName("MerchantName");
		updateWxPmFollowReportGm.setCompanyNo("CompanyNo");
		updateWxPmFollowReportGm.setCompanyName("CompanyName");
		updateWxPmFollowReportGm.setDealerCode("DealerCode");
		updateWxPmFollowReportGm.setShopNo("ShopNo");
		updateWxPmFollowReportGm.setShopName("ShopName");
		updateWxPmFollowReportGm.setShopCode("ShopCode");
		updateWxPmFollowReportGm.setMemberNoGm("MemberNoGm");
		updateWxPmFollowReportGm.setMemberNameGm("MemberNameGm");
		updateWxPmFollowReportGm.setHeadAddress("HeadAddress");
		updateWxPmFollowReportGm.setNumPmNew(0L);
		updateWxPmFollowReportGm.setNumPmNewNotFollow(0L);
		updateWxPmFollowReportGm.setNumPmNewFollow(0L);
		updateWxPmFollowReportGm.setNumPmOld(0L);
		updateWxPmFollowReportGm.setNumPmOldNotFollow(0L);
		updateWxPmFollowReportGm.setNumPmOldFollow(0L);
		updateWxPmFollowReportGm.setNumPmTotal(0L);
		updateWxPmFollowReportGm.setCreateDate(new Date());
		updateWxPmFollowReportGm.setUpdateDate(new Date());
		updateWxPmFollowReportGm.setRemark("Remark");

		wxPmFollowReportGmService.updateWxPmFollowReportGm(updateWxPmFollowReportGm );
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找导购微信客户跟踪日报信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void findWxPmFollowReportGm() throws TsfaServiceException{
		FindWxPmFollowReportGm findWxPmFollowReportGm = new FindWxPmFollowReportGm();
		findWxPmFollowReportGm.setCode("");
		wxPmFollowReportGmService.findWxPmFollowReportGm(findWxPmFollowReportGm);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找导购微信客户跟踪日报信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void findWxPmFollowReportGmPage() throws TsfaServiceException{
		FindWxPmFollowReportGmPage findWxPmFollowReportGmPage = new FindWxPmFollowReportGmPage();
		Page<FindWxPmFollowReportGmPageReturn> page = wxPmFollowReportGmService.findWxPmFollowReportGmPage(findWxPmFollowReportGmPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除导购微信客户跟踪日报信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void delWxPmFollowReportGm() throws TsfaServiceException{
		DelWxPmFollowReportGm delWxPmFollowReportGm = new DelWxPmFollowReportGm();
		delWxPmFollowReportGm.setCode("");
		Assert.assertNotNull(wxPmFollowReportGmService.delWxPmFollowReportGm(delWxPmFollowReportGm));
		
	}	
}
