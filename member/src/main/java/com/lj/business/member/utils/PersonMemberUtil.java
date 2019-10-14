package com.lj.business.member.utils;

import com.lj.base.core.util.StringUtils;
import com.lj.business.member.dto.AddPersonMemberByWx;

public final class PersonMemberUtil {
    private PersonMemberUtil() {
        
    }
    
    public static String getMemberName(AddPersonMemberByWx addPersonMemberByWx, String mn) {
        if(StringUtils.isNotEmpty(addPersonMemberByWx.getMemberName())) {
            mn = addPersonMemberByWx.getMemberName();
        } else if (!StringUtils.isEmpty(addPersonMemberByWx.getNickNameRemarkWx())) {
            mn = addPersonMemberByWx.getNickNameRemarkWx();
        } else if (!StringUtils.isEmpty(addPersonMemberByWx.getNickNameWx())) {
            mn = addPersonMemberByWx.getNickNameWx();
        } else if(StringUtils.isNotEmpty(addPersonMemberByWx.getAlias())) { // 微信昵称、备注等都为空的情况，优先使用微信别名alias，再使用username
            mn = addPersonMemberByWx.getAlias();
        } else if(StringUtils.isNotEmpty(addPersonMemberByWx.getNoWx())) {  // 微信昵称、备注等都为空的情况，优先使用微信别名alias，再使用username
            mn = addPersonMemberByWx.getNoWx();
        }
        return mn;
    }
}
