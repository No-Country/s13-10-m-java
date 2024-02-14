package com.nocountrys13.ecoapp.services.impl;

import com.nocountrys13.ecoapp.dtos.AuthenticationDTO;
import com.nocountrys13.ecoapp.dtos.Jwt;
import com.nocountrys13.ecoapp.dtos.RegisterDTO;
import com.nocountrys13.ecoapp.entities.Usuario;
import com.nocountrys13.ecoapp.repositories.UsuarioRepository;
import com.nocountrys13.ecoapp.security.JwtProvider;
import com.nocountrys13.ecoapp.services.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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

    @Override
    public void register(RegisterDTO dto) {

        if (repository.existsByEmail(dto.email()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El email ya se encuentra registrado");

        var user = new Usuario();
        BeanUtils.copyProperties(dto, user);
        user.setPassword(encoder.encode(dto.password()));

        repository.save(user);
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
