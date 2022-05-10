/*
 * Created on Feb 14, 2005
 */
package com.workcase.gui.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.JComponent;


import org.apache.commons.validator.Arg;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.Form;
import org.apache.commons.validator.Msg;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorResources;
import org.apache.commons.validator.ValidatorResult;
import org.apache.commons.validator.ValidatorResults;

/**
 * Validacoes de valores.
 * 
 * @author frodrigues
 */
public class Validate {

    protected ValidatorResources resources = null;

    /**
     * Construtor do Validate
     * TODO A validacao precisa de 2 arquivos XML, mas eles ficam em lugares
     * diferente qdo estou desenvolvendo no eclipse e qdo vou executar direto de
     * um .jar, entao estou tratando os 2 casos aqui, mas isto *nao* esta legal!!!
     * Fazer de uma outra maneira
     */
    public Validate() {
        InputStream in[] = new InputStream[2]; 
        try {                                     
            in[0] = new FileInputStream("web/WEB-INF/validation.xml");
            in[1] = new FileInputStream("web/WEB-INF/validator-rules.xml");            
        } catch(Exception ex) {
            if (in[0] == null) {
                try {	                
	                URL url = getClass().getResource("/validation.xml");
	                System.out.println(url);
	                if (url != null) {
	                    in[0] = url.openStream();	                    	                    
	                }
	                url = getClass().getResource("/validator-rules.xml");                
	                if (url != null) {
	                    in[1] = url.openStream();	                    
	                }                                       
                } catch(Exception ex1) {
                    ex1.printStackTrace();
                    return;
                }
            }            
        }
                                        
        try {
            resources = new ValidatorResources(in);
            for (int i = 0; i < in.length; i++)
                in[i].close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Valida o bean e retorna o resultado em um Map. Este Map tem como chave
     * o nome do componente que falhou na validacao, e como valor a chave da mensagem
     * de erro
     * @param bean
     * @param beanName
     * @return
     */
    @SuppressWarnings("unchecked")
	public Map validate(Object bean, String beanName) {
        Validator validator = new Validator(resources, beanName);
        validator.setParameter(Validator.BEAN_PARAM, bean);
        validator.setOnlyReturnErrors(true);
        try {
            ValidatorResults results = validator.validate();
            return this.processResults(beanName, results);
        }catch (org.apache.commons.validator.ValidatorException ex) {
        	if (ex.getCause() != null) System.err.println(ex.getCause().getMessage());
            ex.printStackTrace();
        } 
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * Processa o resultado da validacao. Retorna o resultado em um Map. 
     * Este Map tem como chave o nome do componente que falhou na validacao, 
     * e como valor a chave da mensagem de erro
     * @param beanName
     * @param results
     * @return
     */
    @SuppressWarnings("unchecked")
	public Map processResults(String beanName, ValidatorResults results) {
        Form form = resources.getForm(Locale.getDefault(), beanName);

        Map errors = null;
        Iterator propertyNames = results.getPropertyNames().iterator();
        while (propertyNames.hasNext()) {
            String propertyName = (String) propertyNames.next();
            Field field = form.getField(propertyName);
            ValidatorResult result = results.getValidatorResult(propertyName);

            Map actionMap = result.getActionMap();
            Iterator keys = actionMap.keySet().iterator();
            while (keys.hasNext()) {
                String actName = (String) keys.next();                
                if (!result.isValid(actName)) {
                    if (errors == null)
                        errors = new HashMap();
                    
                    Msg msg = (Msg) field.getMessages().get(actName);
                    int index = 0;
                    List args = new ArrayList();
                    while(true) {
                        Arg arg = field.getArg(index++);
                        if (arg == null)
                            break;
                        args.add(arg.getKey());                    
                    }                                
                    
                    Object obj[] = new Object[2];
                    if (msg != null)
                        obj[0] = msg.getKey();
                    else
                        obj[0] = "errors." + actName;                    
                                       
                    if (!args.isEmpty())
                        obj[1] = args; 
                    errors.put(propertyName, obj);
                }
            }
        }
        
        return errors;        
    } 

    /**
     * Ordena o resultado de acordo com a ordem do foco da tela. Esta ordenacao
     * eh feita com o BubbleSort, pois nao deve-se ter muitos campos com erros
     * em uma tela
     * @param errors
     * @param hashComps
     * @return
     */
    @SuppressWarnings("unchecked")
	public static Map.Entry[] getOrder(Map errors, Map hashComps) {
        Map.Entry[] entrys = new Map.Entry[errors.size()];
        errors.entrySet().toArray(entrys);

        String name;
        JComponent comp;
        int order, orderAux;
        Map.Entry entryTmp;
        boolean swap = false;
        int index = 1;
        Object obj;
        while (true) {
            swap = false;
            for (int i = 0; i < entrys.length - index; i++) {
                name = (String) entrys[i].getKey();
                comp = (JComponent) hashComps.get(name);
                if (comp == null) continue;
                obj = (Integer) comp.getClientProperty("focusOrder");
                if (obj == null) // nao teve sua ordem de foco definida
                    continue;
                order = ((Integer) obj).intValue();

                name = (String) entrys[i + 1].getKey();
                comp = (JComponent) hashComps.get(name);
                obj = (Integer) comp.getClientProperty("focusOrder");
                if (obj == null) // nao teve sua ordem de foco definida
                    continue;
                orderAux = ((Integer) obj).intValue();

                if (order > orderAux) {
                    entryTmp = entrys[i];
                    entrys[i] = entrys[i + 1];
                    entrys[i + 1] = entryTmp;
                    swap = true;
                }
            }
            if (!swap)
                break;
            index++;
        }                
        return entrys;
    }
        
    /**
     * Verifica se a string passada eh um numero
     * 
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        if (str == null || str.trim().length() == 0)
            return false;

        try {
            Double.parseDouble(str);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    /**
     * Verifica se a string passada eh um email
     * 
     * @param str
     * @return
     */
    public static boolean isEmail(String str) {
        if (str == null || str.trim().length() == 0)
            return false;

        return Pattern.matches("[A-Za-z0-9_.-]+@([A-Za-z0-9_]+\\.)+[A-Za-z]{2,4}", str);
    }

    /**
     * Verifica se a string passada eh soh caracter
     * 
     * @param str
     * @return
     */
    public static boolean isCharacter(String str) {
        if (str == null || str.trim().length() == 0)
            return false;

        //return Pattern.matches("[A-Za-z]", str);
        return Pattern.matches("[A-Za-zÀ-ü ]*", str);
    }
}