/*
 * Created on Apr 5, 2005
 */
package com.workcase.gui.custom.report;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;

/**
 * Painel que mostra um botao de 'Report' e uma comboBox onde o usuario escolhe
 * o formato do relatorio
 * @author frodrigues
 */
@SuppressWarnings("serial")
public class ReportPanel extends JPanel {
    
    private static ResourceMessage messages = SpringResourceMessage.getInstance();
    protected JComboBox comboBox = null;
    
    public ReportPanel() {
        super();
        this.initialize();
    }
    
    protected void initialize() {        
        this.add(this.getReportButton());
        this.add(this.getComboBox());
    }
    
    protected JComboBox getComboBox() {
        if (this.comboBox == null) {
            Object[] items = {
                    //"Viewer" ,
                    "PDF" //,
                    //"XLS" ,
                    //"CSV" ,
                    //"XML" ,
                    //"HTML"
            };
            this.comboBox = new JComboBox(items);
            this.comboBox.setPreferredSize(new Dimension(75, 22));
        }
        return this.comboBox;
    }
    
    protected JButton getReportButton() {
        JButton b = new JButton(messages.getMessage("buttons.report"));
        b.setPreferredSize(new Dimension(75, 22));
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                report();
            }
        });        
        
        return b;
    }
    
    protected void report() {
        String type = (String) this.comboBox.getSelectedItem();        
        this.firePropertyChange("report", null, type.toUpperCase());
    }
}
