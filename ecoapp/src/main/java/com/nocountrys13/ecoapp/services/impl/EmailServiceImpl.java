package com.nocountrys13.ecoapp.services.impl;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.nocountrys13.ecoapp.entities.EmailVerification;
import com.nocountrys13.ecoapp.entities.Usuario;
import com.nocountrys13.ecoapp.repositories.EmailRepository;
import com.nocountrys13.ecoapp.repositories.UsuarioRepository;
import com.nocountrys13.ecoapp.services.IEmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements IEmailService {

	private final JavaMailSender emailSender;
	private final TemplateEngine templateEngine;
	private final EmailRepository emailRepository;
	private final UsuarioRepository usuarioRepository;

	@Value("MAIL_USER")
	String Originmail;

	@Override
	public void sendVerificationEmail(Usuario user) {

		try {
			EmailVerification emailVeridication = new EmailVerification();
			emailVeridication.setToken(generateVerificationCode());
			emailVeridication.setCreationTime(LocalDateTime.now());
			emailVeridication.setExpirationTime(LocalDateTime.now().plusMinutes(1));
			emailVeridication.setUsuario(user);
			user.setEmailVerification(emailVeridication);
			emailRepository.save(emailVeridication);

			emailSender.send(CreateMensaje("bienvenida", user));

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@Override
	public MimeMessage CreateMensaje(String template, Usuario user) throws MessagingException {

		Context context = new Context();
		context.setVariable("token", user.getEmailVerification().getToken());
		context.setVariable("userName", user.getNombre() + " " + user.getApellido());
		context.setVariable("userId", user.getUserId());

		String htmlContent = templateEngine.process(template, context);

		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom(Originmail);
		helper.setTo(user.getEmail());
		helper.setSubject("GREEPOINT");
		helper.setText(htmlContent, true);

		return message;
	}

	@Override
	public void verifyEmail(String token, UUID userId) throws Exception, ResponseStatusException {
		

		EmailVerification email = emailRepository.findByTokenAndUsuarioId( userId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "codigo o usuarii invalido"));

		Usuario user = email.getUsuario();
				
		if(user.getValidEmail()) {
	       throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "el correo ya ha sido validado");
			 
		}
		
			
		if (LocalDateTime.now().isAfter(email.getExpirationTime())) {
			email.setExpirationTime(LocalDateTime.now().plusMinutes(2));
			email.setCreationTime(LocalDateTime.now());
			email.setToken(generateVerificationCode());
			emailRepository.save(email);
			
			emailSender.send(CreateMensaje("reenviarEmail",  user));
			
	        throw new Exception("La validación ha expirado y se ha enviado un nuevo enlace por correo electrónico");
			
		}  
	
		user.setValidEmail(true);
		usuarioRepository.save(user);

	}

	private String generateVerificationCode() {
		Random random = new Random();
		Long code = random.nextLong(900000000);
		return String.valueOf(code);
	}

}
