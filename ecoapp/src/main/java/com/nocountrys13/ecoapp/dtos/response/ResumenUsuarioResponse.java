package com.nocountrys13.ecoapp.dtos.response;

import com.nocountrys13.ecoapp.entities.Usuario;

public record ResumenUsuarioResponse(  String nombreCompleto, 
		 							   Boolean emailVerificado,
		 							   Integer puntos,	
		 							   Integer premiosObtenidos){
	
	
	public ResumenUsuarioResponse(Usuario user) {
		this(user.getNombre() + " " + user.getApellido() ,
			 user.getValidEmail(),
			 user.getPuntos(),
			 user.getCanje().size());
	}
	
	
}
