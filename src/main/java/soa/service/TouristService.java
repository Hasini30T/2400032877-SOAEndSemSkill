package soa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import soa.entity.Tourist;
import soa.repository.TouristRepository;

@Service
public class TouristService {

    private final TouristRepository touristRepository;

    // The pre-generated valid token, read from application.properties
    private final String validToken;

    public TouristService(TouristRepository touristRepository,
                          @Value("${app.security.token}") String validToken) {
        this.touristRepository = touristRepository;
        this.validToken = validToken;
    }

    /**
     * Returns true only when a token is supplied and it matches the configured token.
     * Does NOT touch the database.
     */
    public boolean isTokenValid(String token) {
        return token != null && !token.isBlank() && token.equals(validToken);
    }

    /** Fetches all tourists. Call only after the token has been validated. */
    public List<Tourist> getAllTourists() {
        return touristRepository.findAll();
    }
}
