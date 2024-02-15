package com.nocountrys13.ecoapp.controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("img")
public class ImageController {
	

	
	private final ImagenService imagenService;
	
	
	@PostMapping
	public ResponseEntity<?> upload(@RequestParam("imagen") MultipartFile multipartFile) throws IOException {
		BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
		if (bi == null) {
			return new ResponseEntity<String>("imagen no válida", HttpStatus.BAD_REQUEST);
		}

		// se puede cambiar por un Dto "ImagenResponseDto" para seguir el patrón
		Imagen imagen = imagenService.save(multipartFile);

		return new ResponseEntity<Imagen>(imagen, HttpStatus.OK);
	}

}
