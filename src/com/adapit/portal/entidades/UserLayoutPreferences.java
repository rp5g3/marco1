package com.adapit.portal.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="UserLayoutPreferences")
public class UserLayoutPreferences implements Serializable{

	private static final long serialVersionUID = 24627534834683L;
	
	@Column(nullable = false, length = 20,name="user_login")
	@Id
	private String login;
	
	private String lookAndFeelClassName;

	

	public String getLookAndFeelClassName() {
		return lookAndFeelClassName;
	}

	public void setLookAndFeelClassName(String lookAndFeel) {
		this.lookAndFeelClassName = lookAndFeel;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
}
