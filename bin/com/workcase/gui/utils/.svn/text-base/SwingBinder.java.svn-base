/*
 * Created on Mar 7, 2005
 */
package com.workcase.gui.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.JTree;
import javax.swing.text.JTextComponent;

import com.workcase.gui.custom.calendar.DateHourChooser;
import com.workcase.utils.Moeda;
import com.workcase.utils.Util;


/**
 * Responsavel em fazer o bind de componentes graficos do swing com os seus
 * respectivos beans. Primeiro registra cada campo visual com o SwingBinder
 * com o metodo addBindProperty passando o bean, o componete visual e o nome
 * do atributo do bean que sera mapeado. Quando quiser popular o bean chama-se
 * o metodo bind.
 * Suporte os seguintes componentes visuais:
 * - JTextComponent e seus derivados (JTextField, JTextArea, JPasswordField...)
 * - JList (FIXME nao esta funcionando)
 * - JSpinner
 * - JToggleButton e seus derivados (JCheckBox, JRadioButton)
 * - JTree (FIXME nao esta funcionando)
 * - JComboBox 
 * - JSlider
 * - DateHourChooser (componente feito por nos que estende JCalendar)
 * @author frodrigues
 */
public class SwingBinder {
	private String entityPackage = "com.adapit.portal.entidades";
    /**
     * Hash que tem como (chave/valor) o (bean/lista de bindValues)
     */
    @SuppressWarnings("unchecked")
	protected Map hashBind;

    /**
     * Cria um SwingBinder
     */
    @SuppressWarnings("unchecked")
	public SwingBinder() {
        this.hashBind = new HashMap();
    }

    /**
     * Mapeia o componente visual com o bean passado. O atributo que sera mapeado
     * eh aquele que tiver como nome o retorno de component.getName()
     * @param bean Bean que sera populado
     * @param component Componente visual que ira popula o atributo
     */
    public void addBindProperty(Object bean, JComponent component) {
        this.addBindProperty(bean, component, component.getName());
    }
    
    /**
     * Mapeia o atributo passado deste bean com o componente visual 
     * @param bean Bean que sera populado
     * @param component Componente visual que ira popula o atributo
     * @param property Nome da propriedade
     */
    @SuppressWarnings("unchecked")
	public void addBindProperty(Object bean, JComponent component, String property) {
        List list = (List) this.hashBind.get(bean);
        if (list == null) {
            list = new ArrayList(bean.getClass().getDeclaredFields().length);
            this.hashBind.put(bean, list);
        }

        list.add(new BindValue(component, property));        
    }

    /**
     * Seta os valores nos componentes graficos
     * TODO o bind do JComboBox nao esta funcionando, provavelmente o da JList
     * tb nao esteja 
     * @param component
     * @param value
     */
    public void setValue(JComponent component, Object value) throws Exception {
    	if (component instanceof DateHourChooser) {
            DateHourChooser calendar = (DateHourChooser) component;
            if (value == null)
                calendar.clearDate();
            else
                calendar.setDate((Date) value);
        }
    	else if (component instanceof JTextComponent) {
            JTextComponent textComponent = (JTextComponent) component;
            if (value == null) textComponent.setText("");
            else if (value instanceof String) textComponent.setText((String) value);
            else textComponent.setText(""+ value);
        } else if (component instanceof JComboBox) {
            JComboBox combo = (JComboBox) component;            
            this.setComboBoxValue(combo, value);
        } else if (component instanceof JList) {
            // TODO JList suporta selecao multipla, como vou tratar isso??
            JList list = (JList) component;
            list.setSelectedValue(value, true);
        } else if (component instanceof JTree) {
            // TODO como vou tratar a selecao em JTree, o caminho todo ou apenas
            // o ultimo nodo. E a selecao multipla?                        
            JTree tree = (JTree) component;
            @SuppressWarnings("unused")
			Object obj = tree.getSelectionPath().getLastPathComponent();
            //tree.setSelectionPath();            
        } else if (component instanceof JSpinner) {
            JSpinner spinner = (JSpinner) component;
            spinner.setValue(value);
        } else if (component instanceof JToggleButton) {
            JToggleButton toggle = (JToggleButton) component;
            Boolean bool = (Boolean) value;
            toggle.setSelected(bool.booleanValue());
        } else if (component instanceof JSlider) {
            JSlider slider = (JSlider) component;
            Integer integer = (Integer) value;
            slider.setValue(integer.intValue());
        } 
    }
    
    /**
     * 
     * @param comboBox
     * @param value
     */
    private void setComboBoxValue(JComboBox comboBox, Object value) {
        if (value == null)
            return;
        
        comboBox.setSelectedItem(value);
/*        ComboBoxModel model = comboBox.getModel();
        int size = model.getSize();
        Object obj;
        for(int i=0; i < size; i++) {
            obj = model.getElementAt(i);
            if (this.equals(value, obj)) {
                model.setSelectedItem(value);
                return;                
            }
        }*/
    }
    
    /**
     * 
     * @param source
     * @param dest
     * @return
     */
    @SuppressWarnings("unused")
	private boolean equals(Object source, Object dest) {
        if (source == null || dest == null)
            return false;
        
        if (source.equals(dest))
            return true;
        
        try {
            Method method = source.getClass().getMethod("getId", (Class[]) null);
            Object retSource = method.invoke(source, (Object[]) null);
            
            method = dest.getClass().getMethod("getId", (Class[]) null);
            Object retDest = method.invoke(dest, (Object[]) null);
            
            if (retSource.equals(retDest))
                return true;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        return false;
    }
    
    /**
     * Popula os campos graficos com os valores dos beans
     * @param bean
     * @return
     */
    @SuppressWarnings("unchecked")
	public boolean reverseBind(Object bean) {
        List list = (List) this.hashBind.get(bean);
        if (list == null)
            return false;

        BindValue bindValue;
        String property, methodName;
        JComponent component;
        Method method = null;
        Object value = null;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            bindValue = (BindValue) it.next();
            property = bindValue.getProperty();
            component = bindValue.getComponent();

            // pega o metodo get
            methodName = Util.getGetMethodName(property);

            // chama o metodo get deste campo e seta o valor no componente
            try {
                method = bean.getClass().getMethod(methodName, (Class[])null);
                value = method.invoke(bean, (Object[])null);
                
                this.setValue(component, value);
            } catch (Exception ex) {
                ex.printStackTrace();
                continue;
            }
        }
        
        return true;
    }
    
    /**
     * Popula o bean com os valores dos campos graficos
     * TODO tem q ver como vai ser tratado os erros de conversoes, pois essa validacao
     * era pra acontecer qdo o commons validator valida o bean, mas antes disso eu preciso
     * popular os campos do bean com seu valores de acordo com o tipo, dai vai acabar 
     * acontecendo uma validacao ai
     * @param bean
     * @return true se o bean vai ser tratado(nao quer dizer q todos os seus 
     * atributos foram tratados), false caso contrario
     */
    @SuppressWarnings({ "unchecked", "unchecked" })
	public boolean bind(Object bean) {
        List list = (List) this.hashBind.get(bean);
        if (list == null)
            return false;

        BindValue bindValue;
        String property, methodName;
        JComponent component;
        Field field = null;
        Class clazzType = null;
        Method method = null;
        Object value = null;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            bindValue = (BindValue) it.next();
            property = bindValue.getProperty();
            component = bindValue.getComponent();
            
            // pega o campo para saber qual o tipo dele
            try {
                field = bean.getClass().getDeclaredField(property);
            } catch (NoSuchFieldException ex) {            	
            	Class clazz = bean.getClass().getSuperclass();
            	while(clazz != null){
	            	try {
	            		field = clazz.getDeclaredField(property);
					} catch (NoSuchFieldException e) {
						clazz = clazz.getSuperclass();
						continue;
					}
					clazz = null;
            	}
            	if (field == null) continue;
			}catch (Exception ex) {
                ex.printStackTrace();
                continue;
            }

            clazzType = field.getType();
            methodName = Util.getSetMethodName(property);
            Class[] types = {clazzType};

            // chama o metodo set deste campo
            
            try {
                method = bean.getClass().getMethod(methodName, types);
                if (method.getParameterTypes() != null && method.getParameterTypes().length > 0){
                	String simpleName = method.getParameterTypes()[0].getSimpleName();
                	if (simpleName.equals("ArrayList")||
                			simpleName.equals("List")||
                			simpleName.equals("Vector")||
                			simpleName.equals("Map")||
                			simpleName.equals("HashMap")||
                			simpleName.equals("Hashtable")) continue;
                	if (method.getParameterTypes()[0].getName().indexOf(entityPackage) > 0) continue;
                }
                value = this.getValue(component, clazzType);
                
                Object[] values = {value};
                method.invoke(bean, values);
            } catch(java.lang.IllegalArgumentException ie){
            	ie.printStackTrace();                
            } catch (Exception ex) {
                ex.printStackTrace();
                continue;
            }
        }
        
        return true;
    }

    
    @SuppressWarnings("unchecked")
	private Hashtable<String,Enum> langEnums = new Hashtable<String, Enum>();
    @SuppressWarnings("unchecked")
	public void putEnumValue(String value, Enum en){
    	langEnums.put(value, en);
    }
    @SuppressWarnings("unchecked")
	public void addEnumValue(String value, Enum en){
    	langEnums.put(value, en);
    }
    @SuppressWarnings("unchecked")
	public void putInternationalizedEnumValue(String value, Enum en){
    	langEnums.put(value, en);
    }
    @SuppressWarnings("unchecked")
	public void addInternationalizedEnumValue(String value, Enum en){
    	langEnums.put(value, en);
    }
    /**
     * Retorna o valor do component 
     * @param component
     * @param type
     * @return
     */
    @SuppressWarnings("unchecked")
	private Object getValue(JComponent component, Class type) {
        if (component instanceof JComboBox) {
        	JComboBox combo = (JComboBox) component;        	
        	if (type.isEnum()) {
        		Object obj = combo.getSelectedItem();
        		if (obj instanceof String){
        			String str = (String) obj;
        			if(langEnums.containsKey(str)){
        				return langEnums.get(str);
        			}else{
        				str = str.replace(" ", "_");
        				return Enum.valueOf(type, str);
        			}
        		}
        		else return obj;
	        }else{            
	        	Object obj = combo.getSelectedItem();
            	return obj;
	        }            
        }
        if (component instanceof JTextComponent) {
            JTextComponent textComponent = (JTextComponent) component;
            return this.getValue(type, textComponent.getText());
        }
        if (component instanceof JList) {            
            JList list = (JList) component;
            Object[] obj = list.getSelectedValues();
            java.util.List objList = new ArrayList();
            for (int i=0; i < obj.length; i++){
            	objList.add(obj[i]);
            }
            return objList;
        }

        if (component instanceof JTree) {
            // TODO como vou tratar a selecao em JTree, o caminho todo ou apenas
            // o ultimo nodo. E a selecao multipla?
            JTree tree = (JTree) component;
            Object obj = tree.getSelectionPath().getLastPathComponent();
            if (obj == null)
                return null;            
            Object ret = this.getValue(type, obj.toString());
            if (ret == null)
                return obj;
        }
        
        if (component instanceof JSpinner) {
            JSpinner spinner = (JSpinner) component;            
            Object obj = spinner.getValue();
            Object ret = this.getValue(type, obj.toString());
            if (ret == null)
                return obj;
        }        
        
        if (component instanceof JToggleButton) {
            JToggleButton toggle = (JToggleButton) component;            
            boolean bool = toggle.isSelected();
            return this.getValue(type, bool ? "true" : "false");
        }         

        if (component instanceof JSlider) {
            JSlider slider = (JSlider) component;            
            int value = slider.getValue();
            return this.getValue(type, value + "");
        }
        
        if (component instanceof DateHourChooser) {
            DateHourChooser calendar = (DateHourChooser) component;
            return calendar.getDate();
        }
        
        return null;
    }

    /**
     * Retorna o valor de acordo com o tipo
     * Trata o tipos primitivos e seus wrapped (String, Integer, Double...)
     * @param type
     * @param value
     * @return
     */
    @SuppressWarnings("unchecked")
	public Object getValue(Class type, String value) {
        String clazzName = type.getName();        
        
        if (type == String.class) {
            return value;
        }
        
        if (value.trim().length() == 0) {
            return null;                
        }

        try {                   
	        if (clazzName.equals("char") || type == Character.class) {
	            return new Character(value.charAt(0));
	        }
	        if (clazzName.equals("byte") || type == Byte.class) {
	            return new Byte(value);
	        }
	        if (clazzName.equals("short") || type == Short.class) {
	            return new Short(value);
	        }
	        if (clazzName.equals("int") || type == Integer.class) {
	            return new Integer(value);
	        }
	        if (clazzName.equals("float") || type == Float.class) {
	        	//return Moeda.getValorReal(new Float(value));
	        	return new Float(Moeda.valorRealToFloat(value)); 
	        	
	            //return new Float(value);
	        }
	        if (clazzName.equals("long") || type == Long.class) {
	        	
	            return new Long(value);
	        }
	        if (clazzName.equals("double") || type == Double.class) {
	        	return new Double(Moeda.valorRealToDouble(value));
	            //return new Double(value);
	        }
	        if (clazzName.equals("boolean") || type == Boolean.class) {
	            return new Boolean(value);
	        }
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
    
    public boolean removeBean(Object bean) {
        Object obj = this.hashBind.remove(bean);                
        return obj != null;
    }

    /**
     * @return Returns the hashBind.
     */
    @SuppressWarnings("unchecked")
	public Map getHashBind() {
        return hashBind;
    }
}

/**
 * Apenas uma estrutura de dados para facilitar a operacao de bind
 * @author frodrigues
 */
class BindValue {   
    private JComponent component;
    private String property;

    public BindValue(JComponent component, String property) {
        this.component = component;
        this.property = property;
    }

    /**
     * @return Returns the component.
     */
    public JComponent getComponent() {
        return component;
    }

    /**
     * @param component The component to set.
     */
    public void setComponent(JComponent component) {
        this.component = component;
    }

    /**
     * @return Returns the property.
     */
    public String getProperty() {
        return property;
    }

    /**
     * @param property The property to set.
     */
    public void setProperty(String property) {
        this.property = property;
    }
}
