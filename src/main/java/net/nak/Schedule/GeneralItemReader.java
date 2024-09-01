package net.nak.Schedule;

import net.nak.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class GeneralItemReader implements ItemReader<Object> {
    private static final Logger logger = LoggerFactory.getLogger(GeneralItemReader.class);
    private final List<Iterator<?>> iterators = new ArrayList<>();
    private Iterator<?> currentIterator;

    @Autowired
    public GeneralItemReader(
            EtatImpayesRepository etatImpayesRepository,
            ChangementDebiteurRepository changementDebiteurRepository,
            DemandeMEJGarantieRepository demandeMEJGarantieRepository,
            DemandeGarantieFOGRepository demandeGarantieFOGRepository,
            DReglementRistourneRepository dReglementRistourneRepository,
            EtatAnnulationMejRepository etatAnnulationMejRepository,
            EtatRecouvrementRealiseRepository etatRecouvrementRealiseRepository,
            EtatReglementPrimeRepository etatReglementPrimeRepository,
            ReglementMejRepository reglementMejRepository,
            RestitutionMEJRepository restitutionMEJRepository) {

        iterators.add(etatImpayesRepository.findAll().iterator());
        iterators.add(changementDebiteurRepository.findAll().iterator());
        iterators.add(demandeMEJGarantieRepository.findAll().iterator());
        iterators.add(demandeGarantieFOGRepository.findAll().iterator());
        iterators.add(dReglementRistourneRepository.findAll().iterator());
        iterators.add(etatAnnulationMejRepository.findAll().iterator());
        iterators.add(etatRecouvrementRealiseRepository.findAll().iterator());
        iterators.add(etatReglementPrimeRepository.findAll().iterator());
        iterators.add(reglementMejRepository.findAll().iterator());
        iterators.add(restitutionMEJRepository.findAll().iterator());

        // Initialiser l'itérateur courant
        currentIterator = iterators.remove(0);
    }

    @Override
    public Object read() throws Exception {
        if (currentIterator == null) {
            return null;
        }

        // Lire la prochaine entrée de l'itérateur courant
        if (currentIterator.hasNext()) {
            return currentIterator.next();
        } else {
            // Fermer l'itérateur courant et passer au suivant
            if (currentIterator instanceof AutoCloseable) {
                try {
                    ((AutoCloseable) currentIterator).close();
                } catch (Exception e) {
                    logger.error("Failed to close iterator", e);
                }
            }
            // Passer au prochain itérateur
            if (iterators.isEmpty()) {
                return null;
            }
            currentIterator = iterators.remove(0);
            return read();
        }
    }
}