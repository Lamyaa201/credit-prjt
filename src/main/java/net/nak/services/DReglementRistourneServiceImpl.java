package net.nak.services;

import net.nak.DTO.DemandeMEJGarantieDTO;
import net.nak.DTO.DetailReglementRistourneDTO;
import net.nak.entities.ChangementDebiteur;
import net.nak.entities.DemandeMEJGarantie;
import net.nak.entities.DetailReglementRistourne;
import net.nak.repositories.DReglementRistourneRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DReglementRistourneServiceImpl implements DReglementRistourneService {

    private final DReglementRistourneRepository detailReglementRistourneRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DReglementRistourneServiceImpl(DReglementRistourneRepository detailReglementRistourneRepository, ModelMapper modelMapper ) {
        this.detailReglementRistourneRepository = detailReglementRistourneRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public DetailReglementRistourneDTO addDetailReglementRistourne(DetailReglementRistourneDTO detailReglementRistourneDTO) {
        DetailReglementRistourne detailReglementRistourne = new DetailReglementRistourne();
        detailReglementRistourne.setIdCredit(detailReglementRistourneDTO.getIdCredit());
        detailReglementRistourne.setDateEcheance(detailReglementRistourneDTO.getDateEcheance());
        detailReglementRistourne.setMontantRistoune(detailReglementRistourneDTO.getMontantRistoune());
        detailReglementRistourne.setDateReglement(detailReglementRistourneDTO.getDateReglement());
        detailReglementRistourne.setRefReglement(detailReglementRistourneDTO.getRefReglement());

        detailReglementRistourne = detailReglementRistourneRepository.save(detailReglementRistourne);
        return modelMapper.map(detailReglementRistourne, DetailReglementRistourneDTO.class);
    }


    @Override
    public DetailReglementRistourneDTO updateDetailReglementRistourne(Long id, DetailReglementRistourneDTO detailReglementRistourneDTO) {
        Optional<DetailReglementRistourne> optionalDetailReglementRistourne = detailReglementRistourneRepository.findById(id);
        if (optionalDetailReglementRistourne.isPresent()) {
            DetailReglementRistourne detailReglementRistourne = optionalDetailReglementRistourne.get();
            detailReglementRistourne.setIdCredit(detailReglementRistourneDTO.getIdCredit());
            detailReglementRistourne.setDateEcheance(detailReglementRistourneDTO.getDateEcheance());
            detailReglementRistourne.setMontantRistoune(detailReglementRistourneDTO.getMontantRistoune());
            detailReglementRistourne.setDateReglement(detailReglementRistourneDTO.getDateReglement());
            detailReglementRistourne.setRefReglement(detailReglementRistourneDTO.getRefReglement());

            detailReglementRistourne = detailReglementRistourneRepository.save(detailReglementRistourne);
            return modelMapper.map(detailReglementRistourne, DetailReglementRistourneDTO.class);
        }
        throw new EntityNotFoundException("ChangementDebiteur not found with id: " + id);
    }

    @Override
    public DetailReglementRistourneDTO getDetailReglementRistourneById(Long id) {
        Optional<DetailReglementRistourne> optionalDetailReglementRistourne = detailReglementRistourneRepository.findById(id);
        return optionalDetailReglementRistourne.map(detailReglementRistourne -> modelMapper.map(detailReglementRistourne, DetailReglementRistourneDTO.class)).orElse(null);
    }
    @Transactional
    @Override
    public List<DetailReglementRistourneDTO> getAllDetailReglementRistourne() {
        List<DetailReglementRistourne> detailReglementRistourneList = detailReglementRistourneRepository.findAll();
        return detailReglementRistourneList.stream()
                .map(detailReglementRistourne -> modelMapper.map(detailReglementRistourne, DetailReglementRistourneDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deactivateDetailReglementRistourne(Long id) {
        Optional<DetailReglementRistourne> detailReglementRistourne = detailReglementRistourneRepository.findById(id);
        if (detailReglementRistourne.isPresent()) {
            DetailReglementRistourne changement = detailReglementRistourne.get();
            changement.setIsActive(false); // Met à jour le champ isActive
            detailReglementRistourneRepository.save(changement);
        }
    }
}
