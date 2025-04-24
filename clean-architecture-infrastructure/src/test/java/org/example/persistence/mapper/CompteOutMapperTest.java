package org.example.persistence.mapper;

import org.example.domain.compte.Compte;
import org.example.domain.compte.CompteCourant;
import org.example.domain.compte.CompteEpargne;
import org.example.persistence.entity.CompteEntity;
import org.example.persistence.entity.TypeCompteEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CompteOutMapperTest {

    @Autowired
    private CompteOutMapper compteOutMapper;

    @Test
    void compteEntityToCompte_shouldMapToCompteCourant() {
        // Given
        String id = "test-id";
        Double solde = 1000.0;
        LocalDateTime createdDate = LocalDateTime.now();
        
        TypeCompteEntity typeCompteEntity = new TypeCompteEntity(1, "Compte Courant", null);
        CompteEntity compteEntity = CompteEntity.builder()
                .id(id)
                .solde(solde)
                .createdDate(createdDate)
                .typeCompte(typeCompteEntity)
                .build();

        // When
        Compte compte = compteOutMapper.compteEntityToCompte(compteEntity);

        // Then
        assertThat(compte).isNotNull();
        assertThat(compte).isInstanceOf(CompteCourant.class);
        assertThat(compte.getId()).isEqualTo(id);
        assertThat(compte.getSolde()).isEqualTo(solde);
        assertThat(compte.getDateCreation()).isEqualTo(createdDate);
    }

    @Test
    void compteEntityToCompte_shouldMapToCompteEpargne() {
        // Given
        String id = "test-id";
        Double solde = 1000.0;
        LocalDateTime createdDate = LocalDateTime.now();
        
        TypeCompteEntity typeCompteEntity = new TypeCompteEntity(2, "Compte Epargne", null);
        CompteEntity compteEntity = CompteEntity.builder()
                .id(id)
                .solde(solde)
                .createdDate(createdDate)
                .typeCompte(typeCompteEntity)
                .build();

        // When
        Compte compte = compteOutMapper.compteEntityToCompte(compteEntity);

        // Then
        assertThat(compte).isNotNull();
        assertThat(compte).isInstanceOf(CompteEpargne.class);
        assertThat(compte.getId()).isEqualTo(id);
        assertThat(compte.getSolde()).isEqualTo(solde);
        assertThat(compte.getDateCreation()).isEqualTo(createdDate);
    }

    @Test
    void compteToCompteEntity_shouldMapFromCompteCourant() {
        // Given
        String id = "test-id";
        Double solde = 1000.0;
        LocalDateTime dateCreation = LocalDateTime.now();
        Compte compte = new CompteCourant(id, solde, dateCreation, 0.0);

        // When
        CompteEntity compteEntity = compteOutMapper.compteToCompteEntity(compte);

        // Then
        assertThat(compteEntity).isNotNull();
        assertThat(compteEntity.getId()).isEqualTo(id);
        assertThat(compteEntity.getSolde()).isEqualTo(solde);
        assertThat(compteEntity.getCreatedDate()).isEqualTo(dateCreation);
        assertThat(compteEntity.getTypeCompte()).isNotNull();
        assertThat(compteEntity.getTypeCompte().getId()).isEqualTo(1);
    }

    @Test
    void compteToCompteEntity_shouldMapFromCompteEpargne() {
        // Given
        String id = "test-id";
        Double solde = 1000.0;
        LocalDateTime dateCreation = LocalDateTime.now();
        Compte compte = new CompteEpargne(id, solde, dateCreation, 0.05);

        // When
        CompteEntity compteEntity = compteOutMapper.compteToCompteEntity(compte);

        // Then
        assertThat(compteEntity).isNotNull();
        assertThat(compteEntity.getId()).isEqualTo(id);
        assertThat(compteEntity.getSolde()).isEqualTo(solde);
        assertThat(compteEntity.getCreatedDate()).isEqualTo(dateCreation);
        assertThat(compteEntity.getTypeCompte()).isNotNull();
        assertThat(compteEntity.getTypeCompte().getId()).isEqualTo(2);
    }

    @Test
    void comptesToCompteEntities_shouldMapList() {
        // Given
        String id1 = "test-id-1";
        Double solde1 = 1000.0;
        LocalDateTime dateCreation1 = LocalDateTime.now();
        Compte compte1 = new CompteCourant(id1, solde1, dateCreation1, 0.0);

        String id2 = "test-id-2";
        Double solde2 = 2000.0;
        LocalDateTime dateCreation2 = LocalDateTime.now();
        Compte compte2 = new CompteEpargne(id2, solde2, dateCreation2, 0.05);

        List<Compte> compteList = Arrays.asList(compte1, compte2);

        // When
        List<CompteEntity> compteEntityList = compteOutMapper.comptesToCompteEntities(compteList);

        // Then
        assertThat(compteEntityList).isNotNull();
        assertThat(compteEntityList).hasSize(2);
        
        assertThat(compteEntityList.get(0).getId()).isEqualTo(id1);
        assertThat(compteEntityList.get(0).getSolde()).isEqualTo(solde1);
        assertThat(compteEntityList.get(0).getCreatedDate()).isEqualTo(dateCreation1);
        assertThat(compteEntityList.get(0).getTypeCompte().getId()).isEqualTo(1);
        
        assertThat(compteEntityList.get(1).getId()).isEqualTo(id2);
        assertThat(compteEntityList.get(1).getSolde()).isEqualTo(solde2);
        assertThat(compteEntityList.get(1).getCreatedDate()).isEqualTo(dateCreation2);
        assertThat(compteEntityList.get(1).getTypeCompte().getId()).isEqualTo(2);
    }

    @Test
    void compteEntitiesToComptes_shouldMapList() {
        // Given
        String id1 = "test-id-1";
        Double solde1 = 1000.0;
        LocalDateTime createdDate1 = LocalDateTime.now();
        TypeCompteEntity typeCompteEntity1 = new TypeCompteEntity(1, "Compte Courant", null);
        CompteEntity compteEntity1 = CompteEntity.builder()
                .id(id1)
                .solde(solde1)
                .createdDate(createdDate1)
                .typeCompte(typeCompteEntity1)
                .build();

        String id2 = "test-id-2";
        Double solde2 = 2000.0;
        LocalDateTime createdDate2 = LocalDateTime.now();
        TypeCompteEntity typeCompteEntity2 = new TypeCompteEntity(2, "Compte Epargne", null);
        CompteEntity compteEntity2 = CompteEntity.builder()
                .id(id2)
                .solde(solde2)
                .createdDate(createdDate2)
                .typeCompte(typeCompteEntity2)
                .build();

        List<CompteEntity> compteEntityList = Arrays.asList(compteEntity1, compteEntity2);

        // When
        List<Compte> compteList = compteOutMapper.compteEntitiesToComptes(compteEntityList);

        // Then
        assertThat(compteList).isNotNull();
        assertThat(compteList).hasSize(2);
        
        assertThat(compteList.get(0)).isInstanceOf(CompteCourant.class);
        assertThat(compteList.get(0).getId()).isEqualTo(id1);
        assertThat(compteList.get(0).getSolde()).isEqualTo(solde1);
        assertThat(compteList.get(0).getDateCreation()).isEqualTo(createdDate1);
        
        assertThat(compteList.get(1)).isInstanceOf(CompteEpargne.class);
        assertThat(compteList.get(1).getId()).isEqualTo(id2);
        assertThat(compteList.get(1).getSolde()).isEqualTo(solde2);
        assertThat(compteList.get(1).getDateCreation()).isEqualTo(createdDate2);
    }
}