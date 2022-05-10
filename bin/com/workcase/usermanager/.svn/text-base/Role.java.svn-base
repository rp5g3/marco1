package com.workcase.usermanager;


import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Role implements Serializable{//Attributes code
	
	private int id;
	
	private java.lang.String name;
	
	private java.lang.String description;
//Associations code
//Operations code

	
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
*	@spring.validator type="required"  arg0resource="role.name" 
*/
	public void setName(java.lang.String name ){
		this.name=name;
	}

	
	@Column(nullable=false)
	public java.lang.String getName(){
		return this.name;
	}

	

/**
*
*	@spring.validator  arg0resource="role.description" type="required"
*/
	public void setDescription(java.lang.String description ){
		this.description=description;
	}

	
	@Column(nullable=true)
	public java.lang.String getDescription(){
		return this.description;
	}


}