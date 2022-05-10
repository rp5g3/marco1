package com.adapit.portal.ui.forms.pessoa;

import com.adapit.portal.entidades.PreferenciaCategoria;

public class PreferenciaUsuario {
	
	public static final String titles[]={
		"Receber news por email",
		"Receber email de publicações",
		"Receber email atualizações",
		"Tem interesse em treinamentos",
		"Tem interesse em consultoria",
		"Receber email sobre eventos"};
	
	public static final String properties[]={
		"receberNotificacaoNewsByEmail",
		"receberEmailNovosProdutos",
		"receberEmailAtualizacoesSoftware",		
		"interesseEmTreinamentos",
		"interesseEmConsultoria",
		"receberEmailSobreEvento"};
	
	public static Object[][] setPreferenciaUsuario(PreferenciaCategoria pref){
		java.lang.Object values[][] = new java.lang.Object[6][2];
		try{
			values[0][0] = titles[0];
			values[0][1] = pref!=null?pref.isReceberNotificacaoNewsByEmail():false;
			
			values[1][0] = titles[1];
			values[1][1] = pref!=null?pref.isReceberEmailNovosProdutos():false;
			
			values[2][0] = titles[2];
			values[2][1] = pref!=null?pref.isReceberEmailAtualizacoesSoftware():false;
			
			values[3][0] = titles[3];
			values[3][1] = pref!=null?pref.isInteresseEmTreinamentos():false;
			
			values[4][0] = titles[4];
			values[4][1] = pref!=null?pref.isInteresseEmConsultoria():false;				
			
			values[5][0] = titles[5];
			values[5][1] = pref!=null?pref.isReceberEmailSobreEventos():false;		
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return values;
	}
}
