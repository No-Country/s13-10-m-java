package com.nocountrys13.ecoapp.services;

import java.io.IOException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import com.nocountrys13.ecoapp.dtos.response.ImagenDtoResponse;

public interface ImagenService {
	
	public ImagenDtoResponse save(MultipartFile multipartFile, UserDetails userDetails) throws IOException;

}
