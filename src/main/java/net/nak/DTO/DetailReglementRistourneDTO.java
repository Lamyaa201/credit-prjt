package net.nak.DTO;

import lombok.Getter;
import lombok.Setter;
import net.nak.entities.Annexe;
import net.nak.entities.ProduitEntreprise;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class DetailReglementRistourneDTO extends AnnexeDTO {
    private String idCredit;
    private Date dateEcheance;
    private Double montantRistoune;
    private Date dateReglement;
    private String refReglement;

    private Boolean isActive = true;
    private List<ProduitEntreprise> produitEntreprise;


}
