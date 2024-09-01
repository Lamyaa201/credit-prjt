package net.nak.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Table(name="ReglementMEJ")
public class ReglementMEJ extends Annexe{

    @Column(name = "id_credit")
    private String idCredit;

    @Column(name = "montant_mej")
    private Double montantMEJ;

    @Column(name = "date_reglement")
    private Date dateReglement;

    @Column(name = "ref_reglement")
    private String refReglement;

    @Column(name = "isActive")
    private Boolean isActive = true;


    @OneToMany(mappedBy = "reglementMEJ", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProduitEntreprise> produitEntreprise;
}
