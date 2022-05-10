/*
 * Created on Feb 17, 2005
 */
package com.workcase.gui.custom.calendar;

import java.awt.BorderLayout;
import java.util.Locale;

import com.toedter.calendar.JCalendar;

/**
 * @author frodrigues
 * @author jmatzenbacher
 */
@SuppressWarnings("serial")
public class PDSCalendar extends JCalendar {
    
    /**
     * Cria um Calendar com os valores default
     *
     */
    public PDSCalendar() {
        this(null, true, true);
    }

    /**
     * Cria um Calendar com os valores default
     * @param locale Locale que o calendar deve obedecer
     */
    public PDSCalendar(Locale locale) {
        this(locale, true, true);
    }    
    
    /**
     * Cria um Calendar
     * @param displayTime Se deve mostrar a hora 
     */
    public PDSCalendar(boolean displayTime) {       
        this(null, displayTime, true);        
    }

    /**
     * Cria um Calendar
     * @param locale Locale que o calendar deve obedecer
     * @param displayTime Se deve mostrar a hora 
     */
    public PDSCalendar(Locale locale, boolean displayTime) {       
        this(locale, displayTime, true);        
    }    
    
    /**
     * Cria um Calendar
     * @param displayTime Se deve mostrar a hora
     * @param canClear Se o usuario pode apagar(pressionando um botao de clear) a hora
     */
    public PDSCalendar(boolean displayTime, boolean canClear) {
        this(null, displayTime, canClear);
    }

    /**
     * Cria um Calendar
     * @param Locale locale Locale que o calendar deve obedecer
     * @param displayTime Se deve mostrar a hora
     * @param canClear Se o usuario pode apagar(pressionando um botao de clear) a hora
     */
    public PDSCalendar(Locale locale, boolean displayTime, boolean canClear) {
        super(null, locale, true, true);        
        this.initialize(locale, displayTime, canClear);
        this.setLocale(locale);
    }    
    
    protected void initialize(Locale myLocale, boolean displayTime, boolean canClear) {        
        dayChooser = new DayChooser(weekOfYearVisible, displayTime, canClear);
        dayChooser.setLocale(myLocale);
        dayChooser.addPropertyChangeListener(this);
        add(dayChooser, BorderLayout.CENTER);
        
        monthChooser.setDayChooser(dayChooser);
        yearChooser.setDayChooser(dayChooser);
    }          
}
