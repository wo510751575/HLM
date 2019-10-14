package com.lj.oms.member;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ape.common.config.Global;
import com.lj.oms.common.BaseController;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
//import com.lj.business.mec.common.Constant;
import com.lj.business.member.dto.company.AddCompanyBankAccount;
import com.lj.business.member.dto.company.DelCompanyBankAccount;
import com.lj.business.member.dto.company.FindBranchCompany;
import com.lj.business.member.dto.company.FindBranchCompanyReturn;
import com.lj.business.member.dto.company.FindCompanyBankAccount;
import com.lj.business.member.dto.company.FindCompanyBankAccountPage;
import com.lj.business.member.dto.company.FindCompanyBankAccountPageReturn;
import com.lj.business.member.dto.company.FindCompanyBankAccountReturn;
import com.lj.business.member.dto.company.UpdateCompanyBankAccount;
import com.lj.business.member.service.IBranchCompanyService;
import com.lj.business.member.service.ICompanyBankAccountService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;
import com.lj.oms.dao.sys.UserDao;
import com.lj.oms.entity.sys.Office;
import com.lj.oms.service.impl.AreaHessianServiceImpl;
import com.lj.oms.service.sys.AreaService;
import com.lj.oms.service.sys.OfficeService;
import com.lj.oms.utils.CcUtils;
import com.lj.oms.utils.UserUtils;
/**
 * 
 * 
 * 类说明：绑定分公司/经销商银行卡账号
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2018年4月26日
 */
@Controller
@RequestMapping(value = "${adminPath}/member/companyBankAccount")
public class CompanyBankAccountController extends BaseController{
	
    @Resource
    private ICompanyBankAccountService companyBankAccountService;
    @Resource
    private IBranchCompanyService branchCompanyService;
    
	@Autowired
	private  AreaHessianServiceImpl areaHessianService;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired	
	private AreaService areaservice;	
	
	@Resource
    private LocalCacheSystemParamsFromCC localCacheSystemParams;	
	
	@Autowired
	private OfficeService officeService;
	
    
    private static final String LIST = "modules/member/companyBankAccountList";
    
    private static final String FROM = "modules/member/companyBankAccountFrom";
    
    private final static String PAGE_VIEW_REDIRECT_MEMBER = "redirect:" + Global.getAdminPath() + "/member/companyBankAccount";
    
    private static final String PAGE = "page";
    
    private static final String DATA = "data";
    
    private static final String USERS = "users";
    
    private static final String BANK_CODES = "BankCodes";
    
//    private static final String MSG = "repMsg";
      
    @RequestMapping(value ={"list",""})
    public String list(Model model,Integer pageNo,Integer pageSize,FindCompanyBankAccountPage findCompanyBankAccountPage,String msg){
    	try {
			if(pageNo != null){
				findCompanyBankAccountPage.setStart((pageNo -1) * pageSize);
			}
			if(pageSize != null){
				findCompanyBankAccountPage.setLimit(pageSize);
			}
			findCompanyBankAccountPage.setMerchantNo(UserUtils.getUser().getCompany().getId());
			Page<FindCompanyBankAccountPageReturn> pages = companyBankAccountService.findCompanyBankAccountPage(findCompanyBankAccountPage);
			List<FindCompanyBankAccountPageReturn> list = Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<FindCompanyBankAccountPageReturn> page = new com.ape.common.persistence.Page<FindCompanyBankAccountPageReturn>(pageNo == null ? 1 : pageNo, pages.getLimit(),
					pages.getTotal(), list);
			page.initialize();
			model.addAttribute(PAGE, page);
			model.addAttribute("findCompanyBankAccountPage", findCompanyBankAccountPage);
			model.addAttribute("repMsg", msg);
    	} catch (Exception e) {
			logger.error("查询银行卡账号信息错误",e);
		}
    	return LIST;
    }
    
    @RequestMapping(value ="form")
    public String from(Model model,FindCompanyBankAccount findCompanyBankAccount){
    	try {
    		if(StringUtils.isNotEmpty(findCompanyBankAccount.getCode())){
    			FindCompanyBankAccountReturn findCompanyBankAccountReturn =	companyBankAccountService.findCompanyBankAccount(findCompanyBankAccount);
    			model.addAttribute(DATA, findCompanyBankAccountReturn);
    		}
			//管理员  
			Office office = new Office();
			office.setId(UserUtils.getMerchantNo());
			List<Office> offices = officeService.findThirdOfficeList(office);
			model.addAttribute(USERS, offices);
//			model.addAttribute(BANK_CODES, localCacheSystemParams.getSystemParamGroup(SystemAliasName.mec.name(), Constant.MEC_BANK_GROUP));//银行
//			model.addAttribute(BANK_CODES, BankCode.values());//银行
			
			model.addAttribute("companyNo", findCompanyBankAccount.getCompanyNo());
			if (StringUtils.isNotEmpty(findCompanyBankAccount.getCompanyNo())) {//添加和编辑是展示经销商信息
                FindBranchCompany findBranchCompany = new FindBranchCompany();
                findBranchCompany.setCompanyNo(findCompanyBankAccount.getCompanyNo());
                FindBranchCompanyReturn findBranchCompanyReturn = branchCompanyService.findBranchCompanyByCompanyNo(findBranchCompany);
                model.addAttribute("company", findBranchCompanyReturn);
            }
    	
		} catch (Exception e) {
			logger.error("查询公司银行账号信息错误!",e);
		}
    	return FROM;
    }
    
    @RequestMapping(value="save")
    public String save(AddCompanyBankAccount addCompanyBankAccount,Model model,RedirectAttributes redirectAttributes){
    	try {
    		addCompanyBankAccount.setMerchantNo(UserUtils.getUser().getCompany().getId());
    		
    		//查询上级省份
    		
    	/*	Area area = areaservice.get(addCompanyBankAccount.getCityCode());
	        if(area.getType() != "4"){
    		if(AreaType.PROVINCE.getValue().equals(area.getType())){
    			addCompanyBankAccount.setProvinceCode(area.getCode());
        		addCompanyBankAccount.setProvinceName(area.getName());  
    		}else{
    			Area province = areaservice.get(area.getParentId());
        		addCompanyBankAccount.setCityCode(area.getCode());
        		addCompanyBankAccount.setCityName(area.getName());
        		addCompanyBankAccount.setProvinceCode(province.getCode());
        		addCompanyBankAccount.setProvinceName(province.getName());
    		}
			}*/
    		companyBankAccountService.addCompanyBankAccount(addCompanyBankAccount);
    		addMessage(redirectAttributes, "添加银行卡信息成功！");
		} catch (TsfaServiceException e) {
			logger.error(e.getExceptionInfo(),e);
		}catch (Exception e) {
			logger.error("添加银行卡信息错误",e);
		}
    	String param = StringUtils.isNotEmpty(addCompanyBankAccount.getCompanyNo()) ? addCompanyBankAccount.getCompanyNo() : "";
    	return PAGE_VIEW_REDIRECT_MEMBER + "?companyNo=" + param;
    	
    }
    
    @RequestMapping(value ="edit")
    public String edit(UpdateCompanyBankAccount updateCompanyBankAccount,Model model,RedirectAttributes redirectAttributes){
    	try {
    		companyBankAccountService.updateCompanyBankAccount(updateCompanyBankAccount);
    		addMessage(redirectAttributes, "修改账号成功！");
		} catch (Exception e) {
			logger.error("修改账号信息错误",e);
			addMessage(redirectAttributes, "修改账号失败！");
		}
    	String param = StringUtils.isNotEmpty(updateCompanyBankAccount.getCompanyNo()) ? updateCompanyBankAccount.getCompanyNo() : "";
    	return PAGE_VIEW_REDIRECT_MEMBER + "?companyNo=" + param;
    }
    
    @RequestMapping(value = "delect")
    public String delect(Model model,DelCompanyBankAccount delCompanyBankAccount,RedirectAttributes redirectAttributes){
    	try {
    		companyBankAccountService.delCompanyBankAccount(delCompanyBankAccount);
    		addMessage(redirectAttributes, "删除账号成功！");
		} catch (Exception e) {
			logger.error("删除账号信息异常",e);
			addMessage(redirectAttributes, "删除认账号失败！");
		}
    	String param = StringUtils.isNotEmpty(delCompanyBankAccount.getCompanyNo()) ? delCompanyBankAccount.getCompanyNo() : "";
    	return PAGE_VIEW_REDIRECT_MEMBER + "?companyNo=" + param;
    }
    
    @RequestMapping(value="updateDefaultAccount")
    public String updateDefaultAccount(UpdateCompanyBankAccount updateCompanyBankAccount,Model model,RedirectAttributes redirectAttributes){
    	try {
    		companyBankAccountService.updateDefaultAccount(updateCompanyBankAccount.getCode());
    		addMessage(redirectAttributes, "设置默认账号成功！");
		} catch (Exception e) {
			logger.error("设置默认账号错误",e);
			addMessage(redirectAttributes, "设置默认账号失败！");
		}
    	String param = StringUtils.isNotEmpty(updateCompanyBankAccount.getCompanyNo()) ? updateCompanyBankAccount.getCompanyNo() : "";
    	return PAGE_VIEW_REDIRECT_MEMBER + "?companyNo=" + param;
    }
}
