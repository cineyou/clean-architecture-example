package org.example.domain.compte;

public class CompteFactory {

    public static Compte createCompte(TypeCompte typeCompte, Double soldeInitial) {
        return switch (typeCompte) {
            case COMPTE_COURANT -> new CompteCourant(soldeInitial);
            case COMPTE_EPARGNE -> new CompteEpargne(soldeInitial);
            default -> throw new IllegalArgumentException("Type de compte inconnu");
        };
    }
}
