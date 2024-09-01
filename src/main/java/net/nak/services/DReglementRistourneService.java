package net.nak.services;

import net.nak.DTO.DetailReglementRistourneDTO;

import java.util.List;

public interface DReglementRistourneService {
    DetailReglementRistourneDTO addDetailReglementRistourne(DetailReglementRistourneDTO detailReglementRistourneDTO);

    DetailReglementRistourneDTO updateDetailReglementRistourne(Long id, DetailReglementRistourneDTO detailReglementRistourneDTO);

    DetailReglementRistourneDTO getDetailReglementRistourneById(Long id);

    List<DetailReglementRistourneDTO> getAllDetailReglementRistourne();

    void deactivateDetailReglementRistourne(Long id);

}
