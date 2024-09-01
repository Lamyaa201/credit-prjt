package net.nak.RestControllers;

import net.nak.DTO.EtatReglementPrimeDTO;
import net.nak.services.EtatReglementPrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/annexes")
public class EtatReglementPrimeRestController {


        @Autowired
        private EtatReglementPrimeService etatReglementPrimeService;

        @PostMapping("/addEtatReglementPrime")
        public ResponseEntity<EtatReglementPrimeDTO> addEtatReglementPrime(@RequestBody EtatReglementPrimeDTO etatReglementPrimeDTO) {
            EtatReglementPrimeDTO savedEtatReglementPrimeDTO = etatReglementPrimeService.addEtatReglementPrime(etatReglementPrimeDTO);
            return ResponseEntity.ok(savedEtatReglementPrimeDTO);
        }

        @PutMapping("/updateEtatReglementPrime/{id}")
        public ResponseEntity<EtatReglementPrimeDTO> updateEtatReglementPrime(@PathVariable Long id, @RequestBody EtatReglementPrimeDTO etatReglementPrimeDTO) {
            EtatReglementPrimeDTO updatedEtatReglementPrimeDTO = etatReglementPrimeService.updateEtatReglementPrime(id, etatReglementPrimeDTO);
            return ResponseEntity.ok(updatedEtatReglementPrimeDTO);
        }

        @GetMapping("/getEtatReglementPrimeById/{id}")
        public ResponseEntity<EtatReglementPrimeDTO> getEtatReglementPrimeById(@PathVariable Long id) {
            EtatReglementPrimeDTO etatReglementPrimeDTO = etatReglementPrimeService.getEtatReglementPrimeById(id);
            return ResponseEntity.ok(etatReglementPrimeDTO);
        }

        @GetMapping("/getAllEtatReglementPrime")
        public ResponseEntity<List<EtatReglementPrimeDTO>> getAllEtatReglementPrime() {
            List<EtatReglementPrimeDTO> etatReglementPrimeDTOList = etatReglementPrimeService.getAllEtatReglementPrime();
            return ResponseEntity.ok(etatReglementPrimeDTOList);
        }

    @PutMapping("/{id}/deactivateEtatReglementPrime")
    public ResponseEntity<Void> deactivateEtatReglementPrime(@PathVariable Long id) {
        etatReglementPrimeService.deactivateEtatReglementPrime(id);
        return ResponseEntity.ok().build();
    }

}
