package com.adapit.portal.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="Publications")
public class Publication extends Paper implements Serializable{

	private static final long serialVersionUID = 976357436834688643L;
	
	@Column(nullable=false,length=5000)
	private String location;
	
	@Enumerated(EnumType.ORDINAL)
	private Qualis qualis=Qualis.A;	
	public enum Qualis{A,B,C,D,NONE};
	
	@Enumerated(EnumType.ORDINAL)
	private PublicationKind publicationKind = PublicationKind.Full_Paper;	
	public enum PublicationKind{Journal,Full_Paper,Short_Paper,Abstract,Poster,Magazine,Invited_Speaker,Presentation,None};
	
	@Enumerated(EnumType.ORDINAL)
	private Midia midia = Midia.Conference;	
	public enum Midia{Press,Workshop,Simposium,Congress,Conference,Academic,White_Paper,Presentation};
	
	@Enumerated(EnumType.ORDINAL)
	private Classification classification = Classification.National;
	public enum Classification{National,International};
	
	

	/**
	 * 
	 * @spring.validator arg0resource="publication.titulo" type="required"
	 */
	public void setTitulo(String titulo) {
		super.setTitulo(titulo);
	}


	public Publication() {
		super();
	}

	/**
	 * 
	 * @spring.validator arg0resource="publication.descricao" type="required"
	 */
	public void setDescricao(String descricao ){
		super.setDescricao(descricao);
	}
	
	/**
	 * 
	 * @spring.validator arg0resource="news.dataPublicacao" type="required"
	 */
	public void setDataPublicacao(Date dataPublicacao) {
		super.setDataPublicacao(dataPublicacao);
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public Qualis getQualis() {
		return qualis;
	}


	public void setQualis(Qualis qualis) {
		this.qualis = qualis;
	}


	public PublicationKind getPublicationKind() {
		return publicationKind;
	}


	public void setPublicationKind(PublicationKind publicationKind) {
		this.publicationKind = publicationKind;
	}


	public Midia getMidia() {
		return midia;
	}


	public void setMidia(Midia midia) {
		this.midia = midia;
	}


	public Classification getClassification() {
		return classification;
	}


	public void setClassification(Classification classification) {
		this.classification = classification;
	}


	
}
