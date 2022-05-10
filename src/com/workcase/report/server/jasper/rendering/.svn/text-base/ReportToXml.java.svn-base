/*
 * Created on Mar 28, 2005
 */
package com.workcase.report.server.jasper.rendering;

import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXmlExporter;

import com.workcase.report.server.ReportRender;
import com.workcase.utils.GenericException;

/**
 * Renderiza o relatorio no formato XML
 * @author frodrigues
 */
public class ReportToXml extends ReportRender {

    /**
     * Renderiza o relatorio no formato XML
     */
    @SuppressWarnings("unchecked")
	public Object render(JasperReport report, Map param, JRDataSource dataSource) throws GenericException {
        JRXmlExporter exporter = new JRXmlExporter();
        return this.export(report, param, dataSource, exporter);       
    }
}
