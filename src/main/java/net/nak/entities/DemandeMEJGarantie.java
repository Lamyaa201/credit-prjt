package net.nak.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor @Getter @Setter
public class DemandeMEJGarantie extends Annexe{

    @Column(name = "num_cin")
    private String numCIN;

    @Column(name = "num_credit")
    private String numCredit;

    @Column(name = "montant")
    private Double montant;

    @Column(name = "date_echeance")
    private Date dateEcheance;

    @Column(name = "date_prem_ech_impaye")
    private Date datePremEchImpaye;

    @Column(name = "montant_restant")
    private Double montantRestant;

    @Column(name = "montant_reclame")
    private Double montantReclame;

    @Column(name = "isActive")
    private Boolean isActive = true;


    @OneToMany(mappedBy = "changementDebiteur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProduitParticulier> produitParticulier;

}
