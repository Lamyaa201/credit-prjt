package net.nak.services;

import net.nak.DTO.EtatRecouvrementRealiseDTO;
import net.nak.DTO.ReglementMejDTO;
import net.nak.entities.EtatRecouvrementRealise;
import net.nak.entities.ProduitEntreprise;
import net.nak.entities.ReglementMEJ;
import net.nak.repositories.ReglementMejRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReglementMejServiceImpl implements ReglementMejService {

    private final ReglementMejRepository reglementMejRepository;
    private final ModelMapper modelMapper;

    public ReglementMejServiceImpl(ReglementMejRepository reglementMejRepository, ModelMapper modelMapper) {
        this.reglementMejRepository = reglementMejRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ReglementMejDTO addReglementMej(ReglementMejDTO reglementMejDTO) {
        ReglementMEJ reglementMEJ = new ReglementMEJ();
        reglementMEJ.setIdCredit(reglementMejDTO.getIdCredit());
        reglementMEJ.setMontantMEJ(reglementMejDTO.getMontantMEJ());
        reglementMEJ.setDateReglement(reglementMejDTO.getDateReglement());
        reglementMEJ.setRefReglement(reglementMejDTO.getRefReglement());

        reglementMEJ = reglementMejRepository.save(reglementMEJ);
        return modelMapper.map(reglementMEJ, ReglementMejDTO.class);
    }

    @Override
    public ReglementMejDTO updateReglementMej(Long id, ReglementMejDTO reglementMejDTO) {
        Optional<ReglementMEJ> optionalReglementMEJ = reglementMejRepository.findById(id);
        if (optionalReglementMEJ.isPresent()) {
            ReglementMEJ reglementMEJ = optionalReglementMEJ.get();
            reglementMEJ.setIdCredit(reglementMejDTO.getIdCredit());
            reglementMEJ.setMontantMEJ(reglementMejDTO.getMontantMEJ());
            reglementMEJ.setDateReglement(reglementMejDTO.getDateReglement());
            reglementMEJ.setRefReglement(reglementMejDTO.getRefReglement());

            reglementMEJ = reglementMejRepository.save(reglementMEJ);
            return modelMapper.map(reglementMEJ, ReglementMejDTO.class);
        }
        throw new EntityNotFoundException("ChangementDebiteur not found with id: " + id);
    }
    @Override
    public ReglementMejDTO getReglementMejById(Long id) {
        ReglementMEJ reglementMej = reglementMejRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ReglementMej not found with id: " + id));
        return modelMapper.map(reglementMej, ReglementMejDTO.class);
    }
    @Transactional
    @Override
    public List<ReglementMejDTO> getAllReglementMej() {
        List<ReglementMEJ> allReglementMej = reglementMejRepository.findAll();
        return allReglementMej.stream()
                .map(reglementMej -> modelMapper.map(reglementMej, ReglementMejDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deactivateReglementMej(Long id) {
        Optional<ReglementMEJ> reglementMEJ = reglementMejRepository.findById(id);
        if (reglementMEJ.isPresent()) {
            ReglementMEJ reglement = reglementMEJ.get();
            reglement.setIsActive(false); // Met à jour le champ isActive
            reglementMejRepository.save(reglement);
        }
    }
}
