/*
 * Created on Mar 21, 2005
 */
package com.workcase.gui.utils;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FocusTraversalPolicy;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JComponent;

/**
 * Metodos utilitarios para a interface grafica
 * @author frodrigues
 */
public class UIUtil {

    /**
     * Retorna o ponto que o componente passado fique no centro da tela
     * @param comp Componente que deve ficar no centro
     * @return
     */
    public static Point getScreenCenter(Component comp) {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (d.getWidth() - comp.getWidth()) / 2;
        int height = (int) (d.getHeight() - comp.getHeight()) / 2;
        
        Point p = new Point(width, height);
        return p;
    }
    
    /**
     * Este metodo percorre todos os elementos que podem possuir foco neste container
     * e coloca a posicao dele nas propriedades do componente. Para pegar esta ordem deve-se
     * chamar:
     * 
     * 	Integer order = component.getClientProperty("focusOrder");
     * 
     * Este inteiro vai de zero ate o numero de elementos - 1
     * @param container
     * @return
     */
	public static boolean processFocus(Container container) {
	    try {
			Object obj[] = getFocusResponsible(container);
			if (obj == null)
			    return false;
			
			Container responsible = (Container) obj[0];
			FocusTraversalPolicy ftp = (FocusTraversalPolicy) obj[1];
			
			JComponent comp = (JComponent) ftp.getFirstComponent(responsible);
			JComponent lastComp = (JComponent) ftp.getLastComponent(responsible);	 	    
			if (comp != null){
				int index = 0;	    
				do {
				    comp.putClientProperty("focusOrder", new Integer(index++));
				} while((comp = (JComponent) ftp.getComponentAfter(responsible, comp)) != null && !lastComp.equals(comp));
				
				comp.putClientProperty("focusOrder", new Integer(index));
			}
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	
	/**
	 * Retorna quem eh o responsavel pelo foco. Esse metodo nao foi totalmente testado,
	 * mas para os casos que estamos usando, com um JInternalFrame esta funcionando.
	 * Ate para um JFrame com um JPanel funciona.
	 * @param container
	 * @return
	 */
	public static Object[] getFocusResponsible(Container container) {
	    Container last = container;
	    FocusTraversalPolicy ftp = null;
	    
	    while((container = last.getParent()) != null) {	        
	        ftp = container.getFocusTraversalPolicy();
	        last = container;
	        if (ftp != null) 
	    	        return new Object[] {last, ftp};	            	        
	    }	    
	    
	    return null;
	}
	
	public static void setFullScreen(Component comp) {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int) (d.getHeight() - 30);
        
        comp.setSize((int)d.getWidth(), height);
        comp.setLocation(0,0);
    }
}
