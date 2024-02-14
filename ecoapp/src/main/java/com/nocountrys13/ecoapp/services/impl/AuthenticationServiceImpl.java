package com.nocountrys13.ecoapp.services.impl;

import com.nocountrys13.ecoapp.dtos.AuthenticationDTO;
import com.nocountrys13.ecoapp.dtos.Jwt;
import com.nocountrys13.ecoapp.dtos.request.RegisterDtoRequest;
import com.nocountrys13.ecoapp.dtos.response.RegisterDtoResponse;
import com.nocountrys13.ecoapp.entities.Usuario;
import com.nocountrys13.ecoapp.repositories.UsuarioRepository;
import com.nocountrys13.ecoapp.security.JwtProvider;
import com.nocountrys13.ecoapp.services.IAuthenticationService;
import com.nocountrys13.ecoapp.services.IEmailService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;
    private final IEmailService emailService;
    
    @Value("${url.imgDefault}")
    private String imgDefaultUrl;

    @Override
    public RegisterDtoResponse register(RegisterDtoRequest dto) {

        if (repository.existsByEmail(dto.email()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El email ya se encuentra registrado");

        var user = new Usuario();
        BeanUtils.copyProperties(dto, user);
        user.setPassword(encoder.encode(dto.password()));
        
        //seteo de imagen por defecto
        user.setImgUrl(imgDefaultUrl);
        
        String jwtToken= jwtProvider.generateToken(user).toString();
        
        //instancia de entidad a retornar
        RegisterDtoResponse registerResponse = new RegisterDtoResponse(dto, imgDefaultUrl, jwtToken );
       
        //enviar el email de bienvenida
        emailService.sendVerificationEmail(user);
        
        repository.save(user);
        
        return registerResponse;
    }

    
    @Override
    public Jwt login(AuthenticationDTO dto) {
        var user = Optional
                .of(repository.findByEmail(dto.email()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Correo o contraseña incorrecta."));

        if (encoder.matches(dto.password(), user.getPassword()))
            return jwtProvider.generateToken(user);
        else
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Correo o contraseña incorrecta.");
    }

}
