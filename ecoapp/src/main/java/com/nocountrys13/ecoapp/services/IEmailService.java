package com.nocountrys13.ecoapp.services;

import java.util.UUID;

import com.nocountrys13.ecoapp.entities.Usuario;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

public interface IEmailService {

	public void sendVerificationEmail(Usuario user);
	
	public void verifyEmail(String token, UUID userId) throws Exception;
	
	public MimeMessage CreateMensaje(String htmlContent, String email) throws MessagingException;
	

}
