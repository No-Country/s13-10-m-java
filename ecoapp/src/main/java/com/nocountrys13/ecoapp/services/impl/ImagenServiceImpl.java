package com.nocountrys13.ecoapp.services.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.nocountrys13.ecoapp.dtos.response.ImagenDtoResponse;
import com.nocountrys13.ecoapp.entities.Imagen;
import com.nocountrys13.ecoapp.entities.Usuario;
import com.nocountrys13.ecoapp.repositories.ImagenRepository;
import com.nocountrys13.ecoapp.repositories.UsuarioRepository;
import com.nocountrys13.ecoapp.services.IUsuarioService;
import com.nocountrys13.ecoapp.services.ImagenService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImagenServiceImpl implements ImagenService {
	
	private final CloudinaryServiceImpl cloudinaryService;
	private final ImagenRepository imagenRepository;
	private final IUsuarioService usuariService;
	private final UsuarioRepository usuarioRepository;

	
	  public ImagenDtoResponse save(MultipartFile multipartFile , UserDetails userDetails) throws IOException{
	    	
		  BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
			if (bi == null) {
				
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cargue una imagen");
			}
			
			Usuario UsuarioLoguado= usuariService.findByEmail(userDetails.getUsername());
			
			//guardo la imagen en cloudinary
			Map <?,?>result = cloudinaryService.upload(multipartFile);
			
			UsuarioLoguado.setImgUrl((String) result.get("url"));
			usuarioRepository.save(UsuarioLoguado);
			
			Imagen imagen = new Imagen();
			imagen.setName((String) result.get("original_filename"));
			imagen.setImagenUrl((String) result.get("url"));
			imagen.setCloudinaryId((String) result.get("public_id"));
			imagen.setUsuario(UsuarioLoguado);
			
	       imagenRepository.save(imagen); 
	       
	       return new ImagenDtoResponse(imagen.getName(), imagen.getImagenUrl());
	        
	    } 
	  
}
