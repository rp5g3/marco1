/*
 * Created on Feb 21, 2005
 */
package com.workcase.gui.custom.calendar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;

/**
 * @author frodrigues
 */
@SuppressWarnings("serial")
public class HourChooser extends JPanel {

    protected JSpinner spinner;
    protected SpinnerDateModel model;
    
    public HourChooser() {
        super();
        this.initialize(true);
    }

    public HourChooser(boolean showButton) {
        super();
        this.initialize(showButton);
    }

    protected void initialize(boolean showButton) {
        this.setLayout(new BorderLayout());

        this.spinner = new JSpinner();

        Object[] obj = { "" };
        SpinnerListModel model1 = new SpinnerListModel(obj);
        spinner.setModel(model1);

        this.add(this.spinner, BorderLayout.CENTER);

        if (showButton) {
            JButton b = new JButton("O");
            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    initializeHour();
                }
            });
            b.setPreferredSize(new Dimension(24, 10));
            this.add(b, BorderLayout.EAST);
        } else {
            this.initializeHour();
        }
    }

    protected void initializeHour() {
        this.model = new SpinnerDateModel();
        this.spinner.setModel(this.model);
        JSpinner.DateEditor editor2 = new JSpinner.DateEditor(this.spinner, "hh:mm a");
        this.spinner.setEditor(editor2);
    }
    
    public SpinnerDateModel getModel() {
        return this.model;
    }
}