package com.lj.oms.tmall;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ape.common.config.Global;
import com.ape.common.utils.StringUtils;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.DateUtils;
import com.lj.business.common.CommonConstant;
import com.lj.business.member.dto.FindTmallOrderPage;
import com.lj.business.member.dto.TmallOrderDto;
import com.lj.business.member.service.ITmallOrderService;
import com.lj.oms.common.BaseController;
import com.lj.oms.utils.DictUtils;
import com.lj.oms.utils.UserUtils;
import com.lj.oms.utils.Validator;
import com.lj.oms.utils.excel.ExportExcel;
import com.lj.oms.utils.excel.ImportExcel;
import com.lj.oms.utils.excel.dto.TmallOrderImportDto;

/**
 * 
 * 
 * 
 * 类说明：天猫订单Controller
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 刘红艳 CreateDate: 2019年2月19日
 */
@Controller
@RequestMapping(value = "${adminPath}/tmall/order")
public class TmallOrderController extends BaseController {

	/** 订单列表页面 **/
	private final static String PAGE_VIEW_ORDER_LIST = "modules/tmall/orderList";
	/** 重定向到列表页面 **/
	private final static String PAGE_VIEW_REDIRECT_ORDER_REPAGE = "redirect:" + Global.getAdminPath()
			+ "/tmall/order/?repage";

	@Resource
	private ITmallOrderService tmallOrderService;

	/**
	 * 
	 *
	 * 方法说明：天猫订单列表
	 *
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param agreementMerchantPageDto
	 * @return 服务提供分页，转换OMS分页列表
	 *
	 * @author 刘红艳 CreateDate: 2019年2月19日
	 *
	 */
	@RequestMapping(value = { "list", "" })
	public String list(Model model, Integer pageNo, Integer pageSize, TmallOrderDto tmallOrderDto, String  returnMessage,
			RedirectAttributes redirectAttributes ) {
		try {
			FindTmallOrderPage param = new FindTmallOrderPage();
			param.setParam(tmallOrderDto);
			tmallOrderDto.setMerchantNo(UserUtils.getMerchantNo());
			if (pageNo != null) {
				param.setStart((pageNo - 1) * pageSize);
			}
			if (pageSize != null) {
				param.setLimit(pageSize);
			}
			Page<TmallOrderDto> pages = tmallOrderService.findTmallOrderPage(param);
			List<TmallOrderDto> list = Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<TmallOrderDto> page = new com.ape.common.persistence.Page<TmallOrderDto>(
					pageNo == null ? 1 : pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("tmallOrderDto", tmallOrderDto);
		} catch (Exception e) {
			logger.error("获取订单数据错误", e);
		}
		return PAGE_VIEW_ORDER_LIST;
	}

	/**
	 * 
	 *
	 * 方法说明：订单导入模板下载
	 *
	 * @param response
	 * @param request
	 * @param redirectAttributes
	 * @return excel
	 *
	 * @author lhy 2019年2月20日
	 *
	 */
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		try {
			String fileName = "订单导入模版.xlsx";
			List<TmallOrderImportDto> list = Lists.newArrayList();
			TmallOrderImportDto tmallOrderDto = new TmallOrderImportDto();
			// add数据录入
			tmallOrderDto.setMemberName("小飞侠");
			tmallOrderDto.setMobile("13800001001");
			tmallOrderDto.setNoWw("Ww00001");
			tmallOrderDto.setOrderNo("2019021256245");
			tmallOrderDto.setProductName("保温水杯");
			tmallOrderDto.setProductUrl("https://www.baidu.com/");
			tmallOrderDto.setAmountStr("99.9");
			tmallOrderDto.setOrderDate("2019-02-20 13:26:56");
			tmallOrderDto.setCommentLevel("1");
			tmallOrderDto.setCommentCtx("物美价廉");
			list.add(tmallOrderDto);
			new ExportExcel("订单导入模版", TmallOrderImportDto.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			logger.error("订单导入模板下载失败！", e);
			addMessage(redirectAttributes, "订单导入模板下载失败！失败信息：" + e.getMessage());
		}
		return PAGE_VIEW_REDIRECT_ORDER_REPAGE;
	}
	
	/**
	 * 
	 *
	 * 方法说明：订单导入
	 * @param file
	 * @param redirectAttributes
	 * @return
	 *
	 * @author lhy CreateDate: 2019年2月20日
	 *
	 */
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importExcel(MultipartFile file, RedirectAttributes redirectAttributes,Model model) {
		logger.debug("importExcel(MultipartFile file={}, RedirectAttributes redirectAttributes={}, Model model={}) - start", file, redirectAttributes, model); 

		try {
			int successNum = 0;		// 成功数量
			int failureNum = 0;		// 失败数量
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);

			List<TmallOrderImportDto> list = ei.getDataList(TmallOrderImportDto.class);
			for (TmallOrderImportDto ele : list) {
				if (StringUtils.isBlank(ele.getMemberName())) {
					continue;
				}
				try {
					// 处理excel转义的手机号码
					ele.setMobile(DictUtils.excelChage(ele.getMobile()));
					ele.setNoWw(DictUtils.excelChage(ele.getNoWw()));
					ele.setOrderNo(DictUtils.excelChage(ele.getOrderNo().trim()));
					//ele.setOrderDate(DictUtils.excelChage(ele.getOrderDate().trim()));
					/* 校验手机号格式 */
					if (!Validator.isMobile(ele.getMobile())) {
						failureMsg.append("<br/>下单人电话： " + ele.getMobile() + "格式不正确; ");
						failureNum++;
						continue;
					}
//					if (StringUtils.isEmpty(ele.getMemberName())) {
//						failureMsg.append("下单人姓名为空！");
//						failureNum++;
//						continue;
//					}
					if (StringUtils.isEmpty(ele.getMobile())) {
						failureMsg.append("下单人手机号为空！");
						failureNum++;
						continue;
					}
					if (StringUtils.isEmpty(ele.getNoWw())) {
						failureMsg.append("下单人旺旺为空！");
						failureNum++;
						continue;
					}
					if (StringUtils.isEmpty(ele.getOrderNo())) {
						failureMsg.append("订单号为空！");
						failureNum++;
						continue;
					}
					if (StringUtils.isEmpty(ele.getAmountStr())) {
						failureMsg.append("金额为空！");
						failureNum++;
						continue;
					}
					if (StringUtils.isEmpty(ele.getOrderDate())) {
						failureMsg.append("下单时间为空！");
						failureNum++;
						continue;
					}
					if (StringUtils.isEmpty(ele.getCommentLevel())) {
						failureMsg.append("评论星级为空！");
						failureNum++;
						continue;
					}
					
					if (CommonConstant.TRUE.equals(checkOrderNo(ele.getOrderNo()))) {
						TmallOrderDto tmallOrder=new TmallOrderDto();
						tmallOrder.setMerchantNo(UserUtils.getMerchantNo());
						tmallOrder.setMemberName(ele.getMemberName());
						tmallOrder.setMobile(ele.getMobile());
						tmallOrder.setNoWw(ele.getNoWw());
						tmallOrder.setOrderNo(ele.getOrderNo());
						tmallOrder.setProductName(ele.getProductName());
						tmallOrder.setProductUrl(ele.getProductUrl());
						
						if(StringUtils.isNotEmpty(ele.getAmountStr())) {
							String a = ele.getAmountStr(); 
							Double d= Double.parseDouble(a); 
							d = d * 100; 
							tmallOrder.setAmount(d.longValue());
						}
						
						try {
							Date d = DateUtils.parseDate(ele.getOrderDate(), DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss);
							logger.info("下单时间："+d.toString());
							tmallOrder.setOrderDate(ele.getOrderDate());
						} catch (ParseException pe) {
							logger.error("importExcel(MultipartFile, RedirectAttributes, Model)", pe); 

							failureMsg.append("<br/>： 下单时间格式错误");
							failureNum++;
							continue;
						}
						tmallOrder.setCommentLevel(ele.getCommentLevel());
						tmallOrder.setCommentCtx(ele.getCommentCtx());
						tmallOrder.setCreateDate(new Date());
						tmallOrder.setCreateId(UserUtils.getUser().getId());
						tmallOrderService.addTmallOrder(tmallOrder);
						successNum++;
					} else {
						failureMsg.append("<br/>订单号 " + ele.getOrderNo() + " 已存在; ");
						failureNum++;
					}
				} catch (Exception ex) {
					logger.error("importExcel(MultipartFile, RedirectAttributes, Model)", ex); 

					failureMsg.append("<br/>订单号 " + ele.getOrderNo() + " 导入失败：" + ex.getMessage());
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条订单，导入信息如下：");
			}
			model.addAttribute("importMsg", "已成功导入 " + successNum + " 条订单" + failureMsg);
			logger.debug("failureMsg:" + failureMsg);
		} catch (Exception e) {
			logger.error("导入订单失败！", e);
			model.addAttribute("importMsg", "导入订单失败！失败信息：" + e.getMessage());
		}
		String returnString = list(model, null, null, new TmallOrderDto(), "", redirectAttributes);
		logger.debug("importExcel(MultipartFile, RedirectAttributes, Model) - end - return value={}", returnString); 
		return returnString;
	}
	
	
	/**
	 * 检测订单是否已存在
	 * @param orderNo 订单号
	 * @return 不存在则返true,存在则false
	 */
	private String checkOrderNo(String orderNo) {
		FindTmallOrderPage findTmallOrderPage=new FindTmallOrderPage();
		TmallOrderDto param=new TmallOrderDto();
		param.setOrderNo(orderNo);
		param.setMerchantNo(UserUtils.getMerchantNo());
		
		findTmallOrderPage.setParam(param);
		List<TmallOrderDto> datas= tmallOrderService.findTmallOrders(findTmallOrderPage);
		if(datas!=null && !datas.isEmpty()) {
			return CommonConstant.FALSE; 
		}
		return CommonConstant.TRUE;
	}
	
	public static void main(String[] args) {
		String a = "25.5"; 
		Double d= Double.parseDouble(a); 
		d = d * 100;
		System.out.println(d.longValue());
	}
}
