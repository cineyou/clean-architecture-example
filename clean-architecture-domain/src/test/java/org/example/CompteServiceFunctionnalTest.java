package org.example;

import org.example.domain.CompteService;
import org.example.domain.compte.Compte;
import org.example.domain.compte.CompteCourant;
import org.example.domain.compte.CompteEpargne;
import org.example.domain.compte.TypeCompte;
import org.example.domain.compte.spi.stubs.InMemoryCompteRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


public class CompteServiceFunctionnalTest {

    private final InMemoryCompteRepository inMemoryComptes = new InMemoryCompteRepository();
    private final CompteService compteService = new CompteService(inMemoryComptes);

    @Test
    public void should_create_compte_courant_with_sold_sup_50() {
        Compte compte = compteService.createCompte(TypeCompte.COMPTE_COURANT, 100.0);
        assertThat(compte).isExactlyInstanceOf(CompteCourant.class);
        assertNotNull(compte);
        assertNotNull(compte.getId());
        assertEquals(100.0, compte.getSolde(), 0.0);
    }

    @Test
    public void should_create_compte_epargne_with_sold_sup_50() {
        Compte compte = compteService.createCompte(TypeCompte.COMPTE_EPARGNE, 100.0);
        assertThat(compte).isExactlyInstanceOf(CompteEpargne.class);
        assertNotNull(compte);
        assertNotNull(compte.getId());
        assertEquals(100.0, compte.getSolde(), 0.0);
    }

    @Test
    public void should_not_create_compte_courant_with_solde_inf_50() {
        assertThrowsExactly(IllegalArgumentException.class, () -> compteService.createCompte(TypeCompte.COMPTE_COURANT, 45.0));
    }

    @Test
    public void should_not_create_compte_epargne_with_solde_inf_50() {
        assertThrowsExactly(IllegalArgumentException.class, () -> compteService.createCompte(TypeCompte.COMPTE_EPARGNE, 45.0));
    }


}
