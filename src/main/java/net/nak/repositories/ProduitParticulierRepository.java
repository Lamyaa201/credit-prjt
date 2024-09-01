package net.nak.repositories;

import net.nak.entities.ProduitParticulier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitParticulierRepository  extends JpaRepository<ProduitParticulier,Long> {

    boolean existsByCodeProduit(String codeProduit);

    boolean existsByNom(String nom);

}

