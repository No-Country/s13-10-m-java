package com.nocountrys13.ecoapp.services;

import java.util.UUID;

import com.nocountrys13.ecoapp.entities.Usuario;

public interface IEmailService {

	public void sendVerificationEmail(Usuario user);
	
	public void verifyEmail(String token, UUID userId);
	

}
