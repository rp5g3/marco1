/*
 * Created on Mar 30, 2005
 */
package com.workcase.gui.utils;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Responsavel em carregar as definicoes do arquivo swingContext.xml
 * @author frodrigues
 */
public class SwingContext {

    protected static SwingContext instance = null;
    protected final static String FILE_1="swingContext.xml";
    protected final static String FILE_2="swingContextServer.xml";
    
    /**
     * 
     * @author fbasso
     * @spring.property name="beanFactory" singleton="true"
     */
    private XmlBeanFactory beanFactory;

    protected SwingContext() {
        beanFactory = new XmlBeanFactory(new ClassPathResource(FILE_2));
    }
    
    public static SwingContext getInstance() {
        if (instance == null)
            instance = new SwingContext();
        
        return instance;
    }
        
    public XmlBeanFactory getBeanFactory() {
        return beanFactory;
    }
}