package org.example;

import org.example.domain.compte.Compte;
import org.example.domain.compte.CompteCourant;
import org.example.domain.compte.CompteEpargne;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompteFunctionnalTest {

    @Test
    public void should_create_compte_courant_with_generateUUID() {
        Compte compte = new CompteCourant(100.0);
        assertNotNull(compte.getId());
    }

    @Test
    public void should_create_compte_epargne_with_generateUUID() {
        Compte compte = new CompteEpargne(100.0);
        assertNotNull(compte.getId());
    }
    @Test
    public void should_create_compte_courant_with_solde() {
        Compte compte = new CompteCourant(100.0);
        assertEquals(100.0, compte.getSolde());
    }

    @Test
    public void should_create_compte_epargne_with_solde() {
        Compte compte = new CompteEpargne(100.0);
        assertEquals(100.0, compte.getSolde());
    }

    @Test
    public void should_not_create_compte_courant_with_solde_inf_50() {
        assertThrowsExactly(IllegalArgumentException.class, () -> new CompteCourant(45.0));
    }

    @Test
    public void should_not_create_compte_epargne_with_solde_inf_50() {
        assertThrowsExactly(IllegalArgumentException.class, () -> new CompteEpargne(45.0));
    }
}
