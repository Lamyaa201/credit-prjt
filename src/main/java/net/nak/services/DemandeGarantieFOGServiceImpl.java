package net.nak.services;

import net.nak.DTO.DemandeGarantieFOGDTO;
import net.nak.DTO.EtatRecouvrementRealiseDTO;
import net.nak.entities.ChangementDebiteur;
import net.nak.entities.DemandeGarantieFOG;
import net.nak.entities.EtatRecouvrementRealise;
import net.nak.repositories.DemandeGarantieFOGRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class DemandeGarantieFOGServiceImpl implements DemandeGarantieFOGService{

        private final DemandeGarantieFOGRepository demandeGarantieFOGRepository;
        private final ModelMapper modelMapper;

        public DemandeGarantieFOGServiceImpl(DemandeGarantieFOGRepository demandeGarantieFOGRepository,ModelMapper modelMapper) {
            this.demandeGarantieFOGRepository = demandeGarantieFOGRepository;
            this.modelMapper = modelMapper;
        }



    @Override
    public DemandeGarantieFOGDTO addDemandeGarantieFOG(DemandeGarantieFOGDTO demandeGarantieFOGDTO) {
        DemandeGarantieFOG demandeGarantieFOG = new DemandeGarantieFOG();

        demandeGarantieFOG.setNom(demandeGarantieFOGDTO.getNom());
        demandeGarantieFOG.setPrenom(demandeGarantieFOGDTO.getPrenom());
        demandeGarantieFOG.setNumCIN(demandeGarantieFOGDTO.getNumCIN());
        demandeGarantieFOG.setSexe(demandeGarantieFOGDTO.getSexe());
        demandeGarantieFOG.setDateNaissance(demandeGarantieFOGDTO.getDateNaissance());
        demandeGarantieFOG.setProfession(demandeGarantieFOGDTO.getProfession());
        demandeGarantieFOG.setNumCreditBq(demandeGarantieFOGDTO.getNumCreditBq());
        demandeGarantieFOG.setMontant(demandeGarantieFOGDTO.getMontant());
        demandeGarantieFOG.setDuree(demandeGarantieFOGDTO.getDuree());
        demandeGarantieFOG.setQuotiteFinancement(demandeGarantieFOGDTO.getQuotiteFinancement());
        demandeGarantieFOG.setObjetCredit(demandeGarantieFOGDTO.getObjetCredit());
        demandeGarantieFOG.setTauxInteret(demandeGarantieFOGDTO.getTauxInteret());
        demandeGarantieFOG.setTauxInteretRetard(demandeGarantieFOGDTO.getTauxInteretRetard());
        demandeGarantieFOG.setCoutGlobal(demandeGarantieFOGDTO.getCoutGlobal());
        demandeGarantieFOG.setPrix(demandeGarantieFOGDTO.getPrix());
        demandeGarantieFOG.setSuperficie(demandeGarantieFOGDTO.getSuperficie());
        demandeGarantieFOG.setCodeVille(demandeGarantieFOGDTO.getCodeVille());
        demandeGarantieFOG.setNumTitreFoncier(demandeGarantieFOGDTO.getNumTitreFoncier());
        demandeGarantieFOG.setFraisCapitale(demandeGarantieFOGDTO.getFraisCapitale());
        demandeGarantieFOG.setTypeLogement(demandeGarantieFOGDTO.getTypeLogement());
        demandeGarantieFOG.setRevenuMensuel(demandeGarantieFOGDTO.getRevenuMensuel());
        demandeGarantieFOG.setMarie(demandeGarantieFOGDTO.getMarie());
        demandeGarantieFOG.setRevenuConjoint(demandeGarantieFOGDTO.getRevenuConjoint());
        demandeGarantieFOG.setNbrPrsnCharge(demandeGarantieFOGDTO.getNbrPrsnCharge());
        demandeGarantieFOG.setAncienneteBancaire(demandeGarantieFOGDTO.getAncienneteBancaire());
        demandeGarantieFOG.setAdresseLogmeent(demandeGarantieFOGDTO.getAdresseLogmeent());
        demandeGarantieFOG.setVendeurLogemet(demandeGarantieFOGDTO.getVendeurLogemet());
        demandeGarantieFOG.setDiffere(demandeGarantieFOGDTO.getDiffere());
        demandeGarantieFOG.setAquisitionIndivision(demandeGarantieFOGDTO.getAquisitionIndivision());
        demandeGarantieFOG.setTypePrime(demandeGarantieFOGDTO.getTypePrime());
        demandeGarantieFOG.setPrixTerrain(demandeGarantieFOGDTO.getPrixTerrain());
        demandeGarantieFOG.setNatureTF(demandeGarantieFOGDTO.getNatureTF());
        demandeGarantieFOG.setPaysAccueil(demandeGarantieFOGDTO.getPaysAccueil());

        demandeGarantieFOG = demandeGarantieFOGRepository.save(demandeGarantieFOG);
        return modelMapper.map(demandeGarantieFOG, DemandeGarantieFOGDTO.class);
    }

    @Override
    public DemandeGarantieFOGDTO updateDemandeGarantieFOG(Long id, DemandeGarantieFOGDTO demandeGarantieFOGDTO) {
        Optional<DemandeGarantieFOG> optionalDemandeGarantieFOG = demandeGarantieFOGRepository.findById(id);
        if (optionalDemandeGarantieFOG.isPresent()) {
            DemandeGarantieFOG demandeGarantieFOG = optionalDemandeGarantieFOG.get();
            demandeGarantieFOG.setNom(demandeGarantieFOGDTO.getNom());
            demandeGarantieFOG.setPrenom(demandeGarantieFOGDTO.getPrenom());
            demandeGarantieFOG.setNumCIN(demandeGarantieFOGDTO.getNumCIN());
            demandeGarantieFOG.setSexe(demandeGarantieFOGDTO.getSexe());
            demandeGarantieFOG.setDateNaissance(demandeGarantieFOGDTO.getDateNaissance());
            demandeGarantieFOG.setProfession(demandeGarantieFOGDTO.getProfession());
            demandeGarantieFOG.setNumCreditBq(demandeGarantieFOGDTO.getNumCreditBq());
            demandeGarantieFOG.setMontant(demandeGarantieFOGDTO.getMontant());
            demandeGarantieFOG.setDuree(demandeGarantieFOGDTO.getDuree());
            demandeGarantieFOG.setQuotiteFinancement(demandeGarantieFOGDTO.getQuotiteFinancement());
            demandeGarantieFOG.setObjetCredit(demandeGarantieFOGDTO.getObjetCredit());
            demandeGarantieFOG.setTauxInteret(demandeGarantieFOGDTO.getTauxInteret());
            demandeGarantieFOG.setTauxInteretRetard(demandeGarantieFOGDTO.getTauxInteretRetard());
            demandeGarantieFOG.setCoutGlobal(demandeGarantieFOGDTO.getCoutGlobal());
            demandeGarantieFOG.setPrix(demandeGarantieFOGDTO.getPrix());
            demandeGarantieFOG.setSuperficie(demandeGarantieFOGDTO.getSuperficie());
            demandeGarantieFOG.setCodeVille(demandeGarantieFOGDTO.getCodeVille());
            demandeGarantieFOG.setNumTitreFoncier(demandeGarantieFOGDTO.getNumTitreFoncier());
            demandeGarantieFOG.setFraisCapitale(demandeGarantieFOGDTO.getFraisCapitale());
            demandeGarantieFOG.setTypeLogement(demandeGarantieFOGDTO.getTypeLogement());
            demandeGarantieFOG.setRevenuMensuel(demandeGarantieFOGDTO.getRevenuMensuel());
            demandeGarantieFOG.setMarie(demandeGarantieFOGDTO.getMarie());
            demandeGarantieFOG.setRevenuConjoint(demandeGarantieFOGDTO.getRevenuConjoint());
            demandeGarantieFOG.setNbrPrsnCharge(demandeGarantieFOGDTO.getNbrPrsnCharge());
            demandeGarantieFOG.setAncienneteBancaire(demandeGarantieFOGDTO.getAncienneteBancaire());
            demandeGarantieFOG.setAdresseLogmeent(demandeGarantieFOGDTO.getAdresseLogmeent());
            demandeGarantieFOG.setVendeurLogemet(demandeGarantieFOGDTO.getVendeurLogemet());
            demandeGarantieFOG.setDiffere(demandeGarantieFOGDTO.getDiffere());
            demandeGarantieFOG.setAquisitionIndivision(demandeGarantieFOGDTO.getAquisitionIndivision());
            demandeGarantieFOG.setTypePrime(demandeGarantieFOGDTO.getTypePrime());
            demandeGarantieFOG.setPrixTerrain(demandeGarantieFOGDTO.getPrixTerrain());
            demandeGarantieFOG.setNatureTF(demandeGarantieFOGDTO.getNatureTF());
            demandeGarantieFOG.setPaysAccueil(demandeGarantieFOGDTO.getPaysAccueil());
            demandeGarantieFOG = demandeGarantieFOGRepository.save(demandeGarantieFOG);
            return modelMapper.map(demandeGarantieFOG, DemandeGarantieFOGDTO.class);
        }
        throw new EntityNotFoundException("ChangementDebiteur not found with id: " + id);
    }

    @Override
        public DemandeGarantieFOGDTO getDemandeGarantieFOGById(Long id) {
            DemandeGarantieFOG demandeGarantieFOG = demandeGarantieFOGRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Demande Garantie FOG not found with id: " + id));
            return modelMapper.map(demandeGarantieFOG, DemandeGarantieFOGDTO.class);
        }

    @Transactional
    @Override
        public List<DemandeGarantieFOGDTO> getAllDemandeGarantieFOG() {
            List<DemandeGarantieFOG> allDemandeGarantieFOG = demandeGarantieFOGRepository.findAll();
            return allDemandeGarantieFOG.stream()
                    .map(demandeGarantieFOG -> modelMapper.map(demandeGarantieFOG, DemandeGarantieFOGDTO.class))
                    .collect(Collectors.toList());
        }

    @Override
    @Transactional
    public void deactivateDemandeGarantieFOG(Long id) {
        Optional<DemandeGarantieFOG> demandeGarantieFOG = demandeGarantieFOGRepository.findById(id);
        if (demandeGarantieFOG.isPresent()) {
            DemandeGarantieFOG demande = demandeGarantieFOG.get();
            demande.setIsActive(false); // Met à jour le champ isActive
            demandeGarantieFOGRepository.save(demande);
        }
    }

}
