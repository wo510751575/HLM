package com.lj.business.weixin.data;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lj.business.member.dto.UpdatePmChatBehavior;
import com.lj.business.weixin.constant.Constants;
import com.lj.business.weixin.domain.ImChatInfo;
import com.lj.distributecache.IQueue;


/**
 * @author dengxiudong
 * @version V1.0
 * @projectname: branches-project-2.1.1
 * @package com.lj.business.weixin.data
 * @description:
 * @Date 2018/3/9 19:43
 */
@Component
public class AsyncStorer {
    private static Logger logger = LoggerFactory.getLogger(AsyncStorer.class);

    @Resource
    private IQueue queue;

    /**
     * @author: dengxiudong
     * @title: AsyncStorer
     * @description:
     * @param:
     * @return: true: 保存成功；false：保存失败
     * @throws:
     **/
    public boolean saveImChatInfo(ImChatInfo imChatInfo) {
        if (imChatInfo == null) {
            logger.error("imChatInfo is null.");
            return false;
        }

        String sql = generateImChatInfoSql(imChatInfo);

        try {
            queue.lpush(Constants.ASYNC_DATA_SQL_QUEUE, sql);
            return true;
        } catch (Exception e) {
            logger.error("缓存sql失败：{} sql: {}", e.toString(), sql);
            return false;
        }
    }

    public boolean updateImChatInfo(ImChatInfo imChatInfo) {
        if (imChatInfo == null) {
            logger.error("imChatInfo is null.");
            return false;
        }
        String sql = generateUpdateImChatInfoSql(imChatInfo);

        try {
            queue.lpush(Constants.ASYNC_DATA_SQL_QUEUE, sql);
            return true;
        } catch (Exception e) {
            logger.error("缓存sql失败：{} sql: {}", e.toString(), sql);
            return false;
        }
    }

    public boolean updatePmChatBehavior(UpdatePmChatBehavior pmChatBehavior) {
        if (pmChatBehavior == null) {
            logger.error("pmChatBehavior is null.");
            return false;
        }
        String sql = generateUpdatePmChatBehaviorSql(pmChatBehavior);

        try {
            queue.lpush(Constants.ASYNC_DATA_SQL_QUEUE, sql);
            return true;
        } catch (Exception e) {
            logger.error("缓存sql失败：{} sql: {}", e.toString(), sql);
            return false;
        }
    }

    private String generateImChatInfoSql(ImChatInfo imChatInfo) {
        String sql = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        StringBuilder fieldSql = new StringBuilder("insert into weixin.im_chat_info(CODE");
        StringBuilder valueSql = new StringBuilder(") values('").append(imChatInfo.getCode()).append("'");

        if (imChatInfo.getMemberNoGm() != null) {
            fieldSql.append(",MEMBER_NO_GM");
            valueSql.append(",'").append(imChatInfo.getMemberNoGm()).append("'");
        }
        if (imChatInfo.getMemberNameGm() != null) {
            fieldSql.append(",MEMBER_NAME_GM");

            String sqlstr = StringEscapeUtils.escapeSql(imChatInfo.getMemberNameGm());
            valueSql.append(",'").append(sqlstr).append("'");
        }
        if (imChatInfo.getNoWxGm() != null) {
            fieldSql.append(",NO_WX_GM");
            valueSql.append(",'").append(imChatInfo.getNoWxGm()).append("'");
        }
        if (imChatInfo.getMemberNo() != null) {
            fieldSql.append(",MEMBER_NO");
            valueSql.append(",'").append(imChatInfo.getMemberNo()).append("'");
        }
        if (imChatInfo.getMemberName() != null) {
            fieldSql.append(",MEMBER_NAME");

            String sqlstr = StringEscapeUtils.escapeSql(imChatInfo.getMemberName());
            valueSql.append(",'").append(sqlstr).append("'");
        }
        if (imChatInfo.getNoWx() != null) {
            fieldSql.append(",NO_WX");
            valueSql.append(",'").append(imChatInfo.getNoWx()).append("'");
        }
        if (imChatInfo.getAlias() != null) {
            fieldSql.append(",ALIAS");

            String sqlstr = StringEscapeUtils.escapeSql(imChatInfo.getAlias());
            valueSql.append(",'").append(sqlstr).append("'");
        }
//        if (imChatInfo.getShopNo() != null) {
//            fieldSql.append(",SHOP_NO");
//            valueSql.append(",'").append(imChatInfo.getShopNo()).append("'");
//        }
//        if (imChatInfo.getShopName() != null) {
//            fieldSql.append(",SHOP_NAME");
//
//            String sqlstr = StringEscapeUtils.escapeSql(imChatInfo.getShopName());
//            valueSql.append(",'").append(sqlstr).append("'");
//        }
        if (imChatInfo.getMerchantNo() != null) {
            fieldSql.append(",MERCHANT_NO");
            valueSql.append(",'").append(imChatInfo.getMerchantNo()).append("'");
        }
        if (imChatInfo.getMerchantName() != null) {
            fieldSql.append(",MERCHANT_NAME");
            String sqlstr = StringEscapeUtils.escapeSql(imChatInfo.getMerchantName());
            valueSql.append(",'").append(sqlstr).append("'");
        }
        if (imChatInfo.getSenderFlag() != null) {
            fieldSql.append(",SENDER_FLAG");
            valueSql.append(",").append(imChatInfo.getSenderFlag());
        }
        if (imChatInfo.getSenderSyncStatus() != null) {
            fieldSql.append(",SENDER_SYNC_STATUS");
            valueSql.append(",").append(imChatInfo.getSenderSyncStatus());
        }
        if (imChatInfo.getType() != null) {
            fieldSql.append(",TYPE");
            valueSql.append(",'").append(imChatInfo.getType()).append("'");
        }
        if (imChatInfo.getStatus() != null) {
            fieldSql.append(",STATUS");
            valueSql.append(",'").append(imChatInfo.getStatus()).append("'");
        }
        if (imChatInfo.getChatTime() != null) {
            fieldSql.append(",CHAT_TIME");

            String chatTime = dateFormat.format(imChatInfo.getChatTime());
            valueSql.append(",'").append(chatTime).append("'");
        }
        if (imChatInfo.getReceivedTime() != null) {
            fieldSql.append(",RECEIVED_TIME");

            String recvTime = dateFormat.format(imChatInfo.getReceivedTime());
            valueSql.append(",'").append(recvTime).append("'");
        }
        if (imChatInfo.getContent() != null) {
            fieldSql.append(",CONTENT");

            String sqlstr = StringEscapeUtils.escapeSql(imChatInfo.getContent());
            valueSql.append(",'").append(sqlstr).append("'");
        }
        if (imChatInfo.getResourcesPath() != null) {
            fieldSql.append(",RESOURCES_PATH");
            String sqlstr = StringEscapeUtils.escapeSql(imChatInfo.getResourcesPath());
            valueSql.append(",'").append(sqlstr).append("'");
        }
        if (imChatInfo.getShareTitle() != null) {
            fieldSql.append(",SHARE_TITLE");

            String sqlstr = StringEscapeUtils.escapeSql(imChatInfo.getShareTitle());
            valueSql.append(",'").append(sqlstr).append("'");
        }
        if (imChatInfo.getShareDes() != null) {
            fieldSql.append(",SHARE_DES");
            valueSql.append(",'").append(imChatInfo.getShareDes()).append("'");
        }
        if (imChatInfo.getShareUrl() != null) {
            fieldSql.append(",SHARE_URL");

            String sqlstr = StringEscapeUtils.escapeSql(imChatInfo.getShareUrl());
            valueSql.append(",'").append(sqlstr).append("'");
        }
        if (imChatInfo.getChatroomType() != null) {
            fieldSql.append(",CHATROOM_TYPE");
            valueSql.append(",").append(imChatInfo.getChatroomType());
        }
        if (imChatInfo.getChatroomNoWx() != null) {
        	fieldSql.append(",CHATROOM_NO_WX");
        	valueSql.append(",'").append(imChatInfo.getChatroomNoWx()).append("'");
        }
        if (imChatInfo.getMsgSource() != null) {
            fieldSql.append(",MSG_SOURCE");
            valueSql.append(",").append(imChatInfo.getMsgSource());
        }
        if (imChatInfo.getImei() != null) {
            fieldSql.append(",IMEI");
            valueSql.append(",'").append(imChatInfo.getImei()).append("'");
        }
        if (imChatInfo.getThirdReadFlag() != null) {
            fieldSql.append(",THIRD_READ_FLAG");
            valueSql.append(",").append(imChatInfo.getThirdReadFlag());
        }
        if (imChatInfo.getErrorCode() != null) {
            fieldSql.append(",ERROR_CODE");
            valueSql.append(",'").append(imChatInfo.getErrorCode()).append("'");
        }
        if (imChatInfo.getErrorMessage() != null) {
            fieldSql.append(",ERROR_MESSAGE");
            valueSql.append(",'").append(imChatInfo.getErrorMessage()).append("'");
        }
        if (imChatInfo.getCreateDate() != null) {
            fieldSql.append(",CREATE_DATE");

            String createDate = dateFormat.format(imChatInfo.getCreateDate());
            valueSql.append(",'").append(createDate).append("'");
        }
        if (imChatInfo.getRemark() != null) {
            fieldSql.append(",REMARK");
            String sqlstr = StringEscapeUtils.escapeSql(imChatInfo.getRemark());
            valueSql.append(",'").append(sqlstr).append("'");
        }
        if (imChatInfo.getRemark2() != null) {
            fieldSql.append(",REMARK2");
            String sqlstr = StringEscapeUtils.escapeSql(imChatInfo.getRemark2());
            valueSql.append(",'").append(sqlstr).append("'");
        }
        if (imChatInfo.getRemark3() != null) {
            fieldSql.append(",REMARK3");
            String sqlstr = StringEscapeUtils.escapeSql(imChatInfo.getRemark3());
            valueSql.append(",'").append(sqlstr).append("'");
        }
        if (imChatInfo.getRemark4() != null) {
            fieldSql.append(",REMARK4");
            String sqlstr = StringEscapeUtils.escapeSql(imChatInfo.getRemark4());
            valueSql.append(",'").append(sqlstr).append("'");
        }
        valueSql.append(");");

        sql = fieldSql.append(valueSql).toString();
        // 对字符‘\’进行转义处理
        // sql = StringUtils.replace(fieldSql.toString(), "\\", "\\\\");

        logger.debug("sql: {}", sql);

        return sql;
    }

    private String generateUpdateImChatInfoSql(ImChatInfo imChatInfo) {
        String sql = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        StringBuilder sqlText = new StringBuilder("update weixin.im_chat_info set ");


        boolean flag = false;
        if (imChatInfo.getMemberNoGm() != null) {
            sqlText.append("MEMBER_NO_GM='").append(imChatInfo.getMemberNoGm()).append("'");
            flag = true;
        }
        if (imChatInfo.getMemberNameGm() != null) {
            if (flag) {
                sqlText.append("MEMBER_NAME_GM='");
            } else {
                flag = true;
                sqlText.append(",MEMBER_NAME_GM='");
            }

            String sqlstr = StringEscapeUtils.escapeSql(imChatInfo.getMemberNameGm());
            sqlText.append(sqlstr).append("'");
        }
        if (imChatInfo.getNoWxGm() != null) {
            if (flag) {
                sqlText.append("NO_WX_GM='");
            } else {
                flag = true;
                sqlText.append(",NO_WX_GM='");
            }

            sqlText.append(imChatInfo.getNoWxGm()).append("'");
        }
        if (imChatInfo.getMemberNo() != null) {
            if (flag) {
                sqlText.append(",MEMBER_NO='");
            } else {
                flag = true;
                sqlText.append("MEMBER_NO='");
            }


            sqlText.append(imChatInfo.getMemberNo()).append("'");
        }
        if (imChatInfo.getMemberName() != null) {
            if (flag) {
                sqlText.append(",MEMBER_NAME='");
            } else {
                flag = true;
                sqlText.append("MEMBER_NAME='");
            }

            String sqlstr = StringEscapeUtils.escapeSql(imChatInfo.getMemberName());
            sqlText.append(sqlstr).append("'");
        }
        if (imChatInfo.getNoWx() != null) {
            if (flag) {
                sqlText.append(",NO_WX='");
            } else {
                flag = true;
                sqlText.append("NO_WX='");
            }

            sqlText.append(imChatInfo.getNoWx()).append("'");
        }
        if (imChatInfo.getAlias() != null) {
            if (flag) {
                sqlText.append(",ALIAS='");
            } else {
                flag = true;
                sqlText.append("ALIAS='");
            }

            String sqlstr = StringEscapeUtils.escapeSql(imChatInfo.getAlias());
            sqlText.append(sqlstr).append("'");
        }
//        if (imChatInfo.getShopNo() != null) {
//            if (flag) {
//                sqlText.append(",SHOP_NO='");
//            } else {
//                flag = true;
//                sqlText.append("SHOP_NO='");
//            }
//
//            sqlText.append(imChatInfo.getShopNo()).append("'");
//        }
//        if (imChatInfo.getShopName() != null) {
//            if (flag) {
//                sqlText.append(",SHOP_NAME='");
//            } else {
//                flag = true;
//                sqlText.append("SHOP_NAME='");
//            }
//
//            String sqlstr = StringEscapeUtils.escapeSql(imChatInfo.getShopName());
//            sqlText.append(sqlstr).append("'");
//        }
        if (imChatInfo.getMerchantNo() != null) {
            if (flag) {
                sqlText.append(",MERCHANT_NO='");
            } else {
                flag = true;
                sqlText.append("MERCHANT_NO='");
            }

            sqlText.append(imChatInfo.getMerchantNo()).append("'");
        }
        if (imChatInfo.getMerchantName() != null) {
            if (flag) {
                sqlText.append(",MERCHANT_NAME='");
            } else {
                flag = true;
                sqlText.append("MERCHANT_NAME='");
            }

            String sqlstr = StringEscapeUtils.escapeSql(imChatInfo.getMerchantName());
            sqlText.append(sqlstr).append("'");
        }
        if (imChatInfo.getSenderFlag() != null) {
            if (flag) {
                sqlText.append(",SENDER_FLAG=");
            } else {
                flag = true;
                sqlText.append("SENDER_FLAG=");
            }

            sqlText.append(imChatInfo.getSenderFlag());
        }
        if (imChatInfo.getSenderSyncStatus() != null) {
            if (flag) {
                sqlText.append(",SENDER_SYNC_STATUS=");
            } else {
                flag = true;
                sqlText.append("SENDER_SYNC_STATUS=");
            }


            sqlText.append(imChatInfo.getSenderSyncStatus());
        }
        if (imChatInfo.getType() != null) {
            if (flag) {
                sqlText.append(",TYPE='");
            } else {
                flag = true;
                sqlText.append("TYPE='");
            }

            sqlText.append(imChatInfo.getType()).append("'");
        }
        if (imChatInfo.getStatus() != null) {
            if (flag) {
                sqlText.append(",STATUS='");
            } else {
                flag = true;
                sqlText.append("STATUS='");
            }

            sqlText.append(imChatInfo.getStatus()).append("'");
        }
        if (imChatInfo.getChatTime() != null) {
            if (flag) {
                sqlText.append(",CHAT_TIME='");
            } else {
                flag = true;
                sqlText.append("CHAT_TIME='");
            }


            String chatTime = dateFormat.format(imChatInfo.getChatTime());
            sqlText.append(chatTime).append("'");
        }
        if (imChatInfo.getReceivedTime() != null) {
            if (flag) {
                sqlText.append(",RECEIVED_TIME='");
            } else {
                flag = true;
                sqlText.append("RECEIVED_TIME='");
            }


            String recvTime = dateFormat.format(imChatInfo.getReceivedTime());
            sqlText.append(recvTime).append("'");
        }
        if (imChatInfo.getContent() != null) {
            if (flag) {
                sqlText.append(",CONTENT='");
            } else {
                flag = true;
                sqlText.append("CONTENT='");
            }


            String sqlstr = StringEscapeUtils.escapeSql(imChatInfo.getContent());
            sqlText.append(sqlstr).append("'");
        }
        if (imChatInfo.getResourcesPath() != null) {
            if (flag) {
                sqlText.append(",RESOURCES_PATH='");
            } else {
                flag = true;
                sqlText.append("RESOURCES_PATH='");
            }

            String sqlstr = StringEscapeUtils.escapeSql(imChatInfo.getResourcesPath());
            sqlText.append(sqlstr).append("'");
        }
        if (imChatInfo.getShareTitle() != null) {
            if (flag) {
                sqlText.append(",SHARE_TITLE='");
            } else {
                flag = true;
                sqlText.append("SHARE_TITLE='");
            }


            String sqlstr = StringEscapeUtils.escapeSql(imChatInfo.getShareTitle());
            sqlText.append(sqlstr).append("'");
        }
        if (imChatInfo.getShareDes() != null) {
            if (flag) {
                sqlText.append(",SHARE_DES='");
            } else {
                flag = true;
                sqlText.append("SHARE_DES='");
            }

            sqlText.append(imChatInfo.getShareDes()).append("'");
        }
        if (imChatInfo.getShareUrl() != null) {
            if (flag) {
                sqlText.append(",SHARE_URL='");
            } else {
                flag = true;
                sqlText.append("SHARE_URL='");
            }


            String sqlstr = StringEscapeUtils.escapeSql(imChatInfo.getShareUrl());
            sqlText.append(sqlstr).append("'");
        }
        if (imChatInfo.getChatroomType() != null) {
        	if (flag) {
        		sqlText.append(",CHATROOM_TYPE=");
        	} else {
        		flag = true;
        		sqlText.append("CHATROOM_TYPE=");
        	}
        	
        	sqlText.append(imChatInfo.getChatroomType());
        }
        if (imChatInfo.getChatroomNoWx() != null) {
        	if (flag) {
        		sqlText.append(",CHATROOM_NO_WX='");
        	} else {
        		flag = true;
        		sqlText.append("CHATROOM_NO_WX='");
        	}
        	
        	sqlText.append(imChatInfo.getChatroomNoWx()).append("'");
        }
        if (imChatInfo.getMsgSource() != null) {
            if (flag) {
                sqlText.append(",MSG_SOURCE=");
            } else {
                flag = true;
                sqlText.append("MSG_SOURCE=");
            }

            sqlText.append(imChatInfo.getMsgSource());
        }
        if (imChatInfo.getImei() != null) {
            if (flag) {
                sqlText.append(",IMEI='");
            } else {
                flag = true;
                sqlText.append("IMEI='");
            }

            sqlText.append(imChatInfo.getImei()).append("'");
        }
        if (imChatInfo.getThirdReadFlag() != null) {
            if (flag) {
                sqlText.append(",THIRD_READ_FLAG=");
            } else {
                flag = true;
                sqlText.append("THIRD_READ_FLAG=");
            }

            sqlText.append(imChatInfo.getThirdReadFlag());
        }
        if (imChatInfo.getErrorCode() != null) {
            if (flag) {
                sqlText.append(",ERROR_CODE='");
            } else {
                flag = true;
                sqlText.append("ERROR_CODE='");
            }

            sqlText.append(imChatInfo.getErrorCode()).append("'");
        }
        if (imChatInfo.getErrorMessage() != null) {
            if (flag) {
                sqlText.append(",ERROR_MESSAGE='");
            } else {
                flag = true;
                sqlText.append("ERROR_MESSAGE='");
            }

            sqlText.append(imChatInfo.getErrorMessage()).append("'");
        }
        if (imChatInfo.getCreateDate() != null) {
            if (flag) {
                sqlText.append(",CREATE_DATE='");
            } else {
                flag = true;
                sqlText.append("CREATE_DATE='");
            }


            String createDate = dateFormat.format(imChatInfo.getCreateDate());
            sqlText.append(createDate).append("'");
        }
        if (imChatInfo.getRemark() != null) {
            if (flag) {
                sqlText.append(",REMARK='");
            } else {
                flag = true;
                sqlText.append("REMARK='");
            }

            String sqlstr = StringEscapeUtils.escapeSql(imChatInfo.getRemark());
            sqlText.append(sqlstr).append("'");
        }
        if (imChatInfo.getRemark2() != null) {
            if (flag) {
                sqlText.append(",REMARK2='");
            } else {
                flag = true;
                sqlText.append("REMARK2='");
            }

            String sqlstr = StringEscapeUtils.escapeSql(imChatInfo.getRemark2());
            sqlText.append(sqlstr).append("'");
        }
        if (imChatInfo.getRemark3() != null) {
            if (flag) {
                sqlText.append(",REMARK3='");
            } else {
                flag = true;
                sqlText.append("REMARK3='");
            }

            String sqlstr = StringEscapeUtils.escapeSql(imChatInfo.getRemark3());
            sqlText.append(sqlstr).append("'");
        }
        if (imChatInfo.getRemark4() != null) {
            if (flag) {
                sqlText.append(",REMARK4='");
            } else {
                flag = true;
                sqlText.append("REMARK4='");
            }

            String sqlstr = StringEscapeUtils.escapeSql(imChatInfo.getRemark4());
            sqlText.append(sqlstr).append("'");
        }
        sqlText.append(" where CODE='").append(imChatInfo.getCode()).append("'");

        // 对字符‘\’进行转义处理
        // sql = StringUtils.replace(sqlText.toString(), "\\", "\\\\");
        sql = sqlText.toString();

        logger.debug("sql: {}", sql);

        return sql;
    }

    private String generateUpdatePmChatBehaviorSql(UpdatePmChatBehavior pmChatBehavior) {
        String sql = null;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String chatTime = null;

        StringBuilder fieldSql = new StringBuilder("INSERT into member.pm_chat_behavior(");
        StringBuilder valueSql = new StringBuilder(") VALUES (");
        StringBuilder updateSql = new StringBuilder(" ON DUPLICATE KEY ");

        if (pmChatBehavior.getMemberNo() != null) {
            fieldSql.append("MEMBER_NO");
            valueSql.append("'").append(pmChatBehavior.getMemberNo()).append("'");
        }
        if (pmChatBehavior.getMemberNoGm() != null) {
            fieldSql.append(",MEMBER_NO_GM");
            valueSql.append(",'").append(pmChatBehavior.getMemberNoGm()).append("'");
        }
        if (pmChatBehavior.getChatTime() != null) {
            fieldSql.append(",CHAT_TIME");
            chatTime = dateFormat.format(pmChatBehavior.getChatTime());
            valueSql.append(",'").append(chatTime).append("'");
            updateSql.append(" UPDATE CHAT_TIME='").append(chatTime).append("'");
        }
        if (pmChatBehavior.getThirdUnreadFlag() != null) {
            fieldSql.append(",THIRD_UNREAD_FLAG");
            valueSql.append(",").append(pmChatBehavior.getThirdUnreadFlag());
            updateSql.append(",THIRD_UNREAD_FLAG=").append(pmChatBehavior.getThirdUnreadFlag());
        }

        valueSql.append(")");

        // sql = "INSERT into member.pm_chat_behavior(MEMBER_NO,MEMBER_NO_GM,CHAT_TIME,THIRD_UNREAD_FLAG) "
        //         + "VALUES ('" + pmChatBehavior.getMemberNo() + "','"
        //         + pmChatBehavior.getMemberNoGm() + "','"
        //         + chatTime + "',"
        //         + pmChatBehavior.getThirdUnreadFlag() + ") "
        //         + " ON DUPLICATE KEY UPDATE CHAT_TIME='" + chatTime
        //         + "',THIRD_UNREAD_FLAG=" + pmChatBehavior.getThirdUnreadFlag();

        sql = fieldSql.append(valueSql).append(updateSql).toString();

        logger.debug("sql: {}", sql);

        return sql;
    }
}
