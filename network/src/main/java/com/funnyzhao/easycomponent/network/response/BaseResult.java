package com.funnyzhao.easycomponent.network.response;

/**
 * Created by zhaowenjie on 2018/3/21.
 *
 * @desc
 */

public class BaseResult {
	//user
	private String userId;
	private String token;
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
}
