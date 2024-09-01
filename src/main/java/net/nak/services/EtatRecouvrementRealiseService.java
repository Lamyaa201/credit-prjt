package net.nak.services;

import net.nak.DTO.EtatRecouvrementRealiseDTO;

import java.util.List;

public interface EtatRecouvrementRealiseService {

    EtatRecouvrementRealiseDTO addEtatRecouvrementRealise(EtatRecouvrementRealiseDTO etatRecouvrementRealiseDTO);
    EtatRecouvrementRealiseDTO updateEtatRecouvrementRealise(Long id, EtatRecouvrementRealiseDTO etatRecouvrementRealiseDTO);
    EtatRecouvrementRealiseDTO getEtatRecouvrementRealiseById(Long id);
    List<EtatRecouvrementRealiseDTO> getAllEtatRecouvrementRealise();

    void deactivateEtatRecouvrementRealise(Long id);

}
