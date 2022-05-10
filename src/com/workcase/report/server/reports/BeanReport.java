/*
 * Created on Apr 6, 2005
 */
package com.workcase.report.server.reports;

import java.util.Map;

import com.workcase.utils.GenericException;

/**
 * Esta classe abstrata deve ser estendida pelas classes que irao gerar os dados
 * para os relatorio
 * @author frodrigues
 */
public abstract class BeanReport {

    protected String reportView;
    
    /**
     * Retorna os dados para o relatorio, normalmente ou um array de beans ou
     * uma lista de beans
     * @param param
     * @return
     * @throws GenericException
     */
    @SuppressWarnings("unchecked")
    public abstract Object getData(Map param) throws GenericException;
    
    public String getReportView() {
        return this.reportView;
    }
    
    public void setReportView(String reportView) {
        this.reportView = reportView;
    }
}
