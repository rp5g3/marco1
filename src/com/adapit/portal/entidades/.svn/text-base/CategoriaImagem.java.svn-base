package com.adapit.portal.entidades;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.swing.Icon;

@Entity
@SequenceGenerator(name="CategoriaImagem_Gen",allocationSize=1,initialValue=1,sequenceName="CategoryImageDataSeq")
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="CategoryImageData")
public class CategoriaImagem implements Serializable{
	
	private static final long serialVersionUID = 2346143723582482486L;
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CategoriaImagem_Gen")
	@Id
	private int id;
	
	@Basic(fetch=FetchType.EAGER)
	private File imageFile;
	
	@Column(name="image_uri")
	private String imgURI;
	
	@Basic(fetch= FetchType.LAZY)
	@OneToOne
	@JoinColumn(name="image_category_id")
	private Categoria categoria;

	@Transient
	private Icon bigIcon,smallIcon;
	
	public void setId(int id ){
		this.id=id;
	}
	
	
	public int getId(){
		return this.id;
	}

	public void setImagem(File imagem ){
		this.imageFile=imagem;
	}
	
	//@Lob
	
	public File getImagem(){
		return this.imageFile;
	}
	
	public String getImgURI() {
		return imgURI;
	}

	public void setImgURI(String imgURI) {
		this.imgURI = imgURI;
	}

	@Transient
	public Icon getBigIcon(boolean update) {
		try {
			if (imageFile != null){				
				if (update || bigIcon == null)	
				try {
					java.awt.Image image = new javax.swing.ImageIcon(getImageBytes(imageFile)).getImage();
					if (image != null) {
						Image im = image.getScaledInstance(50, 50,java.awt.Image.SCALE_SMOOTH);						
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
				return getIcon("/imgs/warn.png",50,50);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return getIcon("/imgs/warn.png",50,50);
			
		}
		return getIcon("/imgs/warn.png",50,50);
	}



	@Transient
	public Icon getSmallIcon(boolean update) {
		try {
			if (imageFile != null){
				if (update || smallIcon == null)	
				try {
					java.awt.Image image = new javax.swing.ImageIcon(getImageBytes(imageFile)).getImage();
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

	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
