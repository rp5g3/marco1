package com.adapit.portal.entidades;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.swing.Icon;
import javax.swing.ImageIcon;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="SupplierPerson")
public class Fornecedor extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1235213325627L;

	private TipoFornecedor tipo;

	private File logotipo;

	private String descricao;

	private Collection<Projeto> projetos = new ArrayList<Projeto>();

	public void setProjetos(Collection<Projeto> projs) {
		this.projetos = projs;
	}

	@OrderBy(value = "codigoProjeto")
	@OneToMany(targetEntity = Projeto.class, mappedBy = "fornecedor", cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	public Collection<Projeto> getProjetos() {
		return this.projetos;
	}

	
	/**
	 * 
	 * @spring.validator arg0resource="pessoaEmDivulgacao.tipoComitente" type="required"
	 */
	public void setTipo(TipoFornecedor tipoComitente) {
		this.tipo = tipoComitente;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	public TipoFornecedor getTipo() {
		return this.tipo;
	}

	public void setLogotipo(File logotipo) {
		this.logotipo = logotipo;
	}

	
	@Basic(fetch = FetchType.LAZY)
	public File getLogotipo() {
		return this.logotipo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	/**
	 * 
	 * @spring.validator arg0resource="pessoaEmDivulgacao.nome" maxlength="100"
	 *                   minlength="2" type="required"
	 */
	public void setNome(String nome) {
		super.setNome(nome);
	}
	
	/**
	 * 
	 * @spring.validator arg0resource="pessoaEmDivulgacao.email" type="required,email"
	 */
	public void setEmail(String email) {
		super.setEmail(email);
	}
	
	/**
	 * 
	 * @spring.validator arg0resource="pessoaEmDivulgacao.telefone" maxlength="20"
	 *                   minlength="10" type="required"
	 */
	public void setTelefone(String telefone) {
		super.setTelefone(telefone);
	}
	
	@Transient
	private Icon bigIcon,smallIcon;
	
	/*@Transient
	public Icon getBigIcon(boolean update) {
		try {
			if (logotipo != null){				
				if (update || bigIcon == null)	
				try {
					java.awt.Image image = new javax.swing.ImageIcon(getImageBytes(logotipo)).getImage();
					if (image != null) {
						Image im = image.getScaledInstance(102, 102,java.awt.Image.SCALE_SMOOTH);						
						bigIcon = new javax.swing.ImageIcon(im);
						return bigIcon;
					}
					else{
						System.err.println("Imagem da categoria é nula");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				else return bigIcon;
			}else{
				return getIcon("/imgs/warn.png",102,102);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return getIcon("/imgs/warn.png",102,102);
			
		}
		return getIcon("/imgs/warn.png",102,102);
	}*/
	
	@Transient
	public Icon getBigIcon(boolean update) {
		try {
			if (logotipo != null){				
				if (update || bigIcon == null)	
				try {
					javax.swing.ImageIcon imageIcon = (ImageIcon) new javax.swing.ImageIcon(getImageBytes(logotipo));
					Image img = imageIcon.getImage();
					if (((javax.swing.ImageIcon)imageIcon).getIconWidth() > 150 || ((javax.swing.ImageIcon)imageIcon).getIconHeight() > 150 ){
						if(imageIcon.getIconWidth() > imageIcon.getIconHeight()){
							int dif = imageIcon.getIconWidth() - 150;
							float scale = (dif * 100) / 150;
							
							int h =(int) (scale * imageIcon.getIconHeight())/100;
							Image im = img.getScaledInstance(150, imageIcon.getIconHeight() - h,java.awt.Image.SCALE_SMOOTH);						
							bigIcon = new javax.swing.ImageIcon(im);
							return bigIcon;
						} else{
							int dif = imageIcon.getIconHeight() - 150;
							float scale = (dif * 100) / 150;
							
							int w =(int) (scale * imageIcon.getIconWidth())/100;
							Image im = img.getScaledInstance(imageIcon.getIconWidth() - w,150,java.awt.Image.SCALE_SMOOTH);						
							bigIcon = new javax.swing.ImageIcon(im);
							return bigIcon;
						}
					}
					if (imageIcon != null) {
						bigIcon = new javax.swing.ImageIcon(img);
						return bigIcon;
					}
					else{
						System.err.println("Imagem da categoria é nula");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				else return bigIcon;
			}else{
				return getIcon("/imgs/warn.png",100,100);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return getIcon("/imgs/warn.png",100,100);
			
		}
		return getIcon("/imgs/warn.png",100,100);
	}



	@Transient
	public Icon getSmallIcon(boolean update) {
		try {
			if (logotipo != null){
				if (update || smallIcon == null)	
				try {
					java.awt.Image image = new javax.swing.ImageIcon(getImageBytes(logotipo)).getImage();
					if (image != null) {
						Image im = image.getScaledInstance(18, 18,java.awt.Image.SCALE_SMOOTH);
						smallIcon= new javax.swing.ImageIcon(im);
						return smallIcon;
					}
					else{
						System.err.println("Imagem da categoria é nula");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				else return smallIcon;
			}else{
				return getIcon("/imgs/warn.png",18,18);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return getIcon("/imgs/warn.png",18,18);
			
		}
		return getIcon("/imgs/warn.png",18,18);
	}
	
	private static byte[] getImageBytes(File file){
		byte[] b = new byte[(int) file.length()];
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(b);
			/*for (int i = 0; i < b.length; i++) {
				System.out.print((char)b[i]);
			}*/
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found.");
			e.printStackTrace();
		}
		catch (IOException e1)
		{
			System.out.println("Error Reading The File.");
			e1.printStackTrace();
		}
		return b;
	}
	
	@Transient
	private static Icon getIcon(String name, int w, int h) {

		try {
			java.net.URL imURL = java.lang.Class.class.getResource(name);
			if (imURL != null) {
				java.awt.Image image = new javax.swing.ImageIcon(imURL)
						.getImage();
				if (image != null) {
					image = image.getScaledInstance(w, h,
							java.awt.Image.SCALE_SMOOTH);
					javax.swing.Icon icon = new javax.swing.ImageIcon(image);
					return icon;
				}
			}
		} catch (java.lang.StackOverflowError e) {
			// TODO
			e.printStackTrace();
		} catch (java.lang.Exception e) {
			// TODO
			e.printStackTrace();
		}// end of catch block
		return null;
	}
}