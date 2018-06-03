package org.softcits.auth.model;

import java.util.Date;

public class UserAndRole {
  
    private Integer id;

  
    private String username;

  
    private String passwd;

  
    private Date createTime;

   
    private String state;

  
    private Integer roleId;

    private String rolename;
  
    public Integer getId() {
        return id;
    }

 
    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

   
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

  
    public String getPasswd() {
        return passwd;
    }

  
    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

   
    public Date getCreateTime() {
        return createTime;
    }

 
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

  
    public String getState() {
        return state;
    }

   
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Integer getRoleId() {
        return roleId;
    }

   
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }


	public String getRolename() {
		return rolename;
	}


	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
    
    
}