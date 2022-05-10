/*
 * Created on Mar 28, 2005
 */
package com.workcase.report.server;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import com.workcase.utils.GenericException;

/**
 * Classe abstrata usada para gerar o relatorio nos mais diversos
 * tipos
 * @author frodrigues
 */
public abstract class ReportRender {
    
    /**
     * 
     * @param report
     * @param data
     * @param conn
     * @return
     * @throws GenericException
     */
    @SuppressWarnings("unchecked")
	public abstract Object render(JasperReport report, Map data, JRDataSource dataSource) throws GenericException;
    
    /**
     * 
     * @param report
     * @param param
     * @param conn
     * @param exporter
     * @return
     * @throws GenericException
     */
    @SuppressWarnings("unchecked")
	public byte[] export(JasperReport report, Map param, JRDataSource dataSource, JRAbstractExporter exporter)
    		throws GenericException {

        JasperPrint jasperPrint;
        try {                        
            jasperPrint = JasperFillManager.fillReport(report, param, dataSource);
        } catch(Exception ex) {
            ex.printStackTrace();
            throw new GenericException("reports.fillError");            
        }
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);				
        
        try {
    		exporter.exportReport();
        } catch(Exception ex) {
            ex.printStackTrace();
            throw new GenericException("reports.exportError");
        }
        
		return baos.toByteArray();         
    }
}
