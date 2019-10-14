package com.lj.oms.member;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ape.common.config.Global;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.business.member.dto.gmAssistantShop.AddGmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.DelGmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopPage;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopPageReturn;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopReturn;
import com.lj.business.member.dto.shopterminal.FindShopTerminalPage;
import com.lj.business.member.dto.shopterminal.FindShopTerminalPageReturn;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.emus.ShopTerminalStatus;
import com.lj.business.member.service.IGmAssistantShopService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.oms.common.BaseController;
import com.lj.oms.dao.sys.UserDao;
import com.lj.oms.entity.sys.Office;
import com.lj.oms.entity.sys.User;
import com.lj.oms.service.sys.UserService;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：导购助手
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年12月2日
 */
@Controller
@RequestMapping(value = "${adminPath}/member/gmAssistantShop")
public class GmAssistantShopController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(GmAssistantShopController.class);
	
	/**  导购助手列表页面 **/
	private final static String PAGE_VIEW_GM_ASSISTANT_SHOP_LIST = "modules/member/gmAssistantShopList";	
	/**  导购助手编辑页面 **/
	private final static String PAGE_VIEW_GM_ASSISTANT_SHOP_FORM = "modules/member/gmAssistantShopForm";	
	/**  重定向到导购助手列表页面 **/
	private final static String PAGE_VIEW_REDIRECT_GM_ASSISTANT_SHOP = "redirect:" +Global.getAdminPath() + "/member/gmAssistantShop";	
	/**  导购助手绑定页面 **/
	private final static String PAGE_VIEW_GM_ASSISTANT_SHOP_Bind = "modules/member/gmAssistantShopBind";	
	/**  普通终端选择页面 **/
	private final static String PAGE_VIEW_GM_SHOP_TERMINAL_SELECT = "modules/member/selectShopTerminal";
	@Resource
	private IGmAssistantShopService gmAssistantShopService;
	@Resource
	private IShopTerminalService shopTerminalService;
	@Resource
	private  UserDao  userDao;
	@Autowired
	private  UserService  userService;
	
	/**
	 * 
	 *
	 * 方法说明：查询导购助手
	 *
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param findGmAssistantShopPage
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月4日
	 *
	 */
	@RequiresPermissions("member:gmAssistantShop:view")
	@RequestMapping(value={"list",""})
	public String list(Model model,Integer pageNo,Integer pageSize,FindGmAssistantShopPage findGmAssistantShopPage,RedirectAttributes redirectAttributes){
		try {
			if(pageNo !=null ){
				findGmAssistantShopPage.setStart((pageNo - 1)* pageSize);
			}
			if(pageSize != null){
				findGmAssistantShopPage.setLimit(pageSize);
			}
			
			findGmAssistantShopPage.setMerchantNo(UserUtils.getMerchantNo());
			Page<FindGmAssistantShopPageReturn> pages=gmAssistantShopService.findGmAssistantShopPage(findGmAssistantShopPage);

			List<FindGmAssistantShopPageReturn> list=Lists.newArrayList();
			list.addAll(pages.getRows());
			
			com.ape.common.persistence.Page<FindGmAssistantShopPageReturn> page =
			new com.ape.common.persistence.Page<FindGmAssistantShopPageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			
			model.addAttribute("findGmAssistantShopPage", findGmAssistantShopPage);
			model.addAttribute("page", page);
		} catch (Exception e) {
			logger.error("导购助手获取信息失败！", e);
			addMessage(redirectAttributes, "系统异常,加载数据异常！");
		}
		return PAGE_VIEW_GM_ASSISTANT_SHOP_LIST;
	}
	
	/**
	 * 
	 *
	 * 方法说明：去到导购助手编辑页面
	 *
	 * @param model
	 * @param findGmAssistantShop
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月4日
	 *
	 */
	@RequiresPermissions("member:gmAssistantShop:edit")
	@RequestMapping(value="form")
	public String form(Model model,FindGmAssistantShop findGmAssistantShop,RedirectAttributes redirectAttributes){
		try {
			User user =new User();
			Office company = new Office(UserUtils.getMerchantNo());
			user.setCompany(company);
			//管理员
			List<User> users = userDao.findMerchantNos(user);
				
			model.addAttribute("users", users);
		} catch (Exception e) {
			logger.error("导购助手获取信息失败！", e);
			addMessage(redirectAttributes, "系统异常,加载数据异常！");
		}
		return PAGE_VIEW_GM_ASSISTANT_SHOP_FORM;
	}
	
	/**
	 * 
	 *
	 * 方法说明：导购助手编辑
	 *
	 * @param redirectAttributes
	 * @param updateGmAssistantShop
	 * @param name
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月4日
	 *
	 */
	/*@Deprecated
	@RequiresPermissions("member:gmAssistantShop:edit")
	@RequestMapping(value="edit")
	public String edit(RedirectAttributes redirectAttributes,UpdateGmAssistantShop updateGmAssistantShop,String name){
		try {
			if(StringUtils.isNotBlank(updateGmAssistantShop.getCode())){
				//String[] tidCode = updateGmAssistantShop.getTidCode().split("\\|");
//				updateGmAssistantShop.setTidCode(tidCode[0]);
//				updateGmAssistantShop.setRemark2(tidCode[1]);//微信号
//				updateGmAssistantShop.setRemark4(tidCode[2]); //微信昵称
//				updateGmAssistantShop.setRemark(tidCode[3]); //终端编码
				gmAssistantShopService.updateGmAssistantShop(updateGmAssistantShop);
				addMessage(redirectAttributes, "修改成功！");
			}
		} catch (Exception e) {
			logger.error("编辑助手信息失败！", e);
			addMessage(redirectAttributes, "修改失败！");
		}
		return PAGE_VIEW_REDIRECT_GM_ASSISTANT_SHOP;
	}*/
	
	/**
	 * 
	 *
	 * 方法说明：新增导购助手
	 *
	 * @param redirectAttributes
	 * @param addGmAssistantShop
	 * @param tidCodes
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月4日
	 *
	 */
   @RequiresPermissions("member:gmAssistantShop:edit")
   @RequestMapping(value="save")
   public String save(RedirectAttributes redirectAttributes,AddGmAssistantShop addGmAssistantShop, String tidCodes){
		try {
			if (StringUtils.isNotBlank(tidCodes)) {
				addGmAssistantShop.setMerchantNo(UserUtils.getMerchantNo());
				addGmAssistantShop.setMerchantName(UserUtils.getMerchantName());
				
				String[] tidArr = tidCodes.split(",");
				/**
				 * 移除原有导购助手的终端
				 */
				gmAssistantShopService.deleteByAssistantNo(addGmAssistantShop.getAssistantNo(), UserUtils.getMerchantNo());
				//查询现有导购助手列表
				User user = userService.get(addGmAssistantShop.getAssistantNo());
				
				//获取下级的到手助手列表
				String noWxs = "";
				String loginNames = userService.findLoginNameByParentOfficeId("%"+user.getOffice().getId()+"%");
				if(StringUtils.isNotBlank(loginNames)) {
					String[] lNames = loginNames.split(",");
					FindGmAssistantShop findGmAssistantShop = new FindGmAssistantShop();
					findGmAssistantShop.setLoginNames(lNames);
					List<FindGmAssistantShopReturn> assistantShopReturns= gmAssistantShopService.findGmAssistantShopList(findGmAssistantShop);
					for (FindGmAssistantShopReturn findGmAssistantShopReturn : assistantShopReturns) {
						noWxs+=findGmAssistantShopReturn.getNoWx()+",";
					}
				}
				
				for (String str : tidArr) {
//					String[] objArr = str.split("\\|");
//					String tidCode = objArr[0];		//tidCode
//					String wx = objArr[1];			//微信号
//					String nickName = objArr[2];	//微信昵称
//					String terminalCode = objArr[3];//终端编码
					FindShopTerminalReturn findShopTerminalReturn= shopTerminalService.findShopTerminalByWx(str);
					if(findShopTerminalReturn ==null) {
						logger.error("终端不存在");
						continue;
					}
					if(noWxs.contains(findShopTerminalReturn.getNoWx())) {
						addGmAssistantShop.setSource("下属微信");
					}else {
						addGmAssistantShop.setSource(null);
					}
					addGmAssistantShop.setTidCode(findShopTerminalReturn.getCode());
					addGmAssistantShop.setNoWx(findShopTerminalReturn.getNoWx()); //微信号
					addGmAssistantShop.setWxNickname(findShopTerminalReturn.getNickname()); //微信昵称
					addGmAssistantShop.setTerminalCode(findShopTerminalReturn.getTerminalCode()); //终端编码
					gmAssistantShopService.addGmAssistantShop(addGmAssistantShop);
				}
				addMessage(redirectAttributes, "保存成功！");
			}
		} catch (Exception e) {
			logger.error("新增导购助手信息失败！", e);
			addMessage(redirectAttributes, "新增失败,系统异常！");
		}
	   return PAGE_VIEW_REDIRECT_GM_ASSISTANT_SHOP;
   }
   
   /**
    * 
    *
    * 方法说明：删除导购助手
    *
    * @param redirectAttributes
    * @param delGmAssistantShop
    * @return
    *
    * @author 曾垂瑜 CreateDate: 2018年4月4日
    *
    */
   @RequestMapping(value="delect")
   public String delect(RedirectAttributes redirectAttributes,DelGmAssistantShop delGmAssistantShop ){
		try {
			gmAssistantShopService.delGmAssistantShop(delGmAssistantShop);
			addMessage(redirectAttributes, "删除完成！");
		} catch (Exception e) {
			logger.error("删除导购助手信息失败！", e);
			addMessage(redirectAttributes, "删除失败！");
		}
	   return PAGE_VIEW_REDIRECT_GM_ASSISTANT_SHOP;
   }
   
   
   /**
	 * 跳转绑定终端页面
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param findProductPage
	 * @return
	 */
	@RequestMapping(value = {"toBindShopTerminal"})
	public String toBindShopTerminal(Model model,Integer pageNo,Integer pageSize, FindShopTerminalPage  findShopTerminalPage){
		try {
			if(pageNo!=null){
				findShopTerminalPage.setStart((pageNo-1)*pageSize);
			}
			findShopTerminalPage.setLimit(Integer.MAX_VALUE);
			
			findShopTerminalPage.setMerchantNo(UserUtils.getMerchantNo());
			findShopTerminalPage.setStatus(ShopTerminalStatus.NORMAL.getValue());
			//获取所有终端
			Page<FindShopTerminalPageReturn>  pageReturn = shopTerminalService.findShopTerminalPage(findShopTerminalPage);
			List<FindShopTerminalPageReturn>  list = new ArrayList<FindShopTerminalPageReturn>();
			list.addAll(pageReturn.getRows());
			
			//获取已绑定的终端
			String tidCodes=gmAssistantShopService.findTidCodesByAssistantNo(findShopTerminalPage.getAssistantNo(), UserUtils.getMerchantNo());

			com.ape.common.persistence.Page<FindShopTerminalPageReturn> page =
					new com.ape.common.persistence.Page<FindShopTerminalPageReturn>(pageNo==null?1:pageNo, findShopTerminalPage.getLimit(), list.size(), new ArrayList<>(list));
			page.initialize();
					
			model.addAttribute("page", page);
			model.addAttribute("findShopTerminalPage", findShopTerminalPage);
			model.addAttribute("tidCodes", tidCodes);
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return PAGE_VIEW_GM_ASSISTANT_SHOP_Bind;
	}
	
	/**
	 * 跳转绑定门店页面
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param findProductPage
	 * @return
	 */
	@RequestMapping(value = {"toSelectShopTerminal"})
	public String toSelectShopTerminal(Model model,Integer pageNo,Integer pageSize, FindShopTerminalPage  findShopTerminalPage, String loginName, String pageType){
		try {
			if(pageNo!=null){
				findShopTerminalPage.setStart((pageNo-1)*pageSize);
			}
			findShopTerminalPage.setLimit(Integer.MAX_VALUE);
			
			findShopTerminalPage.setMerchantNo(UserUtils.getMerchantNo());
			findShopTerminalPage.setStatus(1);
			//获取所有终端
			Page<FindShopTerminalPageReturn>  pageReturn = shopTerminalService.findShopTerminalPage(findShopTerminalPage);
			List<FindShopTerminalPageReturn>  list = new ArrayList<FindShopTerminalPageReturn>();
			list.addAll(pageReturn.getRows());
		
			com.ape.common.persistence.Page<FindShopTerminalPageReturn> page =
					new com.ape.common.persistence.Page<FindShopTerminalPageReturn>(pageNo==null?1:pageNo, findShopTerminalPage.getLimit(), list.size(), new ArrayList<>(list));
			page.initialize();
					
			model.addAttribute("page", page);
			model.addAttribute("findShopTerminalPage", findShopTerminalPage);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	
		return PAGE_VIEW_GM_SHOP_TERMINAL_SELECT;
	}
}
