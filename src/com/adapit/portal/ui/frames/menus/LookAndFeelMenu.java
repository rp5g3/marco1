/*
 * LookAndFeelMenu.java
 *
 * Created on 20 de Fevereiro de 2008, 15:20
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.adapit.portal.ui.frames.menus;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.fife.plaf.OfficeXP.OfficeXPLookAndFeel;
import org.hibernate.Session;

import com.adapit.portal.dto.UsuarioDTO;
import com.adapit.portal.entidades.UserLayoutPreferences;
import com.adapit.portal.services.local.LocalServicesUtility;
import com.adapit.portal.ui.forms.usuario.TelaLoginUsuario;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.incors.plaf.kunststoff.KunststoffLookAndFeel;
import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.theme.DesertBlue;
import com.pagosoft.plaf.PgsLookAndFeel;
import com.pagosoft.plaf.PlafOptions;
import com.pagosoft.plaf.themes.SilverTheme;

/**
 *
 * @author user
 */
@SuppressWarnings("serial")
public class LookAndFeelMenu extends JMenu{

    
    public LookAndFeelMenu() {
        super("Apresentação");
        add(getSystemLookAndFeelMenuItem());
        add(getWindowsClassicLookAndFeelMenuItem());
        add(getXpLookAndFeelMenuItem());
        add(getKunststoffLookAndFeelMenuItem());
        add(getJavaLookAndFeelMenuItem());
        add(getMetalLookAndFeelMenuItem());
        add(getMotifLookAndFeelMenuItem());
        add(getMacLookAndFeelMenuItem());
        add(getPlasticMenu());
        add(getPgsLookAndFeelMenuItem());
        add(getOfficeMenu());
        //add(getJaneThemeMenuItem());
        //add(getMeutoUiLFMenuItem());
        //add(getOyoahaMenuItem());
        add(getQuaquaMenuItem());
        add(getSquarenessMenuItem());
        add(getFhMenuItem());
    }
    
    
    @SuppressWarnings("unchecked")
	private void changeLookAndFeelClass(Class className){
    	Session s =null;
    	try{
    		s = LocalServicesUtility.getInstance().openSession();
    		s.beginTransaction();
    		String str = className.getName();
    		UsuarioDTO user = TelaLoginUsuario.getInstance().getUsuarioDTO();
    		if(user.getPreferences() != null){
    			user.getPreferences().setLookAndFeelClassName(str);
    			s.update(user.getPreferences());
    		}else{
    			UserLayoutPreferences pref = new UserLayoutPreferences();
    			pref.setLogin(user.getId());
    			pref.setLookAndFeelClassName(str);
    			user.setPreferences(pref);
    			s.save(pref);
    		}
    		s.getTransaction().commit();
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}finally{
    		if (s != null && s.isOpen()) s.close();
    	}
    }
    
    private void changeLookAndFeelClass(String name){
    	Session s =null;
    	try{
    		s = LocalServicesUtility.getInstance().openSession();
    		s.beginTransaction();
    		String str = name;
    		UsuarioDTO user = TelaLoginUsuario.getInstance().getUsuarioDTO();
    		if(user.getPreferences() != null){
    			user.getPreferences().setLookAndFeelClassName(str);
    			s.update(user.getPreferences());
    		}else{
    			UserLayoutPreferences pref = new UserLayoutPreferences();
    			pref.setLogin(user.getId());
    			pref.setLookAndFeelClassName(str);
    			s.save(pref);
    		}
    		s.getTransaction().commit();
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}finally{
    		if (s != null && s.isOpen()) s.close();
    	}
    }
    
    private JMenuItem windowsClassicLookAndFeelMenuItem;
    
    public JMenuItem getWindowsClassicLookAndFeelMenuItem(){
        if (windowsClassicLookAndFeelMenuItem == null){
            windowsClassicLookAndFeelMenuItem = new JMenuItem("Windows Classic");
            windowsClassicLookAndFeelMenuItem.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evt) {
                	changeLookAndFeelClass("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
                    try {
                        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
                        // Schedule a job for the event-dispatching thread:
                        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                SwingUtilities.updateComponentTreeUI(AdapitVirtualFrame.getInstance());
                            }
                        });
                        
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
        return windowsClassicLookAndFeelMenuItem;
    }
    
    private JMenuItem xpLookAndFeelMenuItem;
    
    public JMenuItem getXpLookAndFeelMenuItem(){
        if (xpLookAndFeelMenuItem == null){
            xpLookAndFeelMenuItem = new JMenuItem("Windows XP");
            xpLookAndFeelMenuItem.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evt) {
                	changeLookAndFeelClass(OfficeXPLookAndFeel.class);
                    try {
                        UIManager.setLookAndFeel(new OfficeXPLookAndFeel());
                        // Schedule a job for the event-dispatching thread:
                        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                SwingUtilities.updateComponentTreeUI(AdapitVirtualFrame.getInstance());
                            }
                        });
                    } catch (UnsupportedLookAndFeelException e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
        return xpLookAndFeelMenuItem;
    }
    
    private JMenuItem KunststoffLookAndFeelMenuItem;
    
    public JMenuItem getKunststoffLookAndFeelMenuItem(){
        if (KunststoffLookAndFeelMenuItem == null){
            KunststoffLookAndFeelMenuItem = new JMenuItem("Kunststoff");
            KunststoffLookAndFeelMenuItem.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evt) {
                	changeLookAndFeelClass(KunststoffLookAndFeel.class);
                    try {
                        UIManager.setLookAndFeel(new KunststoffLookAndFeel());
                        // Schedule a job for the event-dispatching thread:
                        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                SwingUtilities.updateComponentTreeUI(AdapitVirtualFrame.getInstance());
                            }
                        });
                    } catch (UnsupportedLookAndFeelException e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
        return KunststoffLookAndFeelMenuItem;
    }
    
    private JMenuItem SystemLookAndFeelMenuItem;
    
    public JMenuItem getSystemLookAndFeelMenuItem(){
        if (SystemLookAndFeelMenuItem == null){
            SystemLookAndFeelMenuItem = new JMenuItem("Native");
            SystemLookAndFeelMenuItem.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evt) {
                	changeLookAndFeelClass(UIManager.getSystemLookAndFeelClassName());
                    try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                        // Schedule a job for the event-dispatching thread:
                        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                SwingUtilities.updateComponentTreeUI(AdapitVirtualFrame.getInstance());
                            }
                        });
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
        return SystemLookAndFeelMenuItem;
    }
    
    private JMenuItem javaLookAndFeelMenuItem;
    
    public JMenuItem getJavaLookAndFeelMenuItem(){
        if (javaLookAndFeelMenuItem == null){
            javaLookAndFeelMenuItem = new JMenuItem("Java");
            javaLookAndFeelMenuItem.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evt) {
                	changeLookAndFeelClass(UIManager.getCrossPlatformLookAndFeelClassName());
                    try {
                        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                        // Schedule a job for the event-dispatching thread:
                        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                SwingUtilities.updateComponentTreeUI(AdapitVirtualFrame.getInstance());
                            }
                        });
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
        return javaLookAndFeelMenuItem;
    }
    
    private JMenuItem motifLookAndFeelMenuItem;
    
    public JMenuItem getMotifLookAndFeelMenuItem(){
        if (motifLookAndFeelMenuItem == null){
            motifLookAndFeelMenuItem = new JMenuItem("Motif");
            motifLookAndFeelMenuItem.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evt) {
                	changeLookAndFeelClass("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                    try {
                        UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                        // Schedule a job for the event-dispatching thread:
                        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                SwingUtilities.updateComponentTreeUI(AdapitVirtualFrame.getInstance());
                            }
                        });
                        
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
        return motifLookAndFeelMenuItem;
    }
    
    private JMenuItem MetalLookAndFeelMenuItem;
    
    public JMenuItem getMetalLookAndFeelMenuItem(){
        if (MetalLookAndFeelMenuItem == null){
            MetalLookAndFeelMenuItem = new JMenuItem("Metal");
            MetalLookAndFeelMenuItem.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evt) {
                	changeLookAndFeelClass("javax.swing.plaf.metal.MetalLookAndFeel");
                    try {
                        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                        // Schedule a job for the event-dispatching thread:
                        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                SwingUtilities.updateComponentTreeUI(AdapitVirtualFrame.getInstance());
                            }
                        });
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
        return MetalLookAndFeelMenuItem;
    }
    
    private JMenuItem MacLookAndFeelMenuItem;
    
    public JMenuItem getMacLookAndFeelMenuItem(){
        if (MacLookAndFeelMenuItem == null){
            MacLookAndFeelMenuItem = new JMenuItem("Mac");
            MacLookAndFeelMenuItem.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evt) {
                	changeLookAndFeelClass("javax.swing.plaf.mac.MacLookAndFeel");
                    try {
                        UIManager.setLookAndFeel("javax.swing.plaf.mac.MacLookAndFeel");
                        // Schedule a job for the event-dispatching thread:
                        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                SwingUtilities.updateComponentTreeUI(AdapitVirtualFrame.getInstance());
                            }
                        });
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
        return MacLookAndFeelMenuItem;
    }
    

        
    private JMenuItem PgsLookAndFeelMenuItem;
    
    public JMenuItem getPgsLookAndFeelMenuItem(){
        if (PgsLookAndFeelMenuItem == null){
            PgsLookAndFeelMenuItem = new JMenuItem("Pgs");
            PgsLookAndFeelMenuItem.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evt) {
                	changeLookAndFeelClass(PgsLookAndFeel.class);
                    PlafOptions.setDefaultMenuItemIconSize(new Dimension(20,25));
                    PlafOptions.setCurrentTheme(new SilverTheme());
                    try {
                        UIManager.setLookAndFeel( new PgsLookAndFeel());
                        // Schedule a job for the event-dispatching thread:
                        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                SwingUtilities.updateComponentTreeUI(AdapitVirtualFrame.getInstance());
                            }
                        });
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
        return PgsLookAndFeelMenuItem;
    }
    
    private JMenu officeMenu;
    
    private JMenu getOfficeMenu(){
        if (officeMenu == null){
            officeMenu = new JMenu("Office");
            officeMenu.add(getOfficeLookAndFeelMenuItem());
            officeMenu.add(getOfficeXPLookAndFeelMenuItem());
            officeMenu.add(getOfficeStudioLookAndFeelMenuItem());
            
        }
        return officeMenu;
    }
    

    
    private JMenuItem officeLookAndFeelMenuItem;
    
    public JMenuItem getOfficeLookAndFeelMenuItem(){
        if (officeLookAndFeelMenuItem == null){
            officeLookAndFeelMenuItem = new JMenuItem("2003");
            officeLookAndFeelMenuItem.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evt) {
                    try {
                    	changeLookAndFeelClass("org.fife.plaf.Office2003.Office2003LookAndFeel");
                        PlasticLookAndFeel.setPlasticTheme(new DesertBlue());
                        UIManager.setLookAndFeel(new org.fife.plaf.Office2003.Office2003LookAndFeel());
                        // Schedule a job for the event-dispatching thread:
                        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                SwingUtilities.updateComponentTreeUI(AdapitVirtualFrame.getInstance());
                            }
                        });
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
        return officeLookAndFeelMenuItem;
    }
    

    
    private JMenuItem officeXPLookAndFeelMenuItem;
    
    public JMenuItem getOfficeXPLookAndFeelMenuItem(){
        if (officeXPLookAndFeelMenuItem == null){
            officeXPLookAndFeelMenuItem = new JMenuItem("XP");
            officeXPLookAndFeelMenuItem.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evt) {
                    try {
                    	changeLookAndFeelClass("org.fife.plaf.OfficeXP.OfficeXPLookAndFeel");
                        PlasticLookAndFeel.setPlasticTheme(new DesertBlue());
                        UIManager.setLookAndFeel(new org.fife.plaf.OfficeXP.OfficeXPLookAndFeel());
                        // Schedule a job for the event-dispatching thread:
                        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                SwingUtilities.updateComponentTreeUI(AdapitVirtualFrame.getInstance());
                            }
                        });
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
        return officeXPLookAndFeelMenuItem;
    }
    

    
    private JMenuItem officeStudioLookAndFeelMenuItem;
    
    public JMenuItem getOfficeStudioLookAndFeelMenuItem(){
        if (officeStudioLookAndFeelMenuItem == null){
            officeStudioLookAndFeelMenuItem = new JMenuItem("Visual Studio");
            officeStudioLookAndFeelMenuItem.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evt) {
                    try {
                    	changeLookAndFeelClass("org.fife.plaf.VisualStudio2005.VisualStudio2005LookAndFeel");
                        PlasticLookAndFeel.setPlasticTheme(new DesertBlue());
                        UIManager.setLookAndFeel(new org.fife.plaf.VisualStudio2005.VisualStudio2005LookAndFeel());
                        // Schedule a job for the event-dispatching thread:
                        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                SwingUtilities.updateComponentTreeUI(AdapitVirtualFrame.getInstance());
                            }
                        });
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
        return officeStudioLookAndFeelMenuItem;
    }
    
    private JMenu plasticMenu;
    
    private JMenu getPlasticMenu(){
        if (plasticMenu == null){
            plasticMenu = new JMenu("Plastic");
            plasticMenu.add(getPlasticWindowsLookAndFeelMenuItem());
            plasticMenu.add(getPlasticLookAndFeelMenuItem());
            plasticMenu.add(getPlastic3DLookAndFeelMenuItem());
            plasticMenu.add(getPlasticXpLookAndFeelMenuItem());
        }
        return plasticMenu;
    }
    

    
    private JMenuItem plastic3DLookAndFeelMenuItem;
    
    public JMenuItem getPlastic3DLookAndFeelMenuItem(){
        if (plastic3DLookAndFeelMenuItem == null){
            plastic3DLookAndFeelMenuItem = new JMenuItem("3D");
            plastic3DLookAndFeelMenuItem.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evt) {
                    try {
                    	changeLookAndFeelClass(Plastic3DLookAndFeel.class);
                        PlasticLookAndFeel.setPlasticTheme(new DesertBlue());
                        UIManager.setLookAndFeel(new Plastic3DLookAndFeel());
                        // Schedule a job for the event-dispatching thread:
                        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                SwingUtilities.updateComponentTreeUI(AdapitVirtualFrame.getInstance());
                            }
                        });
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
        return plastic3DLookAndFeelMenuItem;
    }
    
    

    
    private JMenuItem plasticWindowsLookAndFeelMenuItem;
    
    public JMenuItem getPlasticWindowsLookAndFeelMenuItem(){
        if (plasticWindowsLookAndFeelMenuItem == null){
            plasticWindowsLookAndFeelMenuItem = new JMenuItem("Windows");
            plasticWindowsLookAndFeelMenuItem.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evt) {
                    try {
                    	changeLookAndFeelClass("com.jgoodies.looks.windows.WindowsLookAndFeel");
                        PlasticLookAndFeel.setPlasticTheme(new DesertBlue());
                        UIManager.setLookAndFeel(new com.jgoodies.looks.windows.WindowsLookAndFeel());
                        // Schedule a job for the event-dispatching thread:
                        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                SwingUtilities.updateComponentTreeUI(AdapitVirtualFrame.getInstance());
                            }
                        });
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
        return plasticWindowsLookAndFeelMenuItem;
    }
    
    private JMenuItem plasticLookAndFeelMenuItem;
    
   
    
    public JMenuItem getPlasticLookAndFeelMenuItem(){
        if (plasticLookAndFeelMenuItem == null){
            plasticLookAndFeelMenuItem = new JMenuItem("Plastic");
            plasticLookAndFeelMenuItem.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evt) {
                    try {
                    	changeLookAndFeelClass("com.jgoodies.looks.plastic.PlasticLookAndFeel");
                        PlasticLookAndFeel.setPlasticTheme(new DesertBlue());
                        UIManager.setLookAndFeel(new com.jgoodies.looks.plastic.PlasticLookAndFeel());
                        // Schedule a job for the event-dispatching thread:
                        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                SwingUtilities.updateComponentTreeUI(AdapitVirtualFrame.getInstance());
                            }
                        });
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
        return plasticLookAndFeelMenuItem;
    }
    
    private JMenuItem plasticXpLookAndFeelMenuItem;
    
    public JMenuItem getPlasticXpLookAndFeelMenuItem(){
        if (plasticXpLookAndFeelMenuItem == null){
            plasticXpLookAndFeelMenuItem = new JMenuItem("Windows XP");
            plasticXpLookAndFeelMenuItem.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evt) {
                    try {
                    	changeLookAndFeelClass("com.jgoodies.looks.plastic.PlasticXPLookAndFeel");
                        PlasticLookAndFeel.setPlasticTheme(new DesertBlue());
                        UIManager.setLookAndFeel(new com.jgoodies.looks.plastic.PlasticXPLookAndFeel());
                        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                SwingUtilities.updateComponentTreeUI(AdapitVirtualFrame.getInstance());
                            }
                        });
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
        return plasticXpLookAndFeelMenuItem;
    }
    
    private JMenuItem janeThemeMenuItem;
    
    public JMenuItem getJaneThemeMenuItem(){
        if (janeThemeMenuItem == null){
            janeThemeMenuItem = new JMenuItem("Jane Look And Feel");
            janeThemeMenuItem.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evt) {
                    try {
                    	changeLookAndFeelClass("jane.ui.lf.jane.JaneLookAndFeel");
                        UIManager.setLookAndFeel(new jane.ui.lf.jane.JaneLookAndFeel());
                        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                SwingUtilities.updateComponentTreeUI(AdapitVirtualFrame.getInstance());
                            }
                        });
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
        return janeThemeMenuItem;
    }
    
    private JMenuItem meutoUiLFMenuItem;
    
    public JMenuItem getMeutoUiLFMenuItem(){
        if (meutoUiLFMenuItem == null){
            meutoUiLFMenuItem = new JMenuItem("Meuto Look And Feel");
            meutoUiLFMenuItem.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evt) {
                    try {
                    	changeLookAndFeelClass("net.sourceforge.mlf.metouia.MetouiaLookAndFeel");                    			
                        UIManager.setLookAndFeel(new net.sourceforge.mlf.metouia.MetouiaLookAndFeel());
                        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                SwingUtilities.updateComponentTreeUI(AdapitVirtualFrame.getInstance());
                            }
                        });
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
        return meutoUiLFMenuItem;
    }

    
    private JMenuItem oyoahaMenuItem;
    
    public JMenuItem getOyoahaMenuItem(){
        if (oyoahaMenuItem == null){
            oyoahaMenuItem = new JMenuItem("Yooaha Look And Feel");
            oyoahaMenuItem.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evt) {
                    try {
                    	changeLookAndFeelClass("com.oyoaha.swing.plaf.oyoaha.OyoahaLookAndFeel");
                        UIManager.setLookAndFeel(new com.oyoaha.swing.plaf.oyoaha.OyoahaLookAndFeel());
                        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                SwingUtilities.updateComponentTreeUI(AdapitVirtualFrame.getInstance());
                            }
                        });
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
        return oyoahaMenuItem;
    }
    
    private JMenuItem quaquaMenuItem;
    
    public JMenuItem getQuaquaMenuItem(){
        if (quaquaMenuItem == null){
            quaquaMenuItem = new JMenuItem("Quaqua Look And Feel");
            quaquaMenuItem.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evt) {
                    try {
                    	changeLookAndFeelClass("ch.randelshofer.quaqua.QuaquaLookAndFeel");
                        UIManager.setLookAndFeel(new ch.randelshofer.quaqua.QuaquaLookAndFeel());
                        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                SwingUtilities.updateComponentTreeUI(AdapitVirtualFrame.getInstance());
                            }
                        });
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
        return quaquaMenuItem;
    }
    
    
    private JMenuItem squarenessMenuItem;
    
    public JMenuItem getSquarenessMenuItem(){
        if (squarenessMenuItem == null){
            squarenessMenuItem = new JMenuItem("Squareness Look And Feel");
            squarenessMenuItem.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evt) {
                    try {
                    	changeLookAndFeelClass("net.beeger.squareness.SquarenessLookAndFeel");
                        UIManager.setLookAndFeel(new net.beeger.squareness.SquarenessLookAndFeel());
                        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                SwingUtilities.updateComponentTreeUI(AdapitVirtualFrame.getInstance());
                            }
                        });
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
        return squarenessMenuItem;
    }
    
    private JMenuItem fhMenuItem;
    
    public JMenuItem getFhMenuItem(){
        if (fhMenuItem == null){
            fhMenuItem = new JMenuItem("FH Look And Feel");
            fhMenuItem.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evt) {
                    try {
                    	changeLookAndFeelClass("com.shfarr.ui.plaf.fh.FhLookAndFeel");
                        UIManager.setLookAndFeel(new com.shfarr.ui.plaf.fh.FhLookAndFeel());
                        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                SwingUtilities.updateComponentTreeUI(AdapitVirtualFrame.getInstance());
                            }
                        });
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
        return fhMenuItem;
    }
}
