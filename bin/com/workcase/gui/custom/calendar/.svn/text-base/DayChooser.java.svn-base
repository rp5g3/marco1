/*
 * Created on Feb 18, 2005
 */
package com.workcase.gui.custom.calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;

import com.toedter.calendar.JDayChooser;
import com.workcase.gui.utils.SpringResourceMessage;

/**
 * @author frodrigues
 * @author jmatzenbacher
 */
@SuppressWarnings("serial")
public class DayChooser extends JDayChooser {
    
    protected JComboBox hourCombo;
    protected JComboBox minuteCombo;
    protected JRadioButton am;
    protected JRadioButton pm;
    protected JButton dateButton;


    /**
     * Cria um DayChooser com os valores defaults
     *
     */
    public DayChooser() {
        this(true, true, true);
    }

    /**
     * Cria um DayChooser
     * @param weekOfYearVisible Se a contagem da semana deve aparecer
     */
    public DayChooser(boolean weekOfYearVisible) {
        this(weekOfYearVisible, true);
    }    
    
    /**
     * Cria um DayChooser 
     * @param weekOfYearVisible Se a contagem da semana deve aparecer
     * @param displayTime Se deve mostrar a hora
     */
    public DayChooser(boolean weekOfYearVisible, boolean displayTime) {
        this(weekOfYearVisible, displayTime, true);
    }
    
    /**
     * Cria um DayChooser
     * @param weekOfYearVisible Se a contagem da semana deve aparecer
     * @param displayTime Se deve mostrar a hora
     * @param canClear Se o usuario pode apagar(pressionando um botao de clear) a hora
     */
    public DayChooser(boolean weekOfYearVisible, boolean displayTime, boolean canClear) {
        super(weekOfYearVisible);
        this.initialize(displayTime, canClear);
    }

    public void initialize(boolean displayTime, boolean canClear) {
        JPanel south = new JPanel();
        
        if (displayTime) { 
            south.add(createTimePanel());
        } else { 
            // tenho q criar os paneis mesmo q nao os mostre na tela
            // TODO melhorar isso, se nao eh pra mostrar, nao crie!!!
            createTimePanel(); 
        }
        
        
        south.add(createButtonOK());     
        
        if (canClear)
            south.add(createButtonClear());        
        
        this.add(south, BorderLayout.SOUTH);
        this.setAlwaysFireDayProperty(true);
    }   

    public JPanel createTimePanel() {
        Object[] obj = new Object[12];
        for(int i=0; i < obj.length; i++)
            obj[i] = fillZero("" + (i+1), 2);        
        hourCombo = new JComboBox(obj);
        
        obj = new Object[13];
        for(int i=0; i < obj.length - 1; i++)
            obj[i] = fillZero(i * 5 + "", 2);
        obj[12] = "...";
        
        minuteCombo = new JComboBox(obj);                   
        
        JPanel south = new JPanel();               
        south.add(hourCombo);        
        south.add(minuteCombo);
        
        am = new JRadioButton("AM");
        pm = new JRadioButton("PM");
        ButtonGroup group = new ButtonGroup();
        group.add(am);
        group.add(pm);        
        south.add(am);
        south.add(pm);        
                
        ActionListener myAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshTime();
            }
        };
        hourCombo.addActionListener(myAction);
        minuteCombo.addActionListener(myAction);
        am.addActionListener(myAction);
        pm.addActionListener(myAction);
                
        return south;
    }
    
    public JButton createButtonOK() {        
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DayChooser.this.firePropertyChange("OK", -1, 2);                
            }
        });  
        return ok;
    }
    
    public JButton createButtonClear() {
        // TODO o uso da SpringResourceMessage aqui nao eh o ideal, pois este componente deveria ser
        // totalmente independente. Usando a SpringResourceMessage aqui eu crio um amarracao desnecessaria
        JButton clear = new JButton(SpringResourceMessage.getInstance().getMessage("buttons.clear"));
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DayChooser.this.firePropertyChange("clearDate", -1, 2);
            }
        });             
        return clear;
    }
    
    /**
     * Verifica se o minuto esta na combo box
     * @param strMinutes
     * @return
     */
    public boolean isMinute(String strMinutes) {                
        ComboBoxModel cModel = minuteCombo.getModel();
        for(int i=0; i < cModel.getSize(); i++) 
            if (strMinutes.equals(cModel.getElementAt(i)))
                return true;           
              
        return false;
    }
    
    /**
     * Selectiona o minuto passado na combo box, se o minuto nao esta
     * na combo, insere ele e o seleciona
     * @param minute
     */
    public void selectMinute(int minute) {
        String strMinutes = fillZero(minute + "", 2);
        if (this.isMinute(strMinutes))
            minuteCombo.setSelectedItem(strMinutes);
        else 
            this.insertMinute(strMinutes);
    }
    
    /**
     * Insere o minuto na combo. Como eh uma combo ordenada, este metodo procura
     * pela posicao certa. 
     * @param strMinutes
     */
    public void insertMinute(String strMinutes) {
        DefaultComboBoxModel model = (DefaultComboBoxModel) this.minuteCombo.getModel();
        String atual;
        for(int i=0; i < model.getSize(); i++) {
            atual = (String) model.getElementAt(i);
            if (strMinutes.compareTo(atual) < 0) {
                model.insertElementAt(strMinutes, i);
                minuteCombo.setSelectedItem(strMinutes);
                return;
            }
        }    
        // se chegou aqui eh pq eh maior q 50 (ou maior q o ultimo inserido),
        // inserir no penultimo lugar, pois o ultimo eh '...'
        model.insertElementAt(strMinutes, model.getSize() - 1);
        minuteCombo.setSelectedItem(strMinutes);
    }
    
    @SuppressWarnings("deprecation")
	public void refreshTime() {
        String minute = (String) minuteCombo.getSelectedItem();
        int minutes;
        try {
            minutes = Integer.parseInt(minute);
        } catch(Exception ex) {
            // nao eh numero, eh o 'more'
            this.showAllMinutes();
            return;
        }
        
        Date d = calendar.getTime(); 
        int hour = Integer.parseInt((String) hourCombo.getSelectedItem());
        if (pm.isSelected() && hour != 12)
            hour += 12;        
        if (am.isSelected() && hour == 12)
            hour = 0;
        
        d.setHours(hour);
        
        //minutes = Integer.parseInt((String) minuteCombo.getSelectedItem());
        d.setMinutes(minutes);
        
        this.firePropertyChange("hour", null, d);        
    }
    
//    public void showAllMinutes() {
//        DefaultComboBoxModel model = (DefaultComboBoxModel) this.minuteCombo.getModel();
//        model.removeAllElements();
//        
//        for(int i=0; i < 60; i++)
//            model.addElement(fillZero(i + "" , 2)); 
//                        
//        // abre a combo novamente
//        SwingWorker s = new SwingWorker() {
//            public Object construct() {
//                minuteCombo.setPopupVisible(true);
//                return null;
//            }
//        };
//        s.start();
//    }
    
    MouseAdapter adapter = new MouseAdapter() {
    	public void mouseEntered(MouseEvent e) {
        	JButton b = (JButton) e.getSource();
        	b.setBorderPainted(true);
    	}
    	public void mouseExited(MouseEvent e) {
        	JButton b = (JButton) e.getSource();
        	b.setBorderPainted(false);
    	}
	};  

	ActionListener action = new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        closePopup(e.getSource());
	    }
	};    
	
	JPopupMenu mPopup = null;

	public void closePopup(Object e) {                
        JButton b = (JButton) e;
        b.setBorderPainted(false);
        String str = b.getText();
//        str = str.substring(1);
        this.selectMinute(Integer.parseInt(str));        
        mPopup.setVisible(false);
	    
	}

    public void showAllMinutes() {
        
        JPanel panel = new JPanel(new GridLayout(6, 10));
        panel.setBackground(Color.WHITE);                  	    
        JButton button = new JButton();
        Dimension d = new Dimension(30, 20); 
	    for(int i=0; i < 10; i++) {
	        for(int j=0; j < 6; j++) {
	            button = new JButton(DayChooser.fillZero(i * 6 + j + "", 2));
	            button.setMargin(new Insets(0, 0, 0, 0));
	            button.setBorderPainted(false);
	            Font f = button.getFont().deriveFont(Font.PLAIN);	            	            
	            button.setFont(f);
	            button.setBackground(Color.WHITE);
	            button.addMouseListener(adapter);
	            button.addActionListener(action);
	            button.setPreferredSize(d);
	            panel.add(button);	            
	        }
	    }
	    	            
        mPopup = new JPopupMenu();
        mPopup.add(panel);
        
        
        int x = minuteCombo.getWidth() - (int) mPopup.getPreferredSize().getWidth();
        int y = minuteCombo.getY() + minuteCombo.getHeight();
        mPopup.show(minuteCombo, x, y);        
    }
    
    @SuppressWarnings("deprecation")
	public void setNewValue(Date d) {
    	
        int hour;
		try {
			hour = d.getHours();
		} catch (Exception e) {
			d = new Date();
			hour = d.getHours();
		}
        if (hour > 12) {
            hour -= 12;
            pm.setSelected(true);
        } else if (hour == 12) {
            pm.setSelected(true);
        } else {
            am.setSelected(true);
            if (hour == 0)
                hour = 12;
        }
        
        int minute = d.getMinutes();       
        this.selectMinute(minute);        
        
        this.hourCombo.setSelectedItem(fillZero("" + hour, 2));        
    }    
    
    /**
     * Preenche de zero a esquerda ate o tamanho da string for igual ao parametro total
     * Por exemplo fillZero("2", 3) -> "002"
     * @param str
     * @param size
     * @return
     */
    public static String fillZero(String str, int total) {
        return fill(str, '0', total);
    }
    
    /**
     * Preenche com o parametro char passado a esquerda ate o tamanho da string for igual
     * ao paramerto total
     * Por exemplo fillZero("2", '1', 3) -> "112"
     * @param str
     * @param compl
     * @param total
     * @return
     */
    public static String fill(String str, char compl, int total) {
        int strSize = str.length();
        if (strSize == total)
            return str;
        
        StringBuffer buffer = new StringBuffer();
        strSize = total - strSize;
        for(int i=0; i < strSize; i++)
             buffer.append(compl);
        
        buffer.append(str);
        return buffer.toString();
    }    
}