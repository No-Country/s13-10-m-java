package com.nocountrys13.ecoapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nocountrys13.ecoapp.dtos.response.CoordenadasResponseDto;
import com.nocountrys13.ecoapp.services.IGeoLocationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coordenadas")
public class GeoLocationController {
	
	private final IGeoLocationService geoLocationService;
	
	@GetMapping("/{address}")
	public CoordenadasResponseDto getcoordenadas(@PathVariable String address){
		System.out.println("entro al controller");
		return geoLocationService.getCoordenadas(address);
		
	}

}
