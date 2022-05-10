package com.adapit.portal.util.global;

import java.io.File;
import java.io.IOException;

public class GlobalVariables {

	private GlobalVariables() {
	}

	
	public static int ContainerPrincipalWidth=550;
	public static int ContainerPrincipalHeight=300;
	public static int MaxTableRowNumber=450;
	public static String ContainerPrincipalBorderColor="#414125";
	public static String ContainerPrincipalBorderSpec="border:1px #414125 solid;";
	public static int countApresentacaoTab=1;
	
	public static boolean WEB_PAYMENT_ENABLED=false;
	private static String WinDocumentServerFileDir = "C:/Arquivos de programas/Apache Software Foundation/Tomcat 5.5/webapps/";
	private static String LinDocumentServerFileDir = "/var/lib/tomcat5.5/webapps/";
	//public static String BoletoServerFileDir = "C:\\Arquivos de programas\\Apache Software Foundation\\Tomcat 5.5\\webapps\\ROOT\\boletos\\";
	
	private static final String L="localhost:8082/adapit";
	private static final String S="www.adapit.com.br";
	public static final String URL=S;
	public static final String WEBAPPS=LinDocumentServerFileDir;
	private static final boolean dinamicDir=false;
	
	public static final String ADAPIT_ADDRESS="Av. Sete de Setembro, N: 85, 1201, Icaraí - Niterói - RJ CEP 24230-250";
	public static final String ADAPIT_PHONE="F: (21) 2710-6586";
	public static final String ADAPIT_COPYRIGHT="Copyright @ 2007 - 2012 Adapit Soluções em TI. Todos os Direitos Reservados";
	
	public final static String getWebappsDir(){
		//String str="";
		if(dinamicDir){
			try {
				File f2 = new File(".");
				String serverDir = f2.getCanonicalPath();
				return serverDir;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return WEBAPPS;
		//return str;
	} 
}
