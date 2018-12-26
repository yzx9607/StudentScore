package com.score.bean;

import org.springframework.stereotype.Component;

/**
 * 用户实体类
 * @author hmg
 *
 */
@Component
public class User {
	/*用户名称*/
	private int userId;
	/*用户登录名称*/
	private String userName;
	/* 用户登录密码 */
	private String password;
	
	private String type;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
