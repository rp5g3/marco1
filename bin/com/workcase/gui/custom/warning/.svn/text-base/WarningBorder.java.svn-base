/*
 * Created on Mar 3, 2005
 */
package com.workcase.gui.custom.warning;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.border.AbstractBorder;

/**
 * Borda que desenha um icone do lado esquerdo do componente que o usar.
 * Serve por exemplo, para indicar que um campo esta errado em alguma validacao
 * @author frodrigues
 */
@SuppressWarnings("serial")
public class WarningBorder extends AbstractBorder {
    protected Image image;
    
    public WarningBorder(Image image) {
        this.setImage(image);
    }
    
    

	/**
     * This default implementation does no painting.
     * @param c the component for which this border is being painted
     * @param g the paint graphics
     * @param x the x position of the painted border
     * @param y the y position of the painted border
     * @param width the width of the painted border
     * @param height the height of the painted border
     */
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Color oldColor = g.getColor();

        g.setColor(Color.BLACK);
        for(int i = 0; i < 1; i++)  {	    
                g.drawRoundRect(x+i, y+i, width-i-i-1, height-i-i-1, 1, 1);
        }
        
//        int py = y + height - image.getHeight(c);
//        int px = x + width - image.getWidth(c);        
//        g.drawImage(image, px, py, c);
//        g.drawImage(image, x + 2, y + 2, c);
        
        if (image != null) {
            g.drawImage(image, x + 2, (height-image.getHeight(c))/2, c);
    	}
        
        g.setColor(oldColor);        
    }    
    
    /**
     * Returns the insets of the border.
     * @param c the component for which this border insets value applies
     */
    public Insets getBorderInsets(Component c) {
        return getBorderInsets();
    }

    /** 
     * Reinitialize the insets parameter with this Border's current Insets. 
     * @param c the component for which this border insets value applies
     * @param insets the object to be reinitialized
     */
    public Insets getBorderInsets(Component c, Insets insets) {
        int width = 0;
        if (image != null)
            width = image.getWidth(c);        
        insets.left = width + 2;
        insets.top = 1;
        insets.right = 1;
        insets.bottom = 1;
        return insets;
    }
    
    /**
     * Returns the insets of the border.
     */
    public Insets getBorderInsets() {
        int width = 0;
        if (image != null)
            width = image.getWidth(null);
           
        return new Insets(1, width + 2, 1, 1);
    }     
    
    public void setImage(Image image) {
        if (image == null)
            return;
        
        this.image = image;  
        this.image = new ImageIcon(this.image.getScaledInstance(13, 13, 1)).getImage();
    }
}
