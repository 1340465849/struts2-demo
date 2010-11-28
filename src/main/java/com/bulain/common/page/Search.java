package com.bulain.common.page;

import java.io.Serializable;

public abstract class Search implements Serializable{
	private static final long serialVersionUID = -6231534415228625176L;
	
	//page
	private long low;
	private long high;
	
	//order
	private String orderBy;
	private String sequance;
	
	public long getPageSize(){
		return high - low;
	}
	
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getSequance() {
		return sequance;
	}
	public void setSequance(String sequance) {
		this.sequance = sequance;
	}
	public long getHigh() {
		return high;
	}
	public void setHigh(long high) {
		this.high = high;
	}
	public long getLow() {
		return low;
	}
	public void setLow(long low) {
		this.low = low;
	}
}
