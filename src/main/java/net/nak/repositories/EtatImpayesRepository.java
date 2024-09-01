package net.nak.repositories;

import net.nak.entities.EtatImpayes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EtatImpayesRepository  extends JpaRepository<EtatImpayes, Long> {
    List<EtatImpayes> findByIsActiveTrue();


}
