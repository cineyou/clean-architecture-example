package org.example.domain.compte.spi.stubs;

import org.example.domain.compte.Compte;
import org.example.domain.compte.spi.CompteRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Stub
public class InMemoryCompteRepository implements CompteRepository {

    Map<String, Compte> comptesMap = new HashMap<>();

    @Override
    public Compte getById(String id) {
        return comptesMap.get(id);
    }

    @Override
    public Compte save(Compte compte) {
        comptesMap.put(compte.getId(), compte);
        return compte;
    }

    @Override
    public List<Compte> getAll() {
        return comptesMap.values().stream().toList();
    }
}
