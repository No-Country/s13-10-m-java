package com.nocountrys13.ecoapp.services;

import jakarta.mail.MessagingException;

import java.util.UUID;

public interface ICanjeService {
    void canjearPuntos(UUID premioId, String token) throws MessagingException;
}
