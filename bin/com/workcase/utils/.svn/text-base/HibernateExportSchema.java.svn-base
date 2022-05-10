/*
 * Created on 26/10/2004
 *
 * $Id: HibernateExportSchema.java,v 1.1 2007/05/30 18:04:47 jrflach Exp $
 * 
 * $Log: HibernateExportSchema.java,v $
 * Revision 1.1  2007/05/30 18:04:47  jrflach
 * Primeiro commit do projeto horizonte
 *
 * Revision 1.1  2005/02/11 16:13:57  urubatan
 * Commit inicial do framework para o projeto E-Parts
 * e finalização da validação client side utilizando Commons-Validator
 *
 * o framework precisa ser testado
 *
 * Revision 1.1  2004/10/27 19:18:04  urubatan
 * commit inicial do projeto para o novo site do rsjug
 *
 */
package com.workcase.utils;



import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * @author Rodrigo Urubatan Ferreira Jardim
 *
 */
public class HibernateExportSchema {

    public static void main ( String[] args ) {
        Configuration cfg;
        try {
            cfg = new Configuration().configure();
            SchemaExport exp = new SchemaExport(cfg);
            exp.setOutputFile("script.sql");
            exp.create(false, true);
            
        } catch ( HibernateException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
