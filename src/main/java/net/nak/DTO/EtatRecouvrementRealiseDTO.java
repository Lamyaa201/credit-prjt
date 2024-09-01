package net.nak.DTO;

import lombok.Getter;
import lombok.Setter;
import net.nak.entities.ProduitEntreprise;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class EtatRecouvrementRealiseDTO extends AnnexeDTO{
    private String idCredit;

    private Double recouvrementRealise;

    private Double montantFrais;

    private Double partTamwil;

    private Date dateRecouvrementBq;

    private Date dateVirementTamwil;

    private String refReglement;
    private Boolean isActive = true;

    private List<ProduitEntreprise> produitEntreprise;


}
