package com.workcase.hibernate.tools;

import org.hibernate.cfg.AnnotationConfiguration;

public class Oracle10gConnectionDB implements HibernateConnectionDB{
    
    /** Creates a new instance of MySqlHibernateConnectionDB */
    public Oracle10gConnectionDB() {
    }

    
    public void setConnection(AnnotationConfiguration cfg) throws Exception {
      cfg.setProperty( "hibernate.dialect", "org.hibernate.dialect.Oracle9Dialect" );
      cfg.setProperty( "hibernate.connection.driver_class", "oracle.jdbc.driver.OracleDriver" );
      cfg.setProperty( "hibernate.connection.url", "jdbc:oracle:thin:@localhost:1521/worckcase" );
      cfg.setProperty( "hibernate.connection.username", "workcase" );
      cfg.setProperty( "hibernate.connection.password", "5c0fnGG" );
      cfg.setProperty( "hibernate.connection.autocommit", "false" );
      cfg.setProperty( "hibernate .connection.show_sql", "true");
      cfg.setProperty( "hibernate.connection.release_mode", "after_statement" );
      cfg.setProperty( "hibernate.transaction.flush_before_completion", "false" );
      cfg.setProperty( "hibernate.transaction.auto_close_session", "false" );
      cfg.setProperty( "hibernate.query.substitutions", "true 1, false 0, yes 'Y', no 'N'" );
      cfg.setProperty( "hibernate.cglib.use_reflection_optimizer", "true" );
      cfg.setProperty( "hibernate.hbm2ddl.auto", "create-drop" );
      
    }
}
