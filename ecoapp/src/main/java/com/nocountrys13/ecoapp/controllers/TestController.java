package com.nocountrys13.ecoapp.controllers;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nocountrys13.ecoapp.entities.Usuario;
import com.nocountrys13.ecoapp.services.EmailService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class TestController {
	
	private final EmailService emailService;

	@GetMapping("")
	public String test() {
		return "index";
	}
	
	@GetMapping("send")
	public String sendEmail() {
	try {
		Usuario usuario = new Usuario();
		usuario.setUserId(UUID.randomUUID()); // Genera un nuevo UUID
		usuario.setNombre("leonardo");
		usuario.setApellido("vargas");
		usuario.setEmail("john.doe@example.com");
		usuario.setPassword("password123");
		usuario.setNotificacion(true);
		usuario.setPuntos(0);
		emailService.sendVerificationEmail(usuario);
		return"emailEnviado";
		
		}catch (Exception e) {
			
			System.out.println(e.getMessage());
			return"verificarEmail";		}
		
	}
	

}
