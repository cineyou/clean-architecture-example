package org.example.web.compte;

import lombok.AllArgsConstructor;
import org.example.application.CompteServiceFacade;
import org.example.application.mapper.CompteInMapper;
import org.example.web.compte.request.CompteCreationRequest;
import org.example.web.compte.response.CompteCreationResponse;
import org.example.web.compte.response.CompteRechercheResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comptes")
@AllArgsConstructor
public class CompteController {

    private CompteServiceFacade compteServiceFacade;
    private final CompteInMapper compteInMapper;

    @PostMapping
    public ResponseEntity<CompteCreationResponse> createCompte(@RequestBody CompteCreationRequest request) {
        CompteCreationResponse compteCreationResponse = compteServiceFacade.createCompte(request.typeCompte(), request.montant());

        return ResponseEntity.ok(compteCreationResponse);
    }

    @GetMapping
    public ResponseEntity<List<CompteRechercheResponse>> getAllComptes() {
        List<CompteRechercheResponse> compteRechercheResponseList = compteServiceFacade.getAll();
        return ResponseEntity.ok(compteRechercheResponseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompteRechercheResponse> getCompteById(@PathVariable String id) {
        CompteRechercheResponse compteRechercheResponse = compteServiceFacade.getCompteById(id);
        return ResponseEntity.ok(compteRechercheResponse);
    }
}
