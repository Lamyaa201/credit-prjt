package net.nak.services;

import net.nak.DTO.ChangementDebiteurDTO;
import java.util.List;
import java.util.Optional;

public interface ChangementDebiteurService {
        ChangementDebiteurDTO addChangementDebiteur(ChangementDebiteurDTO changementDebiteurDTO);
        ChangementDebiteurDTO updateChangementDebiteur(Long id, ChangementDebiteurDTO changementDebiteurDTO);
        Optional<ChangementDebiteurDTO> getChangementDebiteurById(Long id);
        List<ChangementDebiteurDTO> getAllChangementDebiteur();

        void deactivateChangementDebiteur(Long id);

}
