package org.example.domain.compte.spi;

import org.example.domain.compte.Compte;

import java.util.List;

public interface CompteRepository {
    Compte getById(String id);

    Compte save(Compte compte);

    List<Compte> getAll();
}
