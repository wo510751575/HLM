package com.lj.business.cf.service.impl.job;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.member.emus.MemberType;
import com.lj.cc.clientintf.IJob;
import com.lj.messagecenter.emus.SendType;
import com.lj.messagecenter.msg.dto.AddNotifyInfo;
import com.lj.messagecenter.msg.enums.MsgSystemType;
import com.lj.messagecenter.msg.service.INotifyService;

public class UnfinishTaskRemindJobServiceImplTest extends SpringTestCase {

	@Resource
	private INotifyService notifyService;
	
	@Resource
	private IJob unfinishTaskRemindJobServiceImpl;
	
	@Test
	public void pushTest() throws Exception {
		AddNotifyInfo addNotifyInfo = new AddNotifyInfo();
		addNotifyInfo.setSendType(SendType.SINGLE.toString());
		addNotifyInfo.setMerchantNo("2169d1820e254e86b110dff73e1bd58f");
		addNotifyInfo.setMemberNo("8430ab66cc08459586e827ba7a71aef7");
		addNotifyInfo.setMemberName("导购-小何");
		addNotifyInfo.setMemberType(MemberType.GUID.toString());
		addNotifyInfo.setMobile("18922892246");
		addNotifyInfo.setSysType(MsgSystemType.ALL.toString());
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("unfinishTaskMessage", "今天18:00数据会提交到欧阳总查看，您少了3个新增客户，销售额差6500，请及时完成！");
		addNotifyInfo.setContent(JsonUtils.jsonFromObject(paramMap));
		notifyService.sendCustomMsgInfo(addNotifyInfo);
	}
	
	@Test
	public void jobTest() throws Exception {
		unfinishTaskRemindJobServiceImpl.runJob();
	}
}
