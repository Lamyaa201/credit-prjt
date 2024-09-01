package net.nak.entities;

import javax.persistence.*;

import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Table(name="ProduitEntreprise")
public class ProduitEntreprise extends Produit {


    @ManyToOne
    @JoinColumn(name = "detailReglementRistourne_id")  // the foreign key column
    private DetailReglementRistourne detailReglementRistourne;

    @ManyToOne
    @JoinColumn(name = "etatAnnulationMEJ_id")  // the foreign key column
    private EtatAnnulationMEJ etatAnnulationMEJ;

    @ManyToOne
    @JoinColumn(name = "reglementMEJ_id")  // the foreign key column
    private ReglementMEJ reglementMEJ;

    @ManyToOne
    @JoinColumn(name = "etatRecouvrementRealise_id")  // the foreign key column
    private EtatRecouvrementRealise etatRecouvrementRealise;


    @ManyToOne
    @JoinColumn(name = "restitutionMEJ_id")  // the foreign key column
    private RestitutionMEJ restitutionMEJ;

    @Column(name = "isActive")
    private Boolean isActive = true;

}
