package com.nocountrys13.ecoapp.dtos.response;

import java.util.UUID;
import com.nocountrys13.ecoapp.entities.Usuario;

public record ImagenDtoResponse(UUID imgId,
								String name, 
								String imagenUrl,  
								Usuario userId) {}
