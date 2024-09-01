package net.nak.services;

import net.nak.DTO.DemandeMEJGarantieDTO;
import net.nak.DTO.EtatImpayesDTO;

import java.util.List;
import java.util.Optional;

public interface EtatImpayesService {
    EtatImpayesDTO addEtatImpayes(EtatImpayesDTO etatImpayesDTO);
    EtatImpayesDTO updateEtatImpayes(Long id, EtatImpayesDTO etatImpayesDTO);
    Optional<EtatImpayesDTO> getEtatImpayesById(Long id);
    List<EtatImpayesDTO> getAllEtatImpayes();

    void deactivateEtatImpayes(Long id);


}
