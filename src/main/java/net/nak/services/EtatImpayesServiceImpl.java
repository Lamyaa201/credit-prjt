package net.nak.services;

import net.nak.DTO.EtatImpayesDTO;
import net.nak.entities.EtatImpayes;
import net.nak.entities.ProduitParticulier;
import net.nak.repositories.EtatImpayesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EtatImpayesServiceImpl implements EtatImpayesService {

    private final EtatImpayesRepository etatImpayesRepository;

    private final ModelMapper modelMapper;

    public EtatImpayesServiceImpl(EtatImpayesRepository etatImpayesRepository, ModelMapper modelMapper) {
        this.etatImpayesRepository = etatImpayesRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EtatImpayesDTO addEtatImpayes(EtatImpayesDTO etatImpayesDTO) {
        EtatImpayes etatImpayes = new EtatImpayes();
        etatImpayes.setIsActive(true);
        etatImpayes.setNumCIN(etatImpayesDTO.getNumCIN());
        etatImpayes.setNumCredit(etatImpayesDTO.getNumCredit());
        etatImpayes.setDateImpaye(etatImpayesDTO.getDateImpaye());
        etatImpayes.setPrincipalImpaye(etatImpayesDTO.getPrincipalImpaye());

        etatImpayes = etatImpayesRepository.save(etatImpayes);
        return modelMapper.map(etatImpayes, EtatImpayesDTO.class);
    }



    @Override
    public EtatImpayesDTO updateEtatImpayes(Long id, EtatImpayesDTO etatImpayesDTO) {
        Optional<EtatImpayes> optionalEtatImpayes = etatImpayesRepository.findById(id);
        if (optionalEtatImpayes.isPresent()) {
            EtatImpayes etatImpayes = optionalEtatImpayes.get();
            // Mapping explicite de chaque attribut
            etatImpayes.setNumCIN(etatImpayesDTO.getNumCIN());
            etatImpayes.setNumCredit(etatImpayesDTO.getNumCredit());
            etatImpayes.setDateImpaye(etatImpayesDTO.getDateImpaye());
            etatImpayes.setPrincipalImpaye(etatImpayesDTO.getPrincipalImpaye());

            etatImpayes = etatImpayesRepository.save(etatImpayes);
            return modelMapper.map(etatImpayes, EtatImpayesDTO.class);
        }
        return null;
    }

    @Override
    public Optional<EtatImpayesDTO> getEtatImpayesById(Long id) {
        return etatImpayesRepository.findById(id)
                .map(etatImpayes -> modelMapper.map(etatImpayes, EtatImpayesDTO.class));
    }

    @Transactional
    @Override
    public List<EtatImpayesDTO> getAllEtatImpayes() {
        List<EtatImpayes> allEtatImpayes = etatImpayesRepository.findAll();
        return allEtatImpayes.stream()
                .map(etatImpayes -> modelMapper.map(etatImpayes, EtatImpayesDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public void deactivateEtatImpayes(Long id) {
        Optional<EtatImpayes> etatImpayes = etatImpayesRepository.findById(id);
        if (etatImpayes.isPresent()) {
            EtatImpayes impaye = etatImpayes.get();
            impaye.setIsActive(false); // Met à jour le champ isActive
            etatImpayesRepository.save(impaye);
        }
    }

}