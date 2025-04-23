package org.example.domain.compte;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Compte {

    protected final String id;
    protected Double solde;
    protected final LocalDateTime dateCreation;

    public Compte(String id, Double solde, LocalDateTime dateCreation) {

        if (solde < 50) {
            throw new IllegalArgumentException("Le solde initial ne peut pas être inférieur a 50");
        }
        this.id = id;
        this.solde = solde;
        this.dateCreation = dateCreation;
    }

    public Compte(Double solde) {
        if (solde < 50) {
            throw new IllegalArgumentException("Le solde initial ne peut pas être inférieur a 50");
        }
        this.id = UUID.randomUUID().toString();
        this.solde = solde;
        this.dateCreation = LocalDateTime.now();
    }

    public abstract Compte retrait(Double montant);

    public abstract Compte versement(Double montant);


    public String getId() {
        return id;
    }

    public Double getSolde() {
        return solde;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }
}
