package com.lj.business.cm.dto.wordsInfo;

import java.io.Serializable;
import java.util.List;

import com.lj.business.cm.dto.wordsType.FindWordsTypeSelectReturn;

public class FindWordsInfoWebReturn implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 883241044308399512L;

	/**
	 * 话术信息集合
	 */
	private List<FindWordsInfoReturn> infoList;
	
    /**
     * 话术类型集合
     */
    private List<FindWordsTypeSelectReturn> typeList;

	public List<FindWordsInfoReturn> getInfoList() {
		return infoList;
	}

	public void setInfoList(List<FindWordsInfoReturn> infoList) {
		this.infoList = infoList;
	}

	public List<FindWordsTypeSelectReturn> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<FindWordsTypeSelectReturn> typeList) {
		this.typeList = typeList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindWordsInfoWebReturn [infoList=");
		builder.append(infoList);
		builder.append(", typeList=");
		builder.append(typeList);
		builder.append("]");
		return builder.toString();
	}

}
