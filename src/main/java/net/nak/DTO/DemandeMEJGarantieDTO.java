package net.nak.DTO;

import lombok.Getter;
import lombok.Setter;
import net.nak.entities.ProduitParticulier;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class DemandeMEJGarantieDTO extends AnnexeDTO{
    private String numCIN;
    private String numCredit;
    private Double montant;
    private Date dateEcheance;
    private Date datePremEchImpaye;
    private Double montantRestant;
    private Double montantReclame;
    private List<ProduitParticulier> produitParticulier;

    private Boolean isActive = true;

}
