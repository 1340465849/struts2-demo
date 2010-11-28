package com.bulain.common.page;

import java.io.Serializable;

public class OrderBy implements Serializable{
	private static final long serialVersionUID = -9036210048502351354L;
	
	private String orderBy;
	private String sequance;
	
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
}
