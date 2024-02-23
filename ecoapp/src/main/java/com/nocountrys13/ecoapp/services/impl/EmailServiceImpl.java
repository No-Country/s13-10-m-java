package com.nocountrys13.ecoapp.services.impl;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.nocountrys13.ecoapp.entities.EmailVerification;
import com.nocountrys13.ecoapp.entities.Usuario;
import com.nocountrys13.ecoapp.repositories.EmailRepository;
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

	@Override
	public void sendVerificationEmail(Usuario user) {

		// creamos el tken de verificacion
		String token = generateVerificationCode() ;

		// guardamos el nombre dl user en esta variable para utilizarlo en el contexto
		String userName = user.getNombre() + " " + user.getApellido();
		String userId= String.valueOf(user.getUserId());
		
		// creamos una instancia de la entidad emailverification y seteamos valores
		EmailVerification emailVeridication = new EmailVerification();

		emailVeridication.setToken(token);
		emailVeridication.setCreationTime(LocalDateTime.now());
		// le agregamos 10 minutos para que en ese tiempo verifique el email
		emailVeridication.setExpirationTime(LocalDateTime.now().plusMinutes(10));
		emailVeridication.setUsuario(user);

		// creamos un contexto para enviar al html los dtos de usuario que se registró
		Context context = new Context();
		context.setVariable("token", token);
		context.setVariable("userName", userName);
		context.setVariable("userId", userId);

		// con template engine procesamos nuestra plantilla y le agregamos el context para acceder a las variables
		String htmlContent = templateEngine.process("index", context);

		try {
			// creamos el mensaje a enviar con mimemessage y mimemessagehelper
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			
			//setiamos los datos del email
			helper.setFrom("leonardovargas5d2017@gmail.com");
			helper.setTo(user.getEmail());
			helper.setSubject("ECOAPP");
			helper.setText(htmlContent, true);

			// enviamos el email
			emailSender.send(message);
			
			emailRepository.save(emailVeridication);

		} catch (MailSendException e) {
            // Excepción lanzada por Spring cuando no se puede entregar el correo
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo entregar el correo electrónico. Verifica la dirección de correo.", e);
            
		}catch (MessagingException e) {
			
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST, e.getMessage());

		}

	}

	
	@Override
	public void verifyEmail(String token, String userId) {

		//to-do --> implementar esta logica verificacion buscar el usuario y cambiarel estado de la variable validemail a true
		
		
	}
	
	// Generar un código aleatorio 
		private String generateVerificationCode() {
			Random random = new Random();
			Long code = random.nextLong(900000000);
			return String.valueOf(code);
		}


}
