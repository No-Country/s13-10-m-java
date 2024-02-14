package com.nocountrys13.ecoapp.services;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nocountrys13.ecoapp.entities.Imagen;
import com.nocountrys13.ecoapp.repositories.ImagenRepository;
import com.nocountrys13.ecoapp.services.ClaudinaryService.CloudinaryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImagenServiceImpl implements ImagenService {
	
	private final CloudinaryService cloudinaryService;
	private final ImagenRepository imagenRepository;
	
	  public Imagen save(MultipartFile multipartFile) throws IOException{
	    	
			Map <?,?>result = cloudinaryService.upload(multipartFile);
			Imagen imagen = new Imagen();
			imagen.setName((String) result.get("original_filename"));
			imagen.setImagenUrl((String) result.get("url"));
			imagen.setCloudinaryId((String) result.get("public_id"));
			
	        return imagenRepository.save(imagen); 
	        
	    }
	  
	  
	  

}
