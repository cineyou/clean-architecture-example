package org.example.domain.compte;

public enum TypeCompte {
    COMPTE_COURANT ("compte_courant"),
    COMPTE_EPARGNE ("compte_epargne");

    private final String libelle;

    TypeCompte(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}
