package org.example.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "type_compte", schema = "public")
public class TypeCompteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "type_compte_generator")
    @SequenceGenerator(name = "type_compte_generator", sequenceName = "type_compte_seq", allocationSize = 1)
    private Integer id;
    private String libelle;
    @OneToMany(mappedBy = "typeCompte")
    private List<CompteEntity> comptes;
}
