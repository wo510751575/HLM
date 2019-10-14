package com.lj.oms.st;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ape.common.utils.CacheUtils;
import com.ape.common.utils.DateUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lj.business.member.dto.FindPersonMemberBaseList;
import com.lj.business.member.dto.FindPersonMemberBaseReturnList;
import com.lj.business.member.service.IManagerMemberService;
import com.lj.business.member.service.IPersonMemberBaseService;
import com.lj.business.st.dto.FindClientAnalyze;
import com.lj.business.st.dto.FindClientAnalyzeReturn;
import com.lj.business.st.dto.PmTalkTotal.FindPmTalkTotal;
import com.lj.business.st.dto.PmTalkTotal.FindPmTalkTotalReturn;
import com.lj.business.st.dto.PmTypeTotal.FindPmTypeTotal;
import com.lj.business.st.dto.PmTypeTotal.FindPmTypeTotalReturn;
import com.lj.business.st.emus.DimensionSt;
import com.lj.business.st.service.IClientAnalyzeService;
import com.lj.business.st.service.IEmployeeAnalyzeService;
import com.lj.business.st.service.IPmTalkTotalService;
import com.lj.business.st.service.IPmTypeTotalService;
import com.lj.business.st.service.IWorkRatioAreaService;
import com.lj.business.st.service.IWorkRatioShopService;
import com.lj.oms.common.BaseController;
import com.lj.oms.utils.DateUtil;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：运营Controller
 *  
 * 
 * <p>
 * 详细描述：运营报表相关功能
 *   
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 *   
 * CreateDate: 2017年7月8日
 */
@Controller
@RequestMapping(value = "${adminPath}/operate")
public class OperateController  extends BaseController{
	
    /**
     * 参数名称定义
     */
	private static final String CDMEMBER = "成单客户";

	private static final String YXMEMBER = "意向客户";
	
	private static final String MEMBERTOTLE= "客户总量";
	
	private static final String RETURN_MERCHANTNO = "merchantNo";  //商户名称返回 
	
	private static final String START_TIME = "startTime"; //开始时间
	
	private static final String END_TIME = "endTime";//结束时间
	
	private static final String DAYSTS = "daysts"; //天数
	
	private static final String NUMS="nums";//数量
	
	private static final String INTENTION_NUM ="intentionNum";//意向
	
	private static final String INTENTION_NUM_NO ="intentionNumNo";//意向未到店
	
	private static final String OTHER_NUM ="otherNum";//非意向
	
	private static final String GIVE_UP_NUM ="giveUpNum";//暂停跟进
	
	private static final String SUCCESS_NUM ="successNum";//成单
	
	private static final String DIMENSION_ST = "dimensionSt";//统计类型参数名称
	
	private static final String MEMBERNO_MERCHANT = "memberNoMerchant";
	
	public static final Date EndDate=com.lj.base.core.util.DateUtils.getPreday(org.apache.commons.lang.time.DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH) );
	
//	@Autowired
//	private IShopService shopService;
	
	@Autowired
	private IPmTypeTotalService pmTypeTotalService;
	@Autowired
	private IPmTalkTotalService pmTalkTotalService;			//客户咨询统计服务
	@Autowired
	private IWorkRatioShopService workRatioShopService;		//导购工作统计服务
	@Autowired
	private IWorkRatioAreaService workRatioAreaService;			//门店区域分布统计服务
	
	@Autowired
	private  IClientAnalyzeService clientAnalyzeService;   //客户画像
	
	@Autowired
	private IEmployeeAnalyzeService employeeAnalyzeService; //员工画像
	
	@Autowired
	private IManagerMemberService managerMemberService;
	
	@Autowired
	private  IPersonMemberBaseService personMemberBaseService; 
	
	
	
	
	/**
	 * 方法说明：获取当前登录对象
	 */
	private String getUserMerchantNo() {
		String MERCHANT_NO =UserUtils.getUser().getCompany().getId(); //商户编号
		String merchantNo = null ;
		if(!UserUtils.getUser().isAdmin() || CacheUtils.get(UserUtils.USER_GAIN_MERCHANT_NO) == null){
			merchantNo = MERCHANT_NO;
    	  }else{
    	  merchantNo = (String)CacheUtils.get(UserUtils.USER_GAIN_MERCHANT_NO);
    	  }
		return merchantNo;
	}
	
	/**
	 * 
	 *
	 * 方法说明：运营分析
	 *
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月8日
	 *
	 */
	@RequestMapping(value={"yxfx"})
	public String  finidOperationDayChoose(Model model,Integer pageNo,Integer pageSize,String startTime,String endTime){
		Map<String,Object> parmMap = new HashMap<String, Object>();
		Map<String,Object> maps = new HashMap<String, Object>();
		try {
			 String MERCHANT_NO =UserUtils.getUser().getCompany().getId(); //商户编号
			//判断当前登录用户 
			if(!UserUtils.getUser().isAdmin() || CacheUtils.get(UserUtils.USER_GAIN_MERCHANT_NO) == null){
				parmMap.put(MEMBERNO_MERCHANT,MERCHANT_NO);
				maps.put(MEMBERNO_MERCHANT,MERCHANT_NO);
			}else{
				parmMap.put(MEMBERNO_MERCHANT,CacheUtils.get(UserUtils.USER_GAIN_MERCHANT_NO));
				maps.put(MEMBERNO_MERCHANT,CacheUtils.get(UserUtils.USER_GAIN_MERCHANT_NO));
			}
			
			//员工性别
//			Map<String,Object> returnMap = shopService.countByCondition(parmMap);
//			model.addAttribute("data", returnMap);
			
			Map<String,Object> ManagerReturnMap = managerMemberService.countByCondition(parmMap);
			if(ManagerReturnMap!=null){
//			 returnMap.putAll(ManagerReturnMap);
			}
			FindPersonMemberBaseList baseList=new FindPersonMemberBaseList();
			baseList.setMerchantNo(UserUtils.getUser().getCompany().getId());
			baseList.setStartTime(com.lj.base.core.util.DateUtils.getDateByFirstSeconds(DateUtils.addDays(new Date(), -30)));
			baseList.setEndTime(com.lj.base.core.util.DateUtils.getDateByLastSeconds(new Date()));
			FindPersonMemberBaseReturnList baseReturnList=personMemberBaseService.findPersonMemberBaseNums(baseList);
			List<Map<String,Object>> returnList = Lists.newArrayList();
			
			initMap(baseReturnList, returnList,MEMBERTOTLE,(baseReturnList == null ? "0":(baseReturnList.getMemberNoNum() == null ? "0":baseReturnList.getMemberNoNum())));
			initMap(baseReturnList, returnList,YXMEMBER,(baseReturnList == null ? "0": baseReturnList.getIntentionNum()));
			initMap(baseReturnList, returnList,CDMEMBER,(baseReturnList == null ? "0": baseReturnList.getSuccessNum()));
			//销售漏斗	 罗书明
			model.addAttribute("xsldData", returnList);
			
			//客户分布 段志鹏		start
			Map<String, Object> dayBeginAndEnd = DateUtil.getDayBeginAndEnd(DateUtils.addDays(new Date(), -1));
			maps.put("dimensionSt", DimensionSt.PROVINCE.toString());
			maps.put("startTime", dayBeginAndEnd.get("beginTime"));
			maps.put("endTime", dayBeginAndEnd.get("endTime"));
			
			List<Map<String,Object>> provinces= workRatioAreaService.findBroupProvince(maps);
			List<Map<String,Object>> datas = Lists.newArrayList();
			Map<String,Object> data = Maps.newHashMap();
			if(provinces!=null){
				for (Map<String, Object> map : provinces) {
					data = Maps.newHashMap();
					String name=null;
					if(map.get("PROVINCE_NAME")!=null){
						 name = map.get("PROVINCE_NAME").toString().replaceAll("省", "").replaceAll("市", "").replaceAll("特别行政区", "").replaceAll("自治区", "");
					}
					data.put("name", name);
					data.put("value", map.get("NUM_PM"));
					datas.add(data);
				}	 
			}
			model.addAttribute("provinces",datas);
			//客户分布 段志鹏		end
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "modules/operate/yxfx";
	}
   
	private void initMap(FindPersonMemberBaseReturnList baseReturnList,List<Map<String, Object>> returnList,String name,Object num) {
		Map<String, Object> returnMap= Maps.newHashMap();
		returnMap.put("name", name);
		returnMap.put("value",num);
		returnList.add(returnMap);
	}
	
	/**
	 * 
	 *
	 * 方法说明：客户数据统计分析
	 *
	 * @param model
	 * @param beginDate
	 * @param endDate
	 * @return   
	 *
	 * @author 罗书明 CreateDate: 2017年7月28日
	 *
	 */
	@RequestMapping(value = {"findPmTypeTotalList"})
	@ResponseBody
	public Map<String,Object> findPmTypeTotalList(Model model,String startTime,String endTime) {
		Map<String,Object> returnMap = new HashMap<>();
		FindPmTypeTotal findPmTypeTotal=new FindPmTypeTotal();
		try {
		    String MERCHANT_NO =UserUtils.getUser().getCompany().getId(); //商户编号
			if(!UserUtils.getUser().isAdmin()|| CacheUtils.get(UserUtils.USER_GAIN_MERCHANT_NO) == null){
				findPmTypeTotal.setMerchantNo(MERCHANT_NO);
			}else{
				findPmTypeTotal.setMerchantNo((String)CacheUtils.get(UserUtils.USER_GAIN_MERCHANT_NO));
			}
			findPmTypeTotal.setDimensionSt(DimensionSt.MERCHANT.toString());
			findPmTypeTotal.setDaySt(EndDate);
			List<FindPmTypeTotalReturn> list = pmTypeTotalService.findPmTypeListSum(findPmTypeTotal);
			List<String> intentionNum = Lists.newArrayList();		//意向
			List<String> otherNum = Lists.newArrayList();		//非意向
			List<String> giveUpNum = Lists.newArrayList();		//暂停跟进
			List<String> successNum = Lists.newArrayList();		//成单	
			List<String> intentionNumNo = Lists.newArrayList();  //意向未到店
			for (FindPmTypeTotalReturn map : list) {
				//获取类型
				String pmTypeType = map.getPmTypeType();
				//获取数量
				Integer  pmNum = map.getNumPm();
				
				if(map.getNumPm()!=null){
				//非意向客户
			   if(map.getPmTypeType()!=null){
				/*if(pmTypeType.equals(PmTypeType.OTHER.toString())){
					otherNum.add(pmNum.toString());
				}
				//意向客户
				if(pmTypeType.equals(PmTypeType.INTENTION.toString())){
					intentionNum.add(pmNum.toString());
				}		
				//意向未到店
				if(pmTypeType.equals(PmTypeType.INTENTION_N.toString())){
					intentionNumNo.add(pmNum.toString());
				}
				//暂停跟进客户
				if(pmTypeType.equals(PmTypeType.GIVE_UP.toString())){
					giveUpNum.add(pmNum.toString());
				}
				//成单客户
				if(pmTypeType.equals(PmTypeType.SUCCESS.toString())){
					successNum.add(pmNum.toString());
				}	*/			
			}
				}
			}
			
			returnMap.put(INTENTION_NUM, intentionNum);	//意向
			returnMap.put(INTENTION_NUM_NO, intentionNumNo);	//意向未到店
			returnMap.put(OTHER_NUM, otherNum);			//非意向
			returnMap.put(GIVE_UP_NUM, giveUpNum);			//暂停跟进
			returnMap.put(SUCCESS_NUM, successNum);		//成单
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnMap;
	}
	
	/**
	 * 
	 *
	 * 方法说明：客户年龄结构占比
	 *
	 * @param model
	 * @param startTime
	 * @param endTime
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年7月31日
	 *
	 */
	@RequestMapping(value={"findClientAnalyzeList"})
	@ResponseBody
	public List<Map<String,Object>> findClientAnalyzeList(Model model,String startTime,String endTime){
		List<Map<String,Object>> list=null;
		Map<String,Object> parmMap=new HashMap<String,Object>();
		try {
			//判断超级管理员登录
		    String merchantNo  = getUserMerchantNo();
			parmMap.put(RETURN_MERCHANTNO , merchantNo);
			parmMap.put(START_TIME, startTime);
			parmMap.put(END_TIME, endTime);
			parmMap.put(DIMENSION_ST,DimensionSt.MERCHANT.toString());
			list= clientAnalyzeService.findClientAnalyzeList(parmMap);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 
	 *
	 * 方法说明：查询客户性别比例数量
	 *
	 * @param model
	 * @param findClientAnalyze
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年7月31日
	 *
	 */
	@RequestMapping(value={"findClientAnalyze"})
	@ResponseBody
    public List<FindClientAnalyzeReturn> findClientAnalyze(Model model,FindClientAnalyze findClientAnalyze){
		 List<FindClientAnalyzeReturn> list =null;
      try {
    	  //获取当前登录对象
    	  String merchantNo =  getUserMerchantNo();
    	  findClientAnalyze.setMerchantNo(merchantNo);
    	  findClientAnalyze.setDimensionSt(DimensionSt.MERCHANT.toString());
    	  list=clientAnalyzeService.findClientAnalyze(findClientAnalyze);
    	  if(list.size()>0 && list!=null){
    		//double-->String-->BigDecimal防止丢失精度
    		  for(FindClientAnalyzeReturn maps: list){
    			 if(maps.getNumPm()!=null){
    				 double  numFemale=(double)(maps.getNumPm() * maps.getRatioFemale()); 
            		 double numMale = (double) (maps.getNumPm() * maps.getRatioMale());
          			 maps.setNumMale(new BigDecimal(Double.toString(numMale)).setScale(0, BigDecimal.ROUND_HALF_UP));
          			 maps.setNumFemale(new BigDecimal(Double.toString(numFemale)).setScale(0, BigDecimal.ROUND_HALF_UP));
          			 BigDecimal numUnKnown = 
          					 new BigDecimal(Long.toString(maps.getNumPm())).setScale(0, BigDecimal.ROUND_HALF_UP)
          					 .subtract(new BigDecimal(Double.toString(numMale))).setScale(0, BigDecimal.ROUND_HALF_UP)
          					 .subtract(new BigDecimal(Double.toString(numFemale))).setScale(0, BigDecimal.ROUND_HALF_UP);
          			 maps.setNumUnKnown(numUnKnown);
    			 }else{
    				double  numFemale=0.0; 
            		double numMale = 0.0;
            		double numUnKnown = 0.0;
            		maps.setNumMale(new BigDecimal(numMale));
              		maps.setNumFemale(new BigDecimal(numFemale));	 
              		maps.setNumUnKnown(new BigDecimal(numUnKnown));	 
    			 }
        
        	  }  
    	  }
     
	} catch (Exception e) {
		e.printStackTrace();
	}
      return list;
    	
    }


   
	/**
	 * 
	 *
	 * 方法说明：员工年龄比例
	 *
	 * @param model
	 * @param startTime
	 * @param endTime
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年7月31日
	 *
	 */
	@RequestMapping(value={"findEmployeeAnalyzeList"})
	@ResponseBody
    public Map<String,Object> findEmployeeAnalyzeList(Model model,String startTime,String endTime){
		Map<String,Object> map =null;
       try {
        String MERCHANT_NO =UserUtils.getUser().getCompany().getId(); //商户编号
    	Map<String,Object> parmMap=new HashMap<String,Object>();
    	parmMap.put(RETURN_MERCHANTNO,MERCHANT_NO);
    	parmMap.put(START_TIME, startTime);
    	parmMap.put(END_TIME, endTime);
    	List<Map<String,Object>> list = employeeAnalyzeService.findEmployeeAnalyzeList(parmMap);  
    	if(list.size()>0){
    		map = list.get(0);
    	}
	} catch (Exception e) {
		logger.error("查询员工年龄比例错误",e);
	}
       return map;
    }
	/**
	 * 
	 *
	 * 方法说明：客户咨询统计
	 *
	 * @param model
	 * @param startTime
	 * @param endTime
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月31日
	 *
	 */
	@RequestMapping(value = {"findPmTalkTotalList"})
	@ResponseBody
	public Map<String,Object> findPmTalkTotalList(Model model,String startTime,String endTime) {
		Map<String,Object> returnMap = new HashMap<>();
		try {
			String MERCHANT_NO =UserUtils.getUser().getCompany().getId(); //商户编号
			FindPmTalkTotal findPmTalkTotal = new FindPmTalkTotal();
			findPmTalkTotal.setMerchantNo(MERCHANT_NO);
			findPmTalkTotal.setBeginDate(startTime);
			findPmTalkTotal.setEndDate(endTime);
			findPmTalkTotal.setDimensionSt(DimensionSt.MERCHANT.toString());
			List<FindPmTalkTotalReturn> list= pmTalkTotalService.findPmTalkTotalList(findPmTalkTotal);
			List<String> daysts = Lists.newArrayList();
			List<Integer> nums = Lists.newArrayList();
			for (FindPmTalkTotalReturn findPmTalkTotalReturn : list) {
				nums.add(findPmTalkTotalReturn.getNumTalk());
				daysts.add(findPmTalkTotalReturn.getStartDate());
			}
			
			returnMap.put(DAYSTS, daysts);
			returnMap.put(NUMS, nums);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnMap;
	}
	
}
