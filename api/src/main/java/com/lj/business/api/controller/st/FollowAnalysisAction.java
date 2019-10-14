package com.lj.business.api.controller.st;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.business.member.dto.FindGuidMemberPage;
import com.lj.business.member.dto.FindGuidMemberPageReturn;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.st.dto.FindFollowClientTotalIndex;
import com.lj.business.st.dto.FindFollowClientTotalIndexAllReturn;
import com.lj.business.st.dto.FindFollowClientTotalIndexReturn;
import com.lj.business.st.dto.FindSalesGmDayFirstCompleteRateDto;
import com.lj.business.st.dto.FindSalesGmDayFirstCompleteRateReturn;
import com.lj.business.st.dto.FindSalesGmDayFirstIndex;
import com.lj.business.st.dto.FindSalesGmDayFirstIndexReturn;
import com.lj.business.st.dto.FindWorkDayGmCfAnalyzeIndexReturn;
import com.lj.business.st.dto.FindWorkDayGmIndex;
import com.lj.business.st.dto.FindWorkDayGmIndexReturn;
import com.lj.business.st.dto.CfAnalyze.FindCfAnalyze;
import com.lj.business.st.dto.CfAnalyze.FindCfAnalyzeReturn;
import com.lj.business.st.dto.MaterialTotal.FindMaterialTotal;
import com.lj.business.st.dto.MaterialTotal.FindMaterialTotalAllReturn;
import com.lj.business.st.dto.MaterialTotal.FindMaterialTotalReturn;
import com.lj.business.st.dto.PmLabelTotal.FindPmLabelTotal;
import com.lj.business.st.dto.PmLabelTotal.FindPmLabelTotalAllReturn;
import com.lj.business.st.dto.PmLabelTotal.FindPmLabelTotalReturnDto;
import com.lj.business.st.dto.PmTalkTotal.FindPmTalkTotal;
import com.lj.business.st.dto.PmTalkTotal.FindPmTalkTotalAllReturn;
import com.lj.business.st.dto.PmTalkTotal.FindPmTalkTotalReturn;
import com.lj.business.st.dto.PmTypeTotal.FindPmTypeTotal;
import com.lj.business.st.dto.PmTypeTotal.FindPmTypeTotalAllReturn;
import com.lj.business.st.dto.PmTypeTotal.FindPmTypeTotalReturn;
import com.lj.business.st.dto.SocialAnalyze.FindSocialAnalyzeAllReturn;
import com.lj.business.st.dto.SocialAnalyze.FindSocialAnalyzeReturn;
import com.lj.business.st.dto.SocialAnalyze.FindSocialAnalyzeTotal;
import com.lj.business.st.service.ICfAnalyzeService;
import com.lj.business.st.service.IMaterialTotalService;
import com.lj.business.st.service.IPmLabelTotalService;
import com.lj.business.st.service.IPmTalkTotalService;
import com.lj.business.st.service.IPmTypeTotalService;
import com.lj.business.st.service.ISalesGmDayDetailService;
import com.lj.business.st.service.ISalesGmDayFirstService;
import com.lj.business.st.service.ISocialAnalyzeService;
import com.lj.business.st.service.IWorkRatioGmService;

/**
 * 
 * 
 * 类说明：跟进分析统计
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 冯辉
 * 
 *         CreateDate: 2017年7月28日
 */
@Controller
@RequestMapping(value = "/followAnalysisAction/")
public class FollowAnalysisAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(FollowAnalysisAction.class);

	@Resource
	private IPmTypeTotalService pmTypeTotalService;

	@Resource
	private IMaterialTotalService materialTotalService;

	@Resource
	private IPmLabelTotalService pmLabelTotalService;
	
	@Resource
	private ISocialAnalyzeService socialAnalyzeService;
	
	@Resource
	private IWorkRatioGmService workRatioGmService;
	
    @Resource
    private IPmTalkTotalService pmTalkTotalService;
    
    @Resource
    private ICfAnalyzeService cfAnalyzeService;
    
    @Resource
    private ISalesGmDayFirstService salesGmDayFirstService;
    
    @Resource
    private ISalesGmDayDetailService salesGmDayDetailService;
    
//    @Resource
//    private IClientExpService clientExpService;
//    @Resource
//    private IClientInviteService clientInviteService;
    @Resource
    private IGuidMemberService guidMemberService;
//    @Resource
//    private IClientFollowSummaryService clientFollowSummaryService;
    
	/**
	 * 
	 *
	 * 方法说明：查询客户总量报表
	 *
	 * @param findPmTypeTotal
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年10月9日
	 *
	 */
	@RequestMapping(value = "findPmTypeTotal.do")
	@ResponseBody
	public FindPmTypeTotalAllReturn findPmTypeTotalList(FindPmTypeTotal findPmTypeTotal) {
		logger.debug("findPmTypeTotal(FindPmTypeTotal findPmTypeTotal={}) - start", findPmTypeTotal); //$NON-NLS-1$

		FindPmTypeTotalAllReturn findPmTypeTotalAllReturn = new FindPmTypeTotalAllReturn();
		List<FindPmTypeTotalReturn> list = pmTypeTotalService.findPmTypeTotalListApp(findPmTypeTotal);
		int max = 0;
		String rpm = "";
		String fl = "";
		if (list != null && list.size() > 0) {
			for (FindPmTypeTotalReturn findPmTypeTotalReturn : list){
				if(max < findPmTypeTotalReturn.getNumPm()){
					max = findPmTypeTotalReturn.getNumPm();
					rpm = findPmTypeTotalReturn.getRatioPm();
//					fl = EnumUtils.getValue(PmTypeType.class, findPmTypeTotalReturn.getPmTypeType());
				}
			}
			findPmTypeTotalAllReturn.setTextInfo1("您的" + fl + "客户" + max + "个占比"+formatRatio(rpm)+"%");
			findPmTypeTotalAllReturn .setTextInfo2("您离成功就差一个台阶了,继续保持吧!");
		}

		findPmTypeTotalAllReturn.setList(list);
		logger.debug("findPmTypeTotal() - end - return value={}", findPmTypeTotalAllReturn); //$NON-NLS-1$
		return findPmTypeTotalAllReturn;
	}
	
	private String formatRatio(String rpm) {
		BigDecimal bg = new BigDecimal(new Double(rpm) * 100);
		bg = bg.setScale(2, RoundingMode.HALF_UP);
		return bg.intValue() + "";
	}

	/**
	 * 方法说明：查询客户标签报表
	 *
	 * @param findPmTypeTotal
	 * @return
	 */
	@RequestMapping(value = "findPmLabelTotal.do")
	@ResponseBody
	public FindPmLabelTotalAllReturn findPmTypeTotal(FindPmLabelTotal findPmLabelTotal) {
		logger.debug("findPmLabelTotal(findPmLabelTotal findPmLabelTotal={}) - start", findPmLabelTotal); //$NON-NLS-1$
		FindPmLabelTotalAllReturn findPmLabelTotalAllReturn = new FindPmLabelTotalAllReturn();
		List<FindPmLabelTotalReturnDto> list = pmLabelTotalService.findPmLabelTotalListApp(findPmLabelTotal);
		int max = 0;
		if (list != null && list.size() > 0) {
			max = list.get(0).getPmNum();
			findPmLabelTotalAllReturn.setTextInfo1("小主,您可知道!您" + max + "位客户是"
					+ list.get(0).getLabelName());
			findPmLabelTotalAllReturn.setTextInfo2("多跟客户聊聊宝宝的事情!你懂得!");
			int total = 0;
			for (FindPmLabelTotalReturnDto findPmLabelTotalReturnDto : list) {
				total = total + findPmLabelTotalReturnDto.getPmNum();
			}

			for (FindPmLabelTotalReturnDto findPmLabelTotalReturnDto : list) {
				BigDecimal child = new BigDecimal(
						findPmLabelTotalReturnDto.getPmNum());
				BigDecimal mother = new BigDecimal(total);
				BigDecimal r = new BigDecimal(0);
				if (total > 0) {
					r = child.divide(mother, 4, BigDecimal.ROUND_HALF_UP);
				}
				findPmLabelTotalReturnDto.setRatioPm(r.doubleValue());
			}
		}
		findPmLabelTotalAllReturn.setList(list);
		return findPmLabelTotalAllReturn;
	}

	/**
	 * 
	 *
	 * 方法说明：查询素材分析统计
	 *
	 * @param findMaterialTotal
	 * @return
	 *
	 * @author 冯辉 CreateDate: 2017年7月28日
	 *
	 */
	@RequestMapping(value = "findMaterialTotal.do")
	@ResponseBody
	public FindMaterialTotalAllReturn findMaterialTotal(
			FindMaterialTotal findMaterialTotal) {
		logger.debug("findMaterialTotal(FindMaterialTotal findMaterialTotal={}) - start", findMaterialTotal); //$NON-NLS-1$

		FindMaterialTotalAllReturn findMaterialTotalAllReturn = new FindMaterialTotalAllReturn();
		List<FindMaterialTotalReturn> list = materialTotalService
				.findMaterialTotalApp(findMaterialTotal);
		int max = 0;
		if (list != null && list.size() > 0) {
			max = list.get(0).getRespondNum();
			findMaterialTotalAllReturn.setTextInfo1(max + "位客户问了"
					+ list.get(0).getMaterialTypeName() + "的问题！");
			findMaterialTotalAllReturn.setTextInfo2("客户更关注"
					+ list.get(0).getMaterialTypeName() + "！多跟客户聊类似话题吧");
			int total = 0;
			for (FindMaterialTotalReturn findMaterialTotalReturn : list) {
				total = total + findMaterialTotalReturn.getRespondNum();
			}

			for (FindMaterialTotalReturn findMaterialTotalReturn : list) {
				BigDecimal child = new BigDecimal(
						findMaterialTotalReturn.getRespondNum());
				BigDecimal mother = new BigDecimal(total);
				BigDecimal r = new BigDecimal(0);
				if (total > 0) {
					r = child.divide(mother, 4, BigDecimal.ROUND_HALF_UP);
				}
				findMaterialTotalReturn.setRatioRespond(r.doubleValue());
			}
		}

		findMaterialTotalAllReturn.setList(list);

		logger.debug("findMaterialTotal() - end - return value={}", findMaterialTotalAllReturn); //$NON-NLS-1$
		return findMaterialTotalAllReturn;
	}
	
	/**
	 * 
	 *
	 * 方法说明：社交分析统计
	 *
	 * @param findSocialAnalyzeTotal
	 * @return
	 *
	 * @author 冯辉 CreateDate: 2017年7月28日
	 *
	 */	
	@RequestMapping(value = "findSocialAnalyze.do")
	@ResponseBody
	public FindSocialAnalyzeAllReturn findSocialAnalyze(FindSocialAnalyzeTotal findSocialAnalyzeTotal) {
		logger.debug("findSocialAnalyze(FindSocialAnalyzeTotal findSocialAnalyzeTotal={}) - start", findSocialAnalyzeTotal); //$NON-NLS-1$

		FindSocialAnalyzeAllReturn findSocialAnalyzeAllReturn = new FindSocialAnalyzeAllReturn();
		List<FindSocialAnalyzeReturn> list = socialAnalyzeService.findSocialAnalyzeApp(findSocialAnalyzeTotal);
		int max = 0;
		if (list != null && list.size() > 0) {
			for (FindSocialAnalyzeReturn findSocialAnalyzeReturn : list) {
				if(findSocialAnalyzeReturn.getNumSocial() > max){
					max = findSocialAnalyzeReturn.getNumSocial();
					findSocialAnalyzeAllReturn.setTextInfo1(DateUtils.formatDate(findSocialAnalyzeReturn.getStDate(), DateUtils.MM月DD日) + "社交" + max + "次!");
				}
			}
			findSocialAnalyzeAllReturn.setTextInfo2("客户肯定记住你了!");
		}
		findSocialAnalyzeAllReturn.setList(list);

		logger.debug("findSocialAnalyze() - end - return value={}", findSocialAnalyzeAllReturn); //$NON-NLS-1$
		return findSocialAnalyzeAllReturn;
	}
	
	/**
	 * 
	 *
	 * 方法说明：跟进统计首页
	 *
	 * @param findFollowClientTotalIndex
	 * @return
	 *
	 * @author 冯辉 CreateDate: 2017年7月31日
	 *
	 */
	@RequestMapping(value = "findFcTotalIndex.do")
	@ResponseBody
	public FindFollowClientTotalIndexAllReturn findFcTotalIndex(FindFollowClientTotalIndex findFollowClientTotalIndex) {
		
		FindFollowClientTotalIndexAllReturn findFollowClientTotalIndexAllReturn = new FindFollowClientTotalIndexAllReturn();
		
		FindFollowClientTotalIndexReturn findFollowClientTotalIndexReturn = new FindFollowClientTotalIndexReturn();
		findFollowClientTotalIndex.setBeginDate(findFollowClientTotalIndex.getBeginDateDay());
		findFollowClientTotalIndex.setEndDate(findFollowClientTotalIndex.getEndDateDay());
		Double day = workRatioGmService.findFcTotalIndex(findFollowClientTotalIndex);
		findFollowClientTotalIndexReturn.setRatioWorkDay(day);
		
		findFollowClientTotalIndex.setBeginDate(findFollowClientTotalIndex.getBeginDateWeek());
		findFollowClientTotalIndex.setEndDate(findFollowClientTotalIndex.getEndDateWeek());
		Double week = workRatioGmService.findFcTotalIndex(findFollowClientTotalIndex);
		findFollowClientTotalIndexReturn.setRatioWorkWeek(week);
		
		findFollowClientTotalIndex.setBeginDate(findFollowClientTotalIndex.getBeginDateMonth());
		findFollowClientTotalIndex.setEndDate(findFollowClientTotalIndex.getEndDateMonth());
		Double month = workRatioGmService.findFcTotalIndex(findFollowClientTotalIndex);
		findFollowClientTotalIndexReturn.setRatioWorkMonth(month);
		
		findFollowClientTotalIndexAllReturn.setFindFollowClientTotalIndexReturn(findFollowClientTotalIndexReturn);
		
//		FindGmIndexReturn findGmIndexReturn = new FindGmIndexReturn();
//		findGmIndexReturn.setMemberNoGm(findFollowClientTotalIndex.getMemberNoGm());
//		
//		findFollowClientTotalIndex.setBeginDate(findFollowClientTotalIndex.getBeginDateDay());
//		findFollowClientTotalIndex.setEndDate(findFollowClientTotalIndex.getEndDateDay());
//		Integer fgmday = workRatioGmService.findGmIndex(findFollowClientTotalIndex);
//		findGmIndexReturn.setRownumDay(fgmday);
//		
//		findFollowClientTotalIndex.setBeginDate(findFollowClientTotalIndex.getBeginDateWeek());
//		findFollowClientTotalIndex.setEndDate(findFollowClientTotalIndex.getEndDateWeek());
//		Integer fgmweek = workRatioGmService.findGmIndex(findFollowClientTotalIndex);
//		findGmIndexReturn.setRownumWeek(fgmweek);
//		
//		findFollowClientTotalIndex.setBeginDate(findFollowClientTotalIndex.getBeginDateMonth());
//		findFollowClientTotalIndex.setEndDate(findFollowClientTotalIndex.getEndDateMonth());
//		Integer fgmmonth = workRatioGmService.findGmIndex(findFollowClientTotalIndex);
//		findGmIndexReturn.setRownumMonth(fgmmonth);
//		
//		findFollowClientTotalIndexAllReturn.setFindGmIndexReturn(findGmIndexReturn);
		
		return findFollowClientTotalIndexAllReturn;
		
	}

    /**
     * 方法说明：查询客户行为分析报表
     *
     * @param findPmTalkTotal
     * @return
     */
    @RequestMapping(value = "findPmTalkTotal.do")
    @ResponseBody
    public FindPmTalkTotalAllReturn findPmTalkTotalList(FindPmTalkTotal findPmTalkTotal) {
    	FindPmTalkTotalAllReturn findPmTalkTotalAllReturn = new FindPmTalkTotalAllReturn();
        List<FindPmTalkTotalReturn> list = pmTalkTotalService.findPmTalkTotalListApp(findPmTalkTotal);
        List<FindPmTalkTotalReturn> orglist = pmTalkTotalService.findPmTalkTotalOrgListApp(findPmTalkTotal);
        int max = 0;
        if (list !=null && list.size()>0) {
			max = list.get(0).getNumTalk();
			findPmTalkTotalAllReturn.setTextInfo1(list.get(0).getStartDate()+"-"+list.get(0).getEndDate()+" "+max+"个客户给您回话!");
			findPmTalkTotalAllReturn.setTextInfo2("多跟客户在这个时间段聊天吧!");
		}
        findPmTalkTotalAllReturn.setList(list);
        findPmTalkTotalAllReturn.setOrglist(orglist);
		return findPmTalkTotalAllReturn;
    }
    
    /**
     * 
     *
     * 方法说明：日工作简报
     *
     * @param findWorkDayGmIndex
     * @return
     *
     * @author 冯辉 CreateDate: 2017年7月31日
     *
     */
	@RequestMapping(value = "findWorkDayGmIndex.do")
	@ResponseBody
	public List<FindWorkDayGmIndexReturn> findWorkDayGmIndex(FindWorkDayGmIndex findWorkDayGmIndex) {
		logger.debug("findWorkDayGmIndex(FindWorkDayGmIndex findWorkDayGmIndex={}) - start", findWorkDayGmIndex); //$NON-NLS-1$

		List<FindWorkDayGmIndexReturn> list = new ArrayList<FindWorkDayGmIndexReturn>();
		list = workRatioGmService.findWorkDayGmIndex(findWorkDayGmIndex);

		logger.debug("findWorkDayGmIndex() - end - return value={}", list); //$NON-NLS-1$
		return list;
	}
	
	 
    /**
     * 
     *
     * 方法说明：查找跟进分析摘要表信息_APP用
     *
     * @param findWorkDayGmIndex
     * @return
     *
     * @author 冯辉 CreateDate: 2017年7月31日
     *
     */
	@RequestMapping(value = "findCfAnalyze.do")
	@ResponseBody
	public List<FindCfAnalyzeReturn> findCfAnalyze(FindCfAnalyze findCfAnalyze) {
		logger.debug("findCfAnalyze(FindCfAnalyze findCfAnalyze={}) - start", findCfAnalyze); //$NON-NLS-1$

		List<FindCfAnalyzeReturn> returnList = cfAnalyzeService.findCfAnalyze(findCfAnalyze);
		logger.debug("findCfAnalyze(FindCfAnalyze) - end - return value={}", returnList); //$NON-NLS-1$
		return returnList;
	}
	
	/**
	 * 
	 *
	 * 方法说明：日工作简报和跟进进度合并
	 *
	 * @param findWorkDayGmIndex
	 * @return
	 *
	 * @author 冯辉 CreateDate: 2017年8月1日
	 *
	 */
	@RequestMapping(value = "findWorkDayGmCfAnalyzeIndex.do")
	@ResponseBody
	public FindWorkDayGmCfAnalyzeIndexReturn findWorkDayGmCfAnalyzeIndex(FindWorkDayGmIndex findWorkDayGmIndex) {
		logger.debug("findWorkDayGmCfAnalyzeIndex(FindWorkDayGmIndex findWorkDayGmIndex={}) - start", findWorkDayGmIndex); //$NON-NLS-1$

		FindWorkDayGmCfAnalyzeIndexReturn findWorkDayGmCfAnalyzeIndexReturn = new FindWorkDayGmCfAnalyzeIndexReturn();
		List<FindWorkDayGmIndexReturn> listWorkDay = new ArrayList<FindWorkDayGmIndexReturn>();
		listWorkDay = workRatioGmService.findWorkDayGmIndex(findWorkDayGmIndex);
		findWorkDayGmCfAnalyzeIndexReturn.setListWorkDay(listWorkDay);
		
		//彭阳添加
		FindCfAnalyze findCfAnalyze = new FindCfAnalyze();
		try {
			if(!StringUtils.isEmpty(findWorkDayGmIndex.getStDate())){
				findCfAnalyze.setDaySt(DateUtils.parseDate(findWorkDayGmIndex.getStDate(), DateUtils.PATTERN_yyyy_MM_dd));
			}
		} catch (ParseException e) {
			logger.error("findWorkDayGmCfAnalyzeIndex(FindWorkDayGmIndex)", e); //$NON-NLS-1$
		}
		findCfAnalyze.setMemberNoGm(findWorkDayGmIndex.getMemberNoGm());
		findCfAnalyze.setMerchantNo(findWorkDayGmIndex.getMerchantNo());
		List<FindCfAnalyzeReturn> listCfAnalyze = cfAnalyzeService.findCfAnalyze(findCfAnalyze);
		findWorkDayGmCfAnalyzeIndexReturn.setListCfAnalyze(listCfAnalyze);

		logger.debug("findWorkDayGmCfAnalyzeIndex() - end - return value={}", findWorkDayGmCfAnalyzeIndexReturn); //$NON-NLS-1$
		return findWorkDayGmCfAnalyzeIndexReturn;
	}
	
	/**
	 * 
	 *
	 * 方法说明：app首页导购区域冠军
	 *
	 *
	 * @author 冯辉 CreateDate: 2017年8月1日
	 *
	 */
	@RequestMapping(value = "findSalesGmDayFirst.do")
	@ResponseBody
	public List<FindSalesGmDayFirstIndexReturn> findSalesGmDayFirst(FindSalesGmDayFirstIndex findSalesGmDayFirstIndex){
		logger.debug("findSalesGmDayFirst(FindSalesGmDayFirstIndex findSalesGmDayFirstIndex={}) - start", findSalesGmDayFirstIndex); //$NON-NLS-1$

		List<FindSalesGmDayFirstIndexReturn> list = new ArrayList<FindSalesGmDayFirstIndexReturn>();
		list = salesGmDayFirstService.findSalesGmDayFirst(findSalesGmDayFirstIndex);

		logger.debug("findSalesGmDayFirst() - end - return value={}", list); //$NON-NLS-1$
		return list;
	}
	
	
	/**
	 * 
	 *
	 * 方法说明：区域导购冠军完成度
	 *
	 * @param findSalesGmDayFirstCompleteRateDto
	 * @return
	 *
	 * @author 冯辉 CreateDate: 2017年8月22日
	 *
	 */
	@RequestMapping(value = "findSalesGmDayFirstCompleteRate.do")
	@ResponseBody
	public FindSalesGmDayFirstCompleteRateReturn findSalesGmDayFirstCompleteRate(FindSalesGmDayFirstCompleteRateDto findSalesGmDayFirstCompleteRateDto){
		logger.debug("findSalesGmDayFirstCompleteRate(FindSalesGmDayFirstCompleteRateDto findSalesGmDayFirstCompleteRateDto={}) - start", findSalesGmDayFirstCompleteRateDto); //$NON-NLS-1$

		FindSalesGmDayFirstCompleteRateReturn findSalesGmDayFirstCompleteRateReturn = salesGmDayDetailService.findSalesGmDayFirstCompleteRate(findSalesGmDayFirstCompleteRateDto);

		logger.debug("findSalesGmDayFirstCompleteRate() - end - return value={}", findSalesGmDayFirstCompleteRateReturn); //$NON-NLS-1$
		return findSalesGmDayFirstCompleteRateReturn;
	}
	

	/**
	 * 
	 *
	 * 方法说明：到店统计
	 *
	 * @param merchantNo
	 * @param nowDate
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年8月9日
	 * @deprecated
	 */
	/*@RequestMapping(value = "findExpCount.do")
	@ResponseBody
	public Map<String,Integer> findExpCount(String merchantNo,String nowDate){
		logger.debug("findExpCount(String merchantNo={},String nowDate={}) - start", merchantNo,nowDate); //$NON-NLS-1$
		Map<String,Integer> returnMap =new HashMap<>();
		try {
			Map<String,Object> parmMap = new HashMap<String, Object>();
			parmMap.put("merchantNo", merchantNo);
			parmMap.put("expResult", ExpResult.Y.toString());
			parmMap.put("startTime", DateUtils.parseDate(nowDate, DateUtils.PATTERN_yyyy_MM_dd));
			parmMap.put("endTime", DateUtils.parseDate(nowDate, DateUtils.PATTERN_yyyy_MM_dd));
			List<Map<String,Object>> list = clientExpService.findExpResults(parmMap);
			returnMap.put("expResult", list.size());	//到店人数
			
			parmMap.put("inviteResult", InviteResult.Y.toString());
			list =clientInviteService.findInviteResults(parmMap);
			returnMap.put("inviteResult", list.size());	//邀约到店人数
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.debug("findExpCount() - end - return value={}", returnMap); //$NON-NLS-1$
		return returnMap;
	}*/
	
	/**
	 * 
	 *
	 * 方法说明：到店统计列表
	 *
	 * @param merchantNo
	 * @param memberType
	 * @param shopNo
	 * @param memberNo
	 * @param searchNoGm
	 * @param nowDate
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年8月16日
	 *
	 */
	/*@RequestMapping(value = "findExpCountForMemberType.do")
	@ResponseBody
	public Map<String,Object> findExpCountForMemberType(String merchantNo,String memberType,String shopNo,String memberNoGm,String searchNoGm, String nowDate){
		logger.debug("findExpCountForMemberType(String merchantNo={},String nowDate={}) - start", merchantNo,nowDate); //$NON-NLS-1$
		Map<String,Object> returnMap =new HashMap<>();
		try {
			returnMap= clientExpService.findExpCountForMemberType(merchantNo, memberType, shopNo, memberNoGm, searchNoGm, nowDate);
		} catch (Exception e) {
			logger.error("到店统计列表错误", e);
		}
		logger.debug("findExpCountForMemberType() - end - return value={}", returnMap); //$NON-NLS-1$
		return returnMap;
	}*/
	
	/**
	 * 
	 *
	 * 方法说明：查询导购列表
	 *
	 * @param merchantNo
	 * @param shopNo
	 * @param memberNoGm
	 * @return
	 *
	 * @author 冯辉 CreateDate: 2017年10月9日
	 *
	 */
	@RequestMapping(value = "findGuidsSec.do")
	@ResponseBody
	public List<FindGuidMemberPageReturn> findGuidsSec(String merchantNo,String shopNo,String memberNoGm){
		logger.debug("findGuidsSec(String merchantNo={},String shopNo={},String memberNoGm={}) - start", merchantNo,shopNo); //$NON-NLS-1$
		List<FindGuidMemberPageReturn> list = new ArrayList<FindGuidMemberPageReturn>();
		try {
			FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
			findGuidMemberPage.setMerchantNo(merchantNo);
//			findGuidMemberPage.setShopNo(shopNo);
			findGuidMemberPage.setMemberNo(memberNoGm);
			list= guidMemberService.findGuidMembersNoSelf(findGuidMemberPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("findGuidsSec() - end - return value={}", list); //$NON-NLS-1$
		return list;
	}
	
	/**
	 * 
	 *
	 * 方法说明：查询客户反馈信息
	 *
	 * @param findClientFollowSummary
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年04月16日
	 *
	 */
	/*@RequestMapping(value = "findClientFollow.do")
	@ResponseBody
	public List<FindImClientFollowReturn> findClientFollow(FindClientFollowSummary findClientFollowSummary){
		List<FindImClientFollowReturn> list=new ArrayList<FindImClientFollowReturn>();
		try {
			if (StringUtils.isNotEmpty(findClientFollowSummary.getMemberNo())) {
				for (String s :  findClientFollowSummary.getMemberNo().split(",")){
					findClientFollowSummary.setMemberNo(StringUtils.toString(s));
					FindImClientFollowReturn cf = clientFollowSummaryService.findImClientFollow(findClientFollowSummary);
					if(cf!=null){
						list.add(cf);
					}
				}
			}
			logger.debug("findClientFollow() - end - return value={}", list); //$NON-NLS-1$
		} catch (Exception e) {
			logger.info("查询客户反馈信息失败",e);
		}
		return list;
	}*/
	
}
