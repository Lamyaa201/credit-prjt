package net.nak.repositories;

import net.nak.DTO.ProduitDTO;
import net.nak.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository  extends JpaRepository<Produit,Long> {
}
