package com.nocountrys13.ecoapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nocountrys13.ecoapp.services.IResumenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ResumenController {

	private IResumenService iResumenService;

	@GetMapping
	public ResponseEntity<Object> resumen(@AuthenticationPrincipal UserDetails userLogueado) {

		return new ResponseEntity<>(iResumenService.ResumenUser(userLogueado), HttpStatus.OK);

	}

}
