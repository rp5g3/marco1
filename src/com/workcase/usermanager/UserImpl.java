package com.workcase.usermanager;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class UserImpl implements User, Serializable{//Attributes code
	
	private int id;
	
	private java.lang.String username;
	
	private java.lang.String password;
	
	private boolean active;

	
	protected Role role;
//Operations code

	

	
	public void setRole(Role role ){
		this.role=role;
	}

	
	@Basic(fetch=FetchType.LAZY)
	@OneToMany (targetEntity=Role.class
	,cascade={CascadeType.ALL})
	public Role getRole(){
		return this.role;
	}

	
	public void setId(int id ){
		this.id=id;
	}

	
	@GeneratedValue
	@Id
	public int getId(){
		return this.id;
	}

	

/**
*
*	@spring.validator type="required"  arg0resource="userImpl.username" 
*/
	public void setUsername(java.lang.String username ){
		this.username=username;
	}

	
	@Column(nullable=false)
	public java.lang.String getUsername(){
		return this.username;
	}

	

/**
*
*	@spring.validator type="required"  arg0resource="userImpl.password" 
*/
	public void setPassword(java.lang.String password ){
		this.password=password;
	}

	
	@Column(nullable=false)
	public java.lang.String getPassword(){
		return this.password;
	}

	
	public void setActive(boolean active ){
		this.active=active;
	}

	
	public boolean getActive(){
		return this.active;
	}


}