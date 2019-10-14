package com.lj.business.st.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class FindClientExpReturn implements Serializable {

	 /**
	     * Generate cron.
	     *
	     * @param
	     * @param
	     * @throws 
	     */
	private static final long serialVersionUID = 1L;
	
/**
 * 新增数量
 */
  private Integer numAdd;
  /**
   * 统计时间
   */
  private String stDate;
  public Integer getNumAdd() {
	return numAdd;
 }
  public void setNumAdd(Integer numAdd) {
	this.numAdd = numAdd;
 }
  public String getStDate() {
	return stDate;
 }
  public void setStDate(String stDate) {
	this.stDate = stDate;
 }
@Override
public String toString() {
	return "findClientExpReturn [numAdd=" + numAdd + ", stDate=" + stDate + "]";
}
 
  
  
}
