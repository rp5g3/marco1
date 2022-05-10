/*
 * Created on Mar 28, 2005
 */
package com.workcase.report.server.jasper.rendering;

import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRCsvExporter;

import com.workcase.report.server.ReportRender;
import com.workcase.utils.GenericException;


/**
 * Renderiza o relatorio no formato CSV
 * @author frodrigues
 */
public class ReportToCsv extends ReportRender {    
    
    /**
     * Renderiza o relatorio no formato CSV
     */
    @SuppressWarnings("unchecked")
	public Object render(JasperReport report, Map param, JRDataSource dataSource) throws GenericException {
		JRCsvExporter exporter = new JRCsvExporter();               
		return this.export(report, param, dataSource, exporter);
    }
}
