/*
 * Created on Mar 28, 2005
 */
package com.workcase.report.client.handler;


/**
 * Mostra o relatorio em formato CSV
 * @author frodrigues
 */
public class CsvHandler extends ReportHandler {

    /**
     * Mostra o relatorio em formato CSV
     */
    public void displayReport(Object report) {
        this.displayReport(report, "csv");    
    }
}
