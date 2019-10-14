package com.lj.business.api.dto.hc;

import java.io.Serializable;
import java.util.Arrays;

import com.lj.business.member.dto.ChangePmType;

/**
 * 批量分组参数bean
 * 
 * @author wo510
 *
 */
public class ChangePmTypeBatch implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1176533895081324911L;
	
	/** 更改分组LIST. */
	private ChangePmType [] changePmTypes;

	public ChangePmType[] getChangePmTypes() {
		return changePmTypes;
	}

	public void setChangePmTypes(ChangePmType[] changePmTypes) {
		this.changePmTypes = changePmTypes;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChangePmTypeBatch [changePmTypes=");
		builder.append(Arrays.toString(changePmTypes));
		builder.append("]");
		return builder.toString();
	}

}
