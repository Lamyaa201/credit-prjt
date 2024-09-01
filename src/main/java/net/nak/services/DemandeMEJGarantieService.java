package net.nak.services;

import net.nak.DTO.DemandeMEJGarantieDTO;

import java.util.List;

public interface DemandeMEJGarantieService {
    DemandeMEJGarantieDTO addDemandeMEJGarantie(DemandeMEJGarantieDTO demandeMEJGarantieDTO);
    DemandeMEJGarantieDTO updateDemandeMEJGarantie(Long id, DemandeMEJGarantieDTO demandeMEJGarantieDTO);
    DemandeMEJGarantieDTO getDemandeMEJGarantieById(Long id);
    List<DemandeMEJGarantieDTO> getAllDemandeMEJGarantie();
    void deactivateDemandeMEJGarantie(Long id);

}
