package net.nak.services;

import net.nak.DTO.DemandeGarantieFOGDTO;

import java.util.List;

public interface DemandeGarantieFOGService {
    DemandeGarantieFOGDTO addDemandeGarantieFOG(DemandeGarantieFOGDTO demandeGarantieFOGDTO);
    DemandeGarantieFOGDTO updateDemandeGarantieFOG(Long id, DemandeGarantieFOGDTO demandeGarantieFOGDTO);
    DemandeGarantieFOGDTO getDemandeGarantieFOGById(Long id);
    List<DemandeGarantieFOGDTO> getAllDemandeGarantieFOG();

    void deactivateDemandeGarantieFOG(Long id);

}
