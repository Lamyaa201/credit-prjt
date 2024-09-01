package net.nak.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.nak.entities.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
public class ProduitEntrepriseDTO extends ProduitDTO {

    private DetailReglementRistourne detailReglementRistourne;

    private EtatAnnulationMEJ etatAnnulationMEJ;

    private ReglementMEJ reglementMEJ;

    private EtatRecouvrementRealise etatRecouvrementRealise;

    private RestitutionMEJ restitutionMEJ;
    private Boolean isActive = true;

}