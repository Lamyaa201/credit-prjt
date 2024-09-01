package net.nak.services;

import net.nak.DTO.AnnexeDTO;

import java.util.List;

public interface AnnexeService {

    AnnexeDTO addAnnexe(AnnexeDTO annexeDTO);

    AnnexeDTO updateAnnexe(Long id, AnnexeDTO annexeDTO);


    AnnexeDTO getAnnexeById(Long id);

    List<AnnexeDTO> getAllAnnexe();


}
