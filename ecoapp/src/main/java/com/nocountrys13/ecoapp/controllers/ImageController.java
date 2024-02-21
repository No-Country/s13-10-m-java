package com.nocountrys13.ecoapp.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nocountrys13.ecoapp.entities.Imagen;
import com.nocountrys13.ecoapp.services.ImagenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/img")
public class ImageController {

	private final ImagenService imagenService;

	@PostMapping
	public ResponseEntity<String> upload(@RequestParam("imagen") MultipartFile multipartFile,
			@AuthenticationPrincipal UserDetails userDetails) throws IOException {

		imagenService.save(multipartFile, userDetails);

		return ResponseEntity.ok().body("Imagen guardada con exito");
	}

	
}
