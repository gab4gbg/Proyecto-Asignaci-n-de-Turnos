package com.turnos.turnosSystem.config;

import com.turnos.turnosSystem.model.AdminModel;
import com.turnos.turnosSystem.repository.AdminRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Inserta los correos de administradores al iniciar la aplicación
 * si aún no existen en la tabla admins.
 */
@Component
public class AdminDataInitializer implements ApplicationRunner {

    private final AdminRepository adminRepository;

    private static final List<String[]> ADMINS = List.of(
        new String[]{"gabrielgenarobg@gmail.com",                   "Gabriel Genaro"},
        new String[]{"gabriel.beltran@estudiantesunibague.edu.co",  "Gabriel Beltrán"},
        new String[]{"steven.correa@unibague.edu.co",               "Steven Correa"}
    );

    public AdminDataInitializer(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        for (String[] admin : ADMINS) {
            String email  = admin[0];
            String nombre = admin[1];
            if (!adminRepository.existsByEmail(email)) {
                AdminModel a = new AdminModel();
                a.setEmail(email);
                a.setNombre(nombre);
                adminRepository.save(a);
                System.out.println("[AdminInit] Admin registrado: " + email);
            }
        }
    }
}
