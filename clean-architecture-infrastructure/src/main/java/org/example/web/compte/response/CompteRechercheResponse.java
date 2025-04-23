package org.example.web.compte.response;

import org.example.domain.compte.TypeCompte;

import java.time.LocalDateTime;

public record CompteRechercheResponse(String id, Double solde, TypeCompte type, String dateCreation) {
}
