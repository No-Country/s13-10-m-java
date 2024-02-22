package com.nocountrys13.ecoapp.services;

import com.nocountrys13.ecoapp.dtos.response.CoordenadasResponseDto;

public interface IGeoLocationService {
	
	public CoordenadasResponseDto getCoordenadas(String direccion);

}
