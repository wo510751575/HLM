package com.lj.business.api.utils;

import java.beans.PropertyEditorSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * 类说明：SPRING WEB入参转化工具类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭阳
 * 
 * CreateDate: 2017-7-11
 */
public class ApproveMemberInfoEditor extends PropertyEditorSupport {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(ApproveMemberInfoEditor.class);  
	  
  
    
    /** The allow empty. */
    private boolean allowEmpty = true;  
  
    /**
     * The Constructor.
     */
    public ApproveMemberInfoEditor() {  
    }  
  
  
    /**
     * Parse the Date from the given text, using the specified DateFormat.
     *
     * @param text the as text
     * @throws IllegalArgumentException the illegal argument exception
     */  
    @Override  
    public void setAsText(String text) throws IllegalArgumentException {
		logger.debug("setAsText(String text={}) - start", text); //$NON-NLS-1$
        if (this.allowEmpty && !StringUtils.hasText(text)) {  
            // Treat empty String as null value.  
            setValue(null);  
        } else {
        	//setValue(JsonUtils.objectFromJson(text, ApproveMemberInfo.class));
        }  

    }  
}  
