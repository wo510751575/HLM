package com.lj.oms.common;

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
import com.google.common.collect.Lists;
import com.lj.business.common.CommonConstant;
import com.lj.business.member.dto.CountPersonMemberReturn;
import com.lj.business.member.dto.FindCountPersonMember;
import com.lj.business.member.service.IPersonMemberBaseService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.oms.entity.sys.Office;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：首页
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 * 
 *         CreateDate: 2017年7月7日
 */
@Controller
@RequestMapping(value = "${adminPath}/index")
public class IndexController {

	private static final String MODULES_INDEX_COURSE = "modules/index/course";
	
	private final static String INDEX_VIEW_INDEX = "modules/index/index";
	
//	private final static String PM_TYPE_TYPE = "PM_TYPE_TYPE";
	
//	private final static String NUM_PM = "NUM_PM";
	
//	private final static String DAY_ST = "DAY_ST";
	
	private final static String MERCHANT_NO = "merchantNo";
	
//	@Autowired
//	private IShopService shopService;
//	@Autowired
//	private IGuidMemberService guidMemberService;
	@Autowired
	private IPersonMemberService personMemberService;
//	@Autowired
//	private IManagerMemberService managerMemberService;
//	@Resource
//	private IClientExpTotalService clientExpTotalService;
//	@Autowired
//	private IPmTypeTotalService pmTypeTotalService;
//	@Autowired
//	private IWorkRatioShopService workRatioShopService;
//	@Autowired
//	private IMerchantService merchantService;
	@Autowired
	private IPersonMemberBaseService personMemberBaseService;

	
	
	
	/**
	 * 判断够否当前商户是否为超管
	 * @param merchantNo
	 * @return
	 */
	@RequestMapping(value = "gain")
	@ResponseBody
	public String gainMerchantNo(String merchantNo) {
		if (!Office.ROOT_ID.equals(merchantNo)) {
			return CommonConstant.NO;
		 }
		return CommonConstant.YES;
	}
    /**
     * 
     * 缓存当前页面的商户号,超级管理员切换账号需要当前登录的商户信息缓存，用做数据的查询条件
     * @param merchantNo
     * @return
     */
	@RequestMapping(value = "get")
	@ResponseBody
	public String getMerchantNo(String merchantNo) {
		String values = (String) CacheUtils.get(UserUtils.USER_GAIN_MERCHANT_NO);
		if (values == null) {
			CacheUtils.put(UserUtils.USER_GAIN_MERCHANT_NO, merchantNo);
		}else{
			UserUtils.removeCache(UserUtils.USER_GAIN_MERCHANT_NO);
			CacheUtils.put(UserUtils.USER_GAIN_MERCHANT_NO, merchantNo);
		}
		return merchantNo;
	}

	@RequestMapping(value = { "" })
	public String list(Model model) {
		try {
			model.addAttribute("time", new Date());
			model.addAttribute("merchantName", UserUtils.getMerchantName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INDEX_VIEW_INDEX;
	}

	@RequestMapping(value = "lists")
	public String lists() {
		return MODULES_INDEX_COURSE;
	}

	/**
	 * 
	 *
	 * 方法说明;到店客户体验统计
	 *
	 * @param startTime
	 * @param endTime
	 * @return
	 * 
	 * @author 罗书明 CreateDate: 2017年7月28日
	 *
	 */
	@RequestMapping(value = "client")
	@ResponseBody
	public Map<String, Object> client(String startTime, String endTime) {
		Map<String, Object> returnMap = new HashMap<>();
		Map<String, Object> maps = new HashMap<String, Object>();
		try {
			maps.put(MERCHANT_NO,UserUtils.getMerchantNo());
			maps.put("startTime", startTime);
			maps.put("endTime", endTime);
//			List<FindClientExpReturn> list = clientExpTotalService.findClientExpTotal(maps);
			List<String> datas = Lists.newArrayList();
			List<Integer> numAdds = Lists.newArrayList();
			int nums = 0;
//			for (FindClientExpReturn map : list) {
//				datas.add(map.getStDate());
//				numAdds.add(map.getNumAdd());
//				nums += map.getNumAdd();
//			}
			returnMap.put("numAdds", numAdds);
			returnMap.put("nums", nums);
			returnMap.put("datas", datas);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnMap;
	}

	/**
	 * 
	 *
	 * 方法说明：客户数据图表
	 *
	 * @param model
	 * @param beginDate
	 * @param endDate
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月28日
	 *
	 */
	/*@RequestMapping(value = { "findPmTypeTotalList" })
	@ResponseBody
	@Deprecated
	public Map<String, Object> findPmTypeTotalList(String startTime, String endTime) {
		Map<String, Object> returnMap = new HashMap<>();
		Map<String, Object> parmMap = new HashMap<String, Object>();
		try {
			
			parmMap.put(MERCHANT_NO, UserUtils.getMerchantNo());
			parmMap.put("dimensionSt", "MERCHANT");
//			parmMap.put("startTime", startTime);
//			parmMap.put("endTime", endTime);
//			List<Map<String, Object>> list = pmTypeTotalService.findPmTypeList(parmMap);
			List<String> daysts = Lists.newArrayList(); // 时间
			List<String> intentionNum = Lists.newArrayList(); // 意向
			List<String> otherNum = Lists.newArrayList(); // 非意向
			List<String> urgencyNum = Lists.newArrayList(); // 紧急
			List<String> repeatNum = Lists.newArrayList(); // 交叉
			List<String> giveUpNum = Lists.newArrayList(); // 暂停跟进
			List<String> successNum = Lists.newArrayList(); // 成单
			List<String> intentionNumNo = Lists.newArrayList(); // 意向未到店
			String dayTemp = "";

			if (list.size() > 0) {
				for (Map<String, Object> map : list) {
					if (map.get(PM_TYPE_TYPE) != null) {
						// 非意向客户
						if (map.get(PM_TYPE_TYPE).equals(
								PmTypeType.OTHER.toString())) {
							otherNum.add(map.get(NUM_PM).toString());
						}
						// 意向客户
						if (map.get(PM_TYPE_TYPE).equals(
								PmTypeType.INTENTION.toString())) {
							intentionNum.add(map.get(NUM_PM).toString());
						}
						// 紧急客户
						if (map.get(PM_TYPE_TYPE).equals(
								PmTypeType.URGENCY.toString())) {
							urgencyNum.add(map.get(NUM_PM).toString());
						}
						// 交叉客户
						if (map.get(PM_TYPE_TYPE).equals(
								PmTypeType.REPEAT.toString())) {
							repeatNum.add(map.get(NUM_PM).toString());
						}
						// 暂停跟进客户
						if (map.get(PM_TYPE_TYPE).equals(
								PmTypeType.GIVE_UP.toString())) {
							giveUpNum.add(map.get(NUM_PM).toString());
						}
						// 意向未到店
						if (map.get(PM_TYPE_TYPE).equals(
								PmTypeType.INTENTION_N.toString())) {
							intentionNumNo.add(map.get(NUM_PM).toString());
						}

						// 成单客户
						if (map.get(PM_TYPE_TYPE).equals(
								PmTypeType.SUCCESS.toString())) {
							successNum.add(map.get(NUM_PM).toString());
						}
					}
					if (map.get(DAY_ST) != null) {
						String day = map.get(DAY_ST).toString();
						if (!day.equals(dayTemp)) {
							daysts.add(day);
						}
						dayTemp = day;
					}
				}
			}

			int count = managerMemberService.findCountManagerMember(UserUtils
					.getUser().getCompany().getId());

			returnMap.put("numPmTotle", count); // 总数
			returnMap.put("daysts", daysts); // 日期
			returnMap.put("intentionNum", intentionNum); // 意向
			returnMap.put("otherNum", otherNum); // 非意向
			returnMap.put("urgencyNum", urgencyNum); // 紧急
			returnMap.put("repeatNum", repeatNum); // 交叉
			returnMap.put("giveUpNum", giveUpNum); // 暂停跟进
			returnMap.put("successNum", successNum); // 成单
			returnMap.put("intentionNumNo", intentionNumNo); // 意向未到店

		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnMap;
	}*/

	/**
	 * 
	 *
	 * 方法说明：新增客户数据
	 *
	 * @param model
	 * @param beginDate
	 * @param endDate
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月28日
	 *
	 */
	@RequestMapping(value = { "findIntentionPmList" })
	@ResponseBody
	public Map<String, Object> findIntentionPmList(String startTime, String endTime,
			FindCountPersonMember findCountPersonMember) {
		Map<String, Object> returnMap = new HashMap<>();
		try {
			findCountPersonMember.setMerchantNo(UserUtils.getMerchantNo());
			findCountPersonMember.setStartTime(startTime);
			findCountPersonMember.setEndTime(endTime);
			List<CountPersonMemberReturn> list = personMemberService
					.findGroupCountByDay(findCountPersonMember);
			List<String> daysts = Lists.newArrayList();

			List<String> nums = Lists.newArrayList();
			int numTotle = 0;
			if (list.size() > 0) {
				for (CountPersonMemberReturn countReturn : list) {
					daysts.add(countReturn.getCreateDate());
					nums.add(countReturn.getNumAdd());
					numTotle += Integer
							.valueOf(countReturn.getNumAdd() == null ? "0"
									: countReturn.getNumAdd().toString());
				}
			}
			returnMap.put("daysts", daysts);
			returnMap.put("nums", nums);
			returnMap.put("numTotle", numTotle);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnMap;
	}

	/**
	 * 
	 *
	 * 方法说明：销售额
	 *
	 * @param model
	 * @param startTime
	 * @param endTime
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月28日
	 *
	 */
	/*@RequestMapping(value = { "findSaleList" })
	@ResponseBody
	@Deprecated
	public Map<String, Object> findSaleList(String startTime,String endTime) {
		Map<String, Object> returnMap = new HashMap<>();
		FindWorkRatioShop findWorkRatioShop = new FindWorkRatioShop();
		try {
			findWorkRatioShop.setMerchantNo(UserUtils.getMerchantNo());
			findWorkRatioShop.setBeginDate(startTime);
			findWorkRatioShop.setEndDate(endTime);
			findWorkRatioShop.setDimensionSt(DimensionSt.MERCHANT.toString());
			List<FindExcellentShopReturn> list = workRatioShopService.findWorkRatioShopReturnList(findWorkRatioShop);
			List<String> daysts = Lists.newArrayList();
			List<Long> nums = Lists.newArrayList();
			Long numTotle = 0L;
			for (FindExcellentShopReturn item : list) {
				daysts.add(DateUtils.formatDate(item.getDaySt(), "yyyy-MM-dd"));
				// 数据库存的单位是分,要除以100
				if (item.getNumSale() != 0) {
					nums.add(item.getNumSale() / 100);
					numTotle += item.getNumSale() / 100;
				} else {
					nums.add(item.getNumSale());
					numTotle += item.getNumSale();
				}
			}
			returnMap.put("daysts", daysts);
			returnMap.put("nums", nums);
			returnMap.put("numTotle", numTotle);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnMap;
	}*/

	/**
	 *
	 * 方法说明：根据商户号初始化PMB表的wxOpenId
	 * 
	 * @param merchantNo
	 * @return
	 * @author 李端强 CreateDate: 2018年1月10日
	 */
	@RequestMapping(value = "initWxOpenIdByMerchantNo")
	@ResponseBody
	public boolean initWxOpenIdByMerchantNo(String merchantNo) {
		return personMemberBaseService.initWxOpenIdByMerchantNo(merchantNo);
	}

}
