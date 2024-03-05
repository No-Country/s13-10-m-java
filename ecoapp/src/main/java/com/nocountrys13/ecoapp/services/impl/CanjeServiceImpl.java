package com.nocountrys13.ecoapp.services.impl;

import com.nocountrys13.ecoapp.entities.Canje;
import com.nocountrys13.ecoapp.repositories.CanjeRepository;
import com.nocountrys13.ecoapp.repositories.PremioRepository;
import com.nocountrys13.ecoapp.repositories.UsuarioRepository;
import com.nocountrys13.ecoapp.security.JwtProvider;
import com.nocountrys13.ecoapp.services.ICanjeService;
import com.nocountrys13.ecoapp.services.IEmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CanjeServiceImpl implements ICanjeService {

    private final CanjeRepository canjeRepository;
    private final UsuarioRepository usuarioRepository;
    private final PremioRepository premioRepository;
    private final JwtProvider jwtProvider;
    private final IEmailService emailService;
    private final TemplateEngine templateEngine;
    private final JavaMailSender emailSender;

    public void canjearPuntos(UUID premioId, String token) {

        var usuario = usuarioRepository
                .findById(jwtProvider.extractUserId(token))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        var premio = premioRepository
                .findById(premioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Premio no encontrado"));

        if (premio.getCantidad() == 0 || usuario.getPuntos() < premio.getPuntos())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Puntos insuficientes o stock agotado");

        var canje = new Canje();
        canje.setUsuario(usuario);
        canje.setPremio(premio);

        int puntosActuales = usuario.getPuntos() - premio.getPuntos();
        int cantidadDePremio = premio.getCantidad() - 1;

        usuario.setPuntos(puntosActuales);
        premio.setCantidad(cantidadDePremio);

        canjeRepository.save(canje);
        usuarioRepository.save(usuario);
        premioRepository.save(premio);

        Context context = new Context();
        context.setVariable("userName", usuario.getNombre() + " " + usuario.getApellido());
        context.setVariable("titulo", premio.getNombrePremio());
        context.setVariable("descripcion", premio.getDescripcion());
        context.setVariable("id", premio.getPremioId());
        context.setVariable("direccion", premio.getPuntoVerde().getDireccion());
        context.setVariable("imagen", premio.getImgUrl());

        String htmlContent = templateEngine.process("notificacionPremio", context);

        try {
            emailSender.send(emailService.CreateMensaje(htmlContent, usuario.getEmail()));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al enviar email.");
        }
    }

}
