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
@NoArgsConstructor
@Getter
@Setter
public class EtatReglementPrime extends Annexe{

    @Column(name = "num_cin")
     private String numCin ;

    @Column(name = "num_credit")
    private String numCredit ;

    @Column(name = "Date_echeance")
    private Date dateEcheance;

    @Column(name = "Montant_reglement")
    private Double montantRegle;

    @Column(name = "Ref_reglement")
    private String refReglement;

    @Column(name = "Date_reglement")
    private Date dateReglement;

    @Column(name = "isActive")
    private Boolean isActive = true;


    @OneToMany(mappedBy = "changementDebiteur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProduitParticulier> produitParticulier;
}
