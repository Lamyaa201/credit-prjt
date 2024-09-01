package net.nak.services;

import net.nak.DTO.RestitutionMEJDTO;

import java.util.List;

public interface RestitutionMEJService {
    RestitutionMEJDTO addRestitutionMEJ(RestitutionMEJDTO restitutionMEJDTO);
    RestitutionMEJDTO updateRestitutionMEJ(Long id, RestitutionMEJDTO restitutionMEJDTO);
    RestitutionMEJDTO getRestitutionMEJById(Long id);
    List<RestitutionMEJDTO> getAllRestitutionMEJ();

    void deactivateRestitutionMEJ(Long id);

}
