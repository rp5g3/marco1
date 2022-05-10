package com.workcase.hibernate;

import java.sql.Types;



public class WCPostgreeSQLDialect extends org.hibernate.dialect.PostgreSQLDialect{

	public WCPostgreeSQLDialect() {
		super();
		registerColumnType( Types.REAL, "number($p,$s)" );
		registerHibernateType(Types.REAL, "float");
		
	}

}
