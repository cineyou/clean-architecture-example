package org.example.domain.compte;

import java.time.LocalDateTime;

public class CompteCourant extends Compte{

    private Double decouvert;

    public CompteCourant(String id, Double solde, LocalDateTime dateCreation, Double decouvert) {
        super(id,solde, dateCreation);
        this.decouvert= decouvert;
    }

    public CompteCourant(Double solde) {
        super(solde);
    }

    @Override
    public Compte retrait(Double montant) {
        if(montant < 0) {
            throw new IllegalArgumentException("Le montant du retrait doit être positif");
        }
        if(this.solde - montant >= this.decouvert) {
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
            return new CompteCourant(this.id, this.solde, this.dateCreation, this.decouvert);
        }
    }
}
