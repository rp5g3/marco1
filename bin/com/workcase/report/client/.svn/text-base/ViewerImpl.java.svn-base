/*
 * Created on Mar 28, 2005
 */
package com.workcase.report.client;

import java.util.Map;

import com.workcase.report.client.handler.ReportHandler;
import com.workcase.utils.GenericException;

/**
 * Implementacao de um Viewer
 * @author frodrigues
 */
public class ViewerImpl implements Viewer {

    @SuppressWarnings("unchecked")
	private Map formats;
    
    /**
     * Mostra na tela o relatorio passado
     */
    public void display(Object report, String reportType) throws GenericException {             
        ReportHandler rHandler = (ReportHandler) formats.get(reportType);
        rHandler.displayReport(report);
    }
    
    @SuppressWarnings("unchecked")
	public Map getFormats() {
        return formats;
    }
    
    @SuppressWarnings("unchecked")
	public void setFormats(Map formats) {
        this.formats = formats;
    }
}
