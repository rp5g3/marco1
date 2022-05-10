/*
 * GenerateSchema.java
 *
 * Created on 8 de Dezembro de 2005, 17:00
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.workcase.hibernate.tools;

import java.io.File;
import java.io.FileOutputStream;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import org.hibernate.Session;
import org.hibernate.dialect.PostgreSQLDialect;


/**
 *
 * @author juliocs
 */
public class GenerateSchema {
  private Session session = null;
  private HibernateUtil hu = null;
  private FileOutputStream f = null;
  
  /** Creates a new instance of GenerateSchema */
  public GenerateSchema() {
    hu = new HibernateUtil();
  }
  
  public static void main( String[] args ){
    new GenerateSchema().gerenerateSql();
  }
  
  public void gerenerateSql(){
    
    String[] sqlDrop = hu.getCfg().generateDropSchemaScript( new PostgreSQLDialect() );
     
    String[] sqlCreate= hu.getCfg().generateSchemaCreationScript( new PostgreSQLDialect() );
    
    try{
      f = new FileOutputStream( new File( "ddl.txt") );
    }catch( Exception e ){
      e.printStackTrace();
    }
    
    StringBuffer ddl = new StringBuffer();
    
    for( String linha:sqlDrop ){
      ddl.append( linha ).append( ";\n" );
    }
    
    for( String linha:sqlCreate ){
      ddl.append( linha ).append( ";\n" );
    }
    
    try{
      f.write( ddl.toString().getBytes() );
      f.flush();
      f.close();
    }catch( Exception e ){
      e.printStackTrace();
    }
    
    System.out.print("Funcionou");
    
  }
}
