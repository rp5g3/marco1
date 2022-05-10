/*
 * Created on Mar 28, 2005
 */
package com.workcase.report.client.handler;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Mostra na tela o relatorio com o JasperViewer
 * @author frodrigues
 */
public class ViewerHandler extends ReportHandler {

    /**
     * Mostra na tela o relatorio com o JasperViewer
     */
    public void displayReport(Object report) {
        JasperPrint jp = (JasperPrint) report;

        try {
            JasperViewer.viewReport(jp, false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}