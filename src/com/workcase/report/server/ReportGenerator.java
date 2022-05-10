/*
 * Created on Mar 28, 2005
 */
package com.workcase.report.server;

import java.util.Map;

import com.workcase.utils.GenericException;


/**
 * Classe abstrata que os geradores de relatorio devem estender
 * @author frodrigues
 */
public interface ReportGenerator {
    
    /**
     * Gera o relatorio
     * @param data
     * @return
     * @throws GenericException
     */
    @SuppressWarnings("unchecked")
	public Object generateReport(String reportName, String reportType, Map param) throws GenericException;    
}
