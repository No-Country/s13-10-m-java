package com.nocountrys13.ecoapp.services;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import com.nocountrys13.ecoapp.entities.Imagen;

public interface ImagenService {
	
	public Imagen save(MultipartFile multipartFile, UserDetails userDetails) throws IOException;
	
	public List<Imagen> getAll();

}
