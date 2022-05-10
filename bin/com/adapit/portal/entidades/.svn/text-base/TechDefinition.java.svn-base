package com.adapit.portal.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@SequenceGenerator(sequenceName="TechDefinitionSeq",
			name="TechDefinition_generator",initialValue=1,allocationSize=1)
@Table(name="TechDefinition")
public class TechDefinition  implements Serializable{

	private static final long serialVersionUID = 3000242810591256935L;

	@Id	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TechDefinition_generator")
	private int id;
	
	@Column(nullable=false,length=20000,name="content")
	private String content;
	
	@Column(nullable=false,length=200,name="keywords")
	private String keywords;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	
}
