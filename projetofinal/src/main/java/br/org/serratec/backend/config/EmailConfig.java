package br.org.serratec.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class EmailConfig {

	@Autowired
	private JavaMailSender jms;
	
	public void enviarEmail(String para, String assunto, String texto) {
		SimpleMailMessage smm = new SimpleMailMessage();
		
		smm.setFrom("apirest.projetofinal.grupo03@gmail.com");
		smm.setTo(para);
		smm.setSubject(assunto);
		smm.setText("Seu cadastro foi realizado com sucesso! Abaixo encontram-se as informações do seu cadastro: \n\n" + texto);
		jms.send(smm);
	}
}
