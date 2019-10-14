package com.lj.business.st.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.member.dto.FindMerchantPage;
import com.lj.business.member.dto.FindMerchantPageReturn;
import com.lj.business.member.dto.FindPmTypePageReturn;
import com.lj.business.member.service.IMerchantService;
import com.lj.business.member.service.IPmTypeService;
import com.lj.business.st.dto.PmTypeTotal.AddPmTypeTotal;
import com.lj.business.st.dto.PmTypeTotal.FindPmTypeTotal;
import com.lj.business.st.dto.PmTypeTotal.FindPmTypeTotalReturn;
import com.lj.business.st.emus.DimensionSt;
import com.lj.business.st.service.IPmTypeTotalService;
import com.lj.business.st.util.TestHelp;


/**
 * 类说明：测试类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 邹磊
 * 
 * 
 * CreateDate: 2017-06-14
 */
public class PmTypeTotalServiceImplTest extends SpringTestCase{

	@Resource
	private IPmTypeTotalService pmTypeTotalService;
	
	@Resource
	private IPmTypeService pmTypeService;
	
	@Resource
	private IMerchantService merchantService;



	/**
	 * 
	 *
	 * 方法说明：添加客户分类统计表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void addPmTypeTotal() throws TsfaServiceException{
		AddPmTypeTotal addPmTypeTotal = new AddPmTypeTotal();
		//add数据录入
		addPmTypeTotal.setCode("Code");
		addPmTypeTotal.setMerchantNo("MerchantNo");
		addPmTypeTotal.setShopNo("ShopNo");
		addPmTypeTotal.setShopName("ShopName");
		addPmTypeTotal.setMemberNoGm("MemberNoGm");
		addPmTypeTotal.setMemberNameGm("MemberNameGm");
		addPmTypeTotal.setAreaCode("AreaCode");
		addPmTypeTotal.setProvinceCode("ProvinceCode");
		addPmTypeTotal.setCityCode("CityCode");
		addPmTypeTotal.setCityAreaCode("CityAreaCode");
		addPmTypeTotal.setPmTypeCode("PmTypeCode");
		addPmTypeTotal.setTypeName("TypeName");
		addPmTypeTotal.setNumPm(1);
		//addPmTypeTotal.setRatioPm();
		addPmTypeTotal.setDaySt(new Date());
		addPmTypeTotal.setDimensionSt("DimensionSt");
		addPmTypeTotal.setCreateDate(new Date());
		
		pmTypeTotalService.addPmTypeTotal(addPmTypeTotal );
		
	}
	
	
	/**
	 * 
	 *
	 * 方法说明：查找客户分类统计表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void findPmTypeTotal() throws TsfaServiceException{
		FindPmTypeTotal findPmTypeTotal = new FindPmTypeTotal();
		//findPmTypeTotal.setCode("111");
		//pmTypeTotalService.findPmTypeTotal(findPmTypeTotal);
		findPmTypeTotal.setBeginDate("2017-07-26");
		findPmTypeTotal.setEndDate("2017-07-28");
		List<FindPmTypeTotalReturn> pmTypeTotalList = pmTypeTotalService.findPmTypeTotalList(findPmTypeTotal);
		System.out.println(pmTypeTotalList);
	}

    @Test
    public void selectByPrimaryKey() throws TsfaServiceException{
        pmTypeTotalService.selectByPrimaryKey("LJ_07b1a7dfe1f54a988086848d15bffgf4");
    }
    @Test
    public void findPmTypeTotalMax() throws TsfaServiceException{
    	FindPmTypeTotal findPmTypeTotal=new FindPmTypeTotal();
    	findPmTypeTotal.setMemberNoGm("efbc3bb7be534d24b9cb6d72ac21f666");
    	findPmTypeTotal.setMerchantNo("e79d975846ee41ba8c3c55738fda66a9");
    	FindPmTypeTotalReturn list=pmTypeTotalService.findPmTypeMaxList(findPmTypeTotal);
    	System.out.println(list.toString());
    }
    @Test
    public void findIntentionPmList() throws TsfaServiceException{
    	Map<String,Object> parmMap = new HashMap<String, Object>();
		parmMap.put("memberNoMerchant",TestHelp.merchantNo_test);
		parmMap.put("dimensionSt ", DimensionSt.MERCHANT.toString());
//		parmMap.put("pmTypeType", PmTypeType.INTENTION.toString());
//		parmMap.put("startTime", startTime);
//		parmMap.put("endTime", endTime);
		List<Map<String,Object>> list=pmTypeTotalService.findIntentionPmList(parmMap);
    	System.out.println(list.toString());
    }
    @Test
    public void findPmTypeList() throws TsfaServiceException{
    	Map<String,Object> parmMap = new HashMap<>();
		parmMap.put("merchantNo","87b828cab9f64d37b2eacc4ce5cc0eab");
		parmMap.put("dimensionSt", DimensionSt.MERCHANT.toString());
		List<Map<String,Object>> list=pmTypeTotalService.findPmTypeList(parmMap);
    	System.out.println(list.toString());
    }
    
    @Test
    public void findIntentionSum() throws TsfaServiceException{
    	FindPmTypeTotal findPmTypeTotal=new FindPmTypeTotal();
    	findPmTypeTotal.setMerchantNo("e79d975846ee41ba8c3c55738fda66a9");
    	List<FindPmTypeTotalReturn>list=pmTypeTotalService.findPmTypeListSum(findPmTypeTotal);
    	System.out.println(list.toString());
    }
    
    @Test
	public void findPmTypeTotalList() throws Exception {
    	FindPmTypeTotal findPmTypeTotal = new FindPmTypeTotal();
    	findPmTypeTotal.setMerchantNo("e79d975846ee41ba8c3c55738fda66a9");
    	findPmTypeTotal.setShopNo("LJ_c269478af52646b692fcc48deb10a7ad");
    	pmTypeTotalService.findPmTypeTotalList(findPmTypeTotal);
	}
    
    @Test
    public void test(){
    	AddPmTypeTotal addPmTypeTotal = new AddPmTypeTotal();
    	//循环所有商户统计的数据
    	List<FindMerchantPageReturn> merchants = merchantService.findMerchants(new FindMerchantPage());
    	
    	for (FindMerchantPageReturn merchantPageReturn:merchants) {
    		    String merchantNo = merchantPageReturn.getMerchantNo();
    			//查询时间维度，按时间维度分组
    	    	FindPmTypeTotal findPmTypeTotal = new FindPmTypeTotal();
    	    	findPmTypeTotal.setMerchantNo(merchantNo);
    	    	findPmTypeTotal.setDimensionSt(DimensionSt.MERCHANT.toString());
    	    	List<FindPmTypeTotalReturn>  list = pmTypeTotalService.findPmTypeDaySt(findPmTypeTotal);
    		    //初始化数据
    		    for(FindPmTypeTotalReturn findPmTypeTotalReturn : list){
    		    	findPmTypeTotal = new FindPmTypeTotal();
    		    	findPmTypeTotal.setMerchantNo(merchantNo);
    		    	findPmTypeTotal.setDimensionSt(DimensionSt.MERCHANT.toString());
    		    	findPmTypeTotal.setDaySt(findPmTypeTotalReturn.getDaySt());
    		    	//查找当天的数据统计
    		    	List<FindPmTypeTotalReturn>  totalReturn = pmTypeTotalService.queryPmType(findPmTypeTotal);
    		       	FindPmTypePageReturn findPmTypePageReturn = new FindPmTypePageReturn();
    		   	    findPmTypePageReturn.setMerchantNo(merchantNo);
    		   	    //获取客户分类
    			    List<FindPmTypePageReturn> pmList = pmTypeService.findPmTypePages(findPmTypePageReturn);
    		    	int num1 = totalReturn.size();
    		    	int pmTypeNum = pmList.size();
    		    	List<FindPmTypePageReturn> addList = Lists.newArrayList();
    		    	if(pmTypeNum > num1){
    			    		for(FindPmTypeTotalReturn pmTypeTotalReturn :totalReturn){
    			    			for(FindPmTypePageReturn pageReturn : pmList){
    			    			 if(pageReturn.getPmTypeType().equals(pmTypeTotalReturn.getPmTypeType())){
    			    				 addList.add(pageReturn);
    			    			 }
    			    		}
    			    	}
    			    		pmList.removeAll(addList);
    			    		//初始化为空的数据
    			    		
    			    		for(FindPmTypePageReturn pageReturn : pmList){
    			    		    addPmTypeTotal = new AddPmTypeTotal();
    			    		    addPmTypeTotal.setMerchantNo(pageReturn.getMerchantNo());
    							addPmTypeTotal.setPmTypeCode(pageReturn.getCode());
    						    addPmTypeTotal.setTypeName(pageReturn.getTypeName());
    							addPmTypeTotal.setPmTypeType(pageReturn.getPmTypeType());
    							addPmTypeTotal.setDaySt(findPmTypeTotalReturn.getDaySt());
    							addPmTypeTotal.setCreateDate(new Date());
    							addPmTypeTotal.setDimensionSt(DimensionSt.MERCHANT.toString());
    							pmTypeTotalService.addPmTypeTotal(addPmTypeTotal);
    			    		}
    		    	}
    		    	
    		    }
			
		}
    	
	    
    	
    }
}
