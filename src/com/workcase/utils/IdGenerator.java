/*
 * Created on 22/09/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.workcase.utils;

import java.util.Random;

/**
 * @author Fabio Paulo Basso
 *
 * TODO To change the template for this generated idType comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IdGenerator {
	private static IdGenerator instance;
	
	public static IdGenerator getInstance(){
		if (instance == null) instance = new IdGenerator();
		return instance;
	}
	/**
	 * 
	 */
	private IdGenerator() {
		super();
		
	}
	
	public int generateId(){
		Random rd = new Random();

		try {

			Thread.sleep(10);
			int i = rd.nextInt(100000);
			int j = rd.hashCode();
			return i+j;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}
	
	public String generateId(int len){
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Random rd = new Random();
		
		long l = System.currentTimeMillis();
		rd.setSeed(l);
		String str = "";
		
		for (int in=0; in < len; in++)try {
			int i = 0;
			char c;
			i=rd.nextInt(3);
			if (i == 0) c = getCharDigit(rd);
			else if (i==1) c = getCharUpperCase(rd);
			else c = getCharLowerCase(rd);
			/*while ((!(i >=48 && i<=57)) && (!(i >= 65 && i <=90)) && (!(i >= 97 && i <=122))){
				i = rd.nextInt(256);
			}*/			
			
			str+=c;
			//return i+j;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	private char getCharDigit(Random rd){
		return (char) (rd.nextInt(10)+48);
	}
	
	private char getCharUpperCase(Random rd){
		return (char) (rd.nextInt(25)+65);
	}
	
	private char getCharLowerCase(Random rd){
		return (char) (rd.nextInt(25)+97);
	}
	
	public int getRandomInt(int taxe){
		Random rd = new Random();
		try {
			int i = rd.nextInt(taxe);
			return i;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}
	
	
	public String generateComplexId(){
		Random rd = new Random();
		String str="";
		try {
			for (int c=0; c < 3; c++){
				int i = rd.nextInt(10);
				str+=i;				
			}
			for (int c=0; c < 2; c++){
				int i = rd.nextInt(10);
				str+=i;				
			}
			/*for (int c=0; c < 6; c++){
				int i = rd.nextInt(100000);
				str+=i;				
			}
			for (int c=0; c < 2; c++){
				int i = rd.nextInt(100000);
				str+=i;				
			}*/
			for (int c=0; c < 3; c++){
				int i = rd.nextInt(10);
				str+=i;				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;

	}

}
