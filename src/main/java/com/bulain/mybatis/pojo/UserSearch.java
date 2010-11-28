package com.bulain.mybatis.pojo;

import com.bulain.common.page.Search;

public class UserSearch extends Search{
	private static final long serialVersionUID = -2268325333141949123L;
	
	private String firstName;
    private String lastName;
    
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}