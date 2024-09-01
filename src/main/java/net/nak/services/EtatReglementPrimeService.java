package net.nak.services;

import net.nak.DTO.EtatReglementPrimeDTO;

import java.util.List;

public interface EtatReglementPrimeService {
    EtatReglementPrimeDTO addEtatReglementPrime(EtatReglementPrimeDTO etatReglementPrimeDTO);
    EtatReglementPrimeDTO updateEtatReglementPrime(Long id, EtatReglementPrimeDTO etatReglementPrimeDTO);
    EtatReglementPrimeDTO getEtatReglementPrimeById(Long id);
    List<EtatReglementPrimeDTO> getAllEtatReglementPrime();

    void deactivateEtatReglementPrime(Long id);

}
