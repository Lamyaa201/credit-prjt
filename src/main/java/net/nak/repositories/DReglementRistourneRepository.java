package net.nak.repositories;

import net.nak.entities.DetailReglementRistourne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DReglementRistourneRepository extends JpaRepository<DetailReglementRistourne, Long> {
}
