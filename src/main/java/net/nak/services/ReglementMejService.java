package net.nak.services;

import net.nak.DTO.ReglementMejDTO;
import java.util.List;

public interface ReglementMejService {
    ReglementMejDTO addReglementMej(ReglementMejDTO reglementMejDTO);
    ReglementMejDTO updateReglementMej(Long id, ReglementMejDTO reglementMejDTO);
    ReglementMejDTO getReglementMejById(Long id);
    List<ReglementMejDTO> getAllReglementMej();

    void deactivateReglementMej(Long id);

}
