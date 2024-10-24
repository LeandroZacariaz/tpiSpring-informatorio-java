package com.info.app.projectapptpi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.info.app.projectapptpi.domain.Receta;

public interface RecetaRepository extends JpaRepository<Receta, UUID>{

}
