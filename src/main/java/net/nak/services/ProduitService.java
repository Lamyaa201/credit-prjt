package net.nak.services;

import net.nak.DTO.ProduitDTO;
import net.nak.DTO.ProduitEntrepriseDTO;
import net.nak.DTO.ProduitParticulierDTO;

import java.util.List;
import java.util.Optional;

public interface ProduitService {

    ProduitEntrepriseDTO ajouterProduitEntreprise(ProduitEntrepriseDTO produitEntrepriseDTO);

    ProduitParticulierDTO ajouterProduitParticulier(ProduitParticulierDTO produitParticulierDTO);

    ProduitEntrepriseDTO modifierProduitEntreprise(Long id, ProduitEntrepriseDTO produitEntrepriseDTO);

    ProduitParticulierDTO modifierProduitParticulier(Long id, ProduitParticulierDTO produitParticulierDTO);
    List<ProduitEntrepriseDTO> getAllProduitsEntreprise();

    List<ProduitParticulierDTO> getAllProduitsParticulier();

    List<ProduitDTO> getAllProduits();

    Optional<ProduitEntrepriseDTO> getProduitEntrepriseById(Long id);

    Optional<ProduitParticulierDTO> getProduitParticulierById(Long id);

    boolean existsProduitParticulierByCodeProduit(String codeProduit);
    boolean existsProduitEntrepriseByCodeProduit(String codeProduit);


    boolean existsProduitParticulierByNom(String nom);
    boolean existsProduitEntrepriseByNom(String nom);

    void deactivateProduitEntreprise(Long id);
    void deactivateProduitParticulier(Long id);

    List<ProduitEntrepriseDTO> getProduitsEntrepriseDuMois();
    List<ProduitParticulierDTO> getProduitsParticulierDuMois();

}
