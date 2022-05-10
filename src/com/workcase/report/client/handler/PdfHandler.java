/*
 * Created on Mar 28, 2005
 */
package com.workcase.report.client.handler;

/**
 * Mostra o relatorio em formato PDF
 * @author frodrigues
 */
public class PdfHandler extends ReportHandler {

    /**
     * Mostra o relatorio em formato PDF
     */
    public void displayReport(Object report) {
        this.displayReport(report, "pdf");
    }
}
