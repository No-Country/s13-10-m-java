package com.nocountrys13.ecoapp.services;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

public interface IResumenService {
	
	public Object ResumenUser(@AuthenticationPrincipal UserDetails userLogueado);
    
}
