package org.example.persistence.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.persistence.entity.common.Auditable;


@SuperBuilder
@NoArgsConstructor
@Getter
@Entity
@Table(name = "compte", schema = "public")
public class CompteEntity extends Auditable {
    @Id
    private String id;
    private Double solde;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_compte_id")
    private TypeCompteEntity typeCompte;

}
