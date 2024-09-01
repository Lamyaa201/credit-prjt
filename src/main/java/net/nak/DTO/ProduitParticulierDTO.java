package net.nak.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.nak.entities.*;


@Getter
@Setter
public class ProduitParticulierDTO  extends ProduitDTO{

    private ChangementDebiteur changementDebiteur;

    private DemandeGarantieFOG demandeGarantieFOG;

    private DemandeMEJGarantie demandeMEJGarantie;

    private EtatImpayes etatImpayes;

    private EtatReglementPrime  etatReglementPrime;

    private Boolean isActive = true;


}
