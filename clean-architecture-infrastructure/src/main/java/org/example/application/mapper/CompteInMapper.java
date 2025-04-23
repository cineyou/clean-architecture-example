package org.example.application.mapper;

import org.example.domain.compte.Compte;
import org.example.domain.compte.CompteCourant;
import org.example.domain.compte.CompteEpargne;
import org.example.domain.compte.TypeCompte;
import org.example.web.compte.response.CompteCreationResponse;
import org.example.web.compte.response.CompteRechercheResponse;
import org.mapstruct.Mapper;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Mapper pour les objets de type Compte et aussi un anti-corruption layer pour ne pas poluer le domaine avec des objets de la couche web
 */
@Mapper(componentModel = "spring")
public interface CompteInMapper {


    String DATE_FORMAT = "dd/MM/yyyy:HH:mm:ss";

    CompteCreationResponse compteToCompteCreationResponse(Compte compte);

    List<CompteCreationResponse> compteListToCompteCreationResponseList(List<Compte> compteList);

    default CompteRechercheResponse compteToCompteRechercheResponse(Compte compte) {
        CompteRechercheResponse compteRechercheResponse = null;
        final DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        if (compte instanceof CompteCourant) {
            compteRechercheResponse = new CompteRechercheResponse(compte.getId(), compte.getSolde(), TypeCompte.COMPTE_COURANT, compte.getDateCreation().format(datetimeFormatter));
        } else if (compte instanceof CompteEpargne) {
            compteRechercheResponse = new CompteRechercheResponse(compte.getId(), compte.getSolde(), TypeCompte.COMPTE_EPARGNE, compte.getDateCreation().format(datetimeFormatter));
        }
        return compteRechercheResponse;

    }

    List<CompteRechercheResponse> compteListToCompteRechercheResponseList(List<Compte> compteList);


}
