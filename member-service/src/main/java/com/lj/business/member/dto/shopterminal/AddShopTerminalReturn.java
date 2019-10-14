package com.lj.business.member.dto.shopterminal;

import java.io.Serializable;

/**
 * @author wo510
 *
 */
public class AddShopTerminalReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8526197901775093871L; 
	/**
     * CODE .
     */
    private String code;

    /**
     * 终端编码 .
     */
    private String terminalCode;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTerminalCode() {
		return terminalCode;
	}

	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddShopTerminalReturn [code=");
		builder.append(code);
		builder.append(", terminalCode=");
		builder.append(terminalCode);
		builder.append("]");
		return builder.toString();
	}
    
}
