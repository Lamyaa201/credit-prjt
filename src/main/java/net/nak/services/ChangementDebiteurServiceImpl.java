package net.nak.services;

import net.nak.DTO.ChangementDebiteurDTO;
import net.nak.DTO.EtatImpayesDTO;
import net.nak.entities.ChangementDebiteur;
import net.nak.entities.EtatImpayes;
import net.nak.repositories.ChangementDebiteurRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChangementDebiteurServiceImpl implements ChangementDebiteurService {
    private final ChangementDebiteurRepository changementDebiteurRepository;
    private final ModelMapper modelMapper;

    public ChangementDebiteurServiceImpl(ChangementDebiteurRepository changementDebiteurRepository, ModelMapper modelMapper) {
        this.changementDebiteurRepository = changementDebiteurRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ChangementDebiteurDTO addChangementDebiteur(ChangementDebiteurDTO changementDebiteurDTO) {
        ChangementDebiteur changementDebiteur = new ChangementDebiteur();
        changementDebiteur.setNumCIN(changementDebiteurDTO.getNumCIN());
        changementDebiteur.setNumCredit(changementDebiteurDTO.getNumCredit());
        changementDebiteur.setDebiteurInit(changementDebiteurDTO.getDebiteurInit());
        changementDebiteur.setNouveauDebit(changementDebiteurDTO.getNouveauDebit());
        changementDebiteur.setDateEffetTransfert(changementDebiteurDTO.getDateEffetTransfert());
        changementDebiteur = changementDebiteurRepository.save(changementDebiteur);
        return modelMapper.map(changementDebiteur, ChangementDebiteurDTO.class);
    }

    @Override
    public ChangementDebiteurDTO updateChangementDebiteur(Long id, ChangementDebiteurDTO changementDebiteurDTO) {
        Optional<ChangementDebiteur> optionalChangementDebiteur = changementDebiteurRepository.findById(id);
        if (optionalChangementDebiteur.isPresent()) {
            ChangementDebiteur changementDebiteur = optionalChangementDebiteur.get();
            changementDebiteur.setNumCredit(changementDebiteurDTO.getNumCredit());
            changementDebiteur.setNumCIN(changementDebiteurDTO.getNumCIN());
            changementDebiteur.setDebiteurInit(changementDebiteurDTO.getDebiteurInit());
            changementDebiteur.setNouveauDebit(changementDebiteurDTO.getNouveauDebit());
            changementDebiteur.setDateEffetTransfert(changementDebiteurDTO.getDateEffetTransfert());

            changementDebiteur = changementDebiteurRepository.save(changementDebiteur);
            return modelMapper.map(changementDebiteur, ChangementDebiteurDTO.class);
        }
        throw new EntityNotFoundException("ChangementDebiteur not found with id: " + id);
    }

    @Override
    public Optional<ChangementDebiteurDTO> getChangementDebiteurById(Long id) {
        return changementDebiteurRepository.findById(id)
                .map(changementDebiteur -> modelMapper.map(changementDebiteur, ChangementDebiteurDTO.class));
    }

    @Transactional
    @Override
    public List<ChangementDebiteurDTO> getAllChangementDebiteur() {
        List<ChangementDebiteur> changementDebiteurs = changementDebiteurRepository.findAll();
        return changementDebiteurs.stream()
                .map(changementDebiteur -> modelMapper.map(changementDebiteur, ChangementDebiteurDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deactivateChangementDebiteur(Long id) {
        Optional<ChangementDebiteur> changementDebiteur = changementDebiteurRepository.findById(id);
        if (changementDebiteur.isPresent()) {
            ChangementDebiteur changement = changementDebiteur.get();
            changement.setIsActive(false); // Met à jour le champ isActive
            changementDebiteurRepository.save(changement);
        }
    }
}
