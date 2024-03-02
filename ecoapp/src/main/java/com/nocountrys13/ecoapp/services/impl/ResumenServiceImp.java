package com.nocountrys13.ecoapp.services.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.nocountrys13.ecoapp.dtos.response.ResumenPuntoVerdeResponse;
import com.nocountrys13.ecoapp.dtos.response.ResumenUsuarioResponse;
import com.nocountrys13.ecoapp.services.IResumenService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResumenServiceImp implements IResumenService {
	@Override

	public Object ResumenUser(UserDetails userLogueado) {

		if (usuarioRol(userLogueado, "ROLE_USER") && userLogueado != null) {
			return resumenUserRole();
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " Error en el token");

		}

		//return resumenUserPuntoVErde();

	}

	
	
	
	
	// estadisticas del usuario comun(registrado)
	private ResumenUsuarioResponse resumenUserRole() {
		ResumenUsuarioResponse userResponse = new ResumenUsuarioResponse();
		return userResponse;
	}

	// estadisticas del usuario con punto verde
	private ResumenPuntoVerdeResponse resumenUserPuntoVErde() {
		ResumenPuntoVerdeResponse userConPuntoVerd = new ResumenPuntoVerdeResponse();
		return userConPuntoVerd;
	}

	// verifico el rol del usuario
	private boolean usuarioRol(UserDetails user, String rolBuscado) {
		return user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.anyMatch(rol -> rol.equals(rolBuscado));
	}

}
