package com.nocountrys13.ecoapp.controllers;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.nocountrys13.ecoapp.services.impl.EmailServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {
	
	private final EmailServiceImpl emailServiceImpl;

	
	@GetMapping("/verify")
	public RedirectView verifyEmail( @RequestParam("token") String token, @RequestParam("userId") UUID userId) {

		emailServiceImpl.verifyEmail(token, userId);
		
		  return new RedirectView("https://site-ecoapp.netlify.app/account-verified");
	}
}
