package com.adapit.portal.entidades;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.swing.Icon;
import javax.swing.ImageIcon;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@SequenceGenerator(name="Imagem_Gen",allocationSize=1,initialValue=1,sequenceName="ImageDataSeq")
@Table(name="ImageData")
public class Imagem implements Serializable{

	private static final long serialVersionUID = 828386596463264L;
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Imagem_Gen")
	@Id
	private int id;
	
	@Basic(fetch=FetchType.LAZY)
	private byte fullImageBytes[];
	
	@Basic(fetch=FetchType.LAZY)
	private byte smallImageBytes[];
	
	@Column(nullable=true,name="label")
	private String rotulo;
	
	@Column(name="image_index")
	private int indice;
	
	@Transient
	private ArrayList<ComercialSolution> comercialSolution;
	
	private int height;
	
	private int width;
	
	@Column(length=100)
	private String altText;
	
	@Column(length=4000)
	private String description;
	
	@Column(length=5)
	private String format;
	
	@Column(length=300)
	private String path;
	
	@ManyToOne(targetEntity=Categoria.class,cascade={CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE},fetch=FetchType.LAZY)
	@JoinColumn(name="image_cat_id")
	private Categoria categoria;
	

	@Transient
	private Icon mediumIcon,smallIcon;	
	
	public void setId(int id ){
		this.id=id;
	}
	
	
	public int getId(){
		return this.id;
	}
	
	@Transient
	public Icon getImageIcon() {
		try {			
			if (fullImageBytes != null && fullImageBytes.length > 0){				
				javax.swing.ImageIcon imageIcon = (ImageIcon) new javax.swing.ImageIcon(fullImageBytes);
				return imageIcon;				
			}else{
				return getIcon("/imgs/warn.png",100,100);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return getIcon("/imgs/warn.png",100,100);			
		}		
	}
	
	@Transient
	public Icon getMediumImageIcon(boolean update) {
		try {
			if (fullImageBytes != null && fullImageBytes.length > 0){				
				if (update || mediumIcon == null)	
				try {
					javax.swing.ImageIcon imageIcon = (ImageIcon) new javax.swing.ImageIcon(fullImageBytes/*getImageBytes(imageFile)*/);
					Image img = imageIcon.getImage();
									
					if (((javax.swing.ImageIcon)imageIcon).getIconWidth() > 260 || ((javax.swing.ImageIcon)imageIcon).getIconHeight() > 260 ){
						return getScaledImage(260);
					}
					if (imageIcon != null) {										
						mediumIcon = new javax.swing.ImageIcon(img);
						return mediumIcon;
					}
					else{
						System.err.println("Imagem é nula");
					}
				} catch (Exception e) {					
					e.printStackTrace();
				}
				else return mediumIcon;
			}else{
				return getIcon("/imgs/warn.png",100,100);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return getIcon("/imgs/warn.png",100,100);
			
		}
		return getIcon("/imgs/warn.png",100,100);
	}

	
	public static String getImageFormat(File f) throws Exception{
		int index = f.getName().indexOf(".");		
		String format = f.getName().substring(index+1);
		if (format.equalsIgnoreCase("jpg")) format = "jpeg";
		if (format.equalsIgnoreCase("gif") || format.equalsIgnoreCase("png") || format.equalsIgnoreCase("jpeg") || format.equalsIgnoreCase("jpeg") || format.equalsIgnoreCase("tiff")){
			return format;
		} else throw new Exception("Not Supported Image Format Excpetion");		
	}
	
	@Transient
	public String getImageFormat() throws Exception{
		if (format.equalsIgnoreCase("jpg")) format = "jpeg";
		if (format.equalsIgnoreCase("gif") || format.equalsIgnoreCase("png") || format.equalsIgnoreCase("jpeg") || format.equalsIgnoreCase("jpeg") || format.equalsIgnoreCase("tiff")){
			return format;
		} else throw new Exception("Not Supported Image Format Excpetion");
		
	}


	public static byte[] toByteArray(File file){
		byte[] buffer = null;
		try {
		    BufferedImage image = ImageIO.read(file);
		    ByteArrayOutputStream bos = new ByteArrayOutputStream();
		    ImageIO.write(image, getImageFormat(file), bos);
		    buffer = bos.toByteArray();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return buffer;
	}
	

	public static byte[] toByteArray(Image img, String format, int w, int h) {        
		img = img.getScaledInstance(w, h,java.awt.Image.SCALE_SMOOTH);
		ParameterBlock pb = new ParameterBlock();
        pb.add(img);
        PlanarImage image = JAI.create("awtImage", pb,null);
 
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
 
        JAI.create("encode", image, stream, format); 
        byte[] b = stream.toByteArray();
        return b;
    }
	

	public static byte[] toByteArray(Image img, String format) {        
		if (img == null) return null;
		ParameterBlock pb = new ParameterBlock();
        pb.add(img);
        PlanarImage image = JAI.create("awtImage", pb,null);
        if (format.equals("jpg")) format="jpeg";
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        if (format.equals("")) return null;
        try {
			JAI.create("encode", image, stream, format); 
			byte[] b = stream.toByteArray();
			return b;
		} catch (java.lang.IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
    }


	private static ImageIcon scaleImage(ImageIcon img, int maxSize){
		int width=0,height=0;
		width = img.getIconWidth();
		height = img.getIconHeight();
		System.out.println("Valor real    Width="+width + "  Height="+height);
		if (width > maxSize || height > maxSize ){
			if(width > height){					
				int restant = width - maxSize;
				
				float reductionScale = ((restant * 100) / width);
				int newSize = (int) (((100 - reductionScale) * height)/100);				
				
				Image im = img.getImage().getScaledInstance(maxSize, newSize,java.awt.Image.SCALE_SMOOTH);						
				return new javax.swing.ImageIcon(im);		
				
			} else if (height > width){
				int restant = height - maxSize;
				
				float reductionScale = ((restant * 100) / height);
				
				int newSize = (int) (((100 - reductionScale) * width)/100);
				
				Image im = img.getImage().getScaledInstance(newSize, maxSize,java.awt.Image.SCALE_SMOOTH);						
				return new javax.swing.ImageIcon(im);	
								
			} else{
				Image im = img.getImage().getScaledInstance(maxSize, maxSize,java.awt.Image.SCALE_SMOOTH);						
				return new javax.swing.ImageIcon(im);
			}
		}
		return img;		
		
	}
	

	public static ImageIcon getScaledImage(ImageIcon imageIcon, int maxSize){
		try {
			return scaleImage(imageIcon,maxSize);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	@Transient
	public ImageIcon getScaledImage(int maxSize){
		if (fullImageBytes != null && fullImageBytes.length > 0) try {
			javax.swing.ImageIcon imageIcon = (ImageIcon) new javax.swing.ImageIcon(fullImageBytes/*getImageBytes(imageFile)*/);
			return scaleImage(imageIcon,maxSize);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}


	@Transient
	public Icon getSmallImageIcon(boolean update) {
		try {
			if (smallImageBytes != null && smallImageBytes.length > 0){
				if (update || smallIcon == null)	
				try {
					java.awt.Image image = new javax.swing.ImageIcon(smallImageBytes).getImage();
					if (image != null) {
						smallIcon= new javax.swing.ImageIcon(image);
						return smallIcon;
					}
					else{
						System.err.println("Imagem é nula");
					}
				} catch (Exception e) {					
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
	

	public static byte[] getImageBytes(File file){
		byte[] b = new byte[(int) file.length()];
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(b);
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
	

	public static Icon getIcon(String name, int w, int h) {
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
			
			e.printStackTrace();
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Icon getBigWarningIcon(boolean b,int w,int h) {
		try {
			return getIcon("/imgs/warn.png",w,h);
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return null;		
	}

	
	public String getRotulo() {
		return rotulo;
	}

	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	
	@Transient
	public void setSmallImageScallingAs(int width, int heigth){
		java.awt.Image image = new javax.swing.ImageIcon(fullImageBytes).getImage();
		if (image != null) {
			Image im = image.getScaledInstance(width, heigth,java.awt.Image.SCALE_SMOOTH);
			            
			byte[] pixels;
			try {
				pixels = toByteArray(im, getImageFormat());
				this.smallImageBytes = pixels;
			} catch (Exception e) {
		
				e.printStackTrace();
			}
		}		
	}
	
	@Transient
	public void setSmallImageScallingAs(int len){
		java.awt.Image image = new javax.swing.ImageIcon(fullImageBytes).getImage();
		if (image != null) {
			Image im = getScaledImage(new ImageIcon(image),len).getImage();			
			String format="";
			try {
				format = getImageFormat();
				byte[] pixels = toByteArray(im, format);
				
				this.smallImageBytes = pixels;
			} catch (Exception e) {
				
				e.printStackTrace();
			}        
		}		
	}
	
	@Transient
	public void setBigImageScallingAs(int len){
		java.awt.Image image = new javax.swing.ImageIcon(fullImageBytes).getImage();
		
		if (image != null) {
			Image im = getScaledImage(new ImageIcon(image),len).getImage();			
			
			String format="";
			try {
				format = getImageFormat();
				byte[] pixels = toByteArray(im, format);
				fullImageBytes = pixels;
			} catch (Exception e) {
				
				e.printStackTrace();
			}						
		}		
	}
	
	@Transient
	public void setBigImageScallingAs(int width, int heigth){
		java.awt.Image image = new javax.swing.ImageIcon(fullImageBytes).getImage();
		if (image != null) {
			Image im = image.getScaledInstance(width, heigth,java.awt.Image.SCALE_SMOOTH);
			
			try {
				byte[] pixels = toByteArray(im, getImageFormat());
				fullImageBytes = pixels;
			} catch (Exception e) {				
				e.printStackTrace();
			}		
		}	
	}


	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}


	public String getAltText() {
		return altText;
	}

	public void setAltText(String altText) {
		this.altText = altText;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Transient
	public void setImageFile(File imageFile){
		int index = imageFile.getName().indexOf(".");
		this.format = imageFile.getName().substring(index+1);
		try {
			this.format = getImageFormat();
		} catch (Exception e) {			
			e.printStackTrace();
		}
		fullImageBytes = toByteArray(imageFile);		
	}

	@Transient
	public void setImageData(File imageFile){
		int index = imageFile.getName().indexOf(".");
		this.format = imageFile.getName().substring(index+1);
		try {
			this.format = getImageFormat();
		} catch (Exception e) {			
			e.printStackTrace();
		}		
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	
	public byte[] getSmallImageBytes() {
		return smallImageBytes;
	}

	public void setSmallImageBytes(byte[] smallImageBytes) {
		this.smallImageBytes = smallImageBytes;
	}
	
	
	public byte[] getFullImageBytes(){
		return this.fullImageBytes;
	}
	
	public void setFullImageBytes(byte[] fullImageBytes ){
		this.fullImageBytes=fullImageBytes;
	}

	@Transient
	public void setFullImageBytes(File file, int scale ){
		try {
			this.format = getImageFormat(file);
			this.path = file.getPath();
		} catch (Exception e) {			
			e.printStackTrace();
		}
		this.fullImageBytes=getImageBytes(file);
		setBigImageScallingAs(scale);
	}
	
	@Transient
	public void setFullImageBytes(File file){
		try {
			this.format = getImageFormat(file);
			this.path = file.getPath();
		} catch (Exception e) {			
			e.printStackTrace();
		}
		this.fullImageBytes=getImageBytes(file);		
	}


	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setCategoria(Categoria categoria ){
		this.categoria=categoria;		
	}
	
	
	
	public Categoria getCategoria(){
		return this.categoria;
	}

	

	public ArrayList<ComercialSolution> getComercialSolution() {
		return comercialSolution;
	}

	public void setComercialSolution(ArrayList<ComercialSolution> sol) {
		this.comercialSolution = sol;
	}

	/*@ManyToMany(fetch=FetchType.LAZY, mappedBy="imagens", targetEntity=Produto.class)
	public ArrayList<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}*/
	
/*	@ManyToOne(targetEntity=Produto.class,cascade={CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE},fetch=FetchType.LAZY)
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}*/

}
