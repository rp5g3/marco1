/*
 * Created on Mar 28, 2005
 */
package com.workcase.report.client.handler;

import java.util.Calendar;

import com.workcase.utils.Util;


/**
 * Classe abstrata responsavel em mostrar os reports na tela
 * @author frodrigues
 */
public abstract class ReportHandler {

    public abstract void displayReport(Object report);
    
    /**
     * Grava o relatorio em um arquivo temporario e abre ele
     * @param report
     * @param fileType
     */
    public void displayReport(Object report, String fileType) {
        byte[] b = (byte[]) report;        
        String fileName = "report" + Calendar.getInstance().getTimeInMillis();
        fileName = Util.writeTempFile(b, fileName + "." + fileType);
        Util.runApplication(fileName);
    }
}
