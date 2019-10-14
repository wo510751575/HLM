package com.lj.business.supcon.netty;

import io.netty.channel.Channel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.enums.ConnectSource;
import com.lj.business.supcon.netty.enums.SessionCloseReason;
	
/**
 * 
 * 
 * 类说明：链接会话
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月10日
 */
public class IoSession implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(IoSession.class);

	/** 连接CHANNEL **/
	private Channel channel;

	/** 导购编号 **/
	private String memberNoGm;
	/** 终端编码**/
	private String terminalCode;
	/** 商户编号 **/
	private String merchantNo;
	
	/** 导购微信号(也是终端微信号) **/
	private String noWxGm;
	
	/** 连接来源 **/
	private ConnectSource connectSource;

	/** ip地址 **/
	private String ipAddr;

	/** 是否重连 **/
	private boolean reconnected;
	
	/** 是否登录 **/
	private boolean isLogin;
	
	/** 中控手机客户端绑定的微信号列表  */
	private List<String> zkNoWxList = new ArrayList<>();
	
	/**
	 * 扩展用，保存一些个人数据  
	 * merchantNo
	 */
	private Map<String, Object> attrs = new HashMap<>();

	public String getTerminalCode() {
		return terminalCode;
	}

	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public IoSession() {

	}

	public IoSession(Channel channel) {
		this.channel = channel;
		this.ipAddr = ChannelUtils.getIp(channel);
	}
	
	/**
	 * 
	 *
	 * 方法说明：存储个人数据
	 *
	 * @param key
	 * @param value
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月7日
	 *
	 */
	public void putAttr(String key, Object value) {
		attrs.put(key, value);
	}
	
	/**
	 * 
	 *
	 * 方法说明：获取个人数据
	 *
	 * @param key
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月7日
	 *
	 */
	public Object getAttr(String key) {
		return attrs.get(key);
	}
	
	/**
	 * 
	 *
	 * 方法说明：添加中控手机客户端绑定的微信号
	 *
	 * @param noWx
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月8日
	 *
	 */
	public void addZkNoWx(String noWx) {
		this.zkNoWxList.add(noWx);
	}
	
	/**
	 * 
	 *
	 * 方法说明：添加中控手机客户端绑定的微信号列表
	 *
	 * @param noWx
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月8日
	 *
	 */
	public void addAllZkNoWx(List<String> noWxList) {
		this.zkNoWxList.addAll(noWxList);
	}
	
	/**
	 * 
	 *
	 * 方法说明：获取session登录账号
	 *
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月24日
	 *
	 */
	public String getLoginAccountNo() {
		if(connectSource == ConnectSource.GM) {
			return memberNoGm;
		} else {
			return noWxGm;
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：判断通道是否关闭
	 *
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月13日
	 *
	 */
	public boolean isClose() {
		if (channel == null) {
			return true;
		}
		return !channel.isActive() || !channel.isOpen();
	}
	
	/**
	 * 
	 *
	 * 方法说明：关闭session 
	 *
	 * @param reason {@link SessionCloseReason}
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月13日
	 *
	 */
	public void close(SessionCloseReason reason) {
		isLogin = Boolean.FALSE;
		try{
			if (this.channel == null) {
				return;
			}
			if (channel.isOpen()) {
				channel.close();
				logger.info("Close session[{}], reason is {}", this, reason);
			}else{
				logger.info("Session[{}] already closed", this);
			}
		}catch(Exception e){
			logger.error("Close session[" +  this + "] error", e);
		}
	}

	/**
	 * @return the channel
	 */
	public Channel getChannel() {
		return channel;
	}

	/**
	 * @param channel the channel to set
	 */
	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	/**
	 * @return the memberNoGm
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * @param memberNoGm the memberNoGm to set
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	/**
	 * @return the noWxGm
	 */
	public String getNoWxGm() {
		return noWxGm;
	}

	/**
	 * @param noWxGm the noWxGm to set
	 */
	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
	}


	/**
	 * @return the connectSource
	 */
	public ConnectSource getConnectSource() {
		return connectSource;
	}

	/**
	 * @param connectSource the connectSource to set
	 */
	public void setConnectSource(ConnectSource connectSource) {
		this.connectSource = connectSource;
	}

	/**
	 * @return the ipAddr
	 */
	public String getIpAddr() {
		return ipAddr;
	}

	/**
	 * @param ipAddr the ipAddr to set
	 */
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	/**
	 * @return the reconnected
	 */
	public boolean isReconnected() {
		return reconnected;
	}

	/**
	 * @param reconnected the reconnected to set
	 */
	public void setReconnected(boolean reconnected) {
		this.reconnected = reconnected;
	}

	/**
	 * @return the isLogin
	 */
	public boolean isLogin() {
		return isLogin;
	}

	/**
	 * @param isLogin the isLogin to set
	 */
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	/**
	 * @return the attrs
	 */
	public Map<String, Object> getAttrs() {
		return attrs;
	}

	/**
	 * @param attrs the attrs to set
	 */
	public void setAttrs(Map<String, Object> attrs) {
		this.attrs = attrs;
	}

	/**
	 * @return the zkNoWxList
	 */
	public List<String> getZkNoWxList() {
		return zkNoWxList;
	}

	/**
	 * @param zkNoWxList the zkNoWxList to set
	 */
	public void setZkNoWxList(List<String> zkNoWxList) {
		this.zkNoWxList = zkNoWxList;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IoSession [channel=");
		builder.append(channel);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", noWxGm=");
		builder.append(noWxGm);
		builder.append(", connectSource=");
		builder.append(connectSource);
		builder.append(", ipAddr=");
		builder.append(ipAddr);
		builder.append(", reconnected=");
		builder.append(reconnected);
		builder.append(", isLogin=");
		builder.append(isLogin);
		builder.append(", zkNoWxList=");
		builder.append(zkNoWxList);
		builder.append(", attrs=");
		builder.append(attrs);
		builder.append("]");
		return builder.toString();
	}
}
