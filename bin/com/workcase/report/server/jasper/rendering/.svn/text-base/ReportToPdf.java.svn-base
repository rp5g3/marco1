/*
 * Created on Mar 28, 2005
 */
package com.workcase.report.server.jasper.rendering;

import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

import com.workcase.report.server.ReportRender;
import com.workcase.utils.GenericException;


/**
 * Renderiza o relatorio no formato PDF
 * @author frodrigues
 */
public class ReportToPdf extends ReportRender {
    
    /**
     * Renderiza o relatorio no formato PDF
     */
    @SuppressWarnings("unchecked")
	public Object render(JasperReport report, Map param, JRDataSource dataSource) throws GenericException {
        try {
            return JasperRunManager.runReportToPdf(report, param, dataSource);
        } catch(Exception ex) {
            ex.printStackTrace();
            throw new GenericException("reports.convertError");
        }        
    }
}
