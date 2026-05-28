package com.turnos.turnosSystem.repository;

import com.turnos.turnosSystem.model.AdminModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminModel, Long>
{
    boolean existsByEmail(String email);
    Optional<AdminModel> findByEmail(String email);
}