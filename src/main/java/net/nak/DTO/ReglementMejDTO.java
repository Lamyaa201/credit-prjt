package net.nak.DTO;

import lombok.Getter;
import lombok.Setter;
import net.nak.entities.Annexe;
import net.nak.entities.ProduitEntreprise;

import java.util.Date;
import java.util.List;
@Getter
@Setter
public class ReglementMejDTO extends AnnexeDTO {
    private String idCredit;
    private Double montantMEJ;
    private Date dateReglement;
    private String refReglement;

    private Boolean isActive = true;
    private List<ProduitEntreprise> produitEntreprise;

}
