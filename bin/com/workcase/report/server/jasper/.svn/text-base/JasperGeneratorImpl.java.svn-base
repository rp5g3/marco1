/*
 * Created on Mar 28, 2005
 */
package com.workcase.report.server.jasper;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.report.server.ReportGenerator;
import com.workcase.report.server.ReportRender;
import com.workcase.report.server.reports.BeanReport;
import com.workcase.utils.GenericException;

/**
 * Classe responsavel em fazer a geracao de relatorio pelo jasper
 * @author frodrigues
 */
public class JasperGeneratorImpl implements ReportGenerator {

    @SuppressWarnings("unchecked")
	protected Map reportOutput;
    protected ResourceMessage messages = SpringResourceMessage.getInstance();
    
    /**
     * Gera o relatorio
     */
    @SuppressWarnings("unchecked")
    public Object generateReport(String reportName, String reportType, Map param) throws GenericException {            
        File reportFile = this.getReportFile(reportName);        
        ReportRender reportRender = (ReportRender) this.reportOutput.get(reportType);
        
        JasperReport jp;
        try {
            jp = (JasperReport) JRLoader.loadObject(reportFile);
        } catch(Exception ex) {
            ex.printStackTrace();
            throw new GenericException("reports.loadError");
        }        
        
        JRDataSource dataSource = this.getDataSource(reportName, param);
        
        Map reportParam = new HashMap(); // parametros do relatorio
        reportParam.put("REPORT_RESOURCE_BUNDLE", messages.getResourceBundle());
        return reportRender.render(jp, reportParam, dataSource);        
    }    
    
    /**
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map getReportOutput() {
        return reportOutput;
    }
    
    /**
     * 
     * @param reportOutput
     */
    @SuppressWarnings("unchecked")
    public void setReportOutput(Map reportOutput) {
        this.reportOutput = reportOutput;
    }  
    
    /**
     * 
     * @param reportName
     * @return
     */
    public BeanReport getBeanReport(String reportName) {
        XmlBeanFactory reportBeanFactory = new XmlBeanFactory(new ClassPathResource("reportContext.xml"));                
        return (BeanReport) reportBeanFactory.getBean(reportName);
    }
    
    /**
     * Retorna o arquivo do relatorio
     * @param reportName
     * @return
     * @throws GenericException
     */
    public File getReportFile(String reportName) throws GenericException {
        String str = "/jasper/" + reportName + ".jasper";                
        URL url = getClass().getResource(str);
        if (url == null)
            throw new GenericException("reports.reportNotFound");        
        return new File(url.getFile());
    }
    
    /**
     * Retorna a origem dos dados do relatorio
     * @param reportName
     * @param param
     * @return
     * @throws GenericException
     */
    @SuppressWarnings("unchecked")
    public JRDataSource getDataSource(String reportName, Map param) throws GenericException {
        BeanReport beanReport = this.getBeanReport(reportName);
        if (beanReport == null)
            throw new GenericException("reports.reportNotFound");
        
        List list = (List) beanReport.getData(param);
        
        return new JRBeanCollectionDataSource(list);        
    }
}
