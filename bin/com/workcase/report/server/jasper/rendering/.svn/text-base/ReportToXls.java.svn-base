/*
 * Created on Mar 28, 2005
 */
package com.workcase.report.server.jasper.rendering;

import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;

import com.workcase.report.server.ReportRender;
import com.workcase.utils.GenericException;

/**
 * Renderiza o relatorio no formato XLS
 * @author frodrigues
 */
public class ReportToXls extends ReportRender {

    /**
     * Renderiza o relatorio no formato XLS
     */
    @SuppressWarnings("unchecked")
	public Object render(JasperReport report, Map param, JRDataSource dataSource) throws GenericException {
		JRXlsExporter exporter = new JRXlsExporter();
		return this.export(report, param, dataSource, exporter);      
    }
}
