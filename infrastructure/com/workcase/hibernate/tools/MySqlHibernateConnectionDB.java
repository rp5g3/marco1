/*
 * MySqlHibernateConnectionDB.java
 *
 * Created on 17 de Maio de 2006, 15:34
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.workcase.hibernate.tools;

import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author Fabio Paulo Basso
 */
public class MySqlHibernateConnectionDB implements HibernateConnectionDB{
    
    /** Creates a new instance of MySqlHibernateConnectionDB */
    public MySqlHibernateConnectionDB() {
    }

    
    public void setConnection(AnnotationConfiguration cfg) throws Exception {
      cfg.setProperty( "hibernate.dialect", "org.hibernate.dialect.MySQLDialect" );
      cfg.setProperty( "hibernate.connection.driver_class", "com.mysql.jdbc.Driver" );
      cfg.setProperty( "hibernate.connection.url", "jdbc:mysql://localhost:3306/test" );
      cfg.setProperty( "hibernate.connection.username", "fbasso" );
      cfg.setProperty( "hibernate.connection.password", "fbassoAjE" );
      cfg.setProperty( "hibernate.connection.autocommit", "false" );
      cfg.setProperty( "hibernate .connection.show_sql", "true");
      cfg.setProperty( "hibernate.connection.release_mode", "after_statement" );
      cfg.setProperty( "hibernate.transaction.flush_before_completion", "false" );
      cfg.setProperty( "hibernate.transaction.auto_close_session", "false" );
      cfg.setProperty( "hibernate.query.substitutions", "true 1, false 0, yes 'Y', no 'N'" );
      cfg.setProperty( "hibernate.cglib.use_reflection_optimizer", "true" );
      //cfg.setProperty( "hibernate.hbm2ddl.auto", "create-drop" );
      
    }
}
