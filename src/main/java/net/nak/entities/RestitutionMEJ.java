package net.nak.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestitutionMEJ extends Annexe{


    @Column(name = "id_credit")
    private String idCredit;

    @Column(name = "date_restitution")
    private Date dateRestitution;

    @Column(name = "montant_rest")
    private Double montantRest ;


    @Column(name = "ref_restitution")
    private String refRestitution ;

    @Column(name = "isActive")
    private Boolean isActive = true;


    @OneToMany(mappedBy = "reglementMEJ", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProduitEntreprise> produitEntreprise;
}
