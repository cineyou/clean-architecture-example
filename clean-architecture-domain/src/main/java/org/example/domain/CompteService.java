package org.example.domain;

import org.example.ddd.DomainService;
import org.example.domain.compte.Compte;
import org.example.domain.compte.CompteFactory;
import org.example.domain.compte.TypeCompte;
import org.example.domain.compte.api.CompteUseCase;
import org.example.domain.compte.spi.CompteRepository;

import java.util.List;

@DomainService
public class CompteService implements CompteUseCase {

    private final CompteRepository compteRepository;


    public CompteService(final CompteRepository compteRepository) {
        this.compteRepository = compteRepository;

    }

    @Override
    public Compte createCompte(TypeCompte typeCompte, Double soldeInitial) {
        var compte = CompteFactory.createCompte(typeCompte, soldeInitial);
        compte = compteRepository.save(compte);
        return compte;
    }

    @Override
    public Double getSoldeCompte(String id) {
        return compteRepository.getById(id).getSolde();
    }

    @Override
    public void debiterCompte(String id, Double montant) {
        Compte compte = compteRepository.getById(id);
        if (compte == null) {
            throw new RuntimeException("Compte not found");
        }
        compte.retrait(montant);
        compteRepository.save(compte);
    }

    @Override
    public void crediterCompte(String id, Double montant) {
        Compte compte = compteRepository.getById(id);
        compte.versement(montant);
        compteRepository.save(compte);
    }

    @Override
    public List<Compte> getAllComptes() {
        return compteRepository.getAll();
    }

    @Override
    public Compte getCompteById(String id) {
        return compteRepository.getById(id);
    }
}
