package net.nak.services;

import net.nak.DTO.EtatRecouvrementRealiseDTO;
import net.nak.DTO.RestitutionMEJDTO;
import net.nak.entities.EtatRecouvrementRealise;
import net.nak.entities.ReglementMEJ;
import net.nak.entities.RestitutionMEJ;
import net.nak.repositories.RestitutionMEJRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestitutionMEJServiceImpl implements RestitutionMEJService {

        private final RestitutionMEJRepository restitutionMEJRepository;
        private final ModelMapper modelMapper;

        public RestitutionMEJServiceImpl(RestitutionMEJRepository restitutionMEJRepository,ModelMapper modelMapper) {
            this.restitutionMEJRepository = restitutionMEJRepository;
            this.modelMapper = modelMapper;
        }


    @Override
    public RestitutionMEJDTO addRestitutionMEJ(RestitutionMEJDTO restitutionMEJDTO) {
        RestitutionMEJ restitutionMEJ = new RestitutionMEJ();
        restitutionMEJ.setIdCredit(restitutionMEJDTO.getIdCredit());
        restitutionMEJ.setMontantRest(restitutionMEJDTO.getMontantRest());
        restitutionMEJ.setDateRestitution(restitutionMEJDTO.getDateRestitution());
        restitutionMEJ.setRefRestitution(restitutionMEJDTO.getRefRestitution());

        restitutionMEJ = restitutionMEJRepository.save(restitutionMEJ);
        return modelMapper.map(restitutionMEJ, RestitutionMEJDTO.class);
    }

    @Override
    public RestitutionMEJDTO updateRestitutionMEJ(Long id, RestitutionMEJDTO restitutionMEJDTO) {
        Optional<RestitutionMEJ> optionalRestitutionMEJ = restitutionMEJRepository.findById(id);
        if (optionalRestitutionMEJ.isPresent()) {
            RestitutionMEJ restitutionMEJ = optionalRestitutionMEJ.get();
            restitutionMEJ.setIdCredit(restitutionMEJDTO.getIdCredit());
            restitutionMEJ.setMontantRest(restitutionMEJDTO.getMontantRest());
            restitutionMEJ.setDateRestitution(restitutionMEJDTO.getDateRestitution());
            restitutionMEJ.setRefRestitution(restitutionMEJDTO.getRefRestitution());

            restitutionMEJ = restitutionMEJRepository.save(restitutionMEJ);
            return modelMapper.map(restitutionMEJ, RestitutionMEJDTO.class);
        }
        throw new EntityNotFoundException("ChangementDebiteur not found with id: " + id);
    }

        @Override
        public RestitutionMEJDTO getRestitutionMEJById(Long id) {
            RestitutionMEJ restitutionMEJ = restitutionMEJRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Restitution MEJ not found with id: " + id));
            return modelMapper.map(restitutionMEJ, RestitutionMEJDTO.class);
        }

        @Transactional
        @Override
        public List<RestitutionMEJDTO> getAllRestitutionMEJ() {
            List<RestitutionMEJ> allRestitutionMEJ= restitutionMEJRepository.findAll();
            return allRestitutionMEJ.stream()
                    .map(restitutionMEJ -> modelMapper.map(restitutionMEJ, RestitutionMEJDTO.class))
                    .collect(Collectors.toList());
        }

    @Override
    @Transactional
    public void deactivateRestitutionMEJ(Long id) {
        Optional<RestitutionMEJ> restitutionMEJ = restitutionMEJRepository.findById(id);
        if (restitutionMEJ.isPresent()) {
            RestitutionMEJ restitution = restitutionMEJ.get();
            restitution.setIsActive(false); // Met à jour le champ isActive
            restitutionMEJRepository.save(restitution);
        }
    }

}
