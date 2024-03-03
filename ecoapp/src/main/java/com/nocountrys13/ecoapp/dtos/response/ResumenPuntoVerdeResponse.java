package com.nocountrys13.ecoapp.dtos.response;

import java.util.List;

import com.nocountrys13.ecoapp.entities.Usuario;
import com.nocountrys13.ecoapp.utils.Material;

public record ResumenPuntoVerdeResponse(String nombreCompleto, 
										Boolean emailVerificado,									
										Integer CantPuntosVerde,
										List<Material> materialesAceptados,
										Integer cantPremio) {

	public ResumenPuntoVerdeResponse(Usuario user) {	
	this(user.getNombre() + " " + user.getApellido() ,
			user.getValidEmail(),
			user.getPuntosVerdes().size(),
			user.getPuntosVerdes().get(0).getMaterialesAceptados(),
			user.getPuntosVerdes().get(0).getListPremio().size());
	}
}
