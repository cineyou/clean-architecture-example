package org.example.application;

import lombok.AllArgsConstructor;
import org.example.application.mapper.CompteInMapper;
import org.example.application.mapper.TypeCompteInMapper;
import org.example.domain.compte.TypeCompte;
import org.example.domain.compte.api.CompteUseCase;
import org.example.web.compte.response.CompteCreationResponse;
import org.example.web.compte.response.CompteRechercheResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CompteServiceFacade {

    private final CompteUseCase compteUseCase;
    private final CompteInMapper compteInMapper;
    private final TypeCompteInMapper typeCompteInMapper;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public CompteCreationResponse createCompte(String pTypeCompte, Double soldeInitial) {
        final TypeCompte typeCompte = typeCompteInMapper.typeCompteStringToTypeCompte(pTypeCompte);
        return compteInMapper.compteToCompteCreationResponse(compteUseCase.createCompte(typeCompte, soldeInitial));
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Double getSoldeCompte(String id) {
        return compteUseCase.getSoldeCompte(id);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void debiterCompte(String id, Double montant) {
        compteUseCase.debiterCompte(id, montant);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void crediterCompte(String id, Double montant) {
        compteUseCase.crediterCompte(id, montant);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public List<CompteRechercheResponse> getAll() {
        return compteInMapper.compteListToCompteRechercheResponseList(compteUseCase.getAllComptes());
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public CompteRechercheResponse getCompteById(String id) {
        return compteInMapper.compteToCompteRechercheResponse(compteUseCase.getCompteById(id));
    }

}
