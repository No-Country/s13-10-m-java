package com.nocountrys13.ecoapp.services;

import com.nocountrys13.ecoapp.entities.Usuario;

public interface IEmailService {

	public void sendVerificationEmail(Usuario user);

}
