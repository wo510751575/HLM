package com.lj.business.api.controller.st;
//package com.lj.business.api.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import com.lj.business.st.dto.*;
//import com.lj.business.st.service.*;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.util.CollectionUtils;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.lj.base.core.util.DateUtils;
//import com.lj.business.st.domain.WorkRatioShop;
//import com.lj.business.st.dto.AreaOrderAnalyze.FindAreaOrderAnalyze;
//import com.lj.business.st.dto.AreaOrderAnalyze.FindAreaOrderAnalyzeReturn;
//import com.lj.business.st.dto.CfCountAnalyze.FindCfCountAnalyze;
//import com.lj.business.st.dto.CfCountAnalyze.FindCfCountAnalyzeReturn;
//import com.lj.business.st.dto.PmTalkTotal.FindPmTalkTotal;
//import com.lj.business.st.dto.PmTalkTotal.FindPmTalkTotalReturn;
//import com.lj.business.st.dto.PmTypeTotal.FindPmTypeTotal;
//import com.lj.business.st.dto.PmTypeTotal.FindPmTypeTotalReturn;
//import com.lj.business.st.dto.WorkRatioShop.FindWorkRatioShop;
//import com.lj.business.st.emus.DimensionSt;
//
///**
// * 类说明：运营分析
// * <p>
// * <p>
// * <p>
// * 详细描述：
// *
// * @author 武鹏飞
// * <p>
// * CreateDate: 2017年7月27日
// * @Company: 扬恩科技有限公司
// */
//@Controller
//@RequestMapping(value = "/operationAnalysis/")
//public class OperationAnalysisAction {
//	/**
//	 * Logger for this class
//	 */
//	private static final Logger logger = LoggerFactory.getLogger(OperationAnalysisAction.class);
//
//    @Resource
//    private IPmTypeTotalService pmTypeTotalService;
//
//    @Resource
//    private ICfCountAnalyzeService cfCountAnalyzeService;
//
//    @Resource
//    private IAreaOrderAnalyzeService areaOrderAnalyzeService;
//
//    @Resource
//    private IPmTalkTotalService pmTalkTotalService;
//
//    @Resource
//    private IWorkRatioShopService workRatioShopService;
//
//    @Resource
//    private IClientExpTotalService clientExpTotalService;
//
//    @Resource
//    private IWorkRatioAreaService workRatioAreaService;
//
//    @Resource
//    private IClientAnalyzeService clientAnalyzeService;
//
//    @Resource
//    private IWorkRatioGmService workRatioGmService;
//
//    /**
//     * 方法说明：查询客户总量报表
//     *
//     * @param findPmTypeTotal
//     * @return
//     */
//    @RequestMapping(value = "findPmTypeTotal.do")
//    @ResponseBody
//    public List<FindPmTypeTotalReturn> findPmTypeTotal(FindPmTypeTotal findPmTypeTotal) {
//        return pmTypeTotalService.findPmTypeTotalList(findPmTypeTotal);
//    }
//
//    /**
//     * 方法说明：查询订单量报表
//     *
//     * @param findWorkRatioShop
//     * @return
//     */
//    @RequestMapping(value = "findOrder.do")
//    @ResponseBody
//    public FindOrderReturn findOrder(FindWorkRatioShop findWorkRatioShop) {
//        List<WorkRatioShop> workRatioShopList = workRatioShopService.findWorkRatioShopList(findWorkRatioShop);
//        FindOrderReturn result = new FindOrderReturn();
//        List<Map<String, Object>> dataList = new ArrayList<>();
//        Map<String, Object> maxDatas = new HashMap<>();
//        Long totalNum = 0L;
//        if (!CollectionUtils.isEmpty(workRatioShopList)) {
//            WorkRatioShop maxWorkRatioShop = null;
//            for (WorkRatioShop each : workRatioShopList) {
//                totalNum += each.getNumOrder();
//                Map<String, Object> itemMap = new HashMap<>();
//                itemMap.put("date", DateUtils.formatDate(each.getDaySt(), DateUtils.PATTERN_yyyy_MM_dd));
//                itemMap.put("number", each.getNumOrder());
//                dataList.add(itemMap);
//
//                // 查询最大的数据
//                if (maxWorkRatioShop == null || each.getNumOrder() > maxWorkRatioShop.getNumOrder()) {
//                    maxWorkRatioShop = each;
//                }
//            }
//
//            maxDatas.put("date", DateUtils.formatDate(maxWorkRatioShop.getDaySt(), DateUtils.PATTERN_yyyy_MM_dd));
//            maxDatas.put("number", maxWorkRatioShop.getNumOrder());
//        }
//        result.setMaxDatas(maxDatas);
//        result.setTotalOrderNum(totalNum.intValue());
//        result.setDatas(dataList);
//        return result;
//    }
//
//    /**
//     * 方法说明：查询新增客户报表.
//     *
//     * @param findWorkRatioShop the find work ratio shop
//     * @return the find new customer return
//     */
//    @RequestMapping(value = "findNewCustomer.do")
//    @ResponseBody
//    public FindNewCustomerReturn findNewCustomer(FindWorkRatioShop findWorkRatioShop) {
//		logger.debug("findNewCustomer(FindWorkRatioShop findWorkRatioShop={}) - start", findWorkRatioShop); 
//
//        List<WorkRatioShop> workRatioShopList = workRatioShopService.findWorkRatioShopList(findWorkRatioShop);
//        FindNewCustomerReturn result = new FindNewCustomerReturn();
//        Map<String, Object> maxDatas = new HashMap<>();
//        List<Map<String, Object>> dataList = new ArrayList<>();
//        Long totalNum = 0L;
//        if (!CollectionUtils.isEmpty(workRatioShopList)) {
//            WorkRatioShop maxWorkRatioShop = null;
//            for (WorkRatioShop each : workRatioShopList) {
//                totalNum += each.getNumPmAdd();
//                Map<String, Object> itemMap = new HashMap<>();
//                itemMap.put("date", DateUtils.formatDate(each.getDaySt(), DateUtils.PATTERN_yyyy_MM_dd));
//                itemMap.put("number", each.getNumPmAdd());
//                dataList.add(itemMap);
//
//                // 查询最大的数据
//                if (maxWorkRatioShop == null || each.getNumPmAdd() > maxWorkRatioShop.getNumPmAdd()) {
//                    maxWorkRatioShop = each;
//                }
//            }
//
//            maxDatas.put("date", DateUtils.formatDate(maxWorkRatioShop.getDaySt(), DateUtils.PATTERN_yyyy_MM_dd));
//            maxDatas.put("number", maxWorkRatioShop.getNumPmAdd());
//        }
//        result.setMaxDatas(maxDatas);
//        result.setTotalCustomerNum(totalNum.intValue());
//        result.setDatas(dataList);
//
//		logger.debug("findNewCustomer() - end - return value={}", result); 
//        return result;
//    }
//
//    /**
//     *方法说明： 查询到店体验客户报表
//     *
//     * @param findClientExpTotal
//     * @return
//     */
//    @RequestMapping(value = "findClientExpTotal.do")
//    @ResponseBody
//    public FindClientExpTotalReturn findClientExpTotalList(FindClientExpTotal findClientExpTotal) {
//        return clientExpTotalService.findClientExpTotalList(findClientExpTotal);
//    }
//
//    /**
//     *方法说明： 查询意向客户报表
//     *
//     * @param findWorkRatioShop
//     * @return
//     */
//    @RequestMapping(value = "findIntentionCustomer.do")
//    @ResponseBody
//    public FindIntentionCustomerReturn findIntentionCustomer(FindWorkRatioShop findWorkRatioShop) {
//        List<WorkRatioShop> workRatioShopList = workRatioShopService.findWorkRatioShopList(findWorkRatioShop);
//        FindIntentionCustomerReturn result = new FindIntentionCustomerReturn();
//        if (!CollectionUtils.isEmpty(workRatioShopList)) {
//            Map<String, Object> maxDatas = new HashMap<>();
//            List<Map<String, Object>> dataList = new ArrayList<>();
//
//            Long totalNum = 0L;
//            WorkRatioShop maxWorkRatioShop = null;
//            
//            String preDay = DateUtils.formatDate(DateUtils.getPreday(new Date()), DateUtils.PATTERN_yyyy_MM_dd);//前一天时间
//            
//            for (WorkRatioShop each : workRatioShopList) {
//            	String queryDay = DateUtils.formatDate(each.getDaySt(), DateUtils.PATTERN_yyyy_MM_dd);//前一天时间
//            	
//            	if (preDay.equals(queryDay)) {
//            		totalNum = each.getNumPmIntention();
//				}
//            	
//                Map<String, Object> itemMap = new HashMap<>();
//                itemMap.put("date", DateUtils.formatDate(each.getDaySt(), DateUtils.PATTERN_yyyy_MM_dd));
//                itemMap.put("number", each.getNumPmIntention());
//                dataList.add(itemMap);
//
//                // 查询最大的数据
//                if (maxWorkRatioShop == null || each.getNumPmIntention() > maxWorkRatioShop.getNumPmIntention()) {
//                    maxWorkRatioShop = each;
//                }
//            }
//
//            maxDatas.put("date", DateUtils.formatDate(maxWorkRatioShop.getDaySt(), DateUtils.PATTERN_yyyy_MM_dd));
//            maxDatas.put("number", maxWorkRatioShop.getNumPmIntention());
//            result.setMaxDatas(maxDatas);
//            result.setTotalCustomerNum(totalNum.intValue());
//            result.setDatas(dataList);
//        }
//        return result;
//    }
//
//    /**
//     *方法说明： 查询维护客户报表
//     *
//     * @param findWorkRatioShop
//     * @return
//     */
//    @RequestMapping(value = "findKeepCustomer.do")
//    @ResponseBody
//    public FindKeepCustomerReturn findKeepCustomer(FindWorkRatioShop findWorkRatioShop) {
//        List<WorkRatioShop> workRatioShopList = workRatioShopService.findWorkRatioShopList(findWorkRatioShop);
//        FindKeepCustomerReturn result = new FindKeepCustomerReturn();
//        if (!CollectionUtils.isEmpty(workRatioShopList)) {
//            Map<String, Object> maxDatas = new HashMap<>();
//            List<Map<String, Object>> dataList = new ArrayList<>();
//
//            Long totalNum = 0L;
//            WorkRatioShop maxWorkRatioShop = null;
//            
//            String preDay = DateUtils.formatDate(DateUtils.getPreday(new Date()), DateUtils.PATTERN_yyyy_MM_dd);//前一天时间
//            
//            for (WorkRatioShop each : workRatioShopList) {
//            	String queryDay = DateUtils.formatDate(each.getDaySt(), DateUtils.PATTERN_yyyy_MM_dd);//前一天时间
//            	
//            	if (preDay.equals(queryDay)) {
//            		totalNum = each.getNumPmKeep();
//				}
//            	
//                Map<String, Object> itemMap = new HashMap<>();
//                itemMap.put("date", DateUtils.formatDate(each.getDaySt(), DateUtils.PATTERN_yyyy_MM_dd));
//                itemMap.put("number", each.getNumPmKeep());
//                dataList.add(itemMap);
//
//                // 查询最大的数据
//                if (maxWorkRatioShop == null || each.getNumPmKeep() > maxWorkRatioShop.getNumPmKeep()) {
//                    maxWorkRatioShop = each;
//                }
//            }
//
//            maxDatas.put("date", DateUtils.formatDate(maxWorkRatioShop.getDaySt(), DateUtils.PATTERN_yyyy_MM_dd));
//            maxDatas.put("number", maxWorkRatioShop.getNumPmKeep());
//            result.setMaxDatas(maxDatas);
//            result.setTotalCustomerNum(totalNum.intValue());
//            result.setDatas(dataList);
//        }
//        return result;
//    }
//
//    /**
//     *方法说明： 查询放弃客户报表
//     *
//     * @param findWorkRatioShop
//     * @return
//     */
//    @RequestMapping(value = "findAbondonCustomer.do")
//    @ResponseBody
//    public FindKeepAbondonReturn findAbondonCustomer(FindWorkRatioShop findWorkRatioShop) {
//        List<WorkRatioShop> workRatioShopList = workRatioShopService.findWorkRatioShopList(findWorkRatioShop);
//        FindKeepAbondonReturn result = new FindKeepAbondonReturn();
//        Map<String, Object> maxDatas = new HashMap<>();
//        List<Map<String, Object>> dataList = new ArrayList<>();
//        Long totalNum = 0L;
//        if (!CollectionUtils.isEmpty(workRatioShopList)) {
//            WorkRatioShop maxWorkRatioShop = null;
//            
//            String preDay = DateUtils.formatDate(DateUtils.getPreday(new Date()), DateUtils.PATTERN_yyyy_MM_dd);//前一天时间
//            
//            for (WorkRatioShop each : workRatioShopList) {
//            	
//            	String queryDay = DateUtils.formatDate(each.getDaySt(), DateUtils.PATTERN_yyyy_MM_dd);//前一天时间
//            	if (preDay.equals(queryDay)) {
//            		totalNum = each.getNumPmAbandon();
//				}
//            	
//                Map<String, Object> itemMap = new HashMap<>();
//                itemMap.put("date", DateUtils.formatDate(each.getDaySt(), DateUtils.PATTERN_yyyy_MM_dd));
//                itemMap.put("number", each.getNumPmAbandon());
//                dataList.add(itemMap);
//
//                // 查询最大的数据
//                if (maxWorkRatioShop == null || each.getNumPmAbandon() > maxWorkRatioShop.getNumPmAbandon()) {
//                    maxWorkRatioShop = each;
//                }
//            }
//            maxDatas.put("date", DateUtils.formatDate(maxWorkRatioShop.getDaySt(), DateUtils.PATTERN_yyyy_MM_dd));
//            maxDatas.put("number", maxWorkRatioShop.getNumPmAbandon());
//        }
//        result.setMaxDatas(maxDatas);
//        result.setTotalCustomerNum(totalNum.intValue());
//        result.setDatas(dataList);
//        return result;
//    }
//
//    /**
//     * 方法说明：查询销售漏斗报表
//     *
//     * @param findWorkRatioShop
//     * @return
//     */
//    @RequestMapping(value = "findSalesFunnel.do")
//    @ResponseBody
//    public FindSalesFunnelReturn findSalesFunnel(FindWorkRatioShop findWorkRatioShop) {
//        List<WorkRatioShop> workRatioShopList = workRatioShopService.findWorkRatioShopList(findWorkRatioShop);
//        FindSalesFunnelReturn result = new FindSalesFunnelReturn();
//        Map<String, Object> maxDatas = new HashMap<>();
//        List<Map<String, Object>> dataList = new ArrayList<>();
//        // 计算总量
//        Long totalCustomerNum = 0L;
//        Long intentionCustomerNum = 0L;
//        Long orderNum = 0L;
//        
//        if (!CollectionUtils.isEmpty(workRatioShopList)) {
//            String preDay = DateUtils.formatDate(DateUtils.getPreday(new Date()), DateUtils.PATTERN_yyyy_MM_dd);//前一天时间
//            for (WorkRatioShop each : workRatioShopList) {
//            	
//            	String queryDate = DateUtils.formatDate(each.getDaySt(), DateUtils.PATTERN_yyyy_MM_dd);//查询出时间
//            	
//            	if (preDay.equals(queryDate)) {//取前一天时间的数据
//            		totalCustomerNum = each.getNumPm();
//            		intentionCustomerNum = each.getNumPmIntention();
//				}
//            	
//                orderNum += each.getNumOrder();
//                
//            }
//            
//            for (WorkRatioShop each : workRatioShopList) {
//            	Map<String, Object> itemMap = new HashMap<>();
//                itemMap.put("date", DateUtils.formatDate(each.getDaySt(), DateUtils.PATTERN_yyyy_MM_dd));
//                itemMap.put("number", getRatio(orderNum, each.getNumOrder()));
//                dataList.add(itemMap);
//            }
//
//            maxDatas.put("number", getRatio(totalCustomerNum, orderNum));
//        }
//        result.setMaxDatas(maxDatas);
//        result.setTotalCustomerNum(totalCustomerNum.intValue());
//        result.setIntentionCustomerNum(intentionCustomerNum.intValue());
//        result.setOrderNum(orderNum.intValue());
//        result.setDatas(dataList);
//        return result;
//    }
//
//    /**
//     *方法说明： 查询跟进次数报表
//     *
//     * @param findCfCountAnalyze
//     * @return
//     */
//    @RequestMapping(value = "findCfCountAnalyze.do")
//    @ResponseBody
//    public List<FindCfCountAnalyzeReturn> findCfCountAnalyzeList(FindCfCountAnalyze findCfCountAnalyze) {
//        return cfCountAnalyzeService.findCfCountAnalyzeList(findCfCountAnalyze);
//    }
//
//    /**
//     * 方法说明：查询区域成单报表
//     *
//     * @param findAreaOrderAnalyze
//     * @return
//     */
//    @RequestMapping(value = "findAreaOrderAnalyze.do")
//    @ResponseBody
//    public List<FindAreaOrderAnalyzeReturn> findAreaOrderAnalyzeList(FindAreaOrderAnalyze findAreaOrderAnalyze) {
//        return areaOrderAnalyzeService.findAreaOrderAnalyzeList(findAreaOrderAnalyze);
//    }
//
//    /**
//     * 方法说明：查询客户行为分析报表
//     *
//     * @param findPmTalkTotal
//     * @return
//     */
//    @RequestMapping(value = "findPmTalkTotal.do")
//    @ResponseBody
//    public List<FindPmTalkTotalReturn> findPmTalkTotalList(FindPmTalkTotal findPmTalkTotal) {
//        return pmTalkTotalService.findPmTalkTotalList(findPmTalkTotal);
//    }
//
//    /**
//     * 方法说明：查询客户画像报表
//     *
//     * @param findClientAnalyzeAndOthers
//     * @return
//     */
//    @RequestMapping(value = "findClientAnalyze.do")
//    @ResponseBody
//    public FindClientAnalyzeAndOthersReturn findClientAnalyzeAndOthers(FindClientAnalyzeAndOthers findClientAnalyzeAndOthers) {
//        return clientAnalyzeService.findClientAnalyzeAndOthersReturn(findClientAnalyzeAndOthers);
//    }
//
//    /**
//     * 方法说明：查询区域客户分析报表
//     *
//     * @param findWorkRatioArea
//     * @return
//     */
//    @RequestMapping(value = "findWorkRatioArea.do")
//    @ResponseBody
//    public FindWorkRatioAreaReturn findWorkRatioAreaList(FindWorkRatioArea findWorkRatioArea) {
//        return workRatioAreaService.findWorkRatioAreaList(findWorkRatioArea);
//    }
//
//    private String getRatio(Long total, Long each) {
//        BigDecimal bd = new BigDecimal(total == 0 ? 0 : each * 100.0 / total);
//        bd = bd.setScale(2, RoundingMode.HALF_UP);
//        return bd.toString();
//    }
//    
//    /**
//     * 
//     *
//     * 方法说明：H5首页销售额接口
//     *
//     * @param merchantNo
//     * @return
//     *
//     * @author 段志鹏 CreateDate: 2017年8月4日
//     *
//     */
//    @RequestMapping(value = "indexSale.do", produces = "application/json;charset=UTF-8")
//    @ResponseBody
//    public Map<String, Object> indexSale(String merchantNo, String shopNo, String areaCode, String dimensionSt) {
//    	Map<String, Object> parmMap = new HashMap<String, Object>();
//    	parmMap.put("merchantNo", merchantNo);
//    	if (DimensionSt.SHOP.toString().equals(dimensionSt)) {
//    		parmMap.put("shopNo", shopNo);
//		} else if (DimensionSt.AREA.toString().equals(dimensionSt)) {
//			parmMap.put("areaCode", areaCode);
//		}
//    	parmMap.put("dimensionSt", dimensionSt);
//        return workRatioShopService.indexSale(parmMap);
//    }
//
//    /**
//     * 方法说明：运营分析首页接口
//     * @param findOperateDayReport
//     * @return
//     */
//    @RequestMapping(value = "index.do")
//    @ResponseBody
//    public FindOperateAnalyzeIndexReturn index(FindOperateDayReport findOperateDayReport) {
//        return workRatioGmService.findOperateAnalyzeIndex(findOperateDayReport);
//    }
//
//}
