/*
 * Created on Mar 28, 2005
 */
package com.workcase.report.server.jasper.rendering;

import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;

import com.workcase.report.server.ReportRender;
import com.workcase.utils.GenericException;

/**
 * Renderiza o relatorio no formato que o JasperViewer consegue ler
 * @author frodrigues
 */
public class ReportToViewer extends ReportRender {
    
    /**
     * Renderiza o relatorio no formato que o JasperViewer consegue ler
     */
    @SuppressWarnings("unchecked")
	public Object render(JasperReport report, Map param, JRDataSource dataSource) throws GenericException {
        try {
            return JasperFillManager.fillReport(report, param, dataSource);
        } catch(Exception ex) {
            ex.printStackTrace();
            throw new GenericException("reports.fillError");
        }
    }
}
