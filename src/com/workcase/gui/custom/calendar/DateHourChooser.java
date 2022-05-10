/*
 * Created on Feb 23, 2005
 */
package com.workcase.gui.custom.calendar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

/**
 * @author frodrigues
 */
@SuppressWarnings("serial")
public class DateHourChooser extends JPanel implements ActionListener, PropertyChangeListener {

    protected JTextField dateHourField;
    protected PDSCalendar jcalendar;
    protected JPopupMenu popup;
    protected JButton calendarButton;
    protected Date date;
    protected boolean dateSelected;
    protected String dateFormatString;
    protected DateFormat dateFormat;
    protected Locale locale;
    protected boolean displaytime=false;

    /**
     * Cria um DateHourChooser com os valores defaults
     *
     */
    public DateHourChooser() {
        this(null, true, true, true);
    }

    /**
     * Cria um DateHourChooser com os valores defaults
     * @param locale Locale que o calendario deve obedecer
     */
    public DateHourChooser(Locale locale) {
        this(locale, true, true, true);
    }

    
    /**
     * Cria um DateHourChooser
     * @param displayTime Se deve mostrar a hora
     */
    public DateHourChooser(boolean displayTime) {
        this(null, displayTime, true, true);        
    }

    /**
     * Cria um DateHourChooser
     * @param locale Locale que o calendario deve obedecer
     * @param displayTime Se deve mostrar a hora
     */
    public DateHourChooser(Locale locale, boolean displayTime) {
        this(locale, displayTime, true, true);        
    }
    
    
    /**
     * Cria um DateHourChooser
     * @param displayTime Se deve mostrar a hora
     * @param canClear Se o usuario pode apagar(pressionando um botao de clear) a hora
     */
    public DateHourChooser(boolean displayTime, boolean canClear) {
        this(null, displayTime, canClear, true);        
    }

    /**
     * Cria um DateHourChooser
     * @param locale Locale que o calendario deve obedecer
     * @param displayTime Se deve mostrar a hora
     * @param canClear Se o usuario pode apagar(pressionando um botao de clear) a hora
     */
    public DateHourChooser(Locale locale, boolean displayTime, boolean canClear) {
        this(locale, displayTime, canClear, true);        
    }
    
    /**
     * Cria um DateHourChooser
     * @param displayTime Se deve mostrar a hora
     * @param canClear Se o usuario pode apagar(pressionando um botao de clear) a hora
     * @param isFieldEditable Se o textField pode ser editado pelo usuario atraves 
     * do teclado
     */
    public DateHourChooser(boolean displayTime, boolean canClear, boolean isFieldEditable) {
        this(null, displayTime, canClear, isFieldEditable);
    }
    
    /**
     * Cria um DateHourChooser
     * @param locale Locale que o calendario deve obedecer
     * @param displayTime Se deve mostrar a hora
     * @param canClear Se o usuario pode apagar(pressionando um botao de clear) a hora
     * @param isFieldEditable Se o textField pode ser editado pelo usuario atraves 
     * do teclado
     */
    public DateHourChooser(Locale locale, boolean displayTime, boolean canClear, boolean isFieldEditable) {
        super();
        this.initialize(locale, displayTime, canClear, isFieldEditable);
    }    
    
    protected void initialize(Locale locale, boolean displayTime, boolean canClear, boolean isFieldEditable) {
        this.locale = locale;
        this.setLayout(new BorderLayout());
        this.displaytime=displayTime;
        this.initDateFormat(displayTime);        
        date = Calendar.getInstance().getTime();        
        this.initCalendarButton();
        this.initDateHourField(isFieldEditable);
        this.initCalendar(displayTime, canClear);
        this.initPopup();        
    }

    /**
     * Inicializa a popup que mostra o calendario
     *
     */
    protected void initPopup() {
        popup = new JPopupMenu() {
            public void setVisible(boolean b) {
                Boolean isCanceled = (Boolean) getClientProperty("JPopupMenu.firePopupMenuCanceled");

                if (b || (!b && dateSelected) || ((isCanceled != null) && !b && isCanceled.booleanValue())) {
                    super.setVisible(b);
                }
            }
        };

        popup.setLightWeightPopupEnabled(true);
        popup.add(jcalendar);        
    }
    
    /**
     * Inicializa o calendar
     * @param displayTime
     * @param canClear
     */
    protected void initCalendar(boolean displayTime, boolean canClear) {
        this.jcalendar = new PDSCalendar(this.locale, displayTime, canClear);
        
        this.jcalendar.getDayChooser().addPropertyChangeListener(this);        
    }
    
    /**
     * Inicializa o TextField da data/hora
     * @param isFieldEditable
     */
    protected void initDateHourField(boolean isFieldEditable) {
        this.dateHourField = new JTextField();
        if (!isFieldEditable)
            this.dateHourField.setEditable(false);
        this.add(dateHourField, BorderLayout.CENTER);
    }
    
    /**
     * Inicializa o botao do calendario
     *
     */
    protected void initCalendarButton() {
        URL iconURL = getClass().getResource("images/JDateChooserIcon.gif");
        calendarButton = new JButton(new ImageIcon(iconURL));
        calendarButton.setPreferredSize(new Dimension(22, -1));
        calendarButton.addActionListener(this);        
        this.add(calendarButton, BorderLayout.EAST);        
    }
    
    /**
     * Inicializa o formato da data
     * @param displayTime
     */
    protected void initDateFormat(boolean displayTime) {
        if (displayTime) {
            this.dateFormatString = "dd/MM/yyyy hh:mm a";
        } else {
            this.dateFormatString = "dd/MM/yyyy";
        }
        
        if (this.locale == null) {            
            this.dateFormat = new SimpleDateFormat(this.dateFormatString);
        } else {            
            this.dateFormat = new SimpleDateFormat(this.dateFormatString, this.locale);
        }
    }
    
    public void setDateFormat(String dateFormat) {
        this.setDateFormat(dateFormat, null);
    }
    
    public void setDateFormat(String dateFormat, String timeFormat) {
        this.dateFormatString = "";
        if (dateFormat != null)
            this.dateFormatString = dateFormat;
        if (timeFormat != null)
            this.dateFormatString += " " + timeFormat;
        
        this.dateFormatString = this.dateFormatString.trim();
        
        if (this.locale == null) {            
            this.dateFormat = new SimpleDateFormat(this.dateFormatString);
        } else {            
            this.dateFormat = new SimpleDateFormat(this.dateFormatString, this.locale);
        }        
    }
    
    public void actionPerformed(ActionEvent e) {
        int x = calendarButton.getWidth() - (int) popup.getPreferredSize().getWidth();
        int y = calendarButton.getY() + calendarButton.getHeight();

        if (date != null) {
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date);
	        jcalendar.setCalendar(calendar);
	        DayChooser day = (DayChooser) jcalendar.getDayChooser();
	        day.setNewValue(date);
        }
        
        popup.show(calendarButton, x, y);
        dateSelected = false;        
    }

    @SuppressWarnings("deprecation")
	public void propertyChange(PropertyChangeEvent evt) {
        String propName = evt.getPropertyName();        
        if (propName.equals("day")) {
            setDay(jcalendar.getCalendar().getTime());
        } else if (propName.equals("date")) {
            setDate((Date) evt.getNewValue());
        } else if (propName.equals("OK")) {           
            dateSelected = true;
            popup.setVisible(false);   
            if (this.date != null) {
                this.date.setMonth(this.getJCalendar().getMonthChooser().getMonth());
            	this.date.setYear(this.getJCalendar().getYearChooser().getYear() - 1900);
            	this.drawDate();
            }/*else{
            	this.date = new Date();
            	this.date.setMonth(this.getJCalendar().getMonthChooser().getMonth());
            	this.date.setYear(this.getJCalendar().getYearChooser().getYear() - 1900);
            	this.drawDate();
            }*/
        } else if (propName.equals("hour")) {            
            Date dNew = (Date) evt.getNewValue();
            setTime(dNew);
        } else if (propName.equals("clearDate")) {
            dateSelected = true;
            popup.setVisible(false);
            clearDate();            
        }
    }
    
    public void clearDate() {
        this.date = null;
        dateHourField.setText("");
    }
    
    public void drawDate() {
        dateHourField.setText(dateFormat.format(this.date));
        if (getParent() != null) {
            getParent().validate();
        }                
    }

    @SuppressWarnings("deprecation")
	public void setDay(Date date) {
        if (this.date == null)
            this.date = new Date();
        this.date.setDate(date.getDate());
        this.date.setMonth(this.getJCalendar().getMonthChooser().getMonth());
        this.date.setYear(this.getJCalendar().getYearChooser().getYear() - 1900);
        this.drawDate();
    }
    
    @SuppressWarnings("deprecation")
	public void setTime(Date dt) {
        if (this.date == null)
            this.date = new Date();        
        this.date.setMonth(this.getJCalendar().getMonthChooser().getMonth());
        this.date.setYear(this.getJCalendar().getYearChooser().getYear() - 1900); 
        if (displaytime){
	        try {
				this.date.setHours(dt.getHours());
			} catch(java.lang.IllegalArgumentException e){
				e.printStackTrace();
			}catch (RuntimeException e) {
				e.printStackTrace();
			}
	        try {
				this.date.setMinutes(dt.getMinutes());
			} catch(java.lang.IllegalArgumentException e){
				e.printStackTrace();
			}catch (RuntimeException e) {
				e.printStackTrace();
			}
        }
        this.drawDate();
    }   
    
    public void setDate(Date date) {
        this.date = date;                
        this.drawDate();
    }    
    
    public PDSCalendar getJCalendar() {
        return this.jcalendar;
    }
    /**
     * @return Returns the date.
     */
    public Date getDate() {
        return date;
    }
    /**
     * @return Returns the popup.
     */
    public JPopupMenu getPopup() {
        return popup;
    }
    
    public void setLocale(Locale locale) {
        this.jcalendar.setLocale(locale);
    }

	public JTextField getDateHourField() {
		return dateHourField;
	}

	public JButton getCalendarButton() {
		return calendarButton;
	}
}