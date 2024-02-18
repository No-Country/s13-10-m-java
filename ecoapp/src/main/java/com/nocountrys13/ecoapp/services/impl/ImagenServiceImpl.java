package com.nocountrys13.ecoapp.services.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.nocountrys13.ecoapp.entities.Imagen;
import com.nocountrys13.ecoapp.entities.Usuario;
import com.nocountrys13.ecoapp.repositories.ImagenRepository;
import com.nocountrys13.ecoapp.repositories.UsuarioRepository;
import com.nocountrys13.ecoapp.services.IUsuarioService;
import com.nocountrys13.ecoapp.services.ImagenService;
import com.nocountrys13.ecoapp.services.impl.ClaudinaryServiceImpl.CloudinaryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImagenServiceImpl implements ImagenService {
	
	private final CloudinaryService cloudinaryService;
	private final ImagenRepository imagenRepository;
	private final IUsuarioService usuariService;
	private final UsuarioRepository usuarioRepository;
	
	  public Imagen save(MultipartFile multipartFile, UserDetails userDetails) throws IOException{
	    	
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
			
	        return imagenRepository.save(imagen); 
	        
	    }

	@Override
	public List<Imagen> getAll() {
		// TODO Auto-generated method stub
		return imagenRepository.findAll();
		}
	  
	  
	  

}
