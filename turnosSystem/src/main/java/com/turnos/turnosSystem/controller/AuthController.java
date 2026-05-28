package com.turnos.turnosSystem.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final String CLIENT_ID =
            "205021739328-dl841da3vg0s62mgi90cv355ovh9emvh.apps.googleusercontent.com";

    // Emails autorizados como administradores
    private static final List<String> ADMINS_AUTORIZADOS = List.of(
            "tucorreo@gmail.com"  // ← reemplaza con el email real del admin
    );

    @PostMapping("/google")
    public ResponseEntity<?> verificarGoogle(@RequestBody Map<String, String> body) {
        String token = body.get("token");
        if (token == null) return ResponseEntity.badRequest().build();

        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                    new NetHttpTransport(), GsonFactory.getDefaultInstance())
                    .setAudience(Collections.singletonList(CLIENT_ID))
                    .build();

            GoogleIdToken idToken = verifier.verify(token);
            if (idToken == null) return ResponseEntity.status(401).body("Token inválido");

            String email = idToken.getPayload().getEmail();

            if (!ADMINS_AUTORIZADOS.contains(email)) {
                return ResponseEntity.status(403).body("No tienes permiso de administrador");
            }

            return ResponseEntity.ok(Map.of(
                    "email", email,
                    "nombre", (String) idToken.getPayload().get("name"),
                    "foto",   (String) idToken.getPayload().get("picture")
            ));

        } catch (Exception e) {
            return ResponseEntity.status(401).body("Error al verificar token");
        }
    }
}