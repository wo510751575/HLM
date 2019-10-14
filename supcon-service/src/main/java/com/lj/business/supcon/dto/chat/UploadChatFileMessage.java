package com.lj.business.supcon.dto.chat;

import java.io.Serializable;

/**
 * 请求下载文件类型消息的文件
 */
public class UploadChatFileMessage implements Serializable {

    private static final long serialVersionUID = -8790470874225525802L;

    /**
     * 消息ID
     */
    private String msgId;

    /**
     * 文件下载信息
     */
    private String content;

    /**
     * 微信号
     */
    private String noWxShop;

    /**
     * 查询标志，1：导购端请求查询，2：导购助手请求查询
     */
    private int findFlag = 2;


    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFindFlag() {
        return findFlag;
    }

    public void setFindFlag(int findFlag) {
        this.findFlag = findFlag;
    }

    public String getNoWxShop() {
        return noWxShop;
    }

    public void setNoWxShop(String noWxShop) {
        this.noWxShop = noWxShop;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UploadChatVideoMessage [");
        builder.append("msgId=");
        builder.append(msgId);
        builder.append(", content=");
        builder.append(content);
        builder.append(", findFlag=");
        builder.append(findFlag);
        builder.append(", noWxShop=");
        builder.append(noWxShop);
        builder.append("]");
        return builder.toString();
    }
}
