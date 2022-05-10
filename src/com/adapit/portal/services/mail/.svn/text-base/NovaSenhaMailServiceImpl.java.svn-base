package com.adapit.portal.services.mail;

import org.hibernate.Session;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.adapit.portal.entidades.Fisica;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.local.LocalServicesUtility;
import com.workcase.utils.IdGenerator;

public class NovaSenhaMailServiceImpl implements NovaSenhaMailService {
	private MailSender mailSender;
	private SimpleMailMessage message;
	private LocalServicesUtility service = LocalServicesUtility.getInstance();
	 
	public boolean changePasswordByEmail(String email) throws Exception {
		Usuario usuario=null;
		try {
			Session s = service.openSession();
			usuario = (Usuario) s.createQuery("select user from Usuario user where user.dadosPessoais.email=:email")
			.setParameter("email", email)
			.uniqueResult();
			System.out.println("Usuário não encontrado para o email informado!");
			if (usuario == null) return false;
			
			
			SimpleMailMessage msg = new SimpleMailMessage(this.message);
			msg.setTo(usuario.getDadosPessoais().getEmail());
			String text="";
			text+="Caro(a) " + usuario.getDadosPessoais().getNome();
			if (usuario.getDadosPessoais().getTipoPessoa() instanceof Fisica){
				text+=" "+((Fisica)usuario.getDadosPessoais().getTipoPessoa()).getSobrenome();
			}
			text+=", você acaba de requisitar uma nova senha no sistema da Adapit Soluções em TI!";
			text+='\n'+""+"Esta senha foi gerada. Seus novos dados são mostrados abaixo: "+'\n';
			text+='\n'+""+".............................................."+'\n';
			text+='\n'+""+"      Seu login é: " + usuario.getLogin();
			String senha = IdGenerator.getInstance().generateId(8);
			text+='\n'+""+"      Sua nova senha é: " + senha;
			text+='\n'+""+".............................................."+'\n';
			
			s.createQuery("update Usuario user set user.password=:pwd where user.login=:login")
			.setParameter("pwd",Usuario.encript(senha))
			.setParameter("login",usuario.getLogin())
			.executeUpdate();
			System.out.println("Senha normal " + senha);
			System.out.println("Senha encriptografada " + Usuario.encript(senha));
			msg.setText(text);
			try{
				mailSender.send(msg);
			}
			catch(MailException ex) {
				System.err.println(ex.getMessage());
			}
			return true;
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
		return false;
	}

	public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public SimpleMailMessage getMessage() {
		return message;
	}

	public void setMessage(SimpleMailMessage message) {
		this.message = message;
	}

}
