package net.nak.services;

import net.nak.DTO.ChangementDebiteurDTO;
import net.nak.DTO.EtatReglementPrimeDTO;
import net.nak.entities.ChangementDebiteur;
import net.nak.entities.EtatRecouvrementRealise;
import net.nak.entities.EtatReglementPrime;
import net.nak.repositories.EtatReglementPrimeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EtatReglementPrimeServiceImpl implements EtatReglementPrimeService{

        private final EtatReglementPrimeRepository etatReglementPrimeRepository;
        private final ModelMapper modelMapper;

        public EtatReglementPrimeServiceImpl(EtatReglementPrimeRepository etatReglementPrimeRepository,ModelMapper modelMapper) {
            this.etatReglementPrimeRepository = etatReglementPrimeRepository;
            this.modelMapper = modelMapper;
        }

    @Override
    public EtatReglementPrimeDTO addEtatReglementPrime(EtatReglementPrimeDTO etatReglementPrimeDTO) {
        EtatReglementPrime etatReglementPrime = new EtatReglementPrime();
        etatReglementPrime.setNumCin(etatReglementPrimeDTO.getNumCin());
        etatReglementPrime.setNumCredit(etatReglementPrimeDTO.getNumCredit());
        etatReglementPrime.setDateEcheance(etatReglementPrimeDTO.getDateEcheance());
        etatReglementPrime.setMontantRegle(etatReglementPrimeDTO.getMontantRegle());
        etatReglementPrime.setRefReglement(etatReglementPrimeDTO.getRefReglement());
        etatReglementPrime.setDateReglement(etatReglementPrimeDTO.getDateReglement());
        etatReglementPrime = etatReglementPrimeRepository.save(etatReglementPrime);
        return modelMapper.map(etatReglementPrime, EtatReglementPrimeDTO.class);
    }


    @Override
    public EtatReglementPrimeDTO updateEtatReglementPrime(Long id, EtatReglementPrimeDTO etatReglementPrimeDTO) {
        Optional<EtatReglementPrime> optionalEtatReglementPrime = etatReglementPrimeRepository.findById(id);
        if (optionalEtatReglementPrime.isPresent()) {
            EtatReglementPrime etatReglementPrime = optionalEtatReglementPrime.get();
            etatReglementPrime.setNumCin(etatReglementPrimeDTO.getNumCin());
            etatReglementPrime.setNumCredit(etatReglementPrimeDTO.getNumCredit());
            etatReglementPrime.setDateEcheance(etatReglementPrimeDTO.getDateEcheance());
            etatReglementPrime.setMontantRegle(etatReglementPrimeDTO.getMontantRegle());
            etatReglementPrime.setRefReglement(etatReglementPrimeDTO.getRefReglement());
            etatReglementPrime.setDateReglement(etatReglementPrimeDTO.getDateReglement());

            etatReglementPrime = etatReglementPrimeRepository.save(etatReglementPrime);
            return modelMapper.map(etatReglementPrime, EtatReglementPrimeDTO.class);
        }
        throw new EntityNotFoundException("ChangementDebiteur not found with id: " + id);
    }

        @Override
        public EtatReglementPrimeDTO getEtatReglementPrimeById(Long id) {
            EtatReglementPrime etatReglementPrime = etatReglementPrimeRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Etat Reglement Prime not found with id: " + id));
            return modelMapper.map(etatReglementPrime, EtatReglementPrimeDTO.class);
        }

        @Transactional
        @Override
        public List<EtatReglementPrimeDTO> getAllEtatReglementPrime() {
            List<EtatReglementPrime> allEtatReglementPrime= etatReglementPrimeRepository.findAll();
            return allEtatReglementPrime.stream()
                    .map(etatReglementPrime -> modelMapper.map(etatReglementPrime, EtatReglementPrimeDTO.class))
                    .collect(Collectors.toList());
        }

    @Override
    @Transactional
    public void deactivateEtatReglementPrime(Long id) {
        Optional<EtatReglementPrime> etatReglementPrime = etatReglementPrimeRepository.findById(id);
        if (etatReglementPrime.isPresent()) {
            EtatReglementPrime etatReglementP = etatReglementPrime.get();
            etatReglementP.setIsActive(false); // Met à jour le champ isActive
            etatReglementPrimeRepository.save(etatReglementP);
        }
    }

}
