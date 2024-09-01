package net.nak.services;

import net.nak.DTO.ChangementDebiteurDTO;
import net.nak.DTO.DemandeMEJGarantieDTO;
import net.nak.entities.ChangementDebiteur;
import net.nak.entities.DemandeMEJGarantie;
import net.nak.repositories.DemandeMEJGarantieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DemandeMEJGarantieServiceImpl implements DemandeMEJGarantieService{

        private final DemandeMEJGarantieRepository demandeMEJGarantieRepository;
        private final ModelMapper modelMapper;

        public DemandeMEJGarantieServiceImpl(DemandeMEJGarantieRepository demandeMEJGarantieRepository,ModelMapper modelMapper) {
            this.demandeMEJGarantieRepository = demandeMEJGarantieRepository;
            this.modelMapper = modelMapper;
        }


    @Override
    public DemandeMEJGarantieDTO addDemandeMEJGarantie(DemandeMEJGarantieDTO demandeMEJGarantieDTO) {
        DemandeMEJGarantie demandeMEJGarantie = new DemandeMEJGarantie();
        demandeMEJGarantie.setNumCIN(demandeMEJGarantieDTO.getNumCIN());
        demandeMEJGarantie.setNumCredit(demandeMEJGarantieDTO.getNumCredit());
        demandeMEJGarantie.setMontant(demandeMEJGarantieDTO.getMontant());
        demandeMEJGarantie.setDateEcheance(demandeMEJGarantieDTO.getDateEcheance());
        demandeMEJGarantie.setDatePremEchImpaye(demandeMEJGarantieDTO.getDatePremEchImpaye());
        demandeMEJGarantie.setMontantRestant(demandeMEJGarantieDTO.getMontantRestant());
        demandeMEJGarantie.setMontantReclame(demandeMEJGarantieDTO.getMontantReclame());

        demandeMEJGarantie = demandeMEJGarantieRepository.save(demandeMEJGarantie);
        return modelMapper.map(demandeMEJGarantie, DemandeMEJGarantieDTO.class);
    }

    @Override
    public DemandeMEJGarantieDTO updateDemandeMEJGarantie(Long id, DemandeMEJGarantieDTO demandeMEJGarantieDTO) {
        Optional<DemandeMEJGarantie> optionalDemandeMEJGarantie = demandeMEJGarantieRepository.findById(id);
        if (optionalDemandeMEJGarantie.isPresent()) {
            DemandeMEJGarantie demandeMEJGarantie = optionalDemandeMEJGarantie.get();
            demandeMEJGarantie.setNumCIN(demandeMEJGarantieDTO.getNumCIN());
            demandeMEJGarantie.setNumCredit(demandeMEJGarantieDTO.getNumCredit());
            demandeMEJGarantie.setMontant(demandeMEJGarantieDTO.getMontant());
            demandeMEJGarantie.setDateEcheance(demandeMEJGarantieDTO.getDateEcheance());
            demandeMEJGarantie.setDatePremEchImpaye(demandeMEJGarantieDTO.getDatePremEchImpaye());
            demandeMEJGarantie.setMontantRestant(demandeMEJGarantieDTO.getMontantRestant());
            demandeMEJGarantie.setMontantReclame(demandeMEJGarantieDTO.getMontantReclame());

            demandeMEJGarantie = demandeMEJGarantieRepository.save(demandeMEJGarantie);
            return modelMapper.map(demandeMEJGarantie, DemandeMEJGarantieDTO.class);
        }
        throw new EntityNotFoundException("ChangementDebiteur not found with id: " + id);
    }


        @Override
        public DemandeMEJGarantieDTO getDemandeMEJGarantieById(Long id) {
            DemandeMEJGarantie demandeMEJGarantie = demandeMEJGarantieRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Demande MEJ Garantie not found with id: " + id));
            return modelMapper.map(demandeMEJGarantie, DemandeMEJGarantieDTO.class);
        }

        @Transactional
        @Override
        public List<DemandeMEJGarantieDTO> getAllDemandeMEJGarantie() {
            List<DemandeMEJGarantie> allDemandeMEJGarantie = demandeMEJGarantieRepository.findAll();
            return allDemandeMEJGarantie.stream()
                    .map(demandeMEJGarantie -> modelMapper.map(demandeMEJGarantie, DemandeMEJGarantieDTO.class))
                    .collect(Collectors.toList());
        }

    @Override
    @Transactional
    public void deactivateDemandeMEJGarantie(Long id) {
        Optional<DemandeMEJGarantie> demandeMEJGarantie = demandeMEJGarantieRepository.findById(id);
        if (demandeMEJGarantie.isPresent()) {
            DemandeMEJGarantie demandeMEJ = demandeMEJGarantie.get();
            demandeMEJ.setIsActive(false); // Met à jour le champ isActive
            demandeMEJGarantieRepository.save(demandeMEJ);
        }
    }

}
