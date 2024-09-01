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
public class EtatImpayes extends Annexe {

    @Column(name = "num_cin")
    private String numCIN;

    @Column(name = "num_credit")
    private String numCredit;

    @Column(name = "Date_impaye")
    private Date dateImpaye;

    @Column(name = "Principal_impaye")
    private Double principalImpaye;

    @Column(name = "isActive")
    private Boolean isActive = true;


    @OneToMany(mappedBy = "etatImpayes", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProduitParticulier> produitParticulier;
}
