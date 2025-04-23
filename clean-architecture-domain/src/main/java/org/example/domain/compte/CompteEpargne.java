package org.example.domain.compte;

import java.time.LocalDateTime;

public class CompteEpargne extends Compte {

    private Double tauxInteret;

    public CompteEpargne(String id, Double solde, LocalDateTime dateCreation, Double tauxInteret) {
        super(id, solde, dateCreation);
        this.tauxInteret = tauxInteret;

    }

    public CompteEpargne(Double solde) {
        super(solde);
    }

    @Override
    public Compte retrait(Double montant) {
        if (montant < 0) {
            throw new IllegalArgumentException("Le montant du retrait doit être positif");
        }
        if (this.solde - montant >= 0) {
            this.solde -= montant;
        }
        return this;
    }
    @Override
    public Compte versement(Double montant) {
        {
            if (montant < 0) {
                throw new IllegalArgumentException("Le montant du versement doit être positif");
            }
            this.solde += montant;
            return new CompteEpargne(this.id, this.solde, this.dateCreation, this.tauxInteret);
        }
    }
}
