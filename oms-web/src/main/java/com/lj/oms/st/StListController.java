package com.lj.oms.st;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ape.common.config.Global;
import com.lj.oms.common.BaseController;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.business.st.dto.CfAnalyzeChooseDto;
import com.lj.business.st.dto.FindBgcIndex;
import com.lj.business.st.dto.FindBgcIndexReturn;
import com.lj.business.st.dto.FindOperationAnalysisDayChoose;
import com.lj.business.st.dto.FindOperationDayChoose;
import com.lj.business.st.dto.FindStList;
import com.lj.business.st.dto.FindStListPage;
import com.lj.business.st.dto.FindStListPageReturn;
import com.lj.business.st.dto.FindStListReturn;
import com.lj.business.st.dto.FindWorkDayGmIndex;
import com.lj.business.st.dto.FindWorkDayGmIndexListReturn;
import com.lj.business.st.dto.OperationAnalysisDayChooseDto;
import com.lj.business.st.dto.OperationDayChooseDto;
import com.lj.business.st.dto.UpdateStList;
import com.lj.business.st.dto.WorkBrDayChooseDto;
import com.lj.business.st.dto.CfAnalyze.FindCfAnalyze;
import com.lj.business.st.dto.bestGmChoose.AddBestGmChoose;
import com.lj.business.st.emus.TableList;
import com.lj.business.st.emus.TypeList;
import com.lj.business.st.emus.UnitType;
import com.lj.business.st.service.IBestGmChooseService;
import com.lj.business.st.service.ICfAnalyzeChooseService;
import com.lj.business.st.service.IOperationAnalysisDayChooseService;
import com.lj.business.st.service.IOperationDayChooseService;
import com.lj.business.st.service.IPmTypeTotalService;
import com.lj.business.st.service.IStListService;
import com.lj.business.st.service.IWorkBrDayChooseService;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：报表项目
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年8月1日
 */
@Controller
@RequestMapping(value = "${adminPath}/baseConfig/stList")
public class StListController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(StListController.class);
	@Resource
	private IStListService stListService;//报表项目
    @Resource
    private  ICfAnalyzeChooseService cfAnalyzeChooseService;//跟进分析 
    @Resource
    private IOperationDayChooseService operationDayChooseService;//运营日报
    @Resource
    private IBestGmChooseService bestGmChooseService;//优秀导购
    @Resource
    private IOperationAnalysisDayChooseService operationAnalysisDayChooseService;//运营分析
    @Resource
    private IWorkBrDayChooseService workBrDayChooseService;//日工作简报
    @Resource
    private IPmTypeTotalService pmTypeTotalService;
    
    /**
     * 
     *
     * 方法说明：报表项目统计
     *
     * @param model
     * @param pageNo
     * @param pageSize
     * @param findStListPage
     * @return
     *
     * @author 罗书明 CreateDate: 2017年8月2日
     *
     */
    @RequestMapping(value = {"list", ""})
    public String list(Model model,Integer pageNo,Integer pageSize,FindStListPage findStListPage){
    	try {
    	 	if(pageNo!=null){
        		findStListPage.setStart((pageNo-1)*pageSize);
        	}
        	if(pageSize!=null){
        		findStListPage.setLimit(pageSize);
        	}
        	Page<FindStListPageReturn> pages=stListService.findStListPage(findStListPage);
        	List<FindStListPageReturn> list=Lists.newArrayList();
        	list.addAll(pages.getRows());
        	com.ape.common.persistence.Page<FindStListPageReturn> page
			=new com.ape.common.persistence.Page<FindStListPageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);
        	page.initialize();
        	model.addAttribute("page", page);
			model.addAttribute("findStListPage",findStListPage);
			model.addAttribute("unitLists",UnitType.values());
			model.addAttribute("TypeLists",TypeList.values());
		} catch (Exception e) {
			logger.error("获取项目报表信息错误！");
		}
      return "modules/baseConfig/stList";
    }
   /**
    *  
    *
    * 方法说明：跟进分析选择
    *
    * @param model
    * @return
    *
    * @author 罗书明 CreateDate: 2017年8月1日
    *
    */
   @RequestMapping(value = {"cfAnalyzeChoose"})
   public String cfAnalyzeChoose(Model model){
    	try {
    		//分页   		
        	FindStListPage findStListPage=new FindStListPage();
        	findStListPage.setStatus("Y");
        	findStListPage.setLimit(50);
        	findStListPage.setTableList(TableList.CF_ANALYZE_CHOOSE.toString());
        	Page<FindStListPageReturn> pages=stListService.findStListPage(findStListPage);
        	List<FindStListPageReturn> list=Lists.newArrayList();
        	list.addAll(pages.getRows());
        	//已选择跟进
        	 FindCfAnalyze findCfAnalyze=new FindCfAnalyze();
        	 findCfAnalyze.setMerchantNo(UserUtils.getUser().getCompany().getId());
        	 List<CfAnalyzeChooseDto> cfAnalyzeChooseDto=cfAnalyzeChooseService.findCfAnalyzeChoose(findCfAnalyze);
        	 model.addAttribute("cfAnalyzeChooseDto", cfAnalyzeChooseDto);
        	 List<String> str=Lists.newArrayList();
        	 for(CfAnalyzeChooseDto dto:cfAnalyzeChooseDto){
        		 str.add(dto.getCodeList());
        	 }
        	 //去除已选择
        	 List<FindStListPageReturn> lists=Lists.newArrayList();
        	 for(FindStListPageReturn findStListPageReturn:list){
        		 if( str.contains(findStListPageReturn.getCode())){
    				 lists.add(findStListPageReturn);
    			 }   		 
        	 }
        	 list.removeAll(lists);
        	 model.addAttribute("stList", list);
		} catch (Exception e) {
			logger.error("获取跟进分析选择项目报表信息错误！");
		}
    	 return "modules/baseConfig/cfAnalyzeChoose";	   
   }
    /**
     * 
     *
     * 方法说明：跟进分析保存
     *
     * @param model
     * @param redirectAttributes
     * @param cfAnalyzeChooseDto
     * @param cfAnalyzeChooses
     * @return
     *
     * @author 罗书明 CreateDate: 2017年8月1日
     *
     */
   @RequestMapping(value="cfAnalyzeChooseSave")
    public String cfAnalyzeChooseSave(Model model,RedirectAttributes redirectAttributes,CfAnalyzeChooseDto cfAnalyzeChooseDto ,String[] stLists){
	    try {
	    	 cfAnalyzeChooseService.deleteByPrimaryKey(UserUtils.getUser().getCompany().getId());
	  	   int i=0;
	  	   if(stLists!=null){
	  		   for(String string:stLists){
	  			   String[] info=string.split(",");
	  			   FindStList findStList=new FindStList();
	  			   findStList.setCode(info[0]);
	  			   FindStListReturn findStListReturn=stListService.findStList(findStList);
	  			   cfAnalyzeChooseDto.setMerchantNo(UserUtils.getUser().getCompany().getId());
	  			   cfAnalyzeChooseDto.setNameList(findStListReturn.getNameList());
	  			   cfAnalyzeChooseDto.setTypeList(findStListReturn.getTypeList());
	  			   cfAnalyzeChooseDto.setImgAddr(findStListReturn.getImgAddr());
	  			   cfAnalyzeChooseDto.setCodeList(info[0]);
	  			   cfAnalyzeChooseDto.setSeq(++i);
	  			   cfAnalyzeChooseService.insertSelective(cfAnalyzeChooseDto);
	  			   addMessage(redirectAttributes, "保存成功");
	  		   } 	 
		   }
		} catch (Exception e) {
			logger.error("保存跟进分析选择项目报表信息错误！");
		}
	    return "redirect:" +Global.getAdminPath() + "/baseConfig/stList/cfAnalyzeChoose";		
		
    }
   
  
   /**
    * 
    *
    * 方法说明：运营日报选择
    *
    * @param model
    * @return
    *
    * @author 罗书明 CreateDate: 2017年8月1日
    *
    */
   @RequestMapping(value="operationDayChoose")
   public String operationDayChoose(Model model){
	   try {
		//分页   		
       	FindStListPage findStListPage=new FindStListPage();
       	findStListPage.setStatus("Y");
       	findStListPage.setLimit(50);
       	findStListPage.setTableList(TableList.OPERATION_DAY_CHOOSE.toString());
       	Page<FindStListPageReturn> pages=stListService.findStListPage(findStListPage);
       	List<FindStListPageReturn> list=Lists.newArrayList();
       	list.addAll(pages.getRows());
        //已选择运营日报
       	// 
       	FindOperationDayChoose findOperationDayChoose=new FindOperationDayChoose();
       	findOperationDayChoose.setMerchantNo(UserUtils.getUser().getCompany().getId());
       	List<OperationDayChooseDto> operationDayChooses=operationDayChooseService.findOperationDayChoose(findOperationDayChoose);
        model.addAttribute("operationDayChooses", operationDayChooses);
        List<String> str=Lists.newArrayList();
   	    for(OperationDayChooseDto dto:operationDayChooses){
		 str.add(dto.getCodeList());
	    }
	    List<FindStListPageReturn> lists=Lists.newArrayList();
	    for(FindStListPageReturn findStListPageReturn:list){
		 if( str.contains(findStListPageReturn.getCode())){
			 lists.add(findStListPageReturn);
		 }   		 
	 }
	    list.removeAll(lists);
	    model.addAttribute("stList", list);
	} catch (Exception e) {
		logger.error("运营日报选择项目报表信息错误！");
	}
	   return "modules/baseConfig/operationDayChoose";	
   }
   
    /**
     * 
     *
     * 方法说明：运营保存新增
     *
     * @param model
     * @param redirectAttributes
     * @param operationDayChooseDto
     * @param stLists
     * @return
     *
     * @author 罗书明 CreateDate: 2017年8月1日
     *
     */
   @RequestMapping(value="operationDayChooseSave")
    public String operationDayChooseSave(Model model,RedirectAttributes redirectAttributes,OperationDayChooseDto operationDayChooseDto ,String[] stLists){
    	try {   		
    		operationDayChooseService.deleteByPrimaryKey(UserUtils.getUser().getCompany().getId());
    		int i=0;
    		if(stLists!=null){
    		for(String string:stLists){
    			 String[] info=string.split(",");
	  			 FindStList findStList=new FindStList();
	  			 findStList.setCode(info[0]);
	  			 FindStListReturn findStListReturn=stListService.findStList(findStList);
	  			operationDayChooseDto.setCodeList(info[0]);
	  			operationDayChooseDto.setMerchantNo(UserUtils.getUser().getCompany().getId());
	  			operationDayChooseDto.setNameList(findStListReturn.getNameList());
	  			operationDayChooseDto.setTypeList(findStListReturn.getTypeList());
	  			operationDayChooseDto.setImgAddr(findStListReturn.getImgAddr());
	  			operationDayChooseDto.setSeq(++i);
	  			operationDayChooseService.insertSelectAll(operationDayChooseDto);
	  			addMessage(redirectAttributes, "保存成功");
    		}
    		}
		} catch (Exception e) {
			logger.error("保存运营日报选择项目报表信息错误！");
		}
    	 return "redirect:" +Global.getAdminPath() + "/baseConfig/stList/operationDayChoose";	
    }
   
   /**
    * 
    *
    * 方法说明：优秀导购选择
    *
    * @param model
    * @return
    *
    * @author 罗书明 CreateDate: 2017年8月2日
    *
    */
    @RequestMapping(value="bestGmChoose")
    public String bestGmChoose(Model model){
    	try {
    		//分页   		
           	FindStListPage findStListPage=new FindStListPage();
           	findStListPage.setStatus("Y");
           	findStListPage.setLimit(50);
           	findStListPage.setTableList(TableList.BEST_GM_CHOOSE.toString());
           	Page<FindStListPageReturn> pages=stListService.findStListPage(findStListPage);
           	List<FindStListPageReturn> list=Lists.newArrayList();
           	list.addAll(pages.getRows());
           	//已选择项目
           	FindBgcIndex findBgcIndex=new FindBgcIndex();
           	findBgcIndex.setMerchantNo(UserUtils.getUser().getCompany().getId());
           	List<FindBgcIndexReturn> findBgcIndexReturns=bestGmChooseService.findBgcIndex(findBgcIndex);
            model.addAttribute("findBgcIndexReturns", findBgcIndexReturns);
           	List<String> str=Lists.newArrayList();
           	for(FindBgcIndexReturn dto:findBgcIndexReturns){
           		str.add(dto.getCodeList());
           	}
           	List<FindStListPageReturn> lists=Lists.newArrayList();
           	for(FindStListPageReturn findStListPageReturn:list){
           		if(str.contains(findStListPageReturn.getCode())){
           		  lists.add(findStListPageReturn);
           		}
           	}
           	list.removeAll(lists);
            model.addAttribute("stList", list);
		} catch (Exception e) {
			logger.error("获取优秀导购选择项目报表信息错误！");
		}
    	 return "modules/baseConfig/bestGmChoose";
    }
    
    /**
     * 
     *
     * 方法说明：优秀员工新增数据
     *
     * @param model
     * @param redirectAttributes
     * @param operationDayChooseDto
     * @param stLists
     * @return
     *
     * @author 罗书明 CreateDate: 2017年8月2日
     *
     */
    @RequestMapping(value="bestGmChooseSave")
    public String bestGmChooseSave(Model model,RedirectAttributes redirectAttributes,AddBestGmChoose addBestGmChoose ,String[] stLists){
    	try {
    		bestGmChooseService.deleteByPrimaryKey(UserUtils.getUser().getCompany().getId());
    		int i=0;
    		 if(stLists!=null){
    			for(String string:stLists){
    				 String[] info=string.split(",");
    	  			 FindStList findStList=new FindStList();
    	  			 findStList.setCode(info[0]);
    	  			 FindStListReturn findStListReturn=stListService.findStList(findStList);
    	  			 addBestGmChoose.setCodeList(info[0]);
    	  			 addBestGmChoose.setMerchantNo(UserUtils.getUser().getCompany().getId());
    	  			 addBestGmChoose.setTypeList(findStListReturn.getTypeList());
    	  			 addBestGmChoose.setImgAddr(findStListReturn.getImgAddr());
    	  			 addBestGmChoose.setSeq(++i);
    	  			 addBestGmChoose.setNameList(findStListReturn.getNameList());
    	  		     bestGmChooseService.addBestGmChoose(addBestGmChoose);
    	  			 addMessage(redirectAttributes, "保存成功");
    			}
    		 }
		} catch (Exception e) {
			e.printStackTrace();
			
		}
    	return "redirect:" +Global.getAdminPath() + "/baseConfig/stList/bestGmChoose";	
    }
    /**
     * 
     *
     * 方法说明：运营分析报表选择
     *
     * @param model
     * @return
     *
     * @author 罗书明 CreateDate: 2017年8月2日
     *
     */
    @RequestMapping(value="operationAnalysisDayChoose")
    public String operationAnalysisDayChoose(Model model){
    	try {
    		//分页   		
           	FindStListPage findStListPage=new FindStListPage();
           	findStListPage.setStatus("Y");
           	findStListPage.setLimit(50);
           	findStListPage.setTableList(TableList.OPERATION_ANALYSIS_DAY_CHOOSE.toString());
           	Page<FindStListPageReturn> pages=stListService.findStListPage(findStListPage);
           	List<FindStListPageReturn> list=Lists.newArrayList();
           	list.addAll(pages.getRows());
           	//已选择运营分析 
           	FindOperationAnalysisDayChoose findOperationAnalysisDayChoose=new FindOperationAnalysisDayChoose();
           	findOperationAnalysisDayChoose.setMerchantNo(UserUtils.getUser().getCompany().getId());
           	List<OperationAnalysisDayChooseDto> operationAnalysisDayChooseDtos=operationAnalysisDayChooseService.findOperationAnalysisDayChoose(findOperationAnalysisDayChoose);
            model.addAttribute("operationAnalysisDayChooseDtos", operationAnalysisDayChooseDtos);
            List<String> str=Lists.newArrayList();
            for(OperationAnalysisDayChooseDto dto:operationAnalysisDayChooseDtos){
            	str.add(dto.getCodeList());
            }
            List<FindStListPageReturn> 	lists=Lists.newArrayList();
            for(FindStListPageReturn findStListPageReturn:list){
           		if(str.contains(findStListPageReturn.getCode())){
           			lists.add(findStListPageReturn);
           		}
           	}
            list.removeAll(lists);
            model.addAttribute("stList", list);
		} catch (Exception e) {
			logger.error("保存运营分析项目报表信息错误！");
		}
    	 return "modules/baseConfig/operationAnalysisDayChoose";
    }
    /**
     * 
     *
     * 方法说明：运营分析保存方法
     *
     * @param model
     * @param redirectAttributes
     * @param operationAnalysisDayChooseDto
     * @param stLists
     * @return
     *
     * @author 罗书明 CreateDate: 2017年8月2日
     *
     */
    @RequestMapping(value="operationAnalysisDayChooseSave")
    public String operationAnalysisDayChooseSave
    (Model model,RedirectAttributes redirectAttributes,OperationAnalysisDayChooseDto operationAnalysisDayChooseDto ,String[] stLists){
    	try {
    		operationAnalysisDayChooseService.deleteByMerchantNo(UserUtils.getUser().getCompany().getId());
    		int i=0;
    		if(stLists!=null){
    			for(String string:stLists){
    				 String[] info=string.split(",");
    	  			 FindStList findStList=new FindStList();
    	  			 findStList.setCode(info[0]);
    	  			 FindStListReturn findStListReturn=stListService.findStList(findStList);
    	  			operationAnalysisDayChooseDto.setCodeList(info[0]);
    	  			operationAnalysisDayChooseDto.setMerchantNo(UserUtils.getUser().getCompany().getId());
    	  			operationAnalysisDayChooseDto.setTypeList(findStListReturn.getTypeList());
    	  			operationAnalysisDayChooseDto.setNameList(findStListReturn.getNameList());
    	  			operationAnalysisDayChooseDto.setImgAddr(findStListReturn.getImgAddr());
    	  			operationAnalysisDayChooseDto.setSeq(++i);
    	  			operationAnalysisDayChooseService.insertSelective(operationAnalysisDayChooseDto);
    	  			addMessage(redirectAttributes, "保存成功");
    			}
    		}
		} catch (Exception e) {
			logger.error("获取运营分析项目报表信息错误！");
		}
    	return "redirect:" +Global.getAdminPath() + "/baseConfig/stList/operationAnalysisDayChoose";	 	
    }

    /**
     * 
     *
     * 方法说明：工作日报表选择
     *
     * @param model
     * @return
     *
     * @author 罗书明 CreateDate: 2017年8月2日
     *
     */
     @RequestMapping(value="workBrDayChoose")
     public String workBrDayChoose(Model model){
    	 try {
    			//分页   		
            	FindStListPage findStListPage=new FindStListPage();
            	findStListPage.setStatus("Y");
            	findStListPage.setLimit(50);
            	findStListPage.setTableList(TableList.WORK_BR_DAY_LIST.toString());
            	Page<FindStListPageReturn> pages=stListService.findStListPage(findStListPage);
            	List<FindStListPageReturn> list=Lists.newArrayList();
            	list.addAll(pages.getRows());
            	//已选择日报表
            	FindWorkDayGmIndex findWorkDayGmIndex=new FindWorkDayGmIndex();
            	findWorkDayGmIndex.setMerchantNo(UserUtils.getUser().getCompany().getId());
            	List<FindWorkDayGmIndexListReturn> findWorkDayGmIndexListReturns =workBrDayChooseService.findWorkBrDayChooseList(findWorkDayGmIndex);
            	model.addAttribute("findWorkDayGmIndexListReturns", findWorkDayGmIndexListReturns);
                List<String> str=Lists.newArrayList();
                for(FindWorkDayGmIndexListReturn dto:findWorkDayGmIndexListReturns){
                	str.add(dto.getCodeList());
                }
                List<FindStListPageReturn> 	lists=Lists.newArrayList();
                for(FindStListPageReturn findStListPageReturn:list){
               		if(str.contains(findStListPageReturn.getCode())){
               			lists.add(findStListPageReturn);
               		}
               	}
                list.removeAll(lists);
                model.addAttribute("stList", list);
		} catch (Exception e) {
			logger.error("获取工作日报表选择报表信息错误！");
		}
    	 return "modules/baseConfig/workBrDayChoose";
     }
     /**
      * 
      *
      * 方法说明：日工作选择 保存
      *
      * @param model
      * @param redirectAttributes
      * @param workBrDayChooseDto
      * @param stLists
      * @return
      *
      * @author 罗书明 CreateDate: 2017年8月2日
      *
      */
     @RequestMapping(value="workBrDayChooseSave")
     public String workBrDayChooseSave(Model model,RedirectAttributes redirectAttributes,WorkBrDayChooseDto workBrDayChooseDto,String[] stLists){
    	 try {
    		 workBrDayChooseService.deleteByPrimaryKey(UserUtils.getUser().getCompany().getId());
    		 int i=0;
    		 if(stLists!=null){
    			 for(String string:stLists){
    				 String[] info=string.split(",");
    	  			 FindStList findStList=new FindStList();
    	  			 findStList.setCode(info[0]);
    	  			 FindStListReturn findStListReturn=stListService.findStList(findStList);
    	  			 workBrDayChooseDto.setCodeList(info[0]);
    	  			 workBrDayChooseDto.setSeq(++i);
    	  			 workBrDayChooseDto.setNameList(findStListReturn.getNameList());
    	  			 workBrDayChooseDto.setMerchantNo(UserUtils.getUser().getCompany().getId());
    	  			 workBrDayChooseDto.setTypeList(findStListReturn.getTypeList());
    	  			 workBrDayChooseDto.setImgAddr(findStListReturn.getImgAddr());
    	  			workBrDayChooseService.insertSelective(workBrDayChooseDto);
    	  			addMessage(redirectAttributes, "保存成功");
    			 }
    		 }
		} catch (Exception e) {
			logger.error("保存工作日报表选择报表信息错误！");
		}
    	 return "redirect:" +Global.getAdminPath() + "/baseConfig/stList/workBrDayChoose";
     }
     
     @RequestMapping(value="initializePmTypeTota")
     public String initializePmTypeTota(Model model,RedirectAttributes redirectAttributes){
    	 try {
    		 boolean b = pmTypeTotalService.initializePmTypeTota();
    		 if(b)
    			addMessage(redirectAttributes, "初始化数据成功");
    		 else
    			addMessage(redirectAttributes, "初始化数据失败");
		} catch (Exception e) {
			addMessage(redirectAttributes, "初始化数据失败");
			logger.error("初始化数据失败",e);
		}
    	 return "redirect:" +Global.getAdminPath() + "/baseConfig/stList"; 
     }
}
