package com.nocountrys13.ecoapp.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.nocountrys13.ecoapp.dtos.response.ResumenPuntoVerdeResponse;
import com.nocountrys13.ecoapp.dtos.response.ResumenUsuarioResponse;
import com.nocountrys13.ecoapp.entities.PuntoVerde;
import com.nocountrys13.ecoapp.entities.Usuario;
import com.nocountrys13.ecoapp.repositories.UsuarioRepository;
import com.nocountrys13.ecoapp.services.IResumenService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResumenServiceImp implements IResumenService {
	
	private final UsuarioRepository usuarioRepository;
	
	
	@Override
	public Object ResumenUser(UserDetails userLogueado) {
		
		Optional <Usuario> user = Optional.of( usuarioRepository.findByEmail(userLogueado.getUsername()));
									
		if(user.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Noexiste el usuario");
		}
		
		List<PuntoVerde> listaPV = user.get().getPuntosVerdes();
		
		if ( listaPV.isEmpty()) {
			return resumenUserRole(user.get());
		} else {
			return resumenUserPuntoVErde(listaPV );

		}

	}

	
	
	// estadisticas del usuario comun(registrado)
	private ResumenUsuarioResponse resumenUserRole(Usuario user) {
		ResumenUsuarioResponse userResponse = new ResumenUsuarioResponse();
		return userResponse;
	}

	// estadisticas del usuario con punto verde
	private ResumenPuntoVerdeResponse resumenUserPuntoVErde(List<PuntoVerde> listaPV) {
		ResumenPuntoVerdeResponse userConPuntoVerd = new ResumenPuntoVerdeResponse();
		return userConPuntoVerd;
	}

}
