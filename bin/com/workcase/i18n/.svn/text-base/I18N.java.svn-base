/*
 * Created on Feb 22, 2005
 */
package com.workcase.i18n;

import java.util.Locale;
import java.util.ResourceBundle;


/**
 * Classe que busca as strings de cada idioma/pais dos arquivos de mensagens
 * @author frodrigues
 */
public class I18N {
    private Locale currentLocale;
    private ResourceBundle messages;
    private static I18N instance = null;
    
    protected I18N() {
        this.setDefaultLocale();
    }

    public static I18N getInstance() {
        if (instance == null)
            instance = new I18N();

        return instance;
    }

    /**
     * Define a linguagem e o pais
     * @param language
     * @param country
     */
    public void setLanguage(String language, String country) {
        this.currentLocale = new Locale(language, country);        
        this.initResourcesBundle();
    }
    
    /**
     * Define como a linguagem/pais o default da maquina/java
     */
    public void setDefaultLocale() {
        // o locale default sera ingles pois se tu executar em um maquina
        // q tenha como locale o espanhol, o metodo .getDefault() vai retornar
        // espanhol, e como nos nao tratamos espanhol (ainda?) a aplicacao nao
        // ira funcionar
        // TODO pegar esse locale de um arquivo de conf que tera sempre o ultimi
        // que o usuario escolheu
        //this.currentLocale = Locale.getDefault();
        this.currentLocale = new Locale("en", "US");        
        this.initResourcesBundle();
    }
    
    /**
     * Inicializa o ResourcesBundle de acordo com o Locale
     */
    public void initResourcesBundle() {
        this.messages = ResourceBundle.getBundle("com.workcase.i18n.MessagesBundle", currentLocale);
    }

    /**
     * Retorna a string da chave passada. Se essa chave nao existe no arquivo, eh
     * retornada a chave com um ponto de exclamacao no inicio e no final -> !key!
     * @param key
     * @return
     */
    public String getString(String key) {
        try {
            return this.messages.getString(key);
        } catch(Exception ex) {
            ex.printStackTrace();
            return "!" + key + "!";
        }
    }
    
    /**
     * Retorna o Locale usado
     * @return
     */
    public Locale getCurrentLocale() {
        return currentLocale;
    }
    
    /**
     * Retorna o ResourceBundle
     * @return
     */
    public ResourceBundle getResourceBundle() {
        return messages;
    }        
}
