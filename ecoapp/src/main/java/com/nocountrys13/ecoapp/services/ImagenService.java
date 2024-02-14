package com.nocountrys13.ecoapp.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.nocountrys13.ecoapp.entities.Imagen;

public interface ImagenService {
	
	public Imagen save(MultipartFile multipartFile) throws IOException;

}
