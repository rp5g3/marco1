/*
 * Created on Mar 28, 2005
 */
package com.workcase.report.client.handler;

/**
 * Mostra o relatorio em formato XML
 * @author frodrigues
 */
public class XmlHandler extends ReportHandler {

    /**
     * Mostra o relatorio em formato XML
     */
    public void displayReport(Object report) {
        this.displayReport(report, "xml");
    }
}
