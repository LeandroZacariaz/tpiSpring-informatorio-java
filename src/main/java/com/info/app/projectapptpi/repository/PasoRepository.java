package com.info.app.projectapptpi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.info.app.projectapptpi.domain.Paso;

public interface PasoRepository extends JpaRepository<Paso, UUID>{

}
