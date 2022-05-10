/*
 * Created on Mar 3, 2005
 */
package com.workcase.gui.utils;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;



/**
 * @author frodrigues
 */
@SuppressWarnings("serial")
public class IconCellRenderer extends JLabel implements ListCellRenderer {
        static ImageIcon longIcon = null;

        public IconCellRenderer() {
            Image image = ImageLoader.getInstance().getImage("icons/warn.png");
            if (image != null) {
                image = image.getScaledInstance(13, 13, 1);
                longIcon = new ImageIcon(image);
            }
        }
        
        public Component getListCellRendererComponent(JList list, Object value, // value to display
                int index, // cell index
                boolean isSelected, // is the cell selected
                boolean cellHasFocus) // the list and the cell have the focus
        {
            String s = value.toString();
            setText(s);
            setIcon(longIcon);
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            setEnabled(list.isEnabled());
            setFont(list.getFont());
            setOpaque(true);
            return this;
        }
    }