package org.example.persistence.mapper;

import org.example.domain.compte.Compte;
import org.example.domain.compte.CompteCourant;
import org.example.domain.compte.CompteEpargne;
import org.example.persistence.entity.CompteEntity;
import org.example.persistence.entity.TypeCompteEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompteOutMapper {

    default Compte compteEntityToCompte(CompteEntity compteEntity) {
        if (compteEntity.getTypeCompte().getId() == 1) {
            return new CompteCourant(compteEntity.getId(), compteEntity.getSolde(), compteEntity.getCreatedDate(), null);
        } else {
            return new CompteEpargne(compteEntity.getId(), compteEntity.getSolde(), compteEntity.getCreatedDate(), null);
        }
    }

    default CompteEntity compteToCompteEntity(Compte compte) {
        if (compte instanceof CompteCourant) {
            return  CompteEntity.builder().id(compte.getId()).solde(compte.getSolde()).createdDate(compte.getDateCreation()).typeCompte(new TypeCompteEntity(1, null, null)).build();

        } else {
            return  CompteEntity.builder().id(compte.getId()).solde(compte.getSolde()).createdDate(compte.getDateCreation()).typeCompte(new TypeCompteEntity(2, null, null)).build();
        }
    }

    List<CompteEntity> comptesToCompteEntities(List<Compte> comptes);

    List<Compte> compteEntitiesToComptes(List<CompteEntity> compteEntities);

}
