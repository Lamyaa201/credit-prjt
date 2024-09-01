package net.nak.DTO;

import lombok.Getter;
import lombok.Setter;
import net.nak.entities.ProduitEntreprise;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class RestitutionMEJDTO extends AnnexeDTO {
    private String idCredit;

    private Double montantRest ;

    private Date dateRestitution;

    private String refRestitution ;

    private Boolean isActive = true;

    private List<ProduitEntreprise> produitEntreprise;


}
