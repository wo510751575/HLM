package com.ye.business.hx.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.ye.business.hx.domain.PayDetail;
import com.ye.business.hx.dto.BillDetailDto;
import com.ye.business.hx.dto.BillDto;
import com.ye.business.hx.dto.BillOperateDto;
import com.ye.business.hx.dto.BillRefundDetailDto;
import com.ye.business.hx.dto.BillRefundDto;
import com.ye.business.hx.dto.FindBillPage;
import com.ye.business.hx.dto.PayDetailDto;
import com.ye.business.hx.dto.PayTypeDetailDto;
import com.ye.business.hx.emus.BillType;
import com.ye.business.hx.emus.OptType;
import com.ye.business.hx.emus.RefundType;
import com.ye.business.hx.service.IBillDetailService;
import com.ye.business.hx.service.IBillService;

/**
 * 类说明：测试类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author lhy
 * 
 * 
 * CreateDate: 2019.02.19
 */
public class BillServiceImplTest extends SpringTestCase{

	@Resource
	IBillService billService;


	@Resource
	IBillDetailService billDetailService;


	/**
	 * 
	 *
	 * 方法说明：添加账单信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addBill() throws TsfaServiceException{

		BillDto param= new BillDto();
		List<BillDetailDto> detailDtos=new ArrayList<>();
		BillOperateDto billOperate=new BillOperateDto();
		
		param.setDetails(detailDtos);
		param.setBillOperate(billOperate);
		param.setMerchantNo("73a1b1b90c5a4e9fb323a1a1beb5616a");
		param.setMerchantName("一鸣口腔");
		param.setPatientNo("LJ_126860c0eaf647518f0ae2f9fd578d49");
		param.setPatientName("虎七");
		param.setMedicalNo("PT1903291032130001");
		param.setBillType(BillType.MX.name());
		param.setClinicTime(new Date());//就诊时间
		
		
		billOperate.setOptType(OptType.PAY.name());
		billOperate.setOriginalAmount(100000L);//原价款
		billOperate.setReallyAmount(90000L);//应付款
		billOperate.setDiscountNum(9000);//折扣
		billOperate.setPayAmount(10000L);//已付款
		billOperate.setDebtAmount(80000L);//欠款 
		billOperate.setMemberNoGuid("f19e1fd0d2e342e6af223e7c32a4ae42");
		billOperate.setMemberNameGuid("八师姐");
		billOperate.setRemark("备2222注");
		
		billOperate.setPayType("LJ_8bb8c849426841d7b53fa8c8395ba802");
		billOperate.setPayTypeName("现金");
		billOperate.setPayRemark("[{\"payType\":\"LJ_8bb8c849426841d7b53fa8c8395ba802\",\"yesTotalSum\":100}]");
		
		billOperate.setPayTime(new Date());
		//收款人 前端填入
		billOperate.setRecieverNo("c874ecef6ef44284b9cb1495a929b415");
		billOperate.setRecieverName("李医生");
		
		BillDetailDto billDetail=new BillDetailDto();
		billDetail.setProjectCode("LJ_166d2a50803c4647916065c8caf0da31");
		billDetail.setProjectName("龈下刮治");
		 
		billDetail.setProjectUnit("颗");
		billDetail.setUnitPrice(5000L);
		billDetail.setItemDisUnitPrice(5000L);
		billDetail.setItemNum(5);
		billDetail.setOriginalAmount(50000L);
		billDetail.setItemDiscountAmount(50000L);
		billDetail.setDiscountItem(10000);
		billDetail.setAdvisoryNo("df1fdf9c76d74c6b87ba88444bae2c7e");
		billDetail.setAdvisoryName("罗咨询");
		billDetail.setDoctorNo("f19e1fd0d2e342e6af223e7c32a4ae42");
		billDetail.setDoctorName("八师姐1");
		billDetail.setAssistantNo("88882bc27a8f4241885fce861f2d383e");
		billDetail.setAssistantName("一鸣");
		billDetail.setPayAmount(0L);//实付
		billDetail.setReallyAmount(50000L);//应收  
		billDetail.setDebtAmount(50000L);//欠款 
		
		detailDtos.add(billDetail);
		
		BillDetailDto billDetail2=new BillDetailDto();
		billDetail2.setProjectCode("LJ_166d2a50803c4647916065c8caf0da312");
		billDetail2.setProjectName("龈下刮治2");
		 
		billDetail2.setProjectUnit("颗2");
		billDetail2.setUnitPrice(5000L);
		billDetail2.setItemDisUnitPrice(5000L);
		billDetail2.setItemNum(5);
		billDetail2.setOriginalAmount(50000L);
		billDetail2.setItemDiscountAmount(5000L);
		billDetail2.setDiscountItem(10000);
		billDetail2.setAdvisoryNo("df1fdf9c76d74c6b87ba88444bae2c7e");
		billDetail2.setAdvisoryName("罗咨询");
		billDetail2.setDoctorNo("f19e1fd0d2e342e6af223e7c32a4ae42");
		billDetail2.setDoctorName("八师姐2");
		billDetail2.setAssistantNo("88882bc27a8f4241885fce861f2d383e");
		billDetail2.setAssistantName("一鸣");
		billDetail2.setPayAmount(10000L);//实付
		billDetail2.setReallyAmount(50000L);//应收  
		billDetail2.setDebtAmount(40000L);//欠款 
		detailDtos.add(billDetail2);
		System.out.println(JSON.toJSON(param));
	
//		String  aString="{\"merchantNo\":\"73a1b1b90c5a4e9fb323a1a1beb5616a\",\"merchantName\":\"一鸣口腔\",\"patientNo\":\"LJ_3b2bc86b0ae049218488fd3817575ee8\",\"patientName\":\"嘎嘎嘎\",\"medicalNo\":\"PT1904031545340001\",\"billType\":\"MX\",\"clinicTimeStr\":\"2019-04-23 11:00:50\",\"billOperate\":{\"optType\":\"SVAE\",\"originalAmount\":300,\"reallyAmount\":300,\"discountNum\":10000,\"payAmount\":0,\"debtAmount\":-300,\"memberNoGuid\":\"65f95a2d4c38409284a789f1e8d8a79a\",\"memberNameGuid\":\"胡护士\",\"remark\":\"\",\"payType\":\"\",\"payTypeName\":\"\",\"payTimeStr\":\"2019-04-23 11:00:48\",\"recieverNo\":\"LJ_3b2bc86b0ae049218488fd3817575ee8\",\"recieverName\":\"嘎嘎嘎\"},\"details\":[{\"projectCode\":\"100012\",\"projectName\":\"龈下刮治\",\"projectUnit\":\"牙\",\"unitPrice\":300,\"itemDisUnitPrice\":300,\"itemNum\":1,\"originalAmount\":300,\"itemDiscountAmount\":300,\"discountItem\":10000}]}";
//		param= (BillDto) JsonUtils.objectFromJson(aString, BillDto.class);
//		String aString2="[{\"projectCode\":\"100012\",\"projectName\":\"龈下刮治\",\"projectUnit\":\"牙\",\"unitPrice\":300,\"itemDisUnitPrice\":300,\"itemNum\":1,\"originalAmount\":300,\"itemDiscountAmount\":300,\"discountItem\":10000}]";
//		String aString3="{\"optType\":\"SVAE\",\"originalAmount\":300,\"reallyAmount\":300,\"discountNum\":10000,\"payAmount\":0,\"debtAmount\":-300,\"memberNoGuid\":\"65f95a2d4c38409284a789f1e8d8a79a\",\"memberNameGuid\":\"胡护士\",\"remark\":\"\",\"payType\":\"\",\"payTypeName\":\"\",\"payTimeStr\":\"2019-04-23 11:00:48\",\"recieverNo\":\"LJ_3b2bc86b0ae049218488fd3817575ee8\",\"recieverName\":\"嘎嘎嘎\"}";
//		List<BillDetailDto> detailDtos2 =JSONArray.parseArray(aString2,BillDetailDto.class);
//		BillOperateDto billOperate1 =(BillOperateDto) JsonUtils.objectFromJson(aString3, BillOperateDto.class);
//		System.out.println(detailDtos2.get(0).getProjectCode());
//		System.out.println(billOperate1.getOptType());
		BillDto rt=billService.addBill(param);
		System.out.println(JSON.toJSON(rt));
	}
	
	/**
	 * 
	 *
	 * 方法说明：编辑账单信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateBillInfo() throws TsfaServiceException{

		BillDto param= new BillDto();
		List<BillDetailDto> detailDtos=new ArrayList<>();
		BillOperateDto billOperate=new BillOperateDto();
		
		param.setDetails(detailDtos);
		param.setBillOperate(billOperate);
		
		param.setCode("YE_1e78481a62e448e5b5cd4fad5a86d45b");
//		param.setMerchantNo("73a1b1b90c5a4e9fb323a1a1beb5616a");
//		param.setMerchantName("一鸣口腔");
//		param.setPatientNo("LJ_126860c0eaf647518f0ae2f9fd578d49");
//		param.setPatientName("虎七");
//		param.setMedicalNo("PT1903291032130001");
//		param.setBillType(BillType.MX.name());
		param.setClinicTime(new Date());//就诊时间
		param.setUpdateId("f19e1fd0d2e342e6af223e7c32a4ae42");
		
		billOperate.setCode("YE_bfb2cefa4b1843b7876c3e7d868af37c");
		billOperate.setOptType(OptType.SVAE.name());
		billOperate.setOriginalAmount(100000L);//原价款
		billOperate.setReallyAmount(90000L);//应付款
		billOperate.setDiscountNum(9000);//折扣
		billOperate.setPayAmount(90000L);//已付款
		billOperate.setDebtAmount(0L);//欠款 
		billOperate.setMemberNoGuid("f19e1fd0d2e342e6af223e7c32a4ae42");
		billOperate.setMemberNameGuid("八师姐");
		billOperate.setRemark("备2222注f");
		
		billOperate.setPayType("1");
		billOperate.setPayTypeName("现金");
		billOperate.setPayRemark("[{\"payType\":\"LJ_8bb8c849426841d7b53fa8c8395ba802\",\"yesTotalSum\":100}]");
		
		
		billOperate.setPayTime(new Date());
		//收款人 前端填入
		billOperate.setRecieverNo("c874ecef6ef44284b9cb1495a929b415");
		billOperate.setRecieverName("李医生");
		
		BillDetailDto billDetail=new BillDetailDto();
		billDetail.setProjectCode("LJ_166d2a50803c4647916065c8caf0da31");
		billDetail.setProjectName("龈下刮治a");
		 
		billDetail.setProjectUnit("颗");
		billDetail.setUnitPrice(5000L);
		billDetail.setItemDisUnitPrice(5000L);
		billDetail.setItemNum(5);
		billDetail.setOriginalAmount(50000L);
		billDetail.setItemDiscountAmount(5000L);
		billDetail.setDiscountItem(10000);
		billDetail.setAdvisoryNo("df1fdf9c76d74c6b87ba88444bae2c7e");
		billDetail.setAdvisoryName("罗咨询");
		billDetail.setDoctorNo("f19e1fd0d2e342e6af223e7c32a4ae42");
		billDetail.setDoctorName("八师姐");
		billDetail.setAssistantNo("88882bc27a8f4241885fce861f2d383e");
		billDetail.setAssistantName("一鸣");
		billDetail.setPayAmount(50000L);//实付
		billDetail.setReallyAmount(50000L);//应收  
		billDetail.setDebtAmount(0L);//欠款 
		detailDtos.add(billDetail);
		
		BillDetailDto billDetail2=new BillDetailDto();
		billDetail2.setProjectCode("LJ_166d2a50803c4647916065c8caf0da312");
		billDetail2.setProjectName("龈下刮治2b2");
		 
		billDetail2.setProjectUnit("颗2");
		billDetail2.setUnitPrice(5000L);
		billDetail2.setItemDisUnitPrice(5000L);
		billDetail2.setItemNum(5);
		billDetail2.setOriginalAmount(50000L);
		billDetail2.setItemDiscountAmount(5000L);
		billDetail2.setDiscountItem(10000);
		billDetail2.setAdvisoryNo("df1fdf9c76d74c6b87ba88444bae2c7e");
		billDetail2.setAdvisoryName("罗咨询");
		billDetail2.setDoctorNo("f19e1fd0d2e342e6af223e7c32a4ae42");
		billDetail2.setDoctorName("八师姐");
		billDetail2.setAssistantNo("88882bc27a8f4241885fce861f2d383e");
		billDetail2.setAssistantName("一鸣");
		billDetail2.setPayAmount(50000L);//实付
		billDetail2.setReallyAmount(50000L);//应收  
		billDetail2.setDebtAmount(0L);//欠款 
		
		detailDtos.add(billDetail2);
		System.out.println(JSON.toJSON(param));
	
		
		billService.updateBillInfo(param);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：编辑账单信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void billEditMember() throws TsfaServiceException{

		BillDto param= new BillDto();
		List<BillDetailDto> detailDtos=new ArrayList<>();
		
		param.setDetails(detailDtos);
		
		param.setCode("LJ_ff82c984b3b44bfc96aa6979523e6f90");
//		param.setMerchantNo("73a1b1b90c5a4e9fb323a1a1beb5616a");
//		param.setMerchantName("一鸣口腔");
//		param.setPatientNo("LJ_126860c0eaf647518f0ae2f9fd578d49");
//		param.setPatientName("虎七");
//		param.setMedicalNo("PT1903291032130001");
//		param.setBillType(BillType.MX.name());
//		param.setClinicTime(new Date());//就诊时间
		param.setUpdateId("f19e1fd0d2e342e6af223e7c32a4ae42");
		
		BillDetailDto billDetail=new BillDetailDto();
		billDetail.setCode("LJ_482a4d4c9bfd41c1a0516404b5a0a689");
		billDetail.setAdvisoryNo("df1fdf9c76d74c6b87ba88444bae2c7e");
		billDetail.setAdvisoryName("罗咨询");
		billDetail.setDoctorNo("f19e1fd0d2e342e6af223e7c32a4ae42");
		billDetail.setDoctorName("八师姐a");
		billDetail.setAssistantNo("88882bc27a8f4241885fce861f2d383e");
		billDetail.setAssistantName("一鸣");
		
		detailDtos.add(billDetail);
		
		BillDetailDto billDetail2=new BillDetailDto();
		billDetail2.setCode("LJ_4b1be57709904550b4056065319fc920");
		billDetail2.setAdvisoryNo("df1fdf9c76d74c6b87ba88444bae2c7e");
		billDetail2.setAdvisoryName("罗咨询");
		billDetail2.setDoctorNo("f19e1fd0d2e342e6af223e7c32a4ae42");
		billDetail2.setDoctorName("八师姐b");
		billDetail2.setAssistantNo("88882bc27a8f4241885fce861f2d383e");
		billDetail2.setAssistantName("一鸣");
		detailDtos.add(billDetail2);
		System.out.println(JSON.toJSON(param));
	
		
		billDetailService.updateBillDetailMember(param);
		
	}
	
	
	
	
	/**
	 * 
	 *
	 * 方法说明：修改账单信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateBill() throws TsfaServiceException{
		BillDto billDto = new BillDto();
		//update数据录入
		billDto.setCode("Code");
		billDto.setPatientNo("PatientNo");
		billDto.setPatientName("PatientName");
		billDto.setMedicalNo("MedicalNo");
		billDto.setShopNo("ShopNo");
		billDto.setShopName("ShopName");
		billDto.setMerchantNo("MerchantNo");
		billDto.setMerchantName("MerchantName");
		billDto.setOriginalAmount(10000L);
		billDto.setReallyAmount(10000L);
		billDto.setDiscountNum(10000);
		billDto.setPayAmount(10000L);
		billDto.setDebtAmount(0L);
		billDto.setRtAmount(0L);
		billDto.setPayStatus("PayStatus");
		billDto.setRtStatus("RtStatus");
		billDto.setStatus("Status");
		billDto.setClinicTime(new Date());
		billDto.setUpdateId("UpdateId");
		billDto.setUpdateDate(new Date());
		billDto.setCreateId("CreateId");
		billDto.setCreateName("CreateName");
		billDto.setCreateDate(new Date());
		billDto.setRemark("Remark");
		billDto.setRemark1("Remark1");
		billDto.setRemark2("Remark2");
		billDto.setRemark3("Remark3");
		billDto.setRemark4("Remark4");

		billService.updateBill(billDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找账单信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findBill() throws TsfaServiceException{
		BillDto findBill = new BillDto();
		findBill.setCode("111");
		billService.findBill(findBill);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找账单信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findBillPage() throws TsfaServiceException{
		FindBillPage findBillPage = new FindBillPage();
		Page<BillDto> page = billService.findBillPage(findBillPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找账单信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findBills() throws TsfaServiceException{
		FindBillPage findBillPage = new FindBillPage();
		List<BillDto> page = billService.findBills(findBillPage);
		Assert.assertNotNull(page);
		
	}
	
	@Test
	public void billRefundApply() {

		BillRefundDto billOperate=new BillRefundDto();
		billOperate.setBillCode("LJ_1a9ec2b278474a25bc5c10a2b42d0d51");
		billOperate.setRtAmount(100L);//退款額度
		billOperate.setMemberNoGuid("f19e1fd0d2e342e6af223e7c32a4ae42");
		billOperate.setMemberNameGuid("八师姐");
		
		billOperate.setPayType("1");
		billOperate.setPayTypeName("现金");
		billOperate.setPayRemark("1:100");
		
		billOperate.setRefundTime(new Date());
//		billOperate.setRefundTimeStr(refundTimeStr);
		//收款人 前端填入
		billOperate.setRefundGdNo("c874ecef6ef44284b9cb1495a929b415");
		billOperate.setRefundGdName("李医生");
		billOperate.setRefundType(RefundType.PART.name());
		
		List<BillRefundDetailDto> detailDtos = new ArrayList<>();
		BillRefundDetailDto billDetail=new BillRefundDetailDto();
		billDetail.setProjectCode("LJ_166d2a50803c4647916065c8caf0da31");
		billDetail.setProjectName("龈下刮治");
		billDetail.setReturnAmount(100L);
//		billDetail.setItemNum(1);
		detailDtos.add(billDetail);
		
		billOperate.setDetails(detailDtos);
		
		
		
		System.out.println(JSON.toJSON(billOperate));
		 
		billService.billRefundApply(billOperate);
	
	}
	
	@Test
	public void billRefundApplyEdit() {

		BillRefundDto billOperate=new BillRefundDto();
		billOperate.setCode("LJ_be69eec9a8d94cd6bbfa7408172e1db4");
		billOperate.setBillCode("LJ_1a9ec2b278474a25bc5c10a2b42d0d51");
		billOperate.setRtAmount(1000L);//退款額度
		billOperate.setMemberNoGuid("f19e1fd0d2e342e6af223e7c32a4ae42");
		billOperate.setMemberNameGuid("八师姐");
		
		billOperate.setPayType("1");
		billOperate.setPayTypeName("现金");
		billOperate.setPayRemark("1:90000");
		
		billOperate.setRefundTime(new Date());
//		billOperate.setRefundTimeStr(refundTimeStr);
		//收款人 前端填入
		billOperate.setRefundGdNo("c874ecef6ef44284b9cb1495a929b415");
		billOperate.setRefundGdName("李医生");
		billOperate.setRefundType(RefundType.PART.name());
		
		List<BillRefundDetailDto> detailDtos = new ArrayList<>();
		BillRefundDetailDto billDetail=new BillRefundDetailDto();
		billDetail.setProjectCode("LJ_166d2a50803c4647916065c8caf0da31");
		billDetail.setProjectName("龈下刮治2");
		billDetail.setReturnAmount(1000L);
//		billDetail.setItemNum(1);
		detailDtos.add(billDetail);
		
		billOperate.setDetails(detailDtos);
		System.out.println(JSON.toJSON(billOperate));
		 
		billService.billRefundApplyEdit(billOperate);
	}
	
	@Test
	public void debtRepay() {
		String t="{\"billCode\":\"YE_487e93781e204ff8a3e2210bc04555c8\",\"debtAmount\":50000,\"discountNum\":10000,\"memberNameGuid\":\"八师姐\",\"memberNoGuid\":\"f19e1fd0d2e342e6af223e7c32a4ae42\",\"optType\":\"SAVE\",\"originalAmount\":60000,\"payAmount\":10000,\"payRemark\":\"1:10000\",\"payTimeStr\":\"2019-04-22 15:14:36\",\"payType\":\"1\",\"payTypeName\":\"现金\",\"reallyAmount\":60000,\"recieverName\":\"李医生\",\"recieverNo\":\"c874ecef6ef44284b9cb1495a929b415\"}\n";
		BillOperateDto param =JSON.parseObject(t,BillOperateDto.class);
		List<PayDetailDto> datas=new ArrayList<>();
		PayDetailDto payDetail = new PayDetailDto();
		//add数据录入
		payDetail.setProjectCode("LJ_166d2a50803c4647916065c8caf0da31");
		payDetail.setProjectName("龈下刮治a");
		payDetail.setPayAmount(1L);//实付
		payDetail.setReallyAmount(50000L);//应收  
		payDetail.setDebtAmount(49000L);//欠款 
	 
		datas.add(payDetail);
		param.setPayDetails(datas);
		BillOperateDto rt = billService.debtRepay(param);
		System.out.println(JSON.toJSON(rt));
	}
	
	@Test
	public void debtRepayEdit() {
		String t="{\"billCode\":\"YE_487e93781e204ff8a3e2210bc04555c8\",\"debtAmount\":50000,\"discountNum\":10000,\"memberNameGuid\":\"八师姐\",\"memberNoGuid\":\"f19e1fd0d2e342e6af223e7c32a4ae42\",\"optType\":\"DEBT\",\"originalAmount\":60000,\"payAmount\":10000,\"payRemark\":\"1:10000\",\"payTimeStr\":\"2019-04-22 15:14:36\",\"payType\":\"1\",\"payTypeName\":\"现金\",\"reallyAmount\":60000,\"recieverName\":\"李医生\",\"recieverNo\":\"c874ecef6ef44284b9cb1495a929b415\"}\n";
		BillOperateDto param =JSON.parseObject(t,BillOperateDto.class);
		param.setCode("YE_454772136a674b2ba5d432ce43258d82");
		param.setPayRemark("[{\"payType\":\"LJ_8bb8c849426841d7b53fa8c8395ba802\",\"yesTotalSum\":100}]");
		param.setOptType(OptType.DEBT.name());
		List<PayDetailDto> datas=new ArrayList<>();
		PayDetailDto payDetail = new PayDetailDto();
		//add数据录入
		payDetail.setProjectCode("LJ_166d2a50803c4647916065c8caf0da31");
		payDetail.setProjectName("龈下刮治a");
		payDetail.setPayAmount(1L);//实付
		payDetail.setReallyAmount(50000L);//应收  
		payDetail.setDebtAmount(49000L);//欠款 
	 
		datas.add(payDetail);
		param.setPayDetails(datas);
		BillOperateDto rt = billService.debtRepayEdit(param);
		System.out.println(JSON.toJSON(rt));
	}
	
	public static void main(String[] args) {
		String remark = "[{\"payType\":\"YE_df57cc982d514158a72b43c703b6d9ca\",\"yesTotalSum\":\"1000\"},{\"payType\":\"YE_6f99c6750b4849c99b67837a0292d439\",\"yesTotalSum\":\"700\"},{\"payType\":\"YE_1fc0326dab26476faca11e99ca613bbb\",\"yesTotalSum\":\"90.23\"}]";
		List<PayTypeDetailDto> details = JSONArray.parseArray(remark, PayTypeDetailDto.class);
		for (Iterator<PayTypeDetailDto> iterator = details.iterator(); iterator.hasNext();) {
			PayTypeDetailDto typeDetailDto = (PayTypeDetailDto) iterator.next();
			System.out.println(typeDetailDto.getYesTotalSum()+":"+(typeDetailDto.getYesTotalSum().multiply(new BigDecimal(100)).longValue()));
		}
		
		BigDecimal a=new BigDecimal("52.365");
		long rt=a.multiply(new BigDecimal(100)).longValue();
		System.out.println(rt);
		
		List<PayDetail> datas=new ArrayList<>();
		PayDetail payDetail = new PayDetail();
		//add数据录入
		payDetail.setProjectCode("LJ_166d2a50803c4647916065c8caf0da31");
		payDetail.setProjectName("龈下刮治a");
		payDetail.setPayAmount(1L);//实付
		payDetail.setReallyAmount(50000L);//应收  
		payDetail.setDebtAmount(49000L);//欠款 
	 
		datas.add(payDetail);
		System.out.println(JSON.toJSON(datas));
	}
	
}
