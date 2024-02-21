package com.nocountrys13.ecoapp.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.nocountrys13.ecoapp.dtos.response.ApiResponse;
import com.nocountrys13.ecoapp.dtos.response.CoordenadasResponseDto;
import com.nocountrys13.ecoapp.services.IGeoLocationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GeoLocationServiceImpl implements IGeoLocationService {

	@Value("${google.maps.apiKey}")
	private String apiKey;

	public CoordenadasResponseDto getCoordenadas(String address) {

		String apiUrl = "https://maps.googleapis.com/maps/api/geocode/json?address={address}&key={apiKey}";

		try {
			RestTemplate restTemplate = new RestTemplate();
			
			ApiResponse response = restTemplate.getForObject(apiUrl, ApiResponse.class, address, apiKey);

			return new CoordenadasResponseDto(address,
					String.valueOf(response.getResults().get(0).getGeometry().getLocation().getLat()),
					String.valueOf(response.getResults().get(0).getGeometry().getLocation().getLng()));

		} catch (RestClientException e) {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "no se encontro las coordenadas" + e.getMessage(),
					e);

		}
	}

}
