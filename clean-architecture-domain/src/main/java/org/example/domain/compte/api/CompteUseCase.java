package org.example.domain.compte.api;

import org.example.domain.compte.Compte;
import org.example.domain.compte.TypeCompte;

import java.util.List;

public interface CompteUseCase {

    Compte createCompte(TypeCompte typeCompte, Double soldeInitial);
    Double getSoldeCompte(String id);
    void debiterCompte(String id, Double montant);
    void crediterCompte(String id, Double montant);
    List<Compte> getAllComptes();

    Compte getCompteById(String id);
}
