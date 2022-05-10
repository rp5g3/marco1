package com.adapit.portal.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Inheritance(strategy=InheritanceType.JOINED)
@SequenceGenerator(name="CssDef_Gen",allocationSize=1,initialValue=1,sequenceName="CssDefSeq")
@Entity
public class CssDefinition implements Serializable{

	private static final long serialVersionUID = 1465940360064135451L;

	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CssDef_Gen")
	@Id
	private int id;
	
	@OneToMany(mappedBy="cssDefinition",fetch=FetchType.LAZY)
	private List<ComercialSolution> solutions;
	
	private String name;
	
	@Column(length=3000)
	private String style;
	
	@OneToOne
	private Imagem imageSample;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<ComercialSolution> getSolutions() {
		return solutions;
	}

	public void setSolutions(List<ComercialSolution> solutions) {
		this.solutions = solutions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public Imagem getImageSample() {
		return imageSample;
	}

	public void setImageSample(Imagem imageSample) {
		this.imageSample = imageSample;
	}
}
