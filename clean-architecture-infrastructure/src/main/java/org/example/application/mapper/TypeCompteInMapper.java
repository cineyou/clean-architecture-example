package org.example.application.mapper;

import org.example.domain.compte.TypeCompte;
import org.example.exception.AntiCorruptionLayerException;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface TypeCompteInMapper {

    default TypeCompte typeCompteStringToTypeCompte(final String pTypeCompteString) {
        return Optional.of(TypeCompte.valueOf(pTypeCompteString))
                .orElseThrow(() -> new AntiCorruptionLayerException("Type de compte inconnu"));


    }
}
