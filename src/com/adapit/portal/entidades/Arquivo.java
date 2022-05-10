package com.adapit.portal.entidades;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@SequenceGenerator(name="Arquivo_Gen",allocationSize=1,initialValue=1,sequenceName="FileDataSeq")
@Table(name="FileData")
public class Arquivo implements Serializable{

	private static final long serialVersionUID = 2469980981432762357L;
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Arquivo_Gen")
	@Id
	private int id;
	
	@Basic(fetch=FetchType.LAZY)
	private byte fullBytes[];
	
	@Column(length=5)
	private String format;
	
	@Column(length=10000)
	private String path;
	
	@Column(nullable=true,length=100,name="file_name")
	private String name;
	
	@Column(length=10000, nullable=true)
	private String localPath;
	
	public enum FileKind{
		Paper,Folder,Image,Music,Document,ResourceFile,Zip,Setup,Apostila
	}
	
	@Enumerated(EnumType.ORDINAL)
	private FileKind fileKind=null;
	
	public enum AccessKind{
		FreeAccess,UserAccess,AdminAccess
	}
	
	@Enumerated(EnumType.ORDINAL)
	private AccessKind accessKind=AccessKind.FreeAccess;
	
/*	@Column(nullable=true)
	private int fileVersion;*/
	
	@ManyToMany
	@JoinTable(name="USER_CANREAD_FILE")	
	private List<Usuario> canReadUserList = new ArrayList<Usuario>();
	
	@ManyToMany
	@JoinTable(name="USER_CANWRITE_FILE")
	private List<Usuario> canWriteUserList = new ArrayList<Usuario>();

		
	public void setId(int id ){
		this.id=id;
	}
	
	
	public int getId(){
		return this.id;
	}

	// Returns the contents of the file in a byte array.
    public static byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);
    
        // Get the size of the file
        long length = file.length();
    
        // You cannot create an array using a long type.
        // It needs to be an int type.
        // Before converting to an int type, check
        // to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
    
        // Create the byte array to hold the data
        byte[] bytes = new byte[(int)length];
    
        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
               && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }
    
        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }
    
        // Close the input stream and return bytes
        is.close();
        return bytes;
    }
    
    @Transient
    public static byte[] getBytesFromFile2(File file) throws IOException {
    	byte[] b = new byte[(int) file.length()];
    	//System.out.println("Comprimento " + file.length());
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
		//System.out.println("Conteúdo " + b);
		return b;
        /*InputStream is = new FileInputStream(file);
        
        long length = file.length();

        if (length > Integer.MAX_VALUE) {
            // File is too large
        }

        byte[] bytes = new byte[(int)length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
               && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }
    
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }

        is.close();
        return bytes;*/
    }

	
	public static byte[] toByteArray(File file){
		try {
			return getBytesFromFile2(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getFormat(File f) throws Exception{
		int index = f.getName().indexOf(".");		
		String format = f.getName().substring(index+1);
		return format;	
	}
	
	public static String getName(File f) throws Exception{
		return f.getName().trim().replace(" ","_")
		.replace("ç","c").replace("Ç","C")
		.replace("ã","a").replace("õ","o")
		.replace("â","a").replace("ô","o").replace("ê","e").replace("î","i").replace("û","u")
		.replace("á","a").replace("ó","o").replace("é","e").replace("í","i").replace("ú","u")
		.replace("à","a").replace("ò","o").replace("è","e").replace("ì","i").replace("ù","u")
		.replace("\"","_").replace("\'","_").replace(":","_").replace("<","_").replace(">","_")
		.replace(",","_").replace(";","_").replace("/","_").replace("?","_").replace("~","_")
		.replace("^","_").replace("]","_").replace("}","_").replace("´","_").replace("`","_")
		.replace("[","_").replace("{","_").replace("|","_").replace("\\","_").replace("!","_")
		.replace("@","_").replace("#","_").replace("$","_").replace("%","_").replace("¨","_")
		.replace("&","_").replace("*","_").replace("(","_").replace(")","_").replace("-","_")
		.replace("+","_").replace("=","_")
		.replace("ä","a").replace("ë","e").replace("ï","i").replace("ö","o").replace("ü","u")
		.replace("Ã","A").replace("Õ","O")
		.replace("Â","A").replace("Ô","O").replace("Ê","E").replace("Î","I").replace("Û","U")
		.replace("Á","A").replace("Ó","O").replace("É","E").replace("Í","I").replace("Ú","U")
		.replace("À","A").replace("Ò","O").replace("È","E").replace("Ì","I").replace("Ù","U")
		.replace("Ä","A").replace("Ë","A").replace("Ï","I").replace("Ö","O").replace("Ü","U")
		;
	}
	
	@Transient
	public void setFile(File file) throws Exception{
		int index = file.getName().indexOf(".");
		this.format = file.getName().substring(index+1);
		try {
			this.format = getFormat();
			this.path = file.getAbsolutePath();
			this.name= getName(file);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		this.fullBytes = toByteArray(file);		
	}
	
	@Transient
	public File getFile(){
		File file = null;
		@SuppressWarnings("unused")
		ByteArrayInputStream bis = new ByteArrayInputStream(fullBytes);
		
		return file;	
	}

	@Transient
	public InputStream getInputStream(){		
		ByteArrayInputStream bis = new ByteArrayInputStream(fullBytes);
		return bis;	
	}
	
	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	
	public byte[] getFullBytes(){
		return this.fullBytes;
	}
	
	public void setFullBytes(byte[] fullBytes ){
		this.fullBytes=fullBytes;
	}

	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLocalPath() {
		return localPath;
	}


	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}


	public FileKind getFileKind() {
		return fileKind;
	}


	public void setFileKind(FileKind fileKind) {
		this.fileKind = fileKind;
	}


	public AccessKind getAccessKind() {
		return accessKind;
	}


	public void setAccessKind(AccessKind accessKind) {
		this.accessKind = accessKind;
	}


/*	public int getFileVersion() {
		return fileVersion;
	}


	public void setFileVersion(int version) {
		this.fileVersion = version;
	}*/


	public List<Usuario> getCanReadUserList() {
		return canReadUserList;
	}


	public void setCanReadUserList(List<Usuario> canReadUserList) {
		this.canReadUserList = canReadUserList;
	}


	public List<Usuario> getCanWriteUserList() {
		return canWriteUserList;
	}


	public void setCanWriteUserList(List<Usuario> canWriteUserList) {
		this.canWriteUserList = canWriteUserList;
	}


}