package net.nak.DTO;

import lombok.Getter;
import lombok.Setter;
import net.nak.entities.Annexe;
import net.nak.entities.ProduitEntreprise;
import net.nak.enums.CodeMotif;

import java.util.List;

@Setter
@Getter
public class EtatAnnulationMejDTO extends AnnexeDTO {
    private String idCredit;
    private CodeMotif codeMotif;
    private List<ProduitEntreprise> produitEntreprise;

    private Boolean isActive = true;

}
