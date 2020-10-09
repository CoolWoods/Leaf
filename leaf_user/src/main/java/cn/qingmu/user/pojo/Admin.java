package cn.qingmu.user.pojo;


import java.io.Serializable;

/**
 * admin实体类
 * @author Zongmin
 * @date 2020-9-3 10:28:0
 *
 */
public class Admin implements Serializable{

	private String id;//ID
	
	private String loginName;//登陆名称
	private String password;//密码
	private String state;//状态

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
