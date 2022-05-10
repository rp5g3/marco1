/*
 * Created on Mar 28, 2005
 */
package com.workcase.report.server.jasper.rendering;

import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;

import com.workcase.report.server.ReportRender;
import com.workcase.utils.GenericException;


/**
 * Renderiza o relatorio no formato HTML
 * @author frodrigues
 */
public class ReportToHtml extends ReportRender {    
    
    /**
     * Renderiza o relatorio no formato HTML
     */
    @SuppressWarnings("unchecked")
	public Object render(JasperReport report, Map param, JRDataSource dataSource) throws GenericException {
		JRHtmlExporter exporter = new JRHtmlExporter();
		return this.export(report, param, dataSource, exporter);       
    }
}
