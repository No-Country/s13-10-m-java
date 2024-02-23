package com.nocountrys13.ecoapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/email")
public class EmailController {

	
	@GetMapping("/verify")
	public RedirectView verifyEmail( @RequestParam("token") String token, @RequestParam("userId") String userId) {
		System.out.println("entro al metodo requestparam"  + "----" +  userId + "----" + token);
		  return new RedirectView("https://site-ecoapp.netlify.app/account-verified");
	}
}
