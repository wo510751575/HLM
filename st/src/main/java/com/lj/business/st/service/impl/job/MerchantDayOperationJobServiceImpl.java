package com.lj.business.st.service.impl.job;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.lj.base.core.util.DateUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.view.IText5PdfView;
import com.lj.business.common.SystemParamConstant;
import com.lj.business.member.dto.FindMerchantPageReturn;
import com.lj.business.member.service.IMerchantService;
import com.lj.business.st.dto.MerchantDayOperateDto;
import com.lj.business.st.dto.ShopDayOperateDto;
import com.lj.business.st.service.IMerchantDayOperationService;
import com.lj.cc.clientintf.IJob;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.common.ErrorCode;
import com.lj.cc.enums.SystemAliasName;

@Service
public class MerchantDayOperationJobServiceImpl implements IJob {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(MerchantDayOperationJobServiceImpl.class);

	//private static final String REQUEST_URL = "/merchantDayOperate/view";

	@Resource
	private IMerchantService merchantService;

	@Resource
	private IMerchantDayOperationService merchantDayOperationService;

	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;

	@Override
	public void runJob() {
		logger.info("商户运营日报报表");
		List<FindMerchantPageReturn> findAllMerchant = merchantService.findAllMerchant();
		for (FindMerchantPageReturn findMerchantPageReturn : findAllMerchant) {
			logger.info("统计商户{}运营日报报表数据", findMerchantPageReturn.getMerchantName());
			try {
				/*	Map<String, String> map = new HashMap<>();
				map.put("merchantNo", findMerchantPageReturn.getMerchantNo());
				ModelAndView modelAndView = IText5PdfView.generatePdfViewUrl(REQUEST_URL,map);
				 */				
				MerchantDayOperateDto merchantDayOperateDto = merchantDayOperationService.generatorMerChantDayOperate(findMerchantPageReturn.getMerchantNo());

				StringBuffer html = new StringBuffer();
				//组装成符合W3C标准的html文件，否则不能正确解析
				html.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
				html.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">")
				.append("<head>")
				.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />")
				.append("<style type=\"text/css\" mce_bogus=\"1\">body {font-family: SimSun;}</style>")
				.append("<style type=\"text/css\">")
				.append("table{border:1px solid #000;border-collapse: collapse;padding-top: 50px;}  table td{border:1px solid #000; text-align: right;} table th{border:1px solid #000;}")
				.append("</style>")
				.append("</head>")
				.append("<body>");
				html.append("<table border=\"0\">");
				html.append("<tr><td colspan=\"6\" align=\"center\" style='text-align:center'>销售情况</td></tr>");
				html.append("<tr>");
				html.append("<td align='center'>门店总数:</td>");
				html.append("<td align='left'>" + merchantDayOperateDto.getShopNum() + "</td>");
				html.append("<td>店员总数:</td>");
				html.append("<td align='left'>" + merchantDayOperateDto.getGuidNum() + "</td>");
				html.append("<td>今日销售额:</td>");
				html.append("<td align='left'>" + merchantDayOperateDto.getDaySale()/100 + "元</td>");
				html.append("</tr>");
				html.append("</table>");

				html.append("<div><br/></div>");
				
				html.append("<table border=\"0\">");
				html.append("<tr><td colspan=\"8\" align=\"center\" style='text-align:center'>客户情况</td></tr>");
				html.append("<tr>");
				html.append("<th style='text-align:center;' align='center'>分店名称</th>");
				html.append("<th>客户总数</th>");
				html.append("<th>意向客户</th>");
				html.append("<th>非意向客户</th>");
				html.append("<th>成单客户</th>");
				html.append("<th>暂停客户</th>");
				html.append("<th>成单率</th>");
				html.append("<th>销售额（元）</th>");
				html.append("</tr>");

				Map<String, ShopDayOperateDto> shopData = merchantDayOperateDto.getShopData();
				for (Entry<String, ShopDayOperateDto> entry : shopData.entrySet()) {
					ShopDayOperateDto shopDayOperateDto = entry.getValue();
					html.append("<tr><td style='text-align: left;'>"+ shopDayOperateDto.getShopName() +"</td>");
					html.append("<td>"+ shopDayOperateDto.getNumPm() +"</td>");

					html.append("<td>"+ shopDayOperateDto.getNumPmIntention() +"</td>");
					html.append("<td>"+ shopDayOperateDto.getNumPmNoIntention() +"</td>");
					html.append("<td>"+ shopDayOperateDto.getNumOrderPm() +"</td>");
					html.append("<td>"+ shopDayOperateDto.getNumPmAbandon() +"</td>");
					html.append("<td>"+ shopDayOperateDto.getOrderRatio() +"</td>");
					html.append("<td>"+ shopDayOperateDto.getSale() +"</td>");

					html.append("</tr>");
				}

				html.append("</table>");

				html.append("<div><br/></div>");

				html.append("<table border=\"0\">");
				html.append("<tr><td colspan=\"7\" align=\"center\" style='text-align:center'>工作完成情况</td></tr>");
				html.append("<tr>");
				html.append("<th style='text-align:center;' align='center'>分店名称</th>");
				html.append("<th>跟进<br/>客户数</th>");
				html.append("<th>最高跟进<br/>客户次数</th>");
				html.append("<th>业务任务<br/>产生量</th>");
				html.append("<th>业务任务<br/>完成量</th>");
				html.append("<th>社交任务<br/>产生量</th>");
				html.append("<th>社交任务<br/>完成量</th>");
				html.append("</tr>");
				for (Entry<String, ShopDayOperateDto> entry : shopData.entrySet()) {
					ShopDayOperateDto shopDayOperateDto = entry.getValue();
					html.append("<tr><td style='text-align: left;'>"+ shopDayOperateDto.getShopName() +"</td>");
					html.append("<td >"+ shopDayOperateDto.getNumPmCf() +"</td>");
					html.append("<td>"+ shopDayOperateDto.getMaxPmCfNum() +"</td>");
					html.append("<td>"+ shopDayOperateDto.getNumComTaskProduce() +"</td>");
					html.append("<td>"+ shopDayOperateDto.getNumComTaskComplete() +"</td>");
					html.append("<td>"+ shopDayOperateDto.getNumSocialTaskProduce() +"</td>");
					html.append("<td>"+ shopDayOperateDto.getNumSocialTaskComplete() +"</td>");
					html.append("</tr>");
				}
				html.append("</table>");

				html.append("<div><br/></div>");

				html.append("<table border=\"0\">");
				html.append("<tr><td colspan=\"7\" align=\"center\" style='text-align:center'>工作完成情况</td></tr>");
				html.append("<tr>");
				html.append("<th style='text-align: center;' align='center'>分店名称</th>");
				html.append("<th>邀约任务<br/>完成量</th>");
				html.append("<th>应邀<br/>客户量</th>");
				html.append("<th>到店<br/>客户量</th>");
				html.append("<th>微信<br/>聊天次数</th>");
				html.append("<th>通话<br/>次数</th>");
				html.append("<th>通话<br/>时长</th>");
				html.append("</tr>");
				for (Entry<String, ShopDayOperateDto> entry : shopData.entrySet()) {
					ShopDayOperateDto shopDayOperateDto = entry.getValue();
					html.append("<tr><td style='text-align: left;'>"+ shopDayOperateDto.getShopName() +"</td>");
					html.append("<td>"+ shopDayOperateDto.getNumInviteTaskComplete() +"</td>");
					html.append("<td>"+ shopDayOperateDto.getIsInvitePmNum() +"</td>");
					html.append("<td>"+ shopDayOperateDto.getClientExpPmNum() +"</td>");
					html.append("<td>"+ shopDayOperateDto.getNumWeChat() +"</td>");
					html.append("<td>"+ shopDayOperateDto.getNumCall() +"</td>");
					html.append("<td>"+ shopDayOperateDto.getCallTime() +"</td>");
					html.append("</tr>");
				}
				html.append("</table>");



				html.append("</body></html>");

				logger.debug(html.toString());

				IText5PdfView iText5PdfView = new IText5PdfView();
				Document document = new Document();
				
				//从缓存中获取商户报表上传路径
				String downloadPath = localCacheSystemParams.getSystemParam(SystemAliasName.st.toString(),SystemParamConstant.MERCHANT_OPERATION, SystemParamConstant.MERCHANT_OPERATION_PATH);
				if (StringUtils.isBlank(downloadPath)) {
					throw new TsfaServiceException(ErrorCode.CC_QUERY_ERROR, "系统参数商户报表上传路径为空！");
				}
				downloadPath += "/" + DateUtils.formatDate(DateUtils.getPreday(new Date()), DateUtils.PATTERN_yyyy_MM_dd);
				downloadPath += "/" + findMerchantPageReturn.getMerchantName() + ".pdf";
				File downloadFile = new File(downloadPath);
				if (!downloadFile.getParentFile().exists()) {
					downloadFile.getParentFile().mkdirs();
				}
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(downloadPath));  
				document.open();  
				iText5PdfView.buildPdfDocumentHelpHtml(html.toString() , document, writer);
				writer.close();
				document.close();
			} catch (Exception e) {
				logger.info("统计商户{}运营日报表数据请求错误", findMerchantPageReturn.getMerchantNo(), e);
			}
		}
	}

}
