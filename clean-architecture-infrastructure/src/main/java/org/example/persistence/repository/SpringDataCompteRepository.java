package org.example.persistence.repository;

import org.example.persistence.entity.CompteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCompteRepository extends JpaRepository<CompteEntity, String> {
}
