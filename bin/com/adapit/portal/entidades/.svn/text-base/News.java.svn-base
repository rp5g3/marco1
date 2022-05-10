package com.adapit.portal.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@SequenceGenerator(sequenceName="NewsSeq",
			name="news_generator",initialValue=1,allocationSize=1)
@Table(name="News")
public class News implements Serializable{
	
	private static final long serialVersionUID = 21349888384969L;
	
	@Id	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="news_generator")
	private int id;
	
	@Column(nullable=false,length=20000,name="news_content")
	private String descricao;	
	
	@Column(name="creation_date")
	private Date dataCriacao;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false,name="publication_date")
	private Date dataPublicacao;
	
	@OrderBy(value="indice")
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="NEWS_IMAGE"
			,joinColumns={@JoinColumn(name="NEWS_ID")}
			,inverseJoinColumns={@JoinColumn(name="IMAGE_ID")}
	)
	private List<Imagem> imagens = new ArrayList<Imagem>();
	
	@Column(nullable=false,length=250,name="news_title")
	private String titulo;
	
	@Column(nullable=false,length=1500,name="news_abstract")
	private String resumo;

	
	/**
	 * @return the titulo
	 */

	public String getTitulo() {
		return titulo;
	}


	/**
	 * 
	 * @spring.validator arg0resource="news.titulo" type="required"
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	/**
	 * @return the resumo
	 */
	public String getResumo() {
		return resumo;
	}


	/**
	 * @param resumo the resumo to set
	 */
	public void setResumo(String resumo) {
		this.resumo = resumo;
	}


	public News() {
		super();
		setDataCriacao(new Date());
	}


	
	
	public void setId(int id ){
		this.id=id;
	}
	

	public int getId(){
		return this.id;
	}
	/**
	 * 
	 * @spring.validator arg0resource="news.descricao" type="required"
	 */
	public void setDescricao(String descricao ){
		this.descricao=descricao;
	}
	

	public String getDescricao(){
		return this.descricao;
	}
	

	public Date getDataCriacao() {
		return dataCriacao;
	}


	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	
	@Transient
	public String getFormatedDescricao() {
		String str = "";
		String title = getTitulo();
		try {
			String content = getDescricao();

			if (title != null && !title.equals("")) {
				str += title
						+ "</b></td></tr>";
			}
			if (content != null && !content.equals("")){
				String dados[] = content.split("\n");
				for (int j = 0; j < dados.length; j++) {
					str += "<tr>";
					String dado = dados[j];
					String attval[] = dado.split(":");
					if (attval.length == 2) {
						String att = attval[0];
						str += "<td><b>" + att + ":</b></td>";
						String val = attval[1];
						str += "<td>" + val + "</td>";
					}
					str += "</tr>";
				}
			}
			
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

		return str;
	}

	

	public List<Imagem> getImagens() {
		return imagens;
	}


	public void setImagens(List<Imagem> imagens) {
		this.imagens = imagens;
	}


	/**
	 * @return the dataPublicacao
	 */

	public Date getDataPublicacao() {
		return dataPublicacao;
	}



	/**
	 * 
	 * @spring.validator arg0resource="news.dataPublicacao" type="required"
	 */
	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}


	
}
