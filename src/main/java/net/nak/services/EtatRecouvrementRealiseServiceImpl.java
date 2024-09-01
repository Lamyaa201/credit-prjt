package net.nak.services;

import net.nak.DTO.ChangementDebiteurDTO;
import net.nak.DTO.EtatRecouvrementRealiseDTO;
import net.nak.entities.ChangementDebiteur;
import net.nak.entities.EtatAnnulationMEJ;
import net.nak.entities.EtatRecouvrementRealise;
import net.nak.repositories.EtatRecouvrementRealiseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EtatRecouvrementRealiseServiceImpl implements EtatRecouvrementRealiseService{

        private final EtatRecouvrementRealiseRepository etatRecouvrementRealiseRepository;
        private final ModelMapper modelMapper;

        public EtatRecouvrementRealiseServiceImpl(EtatRecouvrementRealiseRepository etatRecouvrementRealiseRepository,ModelMapper modelMapper) {
            this.etatRecouvrementRealiseRepository = etatRecouvrementRealiseRepository;
            this.modelMapper = modelMapper;
        }

    @Override
    public EtatRecouvrementRealiseDTO addEtatRecouvrementRealise(EtatRecouvrementRealiseDTO etatRecouvrementRealiseDTO) {
        EtatRecouvrementRealise etatRecouvrementRealise = new EtatRecouvrementRealise();
        etatRecouvrementRealise.setIdCredit(etatRecouvrementRealiseDTO.getIdCredit());
        etatRecouvrementRealise.setRecouvrementRealise(etatRecouvrementRealiseDTO.getRecouvrementRealise());
        etatRecouvrementRealise.setMontantFrais(etatRecouvrementRealiseDTO.getMontantFrais());
        etatRecouvrementRealise.setPartTamwil(etatRecouvrementRealiseDTO.getPartTamwil());
        etatRecouvrementRealise.setDateRecouvrementBq(etatRecouvrementRealiseDTO.getDateRecouvrementBq());
        etatRecouvrementRealise.setDateVirementTamwil(etatRecouvrementRealiseDTO.getDateVirementTamwil());
        etatRecouvrementRealise.setRefReglement(etatRecouvrementRealiseDTO.getRefReglement());

        etatRecouvrementRealise = etatRecouvrementRealiseRepository.save(etatRecouvrementRealise);
        return modelMapper.map(etatRecouvrementRealise, EtatRecouvrementRealiseDTO.class);
    }

    @Override
    public EtatRecouvrementRealiseDTO updateEtatRecouvrementRealise(Long id, EtatRecouvrementRealiseDTO etatRecouvrementRealiseDTO) {
        Optional<EtatRecouvrementRealise> optionalEtatRecouvrementRealise = etatRecouvrementRealiseRepository.findById(id);
        if (optionalEtatRecouvrementRealise.isPresent()) {
            EtatRecouvrementRealise etatRecouvrementRealise = optionalEtatRecouvrementRealise.get();
            etatRecouvrementRealise.setIdCredit(etatRecouvrementRealiseDTO.getIdCredit());
            etatRecouvrementRealise.setRecouvrementRealise(etatRecouvrementRealiseDTO.getRecouvrementRealise());
            etatRecouvrementRealise.setMontantFrais(etatRecouvrementRealiseDTO.getMontantFrais());
            etatRecouvrementRealise.setPartTamwil(etatRecouvrementRealiseDTO.getPartTamwil());
            etatRecouvrementRealise.setDateRecouvrementBq(etatRecouvrementRealiseDTO.getDateRecouvrementBq());
            etatRecouvrementRealise.setDateVirementTamwil(etatRecouvrementRealiseDTO.getDateVirementTamwil());
            etatRecouvrementRealise.setRefReglement(etatRecouvrementRealiseDTO.getRefReglement());

            etatRecouvrementRealise = etatRecouvrementRealiseRepository.save(etatRecouvrementRealise);
            return modelMapper.map(etatRecouvrementRealise, EtatRecouvrementRealiseDTO.class);
        }
        throw new EntityNotFoundException("ChangementDebiteur not found with id: " + id);
    }

        @Override
        public EtatRecouvrementRealiseDTO getEtatRecouvrementRealiseById(Long id) {
            EtatRecouvrementRealise etatRecouvrementRealise = etatRecouvrementRealiseRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Etat Recouvrement Realise not found with id: " + id));
            return modelMapper.map(etatRecouvrementRealise, EtatRecouvrementRealiseDTO.class);
        }
    @Transactional
        @Override
        public List<EtatRecouvrementRealiseDTO> getAllEtatRecouvrementRealise() {
            List<EtatRecouvrementRealise> allEtatRecouvrementRealise= etatRecouvrementRealiseRepository.findAll();
            return allEtatRecouvrementRealise.stream()
                    .map(etatRecouvrementRealise -> modelMapper.map(etatRecouvrementRealise, EtatRecouvrementRealiseDTO.class))
                    .collect(Collectors.toList());
        }

    @Override
    @Transactional
    public void deactivateEtatRecouvrementRealise(Long id) {
        Optional<EtatRecouvrementRealise> etatRecouvrementRealise = etatRecouvrementRealiseRepository.findById(id);
        if (etatRecouvrementRealise.isPresent()) {
            EtatRecouvrementRealise etatRecouvrementR = etatRecouvrementRealise.get();
            etatRecouvrementR.setIsActive(false); // Met à jour le champ isActive
            etatRecouvrementRealiseRepository.save(etatRecouvrementR);
        }
    }

}
