package org.example.persistence.repository;

import lombok.AllArgsConstructor;
import org.example.domain.compte.Compte;
import org.example.domain.compte.spi.CompteRepository;
import org.example.persistence.entity.CompteEntity;
import org.example.persistence.mapper.CompteOutMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class JpaCompteRepository implements CompteRepository {

    private final SpringDataCompteRepository springDataCompteRepository;

    private final CompteOutMapper compteOutMapper;

    @Override
    public Compte getById(String id) {
        CompteEntity compteEntity = springDataCompteRepository.findById(id).orElseThrow();
        return compteOutMapper.compteEntityToCompte(compteEntity);
    }

    @Override
    public Compte save(Compte compte) {
        CompteEntity compteEntity = compteOutMapper.compteToCompteEntity(compte);
        springDataCompteRepository.save(compteEntity);
        return compteOutMapper.compteEntityToCompte(compteEntity);
    }

    @Override
    public List<Compte> getAll() {
        return springDataCompteRepository.findAll().stream()
                .map(compteOutMapper::compteEntityToCompte)
                .toList();
    }
}
