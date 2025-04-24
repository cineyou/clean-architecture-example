package org.example.application.mapper;

import org.example.domain.compte.Compte;
import org.example.domain.compte.CompteCourant;
import org.example.domain.compte.CompteEpargne;
import org.example.domain.compte.TypeCompte;
import org.example.web.compte.response.CompteCreationResponse;
import org.example.web.compte.response.CompteRechercheResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CompteInMapperTest {

    @Autowired
    private CompteInMapper compteInMapper;

    @Test
    void compteToCompteCreationResponse_shouldMapCompteCourant() {
        // Given
        String id = "test-id";
        Double solde = 1000.0;
        LocalDateTime dateCreation = LocalDateTime.now();
        Compte compte = new CompteCourant(id, solde, dateCreation, 0.0);

        // When
        CompteCreationResponse response = compteInMapper.compteToCompteCreationResponse(compte);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(id);
        assertThat(response.solde()).isEqualTo(solde);
    }

    @Test
    void compteToCompteCreationResponse_shouldMapCompteEpargne() {
        // Given
        String id = "test-id";
        Double solde = 1000.0;
        LocalDateTime dateCreation = LocalDateTime.now();
        Compte compte = new CompteEpargne(id, solde, dateCreation, 0.05);

        // When
        CompteCreationResponse response = compteInMapper.compteToCompteCreationResponse(compte);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(id);
        assertThat(response.solde()).isEqualTo(solde);
    }

    @Test
    void compteListToCompteCreationResponseList_shouldMapList() {
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
        List<CompteCreationResponse> responseList = compteInMapper.compteListToCompteCreationResponseList(compteList);

        // Then
        assertThat(responseList).isNotNull();
        assertThat(responseList).hasSize(2);
        assertThat(responseList.get(0).id()).isEqualTo(id1);
        assertThat(responseList.get(0).solde()).isEqualTo(solde1);
        assertThat(responseList.get(1).id()).isEqualTo(id2);
        assertThat(responseList.get(1).solde()).isEqualTo(solde2);
    }

    @Test
    void compteToCompteRechercheResponse_shouldMapCompteCourant() {
        // Given
        String id = "test-id";
        Double solde = 1000.0;
        LocalDateTime dateCreation = LocalDateTime.now();
        Compte compte = new CompteCourant(id, solde, dateCreation, 0.0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CompteInMapper.DATE_FORMAT);

        // When
        CompteRechercheResponse response = compteInMapper.compteToCompteRechercheResponse(compte);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(id);
        assertThat(response.solde()).isEqualTo(solde);
        assertThat(response.type()).isEqualTo(TypeCompte.COMPTE_COURANT);
        assertThat(response.dateCreation()).isEqualTo(dateCreation.format(formatter));
    }

    @Test
    void compteToCompteRechercheResponse_shouldMapCompteEpargne() {
        // Given
        String id = "test-id";
        Double solde = 1000.0;
        LocalDateTime dateCreation = LocalDateTime.now();
        Compte compte = new CompteEpargne(id, solde, dateCreation, 0.05);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CompteInMapper.DATE_FORMAT);

        // When
        CompteRechercheResponse response = compteInMapper.compteToCompteRechercheResponse(compte);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(id);
        assertThat(response.solde()).isEqualTo(solde);
        assertThat(response.type()).isEqualTo(TypeCompte.COMPTE_EPARGNE);
        assertThat(response.dateCreation()).isEqualTo(dateCreation.format(formatter));
    }

    @Test
    void compteListToCompteRechercheResponseList_shouldMapList() {
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CompteInMapper.DATE_FORMAT);

        // When
        List<CompteRechercheResponse> responseList = compteInMapper.compteListToCompteRechercheResponseList(compteList);

        // Then
        assertThat(responseList).isNotNull();
        assertThat(responseList).hasSize(2);
        
        assertThat(responseList.get(0).id()).isEqualTo(id1);
        assertThat(responseList.get(0).solde()).isEqualTo(solde1);
        assertThat(responseList.get(0).type()).isEqualTo(TypeCompte.COMPTE_COURANT);
        assertThat(responseList.get(0).dateCreation()).isEqualTo(dateCreation1.format(formatter));
        
        assertThat(responseList.get(1).id()).isEqualTo(id2);
        assertThat(responseList.get(1).solde()).isEqualTo(solde2);
        assertThat(responseList.get(1).type()).isEqualTo(TypeCompte.COMPTE_EPARGNE);
        assertThat(responseList.get(1).dateCreation()).isEqualTo(dateCreation2.format(formatter));
    }
}