package net.nak.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class EtatImpayesDTO extends AnnexeDTO {
    private String numCIN;
    private String numCredit;
    private Date dateImpaye;
    private Double principalImpaye;
    private List<ProduitParticulierDTO> produitParticulier;
    private Boolean isActive = true;

}
