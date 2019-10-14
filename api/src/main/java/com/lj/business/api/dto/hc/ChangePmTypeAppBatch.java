package com.lj.business.api.dto.hc;

import java.io.Serializable;
import java.util.Arrays;

import com.lj.business.member.dto.ChangePmTypeHc;

/**
 * The Class ChangePmTypeAppBatch.
 */
public class ChangePmTypeAppBatch implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1176533895081324911L;
	
	/** 更改分组LIST. */
	private ChangePmTypeHc [] changePmTypeHcList;

	/**
	 * Gets the 更改分组LIST.
	 *
	 * @return the 更改分组LIST
	 */
	public ChangePmTypeHc[] getChangePmTypeHcList() {
		return changePmTypeHcList;
	}

	/**
	 * Sets the 更改分组LIST.
	 *
	 * @param changePmTypeHcList the new 更改分组LIST
	 */
	public void setChangePmTypeHcList(ChangePmTypeHc[] changePmTypeHcList) {
		this.changePmTypeHcList = changePmTypeHcList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChangePmTypeAppBatch [changePmTypeHcList=")
				.append(Arrays.toString(changePmTypeHcList)).append("]");
		return builder.toString();
	}
	
	
	

}
