package cn.qingmu.pojo;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * admin实体类
 * @author zm
 * @date 2020-9-3 10:28:0
 *
 */
public class Admin implements Serializable{

	@Id
	private String id;//ID


	
	private String loginname;//登陆名称
	private String password;//密码
	private String state;//状态

	
	public String getId() {		
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getLoginname() {		
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
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
