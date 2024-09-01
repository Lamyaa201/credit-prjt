package net.nak.entities;

import javax.persistence.*;

import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Table(name="ProduitParticulier")
public class ProduitParticulier extends Produit{

    @ManyToOne
    @JoinColumn(name = "changementDebiteur_id")  // the foreign key column
    private ChangementDebiteur changementDebiteur;

    @ManyToOne
    @JoinColumn(name = "demandeGarantieFOG_id")  // the foreign key column
    private DemandeGarantieFOG demandeGarantieFOG;

    @ManyToOne
    @JoinColumn(name = "demandeMEJGarantie_id")  // the foreign key column
    private DemandeMEJGarantie demandeMEJGarantie;

    @ManyToOne
    @JoinColumn(name = "etatImpayes_id")  // the foreign key column
    private EtatImpayes etatImpayes;

    @ManyToOne
    @JoinColumn(name = "etatReglementPrime_id")  // the foreign key column
    private EtatReglementPrime  etatReglementPrime;

    @Column(name = "isActive")
    private Boolean isActive = true;

}
