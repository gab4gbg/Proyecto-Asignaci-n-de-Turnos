package com.turnos.turnosSystem.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.turnos.turnosSystem.dto.GoogleAuthDto;
import com.turnos.turnosSystem.dto.GoogleAuthResponseDto;
import com.turnos.turnosSystem.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AdminRepository adminRepository;

    @Value("${google.client-id}")
    private String googleClientId;

    public AuthController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @PostMapping("/google")
    public ResponseEntity<?> googleLogin(@RequestBody GoogleAuthDto body) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                    new NetHttpTransport(), GsonFactory.getDefaultInstance())
                    .setAudience(Collections.singletonList(googleClientId))
                    .build();

            GoogleIdToken idToken = verifier.verify(body.getToken());

            if (idToken == null) {
                return ResponseEntity.status(401).body("Token inválido.");
            }

            GoogleIdToken.Payload payload = idToken.getPayload();
            String email  = payload.getEmail();
            String nombre = (String) payload.get("name");
            String foto   = (String) payload.get("picture");

            if (!adminRepository.existsByEmail(email)) {
                return ResponseEntity.status(403).body("Tu cuenta no tiene permiso de administrador.");
            }

            return ResponseEntity.ok(new GoogleAuthResponseDto(nombre, email, foto));

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al verificar el token de Google.");
        }
    }
}