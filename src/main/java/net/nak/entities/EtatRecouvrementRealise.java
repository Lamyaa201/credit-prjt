package net.nak.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Etat_Recouvrement_Realise")
public class EtatRecouvrementRealise extends Annexe {

    @Column(name = "id_credit")
    private String idCredit;

    @Column(name = "recouvrement_realise")
    private Double recouvrementRealise;

    @Column(name = "montant_Frais")
    private Double montantFrais;

    @Column(name = "part_Tamwil")
    private Double partTamwil;

    @Column(name = "date_recouvrement_Bq")
    private Date dateRecouvrementBq;

    @Column(name = "date_Virement_Tamwil")
    private Date dateVirementTamwil;

    @Column(name = "ref_reglement")
    private String refReglement;

    @Column(name = "isActive")
    private Boolean isActive = true;


    @OneToMany(mappedBy = "reglementMEJ", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProduitEntreprise> produitEntreprise;
}

