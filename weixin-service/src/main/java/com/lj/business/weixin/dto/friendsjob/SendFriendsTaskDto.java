package com.lj.business.weixin.dto.friendsjob;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 
 * 类说明：发送朋友圈任务调度参数
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 许新龙
 *   
 * CreateDate: 2017年12月29日
 */
public class SendFriendsTaskDto implements Serializable {
    private static final long serialVersionUID = 1596531258068830239L;

    /**
     * SendFriendsJob实体CODE
     */
    private String code;
    
    /**
     * 1：启动任务（保存任务，启动多个任务），2：执行任务（立即执行，执行单个任务）
     */
    private Integer actionType;
    
    /**
     * 要发送的微信，单个任务
     */
    private String wxNo;
    
    /**
     * 要发送的多个微信，保存任务，启动多个任务
     */
    private List<String> wxNos;
    


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getActionType() {
        return actionType;
    }

    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }

    public String getWxNo() {
        return wxNo;
    }

    public void setWxNo(String wxNo) {
        this.wxNo = wxNo;
    }

    public List<String> getWxNos() {
        return wxNos;
    }

    public void setWxNos(List<String> wxNos) {
        this.wxNos = wxNos;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SendFriendsTaskDto [code=");
        builder.append(code);
        builder.append(", actionType=");
        builder.append(actionType);
        builder.append(", wxNo=");
        builder.append(wxNo);
        builder.append(", wxNos=");
        builder.append(wxNos);
        builder.append("]");
        return builder.toString();
    }
    
}
