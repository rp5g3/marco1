/*
 * Created on Mar 3, 2005
 */
package com.workcase.gui.custom.warning;

import java.awt.BorderLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;


/**
 * Componente visual que coloca um icone ao lado do componente que foi passado
 * para ele. Isso eh feito colocando uma borda em volta do panel com esse icone.
 * Serve por exemplo para campos de tela que sofrem alguma validacao e o
 * programador quiser indicar que este campo esta com algum problema. A
 * principio serve para qualquer componente que estende JComponent, sendo que
 * ate agora usamos apenas para campos que derivam de JTextComponent.
 * 
 * O uso desta classe eh similar ao de um JScrollPane, ou seja:
 * 
 * JPanel panel = new JPanel(); JTextField field = new JTextField();
 * this.add(new JWarningComponent(field));
 * 
 * A unica preocupacao que o programador deve tomar eh quando colocar/retirar a
 * borda de icone;
 * 
 * 	boolean bool = true; 
 * 	field.firePropertyChange("warnBorder",!bool, bool); //vai colocar a borda de warning
 * 
 * Ou se ainda tiver a referencia do JWarningComponent:
 * 
 * 	warn.setWarningBorder(true); // vai colocar a borda de warning
 * 
 * @author frodrigues
 */
@SuppressWarnings("serial")
public class JWarningComponent extends JPanel implements PropertyChangeListener {

    protected Border defaultBorder = null;

    protected WarningBorder warningBorder = null;

    private boolean defaultBorderOnFocus = true;

    protected JComponent component;

    public JWarningComponent(JComponent component) {
        this(component, "/imgs/warn.png", true);
    }

    public JWarningComponent(JComponent component, String iconPath) {
        this(component, iconPath, true);
    }

    public JWarningComponent(JComponent component, boolean defaultBorderOnFocus) {
        this(component, "/imgs/warn.png", defaultBorderOnFocus);
    }

    public JWarningComponent(JComponent component, String iconPath, boolean defaultBorderOnFocus) {
        super();
        this.component = component;
        this.defaultBorder = this.component.getBorder();        
        this.defaultBorderOnFocus = defaultBorderOnFocus;
        this.warningBorder = new WarningBorder(getIcon(iconPath));
        component.addPropertyChangeListener(this);

        this.initialize();
    }

    protected void initialize() {
        this.setLayout(new BorderLayout());
        this.add(this.component, BorderLayout.CENTER);

        if (this.defaultBorderOnFocus) {
            this.component.addFocusListener(new FocusAdapter() {
                public void focusGained(FocusEvent e) {
                    setWarningBorder(false);
                }
            });
        }
    }

    /**
     * Mudanca em alguma propriedade. Hoje soh eh tratado a mudanca de borda
     */
    public void propertyChange(PropertyChangeEvent evt) {
        String propName = evt.getPropertyName();

        if (propName.equals("warnBorder")) {
            Boolean b = (Boolean) evt.getNewValue();
            this.setWarningBorder(b.booleanValue());
            
        }
    }

    /**
     * Indica ou nao se a borda do icone deve ser usada.
     * 
     * @param bool
     */
    public void setWarningBorder(boolean bool) {
        if (bool) {
            this.setBorder(this.warningBorder);
            //this.component.setBorder(BorderFactory.createLineBorder(Color.RED));            
        } else {
            this.setBorder(BorderFactory.createEmptyBorder());
            this.component.setBorder(this.defaultBorder);
            this.component.setToolTipText(null);
        }
        this.setBackground(component.getBackground());
    }

    /**
     * @return Returns the defaultBorderOnFocus.
     */
    public boolean isDefaultBorderOnFocus() {
        return defaultBorderOnFocus;
    }

//    /**
//     * @param defaultBorderOnFocus The defaultBorderOnFocus to set.
//     */
//    public void setDefaultBorderOnFocus(boolean defaultBorderOnFocus) {
//        this.defaultBorderOnFocus = defaultBorderOnFocus;
//    }
    
    /**
     * @return Returns the textComponent.
     */
    public JComponent getComponent() {
        return component;
    }
    
    private static java.awt.Image getIcon(String name) {

		try {
			java.net.URL imURL = java.lang.Class.class.getResource(name);
			if (imURL != null) {
				java.awt.Image image = new javax.swing.ImageIcon(imURL)
						.getImage();				
				if (image == null) System.err.println("ERROR ... Image not found! " + name);
				return image;
			}
		} catch (java.lang.StackOverflowError e) {
			//TODO 
			e.printStackTrace();
		} catch (java.lang.Exception e) {
			//TODO 
			e.printStackTrace();
		}//end of catch block
		return null;
	}
}

