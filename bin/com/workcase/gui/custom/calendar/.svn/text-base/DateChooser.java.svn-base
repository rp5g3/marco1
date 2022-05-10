/*
 * Created on Feb 17, 2005
 */
package com.workcase.gui.custom.calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

/**
 * @author frodrigues
 * @author jmatzenbacher
 */
@SuppressWarnings("serial")
public class DateChooser extends JDateChooser {

    protected JTextField field;
    
    public DateChooser() {
        super();
        this.initialize();
    }

    public DateChooser(ImageIcon icon) {
        super(icon);
        this.initialize();
    }

    public DateChooser(boolean startEmpty) {
        super(startEmpty);
        this.initialize();
    }

    public DateChooser(String dateFormatString, boolean startEmpty) {
        super(dateFormatString, startEmpty);
        this.initialize();
    }

    public DateChooser(JCalendar jcalendar) {
        super(jcalendar);
        this.initialize();
    }

    public DateChooser(JCalendar jcalendar, String dateFormatString, boolean startEmpty, ImageIcon icon) {
        super(jcalendar, dateFormatString, startEmpty, icon);
        this.initialize();
    }
    
    public void initialize() {               
        calendarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DayChooser day = null;
                try {
                    day = (DayChooser) jcalendar.getDayChooser();
                } catch(Exception ex) {
                    ex.printStackTrace();
                    return;
                }

                day.setNewValue(getModel().getDate());
            }
        });      
        
        field = new JTextField();
        field.setEditable(false);        
        //field.setEnabled(false);
        field.setBackground(Color.WHITE);
        //refreshField();
        JSpinner spinner = this.getSpinner();
        spinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                refreshField();
            }
        });
        
        
        this.add(field, BorderLayout.CENTER);
    }
    
    public void refreshField() {
        Date d = (Date) getSpinner().getValue();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm a");        
        field.setText(df.format(d));        
    }
    
    public JButton getCalendarButton() {
        return this.calendarButton;
    }
    
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("day")) {
//            dateSelected = true;
//            popup.setVisible(false);
            setDate(jcalendar.getCalendar().getTime());
            setDateFormatString(dateFormatString);
        } else if (evt.getPropertyName().equals("date")) {
            setDate((Date) evt.getNewValue());
        } else if (evt.getPropertyName().equals("OK")) {
            dateSelected = true;
            popup.setVisible(false);
            setDate(jcalendar.getCalendar().getTime());
            setDateFormatString(dateFormatString);
        } else if (evt.getPropertyName().equals("hour")) {            
            Date dNew = (Date) evt.getNewValue();
            setDate(dNew);
        }        
    }    
}
