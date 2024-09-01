package net.nak.services;

import net.nak.DTO.DemandeMEJGarantieDTO;
import net.nak.DTO.EtatAnnulationMejDTO;
import net.nak.entities.DemandeMEJGarantie;
import net.nak.entities.DetailReglementRistourne;
import net.nak.entities.EtatAnnulationMEJ;
import net.nak.repositories.EtatAnnulationMejRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EtatAnnulationMejServiceImpl implements EtatAnnulationMejService {

    private final EtatAnnulationMejRepository etatAnnulationMejRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EtatAnnulationMejServiceImpl(EtatAnnulationMejRepository etatAnnulationMejRepository, ModelMapper modelMapper ) {
        this.etatAnnulationMejRepository = etatAnnulationMejRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EtatAnnulationMejDTO addEtatAnnulationMej(EtatAnnulationMejDTO etatAnnulationMejDTO) {
        EtatAnnulationMEJ etatAnnulationMEJ = new EtatAnnulationMEJ();
        etatAnnulationMEJ.setIdCredit(etatAnnulationMejDTO.getIdCredit());
        etatAnnulationMEJ.setCodeMotif(etatAnnulationMejDTO.getCodeMotif());
        etatAnnulationMEJ = etatAnnulationMejRepository.save(etatAnnulationMEJ);
        return modelMapper.map(etatAnnulationMEJ, EtatAnnulationMejDTO.class);
    }


    public EtatAnnulationMejDTO updateEtatAnnulationMej(Long id, EtatAnnulationMejDTO etatAnnulationMejDTO) {
        Optional<EtatAnnulationMEJ> optionalEtatAnnulationMEJ = etatAnnulationMejRepository.findById(id);
        if (optionalEtatAnnulationMEJ.isPresent()) {
            EtatAnnulationMEJ etatAnnulationMEJ = optionalEtatAnnulationMEJ.get();
            etatAnnulationMEJ.setIdCredit(etatAnnulationMejDTO.getIdCredit());
            etatAnnulationMEJ.setCodeMotif(etatAnnulationMejDTO.getCodeMotif());
            etatAnnulationMEJ = etatAnnulationMejRepository.save(etatAnnulationMEJ);
            return modelMapper.map(etatAnnulationMEJ, EtatAnnulationMejDTO.class);
        }
        throw new EntityNotFoundException("ChangementDebiteur not found with id: " + id);
    }

    @Override
    public EtatAnnulationMejDTO getEtatAnnulationMejById(Long id) {
        EtatAnnulationMEJ etatAnnulationMej = etatAnnulationMejRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Etat Annulation MEJ not found with id: " + id));
        return modelMapper.map(etatAnnulationMej, EtatAnnulationMejDTO.class);
    }

    @Transactional
    @Override
    public List<EtatAnnulationMejDTO> getAllEtatAnnulationMej() {
        List<EtatAnnulationMEJ> allEtatAnnulationMej = etatAnnulationMejRepository.findAll();
        return allEtatAnnulationMej.stream()
                .map(etatAnnulationMej -> modelMapper.map(etatAnnulationMej, EtatAnnulationMejDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deactivateEtatAnnulationMej(Long id) {
        Optional<EtatAnnulationMEJ> etatAnnulationMEJ = etatAnnulationMejRepository.findById(id);
        if (etatAnnulationMEJ.isPresent()) {
            EtatAnnulationMEJ changement = etatAnnulationMEJ.get();
            changement.setIsActive(false); // Met à jour le champ isActive
            etatAnnulationMejRepository.save(changement);
        }
    }
}
