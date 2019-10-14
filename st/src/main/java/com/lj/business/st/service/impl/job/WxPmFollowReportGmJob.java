//package com.lj.business.st.service.impl.job;
//
//import org.apache.commons.collections.CollectionUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.Set;
//import javax.annotation.Resource;
//import org.springframework.stereotype.Service;
//import com.google.common.collect.Maps;
//import com.lj.base.core.util.DateUtils;
//import com.lj.base.core.util.StringUtils;
//import com.lj.business.member.dto.FindGuidMemberDto;
//import com.lj.business.member.dto.FindPersonMemberReturn;
//import com.lj.business.member.dto.FindShop;
//import com.lj.business.member.dto.FindShopPageReturn;
//import com.lj.business.member.dto.FindShopReturn;
//import com.lj.business.member.dto.GuidMemberReturnDto;
//import com.lj.business.member.dto.company.FindBranchCompany;
//import com.lj.business.member.dto.company.FindBranchCompanyPage;
//import com.lj.business.member.dto.company.FindBranchCompanyReturn;
//import com.lj.business.member.dto.wxPmFollow.FindWxPmByGm;
//import com.lj.business.member.emus.GuidStatus;
//import com.lj.business.member.emus.ShopStatus;
//import com.lj.business.member.service.IBranchCompanyService;
//import com.lj.business.member.service.IGuidMemberService;
//import com.lj.business.member.service.IPersonMemberService;
//import com.lj.business.member.service.IShopService;
//import com.lj.business.st.dto.wxPmFollow.AddWxPmFollowReportCompany;
//import com.lj.business.st.dto.wxPmFollow.AddWxPmFollowReportGm;
//import com.lj.business.st.dto.wxPmFollow.AddWxPmFollowReportShop;
//import com.lj.business.st.service.IWxPmFollowReportCompanyService;
//import com.lj.business.st.service.IWxPmFollowReportGmService;
//import com.lj.business.st.service.IWxPmFollowReportShopService;
//import com.lj.business.weixin.dto.imchat.FindChatTimesByGmAndMemberNo;
//import com.lj.business.weixin.service.IImChatInfoService;
//import com.lj.cc.clientintf.IJob;
//
///**
// * 
// * 
// * 类说明：导购微信客户跟踪日报生成任务
// *  
// * 
// * <p>
// * 详细描述：
// *   
// * @Company: 扬恩科技有限公司
// * @author 许新龙
// *   
// * CreateDate: 2018年8月6日
// */
//@Service
//public class WxPmFollowReportGmJob implements IJob {
//    /**
//     * Logger for this class
//     */
//    private static final Logger logger = LoggerFactory.getLogger(WxPmFollowReportGmJob.class);
//    
//    @Resource
//    private IGuidMemberService guidMemberService;
//    @Resource
//    private IShopService shopService;
//    @Resource
//    private IBranchCompanyService branchCompanyService;
//    @Resource
//    private IPersonMemberService personMemberService;
//    @Resource
//    private IImChatInfoService imChatInfoService;
//    @Resource
//    private IWxPmFollowReportGmService wxPmFollowReportGmService;
//    @Resource
//    private IWxPmFollowReportShopService wxPmFollowReportShopService;
//    @Resource
//    private IWxPmFollowReportCompanyService wxPmFollowReportCompanyService;
//
//    @Override
//    public void runJob() {
//        logger.debug("WxPmFollowReportGmJob runJob() - start"); //$NON-NLS-1$
//        Date now = new Date();
//        Date yesterday = DateUtils.addDays(now, -1);
//        
//        //构建门店维度Map
//        logger.debug("WxPmFollowReportGmJob 构建门店维度Map - start");
//        FindShop findShop2 = new FindShop();
//        findShop2.setStatus(ShopStatus.OPENED.name());
//        List<FindShopPageReturn> findShops = shopService.findShops(findShop2);
//        Map<String, AddWxPmFollowReportShop> wxPmFollowShopMap = Maps.newHashMapWithExpectedSize(findShops.size());
//        FindBranchCompany findBranchCompany = new FindBranchCompany();
//        for (FindShopPageReturn findShopPageReturn : findShops) {
//            findBranchCompany.setCompanyNo(findShopPageReturn.getCompanyNo());
//            FindBranchCompanyReturn branchCompany = null;
//            if (StringUtils.isEmpty(findShopPageReturn.getCompanyNo())) {
//                logger.info("WxPmFollowReportGmJob构建门店维度Map，该门店未绑定经销商，shopNo：[{}]", findShopPageReturn.getShopNo());
//                branchCompany = new FindBranchCompanyReturn();
//            } else {
//                branchCompany = branchCompanyService.findBranchCompanyByCompanyNo(findBranchCompany);
//                if (branchCompany == null) {//经销商已不存在
//                    logger.info("WxPmFollowReportGmJob构建门店维度Map，经销商已不存在，shopNo：[{}]", findShopPageReturn.getShopNo());
//                    branchCompany = new FindBranchCompanyReturn();
//                }
//            }
//            
//            AddWxPmFollowReportShop addWxPmFollowReportShop = new AddWxPmFollowReportShop();
//            addWxPmFollowReportShop.setReportDate(yesterday);
//            addWxPmFollowReportShop.setMerchantNo(findShopPageReturn.getMemberNoMerchant());
//            addWxPmFollowReportShop.setMerchantName(findShopPageReturn.getMemberNameMerchant());
//            addWxPmFollowReportShop.setCompanyNo(branchCompany.getCompanyNo());
//            addWxPmFollowReportShop.setCompanyName(branchCompany.getCompanyName());
//            addWxPmFollowReportShop.setDealerCode(branchCompany.getDealerCode());
//            addWxPmFollowReportShop.setShopNo(findShopPageReturn.getShopNo());
//            addWxPmFollowReportShop.setShopName(findShopPageReturn.getShopName());
//            addWxPmFollowReportShop.setShopCode(findShopPageReturn.getShopNoMerchant());
//            
//            addWxPmFollowReportShop.setNumPmNew(0L);
//            addWxPmFollowReportShop.setNumPmNewNotFollow(0L);
//            addWxPmFollowReportShop.setNumPmNewFollow(0L);
//            addWxPmFollowReportShop.setNumPmOld(0L);
//            addWxPmFollowReportShop.setNumPmOldNotFollow(0L);
//            addWxPmFollowReportShop.setNumPmOldFollow(0L);
//            addWxPmFollowReportShop.setNumPmTotal(0L);
//            
//            wxPmFollowShopMap.put(findShopPageReturn.getShopNo(), addWxPmFollowReportShop);
//        }
//        logger.debug("WxPmFollowReportGmJob 构建门店维度Map - end");
//        
//        //构建经销商维度Map
//        logger.debug("WxPmFollowReportGmJob 构建经销商维度Map - start");
//        FindBranchCompanyPage findBranchCompanyPage = new FindBranchCompanyPage();
//        findBranchCompanyPage.setStatus("NORMAL");
//        List<FindBranchCompanyReturn> branchCompanyList = branchCompanyService.findBranchCompanySelective(findBranchCompanyPage);
//        Map<String, AddWxPmFollowReportCompany> wxPmFollowCompanyMap = Maps.newHashMapWithExpectedSize(branchCompanyList.size());
//        for (FindBranchCompanyReturn findBranchCompanyReturn : branchCompanyList) {
//            AddWxPmFollowReportCompany addWxPmFollowReportCompany = new AddWxPmFollowReportCompany();
//            
//            addWxPmFollowReportCompany.setReportDate(yesterday);
//            addWxPmFollowReportCompany.setMerchantNo(findBranchCompanyReturn.getMerchantNo());
//            addWxPmFollowReportCompany.setMerchantName(findBranchCompanyReturn.getMerchantName());
//            addWxPmFollowReportCompany.setCompanyNo(findBranchCompanyReturn.getCompanyNo());
//            addWxPmFollowReportCompany.setCompanyName(findBranchCompanyReturn.getCompanyName());
//            addWxPmFollowReportCompany.setDealerCode(findBranchCompanyReturn.getDealerCode());
//            
//            addWxPmFollowReportCompany.setNumPmNew(0L);
//            addWxPmFollowReportCompany.setNumPmNewNotFollow(0L);
//            addWxPmFollowReportCompany.setNumPmNewFollow(0L);
//            addWxPmFollowReportCompany.setNumPmOld(0L);
//            addWxPmFollowReportCompany.setNumPmOldNotFollow(0L);
//            addWxPmFollowReportCompany.setNumPmOldFollow(0L);
//            addWxPmFollowReportCompany.setNumPmTotal(0L);
//            
//            wxPmFollowCompanyMap.put(findBranchCompanyReturn.getCompanyNo(), addWxPmFollowReportCompany);
//        }
//        logger.debug("WxPmFollowReportGmJob 构建经销商维度Map - end");
//
//        //导购维度
//        FindGuidMemberDto findGuidMemberDto = new FindGuidMemberDto();
//        findGuidMemberDto.setStatus(GuidStatus.NORMAL.name());
//        List<GuidMemberReturnDto> guidMemberList = guidMemberService.findGuidMemberSelective(findGuidMemberDto);
//        logger.debug("WxPmFollowReportGmJob导购维度guidMemberList size:[{}]", guidMemberList.size());
//        if (CollectionUtils.isNotEmpty(guidMemberList)) {
//            FindShop findShop = new FindShop();
//            FindWxPmByGm findWxPmByGm = new FindWxPmByGm();
//            FindChatTimesByGmAndMemberNo findChatTimesByGmAndMemberNo = new FindChatTimesByGmAndMemberNo();
//            
//            for (GuidMemberReturnDto guidMemberReturnDto : guidMemberList) {
//                findShop.setShopNo(guidMemberReturnDto.getShopNo());
//                FindShopReturn findShopReturn = shopService.findShopByShopNo(findShop);
//                if (findShopReturn == null) {//门店已不存在
//                    logger.info("WxPmFollowReportGmJob门店已不存在，shopNo：[{}]", guidMemberReturnDto.getShopNo());
//                    continue;
//                }
//                AddWxPmFollowReportShop addWxPmFollowReportShop = wxPmFollowShopMap.get(guidMemberReturnDto.getShopNo());
//                
//                //查询经销商信息
//                findBranchCompany.setCompanyNo(findShopReturn.getCompanyNo());
//                FindBranchCompanyReturn branchCompany = null;
//                if (StringUtils.isEmpty(findShopReturn.getCompanyNo())) {
//                    logger.info("WxPmFollowReportGmJob导购维度，该门店未绑定经销商，shopNo：[{}]", findShopReturn.getShopNo());
//                    branchCompany = new FindBranchCompanyReturn();
//                } else {
//                    branchCompany = branchCompanyService.findBranchCompanyByCompanyNo(findBranchCompany);
//                    if (branchCompany == null) {//经销商已不存在
//                        logger.info("WxPmFollowReportGmJob导购维度，经销商已不存在，shopNo：[{}]", guidMemberReturnDto.getShopNo());
//                        branchCompany = new FindBranchCompanyReturn();
//                    }
//                }
//                
//                AddWxPmFollowReportCompany addWxPmFollowReportCompany = wxPmFollowCompanyMap.get(branchCompany.getCompanyNo());
//                
//                clearParam(findWxPmByGm);
//                findWxPmByGm.setMemberNoGm(guidMemberReturnDto.getMemberNo());
//                long pmNumTotal = personMemberService.findWxPmCountByGm(findWxPmByGm);//总客户数
//                
//                long numPmNew = 0L;
//                long numPmNewNotFollow = 0L;
//                long numPmNewFollow = 0L;
//                long numPmOld = 0L;
//                long numPmOldNotFollow = 0L;
//                long numPmOldFollow = 0L;
//                
//                if (pmNumTotal > 0) {
//                    clearParam(findWxPmByGm);
//                    findWxPmByGm.setMemberNoGm(guidMemberReturnDto.getMemberNo());
//                    
//                    findWxPmByGm.setAddBeginTime(DateUtils.getDateByFirstSeconds(yesterday));
//                    findWxPmByGm.setAddEndTime(DateUtils.getDateByLastSeconds(yesterday));
//                    numPmNew = personMemberService.findWxPmCountByGm(findWxPmByGm);//新增客户数
//                    if (numPmNew > 0) {//如果有新增客户，再统计跟踪和未跟踪客户数
//                        List<FindPersonMemberReturn> wxPmByGm = personMemberService.findWxPmByGm(findWxPmByGm);
//                        if (CollectionUtils.isNotEmpty(wxPmByGm)) {
//                            for (FindPersonMemberReturn findPersonMemberReturn : wxPmByGm) {
//                                findChatTimesByGmAndMemberNo.setMemberNo(findPersonMemberReturn.getMemberNo());
//                                findChatTimesByGmAndMemberNo.setMemberNoGm(findPersonMemberReturn.getMemberNoGm());
//                                findChatTimesByGmAndMemberNo.setChatBeginTime(DateUtils.getDateByFirstSeconds(yesterday));
//                                findChatTimesByGmAndMemberNo.setChatEndTime(DateUtils.getDateByLastSeconds(yesterday));
//                                int chatTimes = imChatInfoService.findChatTimesByGmAndMemberNo(findChatTimesByGmAndMemberNo);
//                                logger.debug("WxPmFollowReportGmJob新增客户MemberNo:[{}]，导购MemberNoGm：[{}]，聊天次数：[{}]", 
//                                        findPersonMemberReturn.getMemberNo(), findPersonMemberReturn.getMemberNoGm(), chatTimes);
//                                if (chatTimes < 3) {
//                                    numPmNewNotFollow++;
//                                } else {
//                                    numPmNewFollow++;
//                                }
//                            }
//                        }
//                    }
//                    
//                    clearParam(findWxPmByGm);
//                    findWxPmByGm.setMemberNoGm(guidMemberReturnDto.getMemberNo());
//                    findWxPmByGm.setAddEndTime(DateUtils.getDateByFirstSeconds(yesterday));
//                    numPmOld = personMemberService.findWxPmCountByGm(findWxPmByGm);//老客户数
//                    if (numPmOld > 0) {//如果有老客户，再统计跟踪和未跟踪客户数
//                        List<FindPersonMemberReturn> wxPmByGm = personMemberService.findWxPmByGm(findWxPmByGm);
//                        if (CollectionUtils.isNotEmpty(wxPmByGm)) {
//                            for (FindPersonMemberReturn findPersonMemberReturn : wxPmByGm) {
//                                findChatTimesByGmAndMemberNo.setMemberNo(findPersonMemberReturn.getMemberNo());
//                                findChatTimesByGmAndMemberNo.setMemberNoGm(findPersonMemberReturn.getMemberNoGm());
//                                findChatTimesByGmAndMemberNo.setChatBeginTime(DateUtils.getDateByFirstSeconds(yesterday));
//                                findChatTimesByGmAndMemberNo.setChatEndTime(DateUtils.getDateByLastSeconds(yesterday));
//                                int chatTimes = imChatInfoService.findChatTimesByGmAndMemberNo(findChatTimesByGmAndMemberNo);
//                                logger.debug("WxPmFollowReportGmJob老客户MemberNo:[{}]，导购MemberNoGm：[{}]，聊天次数：[{}]", 
//                                        findPersonMemberReturn.getMemberNo(), findPersonMemberReturn.getMemberNoGm(), chatTimes);
//                                if (chatTimes < 3) {
//                                    numPmOldNotFollow++;
//                                } else {
//                                    numPmOldFollow++;
//                                }
//                            }
//                        }
//                    }
//                }
//                
//                AddWxPmFollowReportGm addWxPmFollowReportGm = new AddWxPmFollowReportGm();
//                
//                addWxPmFollowReportGm.setReportDate(yesterday);
//                addWxPmFollowReportGm.setMerchantNo(guidMemberReturnDto.getMerchantNo());
//                addWxPmFollowReportGm.setMerchantName(guidMemberReturnDto.getMerchantName());
//                addWxPmFollowReportGm.setCompanyNo(branchCompany.getCompanyNo());
//                addWxPmFollowReportGm.setCompanyName(branchCompany.getCompanyName());
//                addWxPmFollowReportGm.setDealerCode(branchCompany.getDealerCode());
//                addWxPmFollowReportGm.setShopNo(findShopReturn.getShopNo());
//                addWxPmFollowReportGm.setShopName(findShopReturn.getShopName());
//                addWxPmFollowReportGm.setShopCode(findShopReturn.getShopNoMerchant());
//                
//                addWxPmFollowReportGm.setMemberNoGm(guidMemberReturnDto.getMemberNo());
//                addWxPmFollowReportGm.setMemberNameGm(guidMemberReturnDto.getMemberName());
//                addWxPmFollowReportGm.setHeadAddress(guidMemberReturnDto.getHeadAddress());
//                
//                addWxPmFollowReportGm.setNumPmNew(numPmNew);
//                addWxPmFollowReportGm.setNumPmNewNotFollow(numPmNewNotFollow);
//                addWxPmFollowReportGm.setNumPmNewFollow(numPmNewFollow);
//                addWxPmFollowReportGm.setNumPmOld(numPmOld);
//                addWxPmFollowReportGm.setNumPmOldNotFollow(numPmOldNotFollow);
//                addWxPmFollowReportGm.setNumPmOldFollow(numPmOldFollow);
//                addWxPmFollowReportGm.setNumPmTotal(pmNumTotal);
//                wxPmFollowReportGmService.addWxPmFollowReportGm(addWxPmFollowReportGm);
//                
//                //累加门店维度统计数据
//                if (addWxPmFollowReportShop != null) {
//                    addWxPmFollowReportShop.setNumPmNew(addWxPmFollowReportShop.getNumPmNew() + numPmNew);
//                    addWxPmFollowReportShop.setNumPmNewNotFollow(addWxPmFollowReportShop.getNumPmNewNotFollow() + numPmNewNotFollow);
//                    addWxPmFollowReportShop.setNumPmNewFollow(addWxPmFollowReportShop.getNumPmNewFollow() + numPmNewFollow);
//                    addWxPmFollowReportShop.setNumPmOld(addWxPmFollowReportShop.getNumPmOld() + numPmOld);
//                    addWxPmFollowReportShop.setNumPmOldNotFollow(addWxPmFollowReportShop.getNumPmOldNotFollow() + numPmOldNotFollow);
//                    addWxPmFollowReportShop.setNumPmOldFollow(addWxPmFollowReportShop.getNumPmOldFollow() + numPmOldFollow);
//                    addWxPmFollowReportShop.setNumPmTotal(addWxPmFollowReportShop.getNumPmTotal() + pmNumTotal);
//                }
//                //累加经销商维度统计数据
//                if (addWxPmFollowReportCompany != null) {
//                    addWxPmFollowReportCompany.setNumPmNew(addWxPmFollowReportCompany.getNumPmNew() + numPmNew);
//                    addWxPmFollowReportCompany.setNumPmNewNotFollow(addWxPmFollowReportCompany.getNumPmNewNotFollow() + numPmNewNotFollow);
//                    addWxPmFollowReportCompany.setNumPmNewFollow(addWxPmFollowReportCompany.getNumPmNewFollow() + numPmNewFollow);
//                    addWxPmFollowReportCompany.setNumPmOld(addWxPmFollowReportCompany.getNumPmOld() + numPmOld);
//                    addWxPmFollowReportCompany.setNumPmOldNotFollow(addWxPmFollowReportCompany.getNumPmOldNotFollow() + numPmOldNotFollow);
//                    addWxPmFollowReportCompany.setNumPmOldFollow(addWxPmFollowReportCompany.getNumPmOldFollow() + numPmOldFollow);
//                    addWxPmFollowReportCompany.setNumPmTotal(addWxPmFollowReportCompany.getNumPmTotal() + pmNumTotal);
//                }
//            }
//            
//            //门店维度
//            Set<Entry<String,AddWxPmFollowReportShop>> shopEntrySet = wxPmFollowShopMap.entrySet();
//            for (Entry<String, AddWxPmFollowReportShop> entry : shopEntrySet) {
//                AddWxPmFollowReportShop addWxPmFollowReportShop = entry.getValue();
//                wxPmFollowReportShopService.addWxPmFollowReportShop(addWxPmFollowReportShop);
//            }
//            //经销商维度
//            Set<Entry<String,AddWxPmFollowReportCompany>> companyEntrySet = wxPmFollowCompanyMap.entrySet();
//            for (Entry<String, AddWxPmFollowReportCompany> entry : companyEntrySet) {
//                AddWxPmFollowReportCompany addWxPmFollowReportCompany = entry.getValue();
//                wxPmFollowReportCompanyService.addWxPmFollowReportCompany(addWxPmFollowReportCompany);
//            }
//        }
//
//        logger.debug("WxPmFollowReportGmJob runJob() - end"); //$NON-NLS-1$
//    }
//    
//    private void clearParam(FindWxPmByGm findWxPmByGm) {
//        findWxPmByGm.setMemberNoGm(null);
//        findWxPmByGm.setAddBeginTime(null);
//        findWxPmByGm.setAddEndTime(null);
//    }
//
//}
