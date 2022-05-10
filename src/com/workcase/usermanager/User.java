package com.workcase.usermanager;

public interface User {

	public void setId(int id);

	public int getId();

	public void setUsername(java.lang.String username);

	public java.lang.String getUsername();

	public void setPassword(java.lang.String password);

	public java.lang.String getPassword();

	public void setActive(boolean active);

	public boolean getActive();

	public void setRole(Role role);

	public Role getRole();

}