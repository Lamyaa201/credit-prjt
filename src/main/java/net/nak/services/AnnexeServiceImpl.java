package net.nak.services;

import net.nak.DTO.AnnexeDTO;
import net.nak.entities.Annexe;
import net.nak.repositories.AnnexeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnnexeServiceImpl implements AnnexeService {

    private final AnnexeRepository annexeRepository;
    private final ModelMapper modelMapper;

    public AnnexeServiceImpl(AnnexeRepository annexeRepository, ModelMapper modelMapper) {
        this.annexeRepository = annexeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AnnexeDTO addAnnexe(AnnexeDTO annexeDTO) {
        Annexe annexe = new Annexe();
        annexe.setLibelle(annexeDTO.getLibelle());
        annexe.setCodeBq(annexeDTO.getCodeBq()); // codeBq peut être vide
        annexe.setNumeroAnnexe(annexeDTO.getNumeroAnnexe());
        annexe = annexeRepository.save(annexe);
        return modelMapper.map(annexe, AnnexeDTO.class);
    }

    @Override
    public AnnexeDTO updateAnnexe(Long id, AnnexeDTO annexeDTO) {
        Optional<Annexe> optionalAnnexe = annexeRepository.findById(id);
        if (optionalAnnexe.isPresent()) {
            Annexe annexe = optionalAnnexe.get();
            annexe.setLibelle(annexeDTO.getLibelle());
            annexe.setCodeBq(annexeDTO.getCodeBq()); // codeBq peut être mis à jour, même s'il est vide initialement
            annexe.setNumeroAnnexe(annexeDTO.getNumeroAnnexe());
            annexe = annexeRepository.save(annexe);
            return modelMapper.map(annexe, AnnexeDTO.class);
        }
        throw new EntityNotFoundException("Annexe not found with id: " + id);
    }

    @Override
    public AnnexeDTO getAnnexeById(Long id) {
        Annexe annexe = annexeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Annexe not found with id: " + id));
        return modelMapper.map(annexe, AnnexeDTO.class);
    }

    @Override
    @Transactional
    public List<AnnexeDTO> getAllAnnexe() {
        List<Annexe> allAnnexe = annexeRepository.findAll();
        return allAnnexe.stream()
                .map(annexe -> modelMapper.map(annexe, AnnexeDTO.class))
                .collect(Collectors.toList());
    }
}
