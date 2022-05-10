/*
 * Created on Mar 31, 2005
 */
package com.workcase.gui.utils;



import java.util.ResourceBundle;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author frodrigues
 */
public class SpringResourceMessage extends ResourceMessage {

    private static SpringResourceMessage instance = null;
    protected ResourceBundleMessageSource resourceSource;
    
    protected SpringResourceMessage() {
        this.resourceSource = (ResourceBundleMessageSource) SwingContext.getInstance().getBeanFactory().getBean("messageSource");
        this.setDefaultLocale();
    }
    
    public static ResourceMessage getInstance() {
        if (instance == null)
            instance = new SpringResourceMessage();
        
        return instance;
    }
    
    
    /**
     * Retorna a mensagem do codigo passado com os argumentos.
     * Os argumentos ou podem ser colocados direto na mensagem, sem
     * traducao, ou podem ser primeiramente traduzidos e depois colocados
     * na mensagem. A primeira coluna do array indica qual o texto que sera
     * inserido(traduzido) na mensagem. A segunda coluna eh um Boolean que indica
     * se ele deve ser ou nao traduzido. Se a segunda coluna for omitida, o texto
     * sera traduzido. Segue abaixo um exemplo de argumentos
     * 
     *  Object[][] args = { 
     *          {"buttons.cancel", Boolean.TRUE} , 
     *          {"1", Boolean.FALSE} , 
     *          {"buttons.save"} 
     *  };
     * 
     * O primeiro elemento "buttons.cancel" sera traduzido, o segundo "1" nao sera
     * traduzido e o terceiro "button.save" sera traduzido.
     * 
     * @param code
     * @param args
     * @return
     */
    public String getMessage(String code, Object[][] args) {
        Object[] argsResolved = this.resolveArgs(args);
        try {
            return this.resourceSource.getMessage(code, argsResolved, this.currentLocale);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return "!" + code + "!";
    }   
    
    /**
     * Verifica qual dos parametros devem ser traduzidos e usa a classe
     * DefaultMessageSourceResolvable para indicar ao spring que ele deve
     * traduzir
     * @param args
     * @return
     */
    protected Object[] resolveArgs(Object[][] args) {
        if (args == null || args.length == 0)
            return null;
        
        Object[] ret = new Object[args.length];
        String[] arrayTmp = null;
        Boolean boolAux = null;
        for(int i=0; i < args.length; i++) {
            if (args[i].length == 1 || args[i][1] == null) {
                arrayTmp = new String[1];
                arrayTmp[0] = (String) args[i][0];
                ret[i] = new DefaultMessageSourceResolvable(arrayTmp);
            } else {
                boolAux = (Boolean) args[i][1];
                if (boolAux.booleanValue()) {
                    arrayTmp = new String[1];
                    arrayTmp[0] = (String) args[i][0];
                    ret[i] = new DefaultMessageSourceResolvable(arrayTmp);                    
                } else {
                    ret[i] = (String) args[i][0];
                }
            }
        }
        
        return ret;
    }
    
    /**
     * TODO aqui deve retornar o resource bundle... nao sei como fazer isso!
     */
    public ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle("com.workcase.i18n.application", currentLocale);
    }
}
