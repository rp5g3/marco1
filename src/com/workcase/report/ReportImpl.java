/*
 * Created on Apr 4, 2005
 */
package com.workcase.report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.view.jasperreports.AbstractJasperReportsView;

import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.report.server.reports.BeanReport;
import com.workcase.utils.GenericException;

/**
 * @spring.bean name="report" singleton="true"
 * @author frodrigues
 */
public class ReportImpl implements Report {
    
    protected GenericApplicationContext context = null;
    
    public ReportImpl() {
        context = new GenericApplicationContext();
        XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(context);
        xmlReader.loadBeanDefinitions(new ClassPathResource("site-views.xml"));        
    }
    
    /**
     * Retorna o relatorio
     * @return Este retorno eh um array de bytes
     */
    @SuppressWarnings("unchecked")
	public Object getReport(String reportName, String reportType, Map param) throws GenericException {        
        if (param == null)
            throw new GenericException("reports.invalidParameters");               
        
        BeanReport b = (BeanReport) context.getBean(reportName);
        String reportView = b.getReportView();        
        
        AbstractJasperReportsView jasperView = (AbstractJasperReportsView) context.getBean(reportView);
        jasperView.setUrl(this.fixUrl(jasperView.getUrl()));
        jasperView.setApplicationContext(context);
        
        Map renderParam = new HashMap();
        renderParam.put("data", b.getData(param));		
        renderParam.put("REPORT_RESOURCE_BUNDLE", SpringResourceMessage.getInstance().getResourceBundle());
                
        ResponseWrapper response = new ResponseWrapper();        
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();        
        response.setOutputStream(new FakeServletOutputStream(byteOutput));
        
        try {
            jasperView.render(renderParam, null, response);
        } catch(Exception ex) {
            ex.printStackTrace();
            throw new GenericException("reports.reportError");
        }
        
        return byteOutput.toByteArray();        
    }
    
    /**
     * TODO A url que eh passada para eh mim, eh por exemplo '/WEB-INF/jasper/report.jasper'
     * mas, eu nao consigo pegar esse arquivo, preciso tirar o '/WEB-INF/' da frente para que fique
     * '/jasper/report.jasper', nao sei se a web tambem teria este problema, acho que nao!
     * @param url
     * @return
     */
    public String fixUrl(String url) {
        if (url.startsWith("/WEB-INF/")) {
            url = url.substring(url.indexOf("/", 1));
        } else if (url.startsWith("WEB-INF/")) {
            url = url.substring(url.indexOf("/"));
        }
        return url;
    }
}

class ResponseWrapper implements HttpServletResponse {

    // TODO
    ServletOutputStream outputStream;
    
    public ServletOutputStream getOutputStream() throws IOException {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = (ServletOutputStream) outputStream;
    }
    
    public void addCookie(Cookie arg0) {
    }

    public boolean containsHeader(String arg0) {
        return false;
    }

    public String encodeURL(String arg0) {
        return null;
    }

    public String encodeRedirectURL(String arg0) {
        return null;
    }

    public String encodeUrl(String arg0) {
        return null;
    }

    public String encodeRedirectUrl(String arg0) {
        return null;
    }

    public void sendError(int arg0, String arg1) throws IOException {
    }

    public void sendError(int arg0) throws IOException {
    }

    public void sendRedirect(String arg0) throws IOException {
    }

    public void setDateHeader(String arg0, long arg1) {
    }

    public void addDateHeader(String arg0, long arg1) {
    }

    public void setHeader(String arg0, String arg1) {
    }

    public void addHeader(String arg0, String arg1) {
    }

    public void setIntHeader(String arg0, int arg1) {
    }

    public void addIntHeader(String arg0, int arg1) {
    }

    public void setStatus(int arg0) {
    }

    public void setStatus(int arg0, String arg1) {
    }

    public String getCharacterEncoding() {
        return null;
    }

    public String getContentType() {
        return null;
    }

    public PrintWriter getWriter() throws IOException {
        return null;
    }

    public void setCharacterEncoding(String arg0) {
    }

    public void setContentLength(int arg0) {
    }

    public void setContentType(String arg0) {
    }

    public void setBufferSize(int arg0) {
    }

    public int getBufferSize() {
        return 0;
    }

    public void flushBuffer() throws IOException {
    }

    public void resetBuffer() {
    }

    public boolean isCommitted() {
        return false;
    }

    public void reset() {
    }

    public void setLocale(Locale arg0) {
    }

    public Locale getLocale() {
        return null;
    }
    
}

class FakeServletOutputStream extends ServletOutputStream {
    
    protected OutputStream output;
    
    public FakeServletOutputStream(OutputStream output) {
        this.output = output;
    }
    
    public void write(int b) throws IOException {
        this.output.write(b);
    }
    
    public void write(byte[] b) throws IOException {
        this.output.write(b);
    }
    
    public void write(byte[] b, int off, int len) throws IOException {
        this.output.write(b, off, len);
    }
}
