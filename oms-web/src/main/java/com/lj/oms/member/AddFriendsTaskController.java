package com.lj.oms.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ape.common.config.Global;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.pagination.PageSortType;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.common.CommonConstant;
import com.lj.business.member.dto.AddFriendsTaskCountDto;
import com.lj.business.member.dto.AddFriendsTaskDetailDto;
import com.lj.business.member.dto.AddFriendsTaskDto;
import com.lj.business.member.dto.FindAddFriendsTaskDetailPage;
import com.lj.business.member.dto.FindAddFriendsTaskPage;
import com.lj.business.member.dto.shopterminal.FindShopTerminal;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.emus.AddfriendsTaskStatus;
import com.lj.business.member.emus.ShopTerminalStatus;
import com.lj.business.member.service.IAddFriendsTaskDetailService;
import com.lj.business.member.service.IAddFriendsTaskService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.distributecache.RedisCache;
import com.lj.oms.common.BaseController;
import com.lj.oms.utils.DictUtils;
import com.lj.oms.utils.UserUtils;
import com.lj.oms.utils.Validator;
import com.lj.oms.utils.excel.ExportExcel;
import com.lj.oms.utils.excel.ImportExcel;
import com.lj.oms.utils.excel.dto.ImportAddWxFriendsDto;

/**
 * 
 * 
 * 类说明：跟进记录Controller
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 * 
 *         CreateDate: 2017年7月7日
 */
@Controller
@RequestMapping(value = "${adminPath}/member/addFriendsTask")
public class AddFriendsTaskController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(AddFriendsTaskController.class);
	private final static String PAGE_VIEW_REDIRECT_DETAIL = "redirect:" + Global.getAdminPath()
			+ "/member/addFriendsTask/selectAddFriendsTaskDetailList";
	private final static String PAGE_VIEW_REDIRECT_TASK = "redirect:" + Global.getAdminPath()
			+ "/member/addFriendsTask/selectAddFriendsTaskList";
	@Autowired
	private RedisCache redisCache;

	@Resource
	private IAddFriendsTaskService addFriendsTaskService;
	@Autowired
	private IShopTerminalService shopTerminalService;
	@Autowired
	private IAddFriendsTaskDetailService addFriendsTaskDetailService;

	@RequestMapping(value = "/template")
	public String importFileTemplate(HttpServletResponse response, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		try {
			String fileName = "手机号模板下载.xlsx";
			List<ImportAddWxFriendsDto> list = Lists.newArrayList();
			ImportAddWxFriendsDto importAddWxFriendsDto = new ImportAddWxFriendsDto();
			importAddWxFriendsDto.setMemberName("张三");
			importAddWxFriendsDto.setMobile("181111222");
			importAddWxFriendsDto.setValidation("您好，我是XXX");
			list.add(importAddWxFriendsDto);

			ImportAddWxFriendsDto importAddWxFriendsDto2 = new ImportAddWxFriendsDto();
			importAddWxFriendsDto2.setMobile("186333444");
			importAddWxFriendsDto2.setMemberName("李四");
			importAddWxFriendsDto2.setValidation("您好，我是XXX");
			list.add(importAddWxFriendsDto2);
			new ExportExcel("手机号加粉导入模版", ImportAddWxFriendsDto.class, 2).setDataList(list).write(response, fileName)
					.dispose();
		} catch (Exception e) {
			logger.error("店员导入模板下载失败！", e);
			addMessage(redirectAttributes, "店员导入模板下载失败！失败信息：" + e.getMessage());
		}
		return null;
	}

	/**
	 * 
	 *
	 * 方法说明：导入电话号码
	 *
	 * @param file
	 * @param redirectAttributes
	 * @return
	 *
	 * @author zlh CreateDate: 2017年7月21日
	 *
	 */
	@RequestMapping(value = "importMobilePhone", method = RequestMethod.POST)
	public String importExcel(MultipartFile file, Model model, AddFriendsTaskDto addFriendsTask,
			RedirectAttributes redirectAttributes) {
		try {

			// 防止1秒内多次提交
			String fileName = file.getOriginalFilename();
			String p = redisCache.get("REDIS-KEY-QUERY-PHONE-" + fileName);
			if (StringUtils.isNotEmpty(p)) {
				addMessage(redirectAttributes, "请不要重复导入！");
				return PAGE_VIEW_REDIRECT_TASK;
			}
			// 防止3秒内多次提交
			redisCache.set("REDIS-KEY-QUERY-PHONE-" + fileName, fileName, 3);

			int successNum = 0;
			int failureNum = 0;

			String arrayStr[] = addFriendsTask.getNoWxArrays().split(",");

			StringBuilder failureMsg = new StringBuilder();

			String taskCode = GUID.generateByUUID();

			List<AddFriendsTaskDetailDto> detailList = new ArrayList<AddFriendsTaskDetailDto>();
			AddFriendsTaskDetailDto addFriendsTaskDetail = null;
			List<String> mobileList = new ArrayList<String>();

			// 导入的数据
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<ImportAddWxFriendsDto> list = ei.getDataList(ImportAddWxFriendsDto.class);
			int num = 0;
			String merchantNo = UserUtils.getMerchantNo();
			String merchantName = UserUtils.getMerchantName();
			for (ImportAddWxFriendsDto user : list) {
				try {

					// 处理excel转义的手机号码
					user.setMobile(DictUtils.excelChage(user.getMobile()));
					// user.setNoWx(DictUtils.excelChage(user.getNoWx()));
					/* 校验手机号格式 */
					if (!Validator.isMobile(user.getMobile())) {
						failureMsg.append("<br/>客户手机号： " + user.getMobile() + "格式不正确！");
						logger.error("导入手机号有个别错误手机号 " + user.getMobile());
						failureNum++;
						continue;
					}

					// 判断手机号是否重复
					if (mobileList.contains(user.getMobile())) {
						failureMsg.append("<br/>客户手机号： " + user.getMobile() + "重复，跳过！");
						logger.error("导入手机号重复" + user.getMobile());
						failureNum++;
						continue;
					}

					// 把用户平均分给每一个微信
					if (arrayStr.length == num) {
						num = 0;
					}
					String str = arrayStr[num];
					addFriendsTaskDetail = new AddFriendsTaskDetailDto();
					addFriendsTaskDetail.setNoWxGm(str);
					addFriendsTaskDetail.setCode(GUID.generateCode());
					addFriendsTaskDetail.setPhone(user.getMobile());
					addFriendsTaskDetail.setStatus(AddfriendsTaskStatus.START.getCode());// 默认直接启动
					addFriendsTaskDetail.setTaskCode(taskCode);
					addFriendsTaskDetail.setUsername(user.getMemberName());
					addFriendsTaskDetail.setCreateDate(new Date());
					addFriendsTaskDetail.setMerchantNo(merchantNo);
					addFriendsTaskDetail.setMerchantName(merchantName);
					String validation = StringUtils.isNotEmpty(user.getValidation()) ? user.getValidation()
							: addFriendsTask.getSendMessage();
					addFriendsTaskDetail.setSendMessage(StringUtils.isNotEmpty(validation) ? validation : "");
					detailList.add(addFriendsTaskDetail);
					mobileList.add(user.getMobile());
					successNum++;
					num++;
				} catch (Exception e) {
					failureMsg.append("<br/>手机号 " + user.getMobile() + " 导入失败：" + e.getMessage());
					logger.error("导入手机号失败:{}", e);
				}
			}

			addFriendsTask.setTotalPhonenum(successNum);
			addFriendsTask.setCode(taskCode);
			addFriendsTask.setStatus(String.valueOf(CommonConstant.I_NO));
			addFriendsTask.setMerchantNo(merchantNo);
			addFriendsTask.setMerchantName(merchantName);
			addFriendsTask.setDetailList(detailList);
			addFriendsTaskService.addAddFriendsTask(addFriendsTask);
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条手机号，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条手机号" + failureMsg);
		} catch (TsfaServiceException e) {
			logger.error("导入数据失败！", e);
			addMessage(redirectAttributes, "导入手机号失败！失败信息：" + e.getExceptionInfo());
		} catch (Exception e) {
			logger.error("导入数据失败！", e);
			addMessage(redirectAttributes, "导入手机号失败！失败信息：" + e.getMessage());
		}
		return PAGE_VIEW_REDIRECT_TASK; // 跳转到列表页
	}

	/**
	 * 方法说明：到添加任务页面
	 * 
	 * @return
	 */
	@RequestMapping(value = { "toAddFriendsTaskPage" })
	public String toAddFriendsTaskPage(Model model, String code, HttpServletRequest request,
			HttpServletResponse response) {
		// 终端列表
		FindShopTerminal findShopTerminal = new FindShopTerminal();
		findShopTerminal.setMerchantNo(UserUtils.getMerchantNo());
		findShopTerminal.setStatus(ShopTerminalStatus.NORMAL.getValue());
		List<FindShopTerminalReturn> findShopTerminalReturns = shopTerminalService
				.findShopTerminalSelect(findShopTerminal);
		model.addAttribute("findShopTerminalReturns", findShopTerminalReturns);
		return "modules/member/addFriendsTask/addFriendsTaskAdd";
	}

	/**
	 * 方法说明：查找好友任务列表（加粉任务列表）
	 * 
	 * @return
	 */
	@RequestMapping(value = { "selectAddFriendsTaskList" })
	public String selectAddFriendsTaskList(Model model, FindAddFriendsTaskPage findAddFriendsTaskPage, Integer pageNo,
			Integer pageSize) {
		try {
			findAddFriendsTaskPage.setStart(pageNo == null ? 0 : (pageNo - 1) * pageSize);
			findAddFriendsTaskPage.setLimit(pageSize == null ? 10 : pageSize);
			if (null == findAddFriendsTaskPage.getParam()) {
				findAddFriendsTaskPage.setParam(new AddFriendsTaskDto());
			}
			findAddFriendsTaskPage.getParam().setMerchantNo(UserUtils.getMerchantNo());
			Page<AddFriendsTaskDto> addFriendsTask = addFriendsTaskService
					.findAddFriendsTaskPage(findAddFriendsTaskPage);
			com.ape.common.persistence.Page<AddFriendsTaskDto> page = new com.ape.common.persistence.Page<AddFriendsTaskDto>(
					pageNo == null ? 1 : pageNo, addFriendsTask.getLimit(), addFriendsTask.getTotal(),
					new ArrayList<>(addFriendsTask.getRows()));
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("findAddFriendsTaskPage", findAddFriendsTaskPage);
		} catch (Exception e) {
			logger.error("查询添加好友任务列表失败！", e);
		}
		return "modules/member/addFriendsTask/addFriendsTaskList";
	}

	/**
	 * 方法说明：查找添加好友任务执行详情列表（加粉详情）
	 * 
	 * @return
	 */
	@RequestMapping(value = { "selectAddFriendsTaskDetailList" })
	public String selectAddFriendsTaskDetailList(Model model, FindAddFriendsTaskDetailPage findAddFriendsTaskDetailPage,
			Integer pageNo, Integer pageSize) {
		try {
			findAddFriendsTaskDetailPage.setStart(pageNo == null ? 0 : (pageNo - 1) * pageSize);
			findAddFriendsTaskDetailPage.setLimit(pageSize == null ? 10 : pageSize);
			if (null == findAddFriendsTaskDetailPage.getParam()) {
				findAddFriendsTaskDetailPage.setParam(new AddFriendsTaskDetailDto());
			}
			findAddFriendsTaskDetailPage.getParam().setMerchantNo(UserUtils.getMerchantNo());
			Page<AddFriendsTaskDetailDto> addFriendsTaskDetailPage = addFriendsTaskDetailService
					.findAddFriendsTaskDetailPage(findAddFriendsTaskDetailPage);
			com.ape.common.persistence.Page<AddFriendsTaskDetailDto> page = new com.ape.common.persistence.Page<AddFriendsTaskDetailDto>(
					pageNo == null ? 1 : pageNo, addFriendsTaskDetailPage.getLimit(),
					addFriendsTaskDetailPage.getTotal(), new ArrayList<>(addFriendsTaskDetailPage.getRows()));
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("findAddFriendsTaskDetailPage", findAddFriendsTaskDetailPage);
			model.addAttribute("statuss", AddfriendsTaskStatus.values());
		} catch (Exception e) {
			logger.error("查询添加好友任务列表详情失败！", e);
		}
		return "modules/member/addFriendsTask/addFriendsTaskDetail";
	}

	/**
	 * 方法说明：加粉统计
	 * 
	 * @return
	 */
	@RequestMapping(value = { "selectAddFriendsTaskDetailCount" })
	public String selectAddFriendsTaskDetailCount(Model model, Integer pageNo, Integer pageSize,
			FindAddFriendsTaskPage findAddFriendsTaskPage) {
		try {

			AddFriendsTaskDto addFriendsTaskDto = new AddFriendsTaskDto();
			addFriendsTaskDto.setMerchantNo(UserUtils.getMerchantNo());
			AddFriendsTaskCountDto addFriendsTaskCountDto = addFriendsTaskService
					.selectAddFriendsTaskDetailCount(addFriendsTaskDto);

			findAddFriendsTaskPage.setStart(pageNo == null ? 0 : (pageNo - 1) * pageSize);
			findAddFriendsTaskPage.setLimit(pageSize == null ? 10 : pageSize);
			if (null == findAddFriendsTaskPage.getParam()) {
				findAddFriendsTaskPage.setParam(new AddFriendsTaskDto());
			}
			findAddFriendsTaskPage.setSortBy("trans");
			findAddFriendsTaskPage.setSortDir(PageSortType.desc);
			findAddFriendsTaskPage.getParam().setMerchantNo(UserUtils.getMerchantNo());
			Page<AddFriendsTaskDto> addFriendsTask = addFriendsTaskService
					.findAddFriendsTaskPage(findAddFriendsTaskPage);
			com.ape.common.persistence.Page<AddFriendsTaskDto> page = new com.ape.common.persistence.Page<AddFriendsTaskDto>(
					pageNo == null ? 1 : pageNo, addFriendsTask.getLimit(), addFriendsTask.getTotal(),
					new ArrayList<>(addFriendsTask.getRows()));
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("detail", addFriendsTaskCountDto);

		} catch (Exception e) {
			logger.error("查询添加好友任务列表详情失败！", e);
		}
		return "modules/member/addFriendsTask/addFriendsTaskCount";
	}

	@RequestMapping(value = "del")
	public String del(Model model, RedirectAttributes redirectAttributes,
			AddFriendsTaskDetailDto addFriendsTaskDetailDto) {
		try {

			AddFriendsTaskDetailDto dto = addFriendsTaskDetailService.findAddFriendsTaskDetail(addFriendsTaskDetailDto);
			if (null == dto) {
				addMessage(redirectAttributes, "任务不存在！");
			}

			if (AddfriendsTaskStatus.EXECUTING.getCode().equals(dto.getStatus())) {
				addMessage(redirectAttributes, "任务执行中不能删除！");
			}
			if (AddfriendsTaskStatus.SUCCESS.getCode().equals(dto.getStatus())) {
				addMessage(redirectAttributes, "任务已完成不能删除！");
			}
			addFriendsTaskDetailService.deleteByPrimaryKey(dto.getCode());
			addMessage(redirectAttributes, "删除任务成功");
			FindAddFriendsTaskDetailPage findAddFriendsTaskDetailPage = new FindAddFriendsTaskDetailPage();
			AddFriendsTaskDetailDto param = new AddFriendsTaskDetailDto();
			param.setTaskCode(dto.getTaskCode());
			findAddFriendsTaskDetailPage.setParam(param);
			return selectAddFriendsTaskDetailList(model, findAddFriendsTaskDetailPage, null, null);
		} catch (TsfaServiceException e) {
			logger.debug("删除任务错误={}", e);
			addMessage(redirectAttributes, "删除任务失败：" + e.getExceptionInfo());
			e.printStackTrace();
		} catch (Exception e) {
			logger.debug("删除任务错误={}", e);
			addMessage(redirectAttributes, "删除任务失败：" + e.getMessage());
			e.printStackTrace();
		}
		return PAGE_VIEW_REDIRECT_DETAIL;
	}

	@RequestMapping(value = "updateStatus")
	public String updateStatus(Model model, RedirectAttributes redirectAttributes,
			AddFriendsTaskDetailDto addFriendsTaskDetailDto) {
		try {
			addFriendsTaskDetailService.updateByCond(addFriendsTaskDetailDto);
			addMessage(redirectAttributes, "修改任务成功");
			FindAddFriendsTaskDetailPage findAddFriendsTaskDetailPage = new FindAddFriendsTaskDetailPage();
			AddFriendsTaskDetailDto param = new AddFriendsTaskDetailDto();
			param.setTaskCode(addFriendsTaskDetailDto.getTaskCode());
			findAddFriendsTaskDetailPage.setParam(param);
			return selectAddFriendsTaskDetailList(model, findAddFriendsTaskDetailPage, null, null);
		} catch (TsfaServiceException e) {
			logger.debug("修改任务错误={}", e);
			addMessage(redirectAttributes, "修改任务失败：" + e.getExceptionInfo());
			e.printStackTrace();
		} catch (Exception e) {
			logger.debug("修改任务错误={}", e);
			addMessage(redirectAttributes, "修改任务失败：" + e.getMessage());
			e.printStackTrace();
		}
		return PAGE_VIEW_REDIRECT_DETAIL;
	}

	@RequestMapping(value = "allStatus")
	public String allStatus(Model model, RedirectAttributes redirectAttributes,
			AddFriendsTaskDetailDto addFriendsTaskDetailDto) {
		try {
			addFriendsTaskDetailService.updateByCond(addFriendsTaskDetailDto);
			addMessage(redirectAttributes, "修改任务成功");
			FindAddFriendsTaskDetailPage findAddFriendsTaskDetailPage = new FindAddFriendsTaskDetailPage();
			AddFriendsTaskDetailDto param = new AddFriendsTaskDetailDto();
			param.setTaskCode(addFriendsTaskDetailDto.getTaskCode());
			findAddFriendsTaskDetailPage.setParam(param);
			return selectAddFriendsTaskDetailList(model, findAddFriendsTaskDetailPage, null, null);
		} catch (TsfaServiceException e) {
			logger.debug("修改任务错误={}", e);
			addMessage(redirectAttributes, "修改任务失败：" + e.getExceptionInfo());
			e.printStackTrace();
		} catch (Exception e) {
			logger.debug("修改任务错误={}", e);
			addMessage(redirectAttributes, "修改任务失败：" + e.getMessage());
			e.printStackTrace();
		}
		return PAGE_VIEW_REDIRECT_DETAIL;
	}
}
