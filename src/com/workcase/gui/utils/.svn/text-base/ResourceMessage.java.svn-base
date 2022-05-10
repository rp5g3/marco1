/*
 * Created on Mar 30, 2005
 */
package com.workcase.gui.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 * @author frodrigues
 */
public class ResourceMessage {

    private static ResourceMessage instance = null;
    protected Locale currentLocale = null;    
    protected ResourceBundle resourceBundle = null;
    
    protected ResourceMessage() {        
        this.setDefaultLocale();
        this.initResourcesBundle();
    }
    
    public static ResourceMessage getInstance() {
        if (instance == null)
            instance = new ResourceMessage();
        
        return instance;
    }
    
    /**
     * Inicializa o ResourcesBundle de acordo com o Locale
     */
    public void initResourcesBundle() {
        this.resourceBundle = ResourceBundle.getBundle("com.workcase.i18n.application", currentLocale);
    }    
    
    /**
     * Retorna a mensagem do codigo passado
     * @param code
     * @return
     */
    public String getMessage(String code) {
        return this.getMessage(code, null);
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
        String message = null;
        try {
            message = this.resourceBundle.getString(code);
        } catch(Exception ex) {
            ex.printStackTrace();
            return "!" + code + "!";
        }
        
        // se nao tiver nenhum argumento
        if (args == null || args.length == 0)
            return message;
                        
        // resolve os argumentos e formata a mensagem
        Object[] argsResolved = this.resolveArgs(args);
        String ret = MessageFormat.format(message, argsResolved);
        return ret;
    }    
    
    /**
     * Traduz os argumento que devem ser traduzidos
     * @param args
     * @return
     */
    protected Object[] resolveArgs(Object[][] args) {
        if (args == null || args.length == 0)
            return null;
        
        Object[] ret = new Object[args.length];
        Boolean boolAux = null;
        for(int i=0; i < args.length; i++) {            
            if (args[i].length == 1) {
                ret[i] = this.getMessage((String) args[i][0]);
            } else {
                boolAux = (Boolean) args[i][1];
                if (boolAux.booleanValue()) {
                    ret[i] = this.getMessage((String) args[i][0]);                    
                } else {
                    ret[i] = (String) args[i][0];
                }
            }
        }
        
        return ret;
    }
    
    /**
     * Define a linguagem/pais default
     */
    public void setDefaultLocale() {
        // o locale default sera portugues pois se tu executar em um maquina
        // q tenha como locale o espanhol, o metodo .getDefault() vai retornar
        // espanhol, e como nos nao tratamos espanhol (ainda?) a aplicacao nao
        // ira funcionar
        // TODO pegar esse locale de um arquivo de conf que tera sempre o ultimo
        // que o usuario escolheu?
        //this.currentLocale = Locale.getDefault();
        this.currentLocale = new Locale("pt", "BR");        
    }
    
    /**
     * Define a linguagem e o pais
     * @param language
     * @param country
     */
    public void setLanguage(String language, String country) {
        this.currentLocale = new Locale(language, country);        
    }
    
    public Locale getCurrentLocale() {
        return currentLocale;
    }
    
    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }
    
    public ResourceBundle getResourceBundle() {
        return this.resourceBundle;
    }
}
