package net.nak.DTO;

import lombok.Getter;
import lombok.Setter;
import net.nak.entities.ProduitParticulier;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class EtatReglementPrimeDTO extends AnnexeDTO {
    private String numCin ;

    private String numCredit ;

    private Date dateEcheance;

    private Double montantRegle;

    private String refReglement;

    private Date dateReglement;
    private Boolean isActive = true;

    private List<ProduitParticulier> produitParticulier;

}
