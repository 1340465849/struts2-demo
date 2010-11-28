package com.bulain.common.model;

import java.io.Serializable;
import java.util.Date;

public interface Entity extends Serializable{
	String getCreatedBy();
	void setCreatedBy(String createdBy);
	Date getCreatedAt();
	void setCreatedAt(Date createdAt);
	String getUpdatedBy();
	void setUpdatedBy(String updatedBy);
	Date getUpdatedAt();
	void setUpdatedAt(Date updatedAt);
}
