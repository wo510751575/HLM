package com.lj.oms.weixin;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ape.common.utils.DateUtils;
import com.ape.common.utils.StringUtils;
import com.lj.oms.common.BaseController;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.business.common.SystemParamConstant;
import com.lj.business.member.dto.FindGuidMemberPage;
import com.lj.business.member.dto.FindGuidMemberPageReturn;
import com.lj.business.member.dto.FindPersonMemberBase;
import com.lj.business.member.dto.FindPersonMemberBaseReturn;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IPersonMemberBaseService;
import com.lj.business.weixin.dto.FindWxChatInfoPage;
import com.lj.business.weixin.dto.FindWxChatInfoPageReturn;
import com.lj.business.weixin.service.IWxChatInfoService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;
import com.lj.oms.utils.JsonPage;

/**
 * 类说明：微信聊天记录Controller
 * <p>
 * <p>
 * <p>
 * 详细描述：
 *
 * @author 段志鹏
 * <p>
 * CreateDate: 2017年7月14日
 * @Company: 扬恩科技有限公司
 */
@Controller
@RequestMapping(value = "${adminPath}/weixin/chatInfo")
public class ChatInfoController {

    private static final int PAGE_SIZE = 10;

    @Resource
    private IWxChatInfoService wxChatInfoService;
    @Resource
    private IGuidMemberService guidMemberService;
    @Resource
    private IPersonMemberBaseService personMemberBaseService;
    @Resource
    private LocalCacheSystemParamsFromCC localCacheSystemParams;

    /**
     * 方法说明：验证微信聊天记录查看密码
     *
     * @param psw
     * @return
     * @author 罗书明 CreateDate: 2017年8月17日
     */
    @RequestMapping(value = "psw")
    @ResponseBody
    public boolean psw(String psw) {
        String pwd = localCacheSystemParams.getSystemParam(SystemAliasName.weixin.toString(), SystemParamConstant.PWD, SystemParamConstant.CHATINFO_VIEW_PWD);
        if (StringUtils.isBlank(pwd)) {
            return false;
        }
        if (psw.equals(pwd)) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 方法说明：聊天记录列表
     *
     * @param model
     * @param request
     * @param response
     * @return
     * @author 段志鹏 CreateDate: 2017年7月14日
     */
    @RequestMapping(value = {"list", ""})
    public String list(Model model, Integer pageNo, Integer pageSize, String talker, String startTime, String endTime, String memberNoGm) {
        try {

            Map<String, Object> parmMap = new HashMap<String, Object>();
            parmMap.put("memberNoGm", memberNoGm);
            parmMap.put("talker", talker);
            parmMap.put("limit", pageSize != null ? pageSize : PAGE_SIZE);
            parmMap.put("start", pageNo != null ? (pageNo - 1) * pageSize : 0);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date startTimes = null;
            Date endTimes = null;
            if (StringUtils.isNotBlank(startTime)) {
                Date date = sdf.parse(startTime);
                Calendar calendar = DateUtils.toCalendar(date);
                // 将小时至0
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                // 将分钟至0
                calendar.set(Calendar.MINUTE, 0);
                // 将秒至0
                calendar.set(Calendar.SECOND, 0);
                startTimes = calendar.getTime();
            }
            if (StringUtils.isNotBlank(endTime)) {
                Date dates = sdf.parse(endTime);
                Calendar calendar = DateUtils.toCalendar(dates);
                // 将小时至23
                calendar.set(Calendar.HOUR_OF_DAY, 23);
                // 将分钟至59
                calendar.set(Calendar.MINUTE, 59);
                // 将秒至59
                calendar.set(Calendar.SECOND, 59);
                endTimes = calendar.getTime();
            }
            parmMap.put("startTime", startTimes);
            parmMap.put("endTime", endTimes);

            Page<Map<String, Object>> pageDto = wxChatInfoService.findWxChatInfoPageOMS(parmMap);
            List<Map<String, Object>> list = Lists.newArrayList();

            list.addAll(pageDto.getRows());
            System.out.println(pageDto);
            if (StringUtils.isEmpty(talker)) {
                list = Lists.newArrayList();
            }
            com.ape.common.persistence.Page<Map<String, Object>> page = new com.ape.common.persistence.Page<Map<String, Object>>(pageNo == null ? 1 : pageNo, pageDto.getLimit(), pageDto.getTotal(), list);
            page.initialize();
            model.addAttribute("page", page);
            //鍙傛暟
            model.addAttribute("parmMap", parmMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "modules/weixin/chatInfoList";
    }

    @RequestMapping(value = {"view"})
    @ResponseBody
    public JsonPage<FindWxChatInfoPageReturn> list(Model model, Integer pageNo, Integer pageSize, FindWxChatInfoPage findWxChatInfoPage) {
        try {
            if (pageNo != null) {
                findWxChatInfoPage.setStart((pageNo - 1) * pageSize);
            }
            if (pageSize != null) {
                findWxChatInfoPage.setLimit(pageSize);
            }
            findWxChatInfoPage.setEndTime(DateUtils.addDays(findWxChatInfoPage.getStartTime(), 1));
            Page<FindWxChatInfoPageReturn> pageDto = wxChatInfoService.findWxChatInfoPage(findWxChatInfoPage);
            List<FindWxChatInfoPageReturn> list = Lists.newArrayList();
            list.addAll(pageDto.getRows());
            for (FindWxChatInfoPageReturn findWxChatInfoPageReturn : list) {
                //获取导购头像
                FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
                findGuidMemberPage.setMemberNo(findWxChatInfoPageReturn.getMemberNo());
                List<FindGuidMemberPageReturn> guidMember = guidMemberService.findGuidMembers(findGuidMemberPage);
                if (guidMember != null && guidMember.size() > 0) {
                    findWxChatInfoPageReturn.setGmPhoto(guidMember.get(0).getHeadAddress());
                }
                //获取客户头像和昵称
                FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
                findPersonMemberBase.setNoWx(findWxChatInfoPageReturn.getTalker());
                FindPersonMemberBaseReturn member = personMemberBaseService.findPersonMemberBase(findPersonMemberBase);
                if (member != null) {
                    findWxChatInfoPageReturn.setPmNickName(member.getNickNameWx());
                    findWxChatInfoPageReturn.setPmPhoto(member.getHeadAddress());
                }
            }
            com.lj.oms.utils.JsonPage<FindWxChatInfoPageReturn> page = new com.lj.oms.utils.JsonPage<FindWxChatInfoPageReturn>(pageNo == null ? 1 : pageNo, pageDto.getLimit(), pageDto.getTotal(), list);
            page.initialize();
            return page;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonPage<FindWxChatInfoPageReturn>();
    }
}
