
package com.lj.business.supcon.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 
 * 类说明：微信数据XML解析类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年8月2日
 */
public class WeixinXMLParser {

    public static final String REGEX_SHARE_TYPE = "<type>(.*?)</type>";
    public static final String REGEX_SHARE_URL = "<url>(.*?)</url>";
    public static final String REGEX_SHARE_TITLE = "<title>(.*?)</title>";
    public static final String REGEX_SHARE_DES = "<des>(.*?)</des>";
    public static final String REGEX_APP_MSG_DES = "<des>(.*?)</des>";
    public static final String REGEX_SHARE_THUMB_URL = "<thumburl>(.*?)</thumburl>";
    public static final String REGEX_POSITION_X = "x\\s*=\\s*\"(.*?)\"";
    public static final String REGEX_POSITION_Y = "y\\s*=\\s*\"(.*?)\"";
    public static final String REGEX_POSITION_LABEL = "label\\s*=\\s*\"(.*?)\"";
    public static final String REGEX_POSITION_POINAME = "poiname\\s*=\\s*\"(.*?)\"";
    public static final String REGEX_POSITION_SCALE = "scale=\"(.*?)\"";

    public static final String REGEX_FORMUSERNAME = "fromusername\\s*=\\s*\"(.*?)\"";
    public static final String REGEX_FORMNICKNAME = "fromnickname\\s*=\\s*\"(.*?)\"";
    public static final String REGEX_FORMSEX = "sex\\s*=\\s*\"(.*?)\"";
    public static final String REGEX_FORMALIAS = "alias\\s*=\\s*\"(.*?)\"";
    public static final String REGEX_FORMBIGURL = "bigheadimgurl\\s*=\\s*\"(.*?)\"";
    public static final String REGEX_FORMSMALLURL = "smallheadimgurl\\s*=\\s*\"(.*?)\"";
    public static final String REGEX_FORMSIGN = "sign\\s*=\\s*\"(.*?)\"";
    public static final String REGEX_TICKET = "ticket\\s*=\\s*\"(.*?)\"";
    public static final String REGEX_CONTENT = "content\\s*=\\s*\"(.*?)\"";
    public static final String REGEX_FULLPY = "fullpy\\s*=\\s*\"(.*?)\"";
    public static final String REGEX_SHORTPY = "shortpy\\s*=\\s*\"(.*?)\"";
    public static final String REGEX_COUNTRY = "country\\s*=\\s*\"(.*?)\"";
    public static final String REGEX_PROVINCE = "province\\s*=\\s*\"(.*?)\"";
    public static final String REGEX_CITY = "city\\s*=\\s*\"(.*?)\"";
    public static final String REGEX_OPCODE = "opcode\\s*=\\s*\"(.*?)\"";

    public static final String REGEX_VOIP = "<voipinvitemsg><roomid>(.*?)</roomid><key>(.*?)</key><status>(.*?)</status><invitetype>(.*?)</invitetype></voipinvitemsg>";
    public static final String REGEX_VOIP_STATUS = "<status>(.*?)</status>";
    public static final String REGEX_VOIP_TYPE = "<invitetype>(.*?)</invitetype>";


    public static final String REGEX_ID = "<id><!\\[CDATA\\[(.+?)\\]\\]></id>";
    public static final String REGEX_USERNAME = "<username><!\\[CDATA\\[(.+?)\\]\\]></username>";
    public static final String REGEX_CONTENTDES = "<contentDesc><!\\[CDATA\\[([\\s\\S]+?)\\]\\]></contentDesc>";
    public static final String REGEX_MEDIA = "<media>([\\s\\S]+?)<url.*?><!\\[CDATA\\[(.+?)\\]\\]></url>.*?</media>";
    public static final String REGEX_MEDIA_URL = "<media>.*?<url.*?><!\\[CDATA\\[(.+?)\\]\\]></url>.*?</media>";
    public static final String REGEX_CREATETIME = "<createTime><!\\[CDATA\\[(.+?)\\]\\]></createTime>";
    // 歌曲分享
    public static final String REGEX_CONTENT_URL = "<contentUrl><!\\[CDATA\\[(.+?)\\]\\]></contentUrl>";
    public static final String REGEX_THUMB = "<media>.*?<thumb.*?><!\\[CDATA\\[(.+?)\\]\\]></thumb>.*?</media>";
    public static final String REGEX_THUMB_URL = "<media>([\\s\\S]+?)<thumb.*?><!\\[CDATA\\[(.+?)\\]\\]></thumb>.*?</media>";
    public static final String REGEX_TITLE= "<title><!\\[CDATA\\[(.+?)\\]\\]></title>";
    public static final String REGEX_DES = "<description><!\\[CDATA\\[(.+?)\\]\\]></description>";
    // 添加好友不成功，不是
    public static final String REGEX_ADD_FRIEND= "<Content><!\\[CDATA\\[(.+?)\\]\\]></Content>";

    //下载图片
    public static final String REGEX_AESKEY = "aeskey\\s*=\\s*\"(.*?)\"";
    public static final String REGEX_ENCKEY = "<url.*?key=\"(.*?)\"";
    //hd
    public static final String REGEX_HDLENGTH = "hdlength\\s*=\\s*\"(.*?)\"";
    public static final String REGEX_CDNBIGIMGURL = "cdnbigimgurl\\s*=\\s*\"(.*?)\"";
    //mid
    public static final String REGEX_LENGTH = "\\s+length\\s*=\\s*\"(.*?)\"";
    public static final String REGEX_CDNMIDIMGURL = "cdnmidimgurl\\s*=\\s*\"(.*?)\"";
    //thumb
    public static final String REGEX_CDNTHUMBLENGTH = "cdnthumblength\\s*=\\s*\"(.*?)\"";
    public static final String REGEX_CDNTHUMBURL = "cdnthumburl\\s*=\\s*\"(.*?)\"";

    //小程序分享
    public static final String REGEX_WE_APP_INFO = "<weappinfo>([\\W\\w]*?)</weappinfo>";
    public static final String REGEX_APP_MSG_APP_ID = "<appid>(.*?)</appid>";
    public static final String REGEX_APP_MSG_USER_NAME = "<username>(.*?)</username>";
    public static final String REGEX_APP_MSG_ICON_URL= "<weappiconurl><!\\[CDATA\\[(.+?)\\]\\]></weappiconurl>";
    public static final String REGEX_APP_MSG_TYPE = "<type>(.*?)</type>";
    public static final String REGEX_APP_MSG_PAGE_PATH = "<pagepath><!\\[CDATA\\[(.+?)\\]\\]></pagepath>";
    public static final String REGEX_APP_MSG_VERSION = "<version>(.*?)</version>";
    public static final String REGEX_APP_MSG_SERVICE_TYPE = "<appservicetype>(.*?)</appservicetype>";
    public static final String REGEX_APP_MSG_DISPLAY_NAME = "<sourcedisplayname>(.*?)</sourcedisplayname>";

    // 微信名片
    public static final String REGEX_CARD_CERTFLAG = "certflag\\s*=\\s*\"(.*?)\"";	// 名片类型：0个人名片、8公众号
    public static final String REGEX_CARD_USERNAME = "username\\s*=\\s*\"(.*?)\"";
    public static final String REGEX_CARD_ANTISPAMTICKET = "antispamticket\\s*=\\s*\"(.*?)\"";
    public static final String REGEX_CARD_ALIAS = "alias\\s*=\\s*\"(.*?)\"";
    public static final String REGEX_CARD_NICKNAME = "nickname\\s*=\\s*\"(.*?)\"";
    public static final String REGEX_CARD_BRANDICONURL = "brandIconUrl\\s*=\\s*\"(.*?)\"";
    public static final String REGEX_CARD_SMALLHEADIMGURL = "smallheadimgurl\\s*=\\s*\"(.*?)\"";
    public static final String REGEX_CARD_BIGHEADIMGURL = "bigheadimgurl\\s*=\\s*\"(.*?)\"";
    public static final String REGEX_CARD_SIGN = "sign\\s*=\\s*\"(.*?)\"";
    public static final String REGEX_CARD_CERTINFO = "certinfo\\s*=\\s*\"(.*?)\"";
    
    public static String getPropertyValue(String xmlResult, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(xmlResult);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "";
        }
    }

    public static String getPropertyValue2(String xmlResult, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(xmlResult);
        if (matcher.find()) {
            return matcher.group(2);
        } else {
            return "";
        }
    }
}
