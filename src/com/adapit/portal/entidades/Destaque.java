package com.adapit.portal.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="Highlight")
public class Destaque  implements Serializable {

	private static final long serialVersionUID = 37665356767628L;
	
	@Id
	private int id;
	
	@OneToOne
	private News news;
	
	@OneToOne
	private SoftwareSolution software;
	
	@OneToOne
	private Publication publication;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public SoftwareSolution getSoftware() {
		return software;
	}

	public void setSoftware(SoftwareSolution software) {
		this.software = software;
	}

	public Publication getPublication() {
		return publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}
	


}
