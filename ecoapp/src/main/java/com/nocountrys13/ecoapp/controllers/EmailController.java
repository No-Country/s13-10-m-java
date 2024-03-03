package com.nocountrys13.ecoapp.controllers;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.nocountrys13.ecoapp.services.impl.EmailServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("api/v1/email")
@RequiredArgsConstructor
public class EmailController {

	private final EmailServiceImpl emailServiceImpl;

	@GetMapping("/verify")
	public String verificarEmail( @RequestParam("token") String token, 
							      @RequestParam("userId") UUID userId) {
		try {
			emailServiceImpl.verifyEmail(token, userId);
			return "redirect:https://site-ecoapp.netlify.app";


        } catch (ResponseStatusException e) {
            return "validado";

		} catch (Exception ex) {
			return "enlaceExpirado";
		}	
	}
	
}