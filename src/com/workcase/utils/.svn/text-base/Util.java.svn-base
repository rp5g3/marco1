/*
 * Created on Mar 22, 2005
 */
package com.workcase.utils;

import java.io.FileOutputStream;

/**
 * @author frodrigues
 */
public class Util {
    
    public static String tmpDir = System.getProperty("java.io.tmpdir");
    
    /**
     * 
     * @param property
     * @return
     */
    public static String getMethodName(String property) {
        String p = property.substring(0, 1);
        return p.toUpperCase() + property.substring(1);
    }
    
    /**
     * 
     * @param property
     * @return
     */
    public static String getSetMethodName(String property) {
        return "set" + Util.getMethodName(property);
    }

    /**
     * 
     * @param property
     * @return
     */
    public static String getGetMethodName(String property) {
        return "get" + Util.getMethodName(property);
    }        
    
    /**
     * Escreve no diretorio temporario um arquivo
     * @param b
     * @param fileName
     */
	public static String writeTempFile(byte[] b, String fileName) {
	    fileName = tmpDir + System.getProperty("file.separator") + fileName;
	    try {	         
		    FileOutputStream output = new FileOutputStream(fileName);
		    output.write(b);
		    output.flush();
		    output.close();
	    } catch(Exception ex) {
	        ex.printStackTrace();
	    }
	    return fileName;
	}    
	
    /**
     * Executa a aplicacao que esta associada com este tipo de arquivo
	 * TODO desta maneira soh vai rodar em windows, colocar suporte para UNIX-like
	 * TODO nao consigo saber se o windows realmente abriu, ou seja, se nao houver um
	 * programa para abrir eu nao fico sabendo
     * @param fileName
     */
	public static void runApplication(String fileName) {
	    try {
	    	@SuppressWarnings("unused")
			Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + fileName);	       
	    } catch(Exception ex) {
	        ex.printStackTrace();
	    }	    	   
	}	
}
