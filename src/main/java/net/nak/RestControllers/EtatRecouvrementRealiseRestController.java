package net.nak.RestControllers;

import net.nak.DTO.EtatRecouvrementRealiseDTO;
import net.nak.services.EtatRecouvrementRealiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/annexes")
public class EtatRecouvrementRealiseRestController {


        @Autowired
        private EtatRecouvrementRealiseService etatRecouvrementRealiseService;

        @PostMapping("/addEtatRecouvrementRealise")
        public ResponseEntity<EtatRecouvrementRealiseDTO> addEtatRecouvrementRealise(@RequestBody EtatRecouvrementRealiseDTO etatRecouvrementRealiseDTO) {
            EtatRecouvrementRealiseDTO savedEtatRecouvrementRealiseDTO = etatRecouvrementRealiseService.addEtatRecouvrementRealise(etatRecouvrementRealiseDTO);
            return ResponseEntity.ok(savedEtatRecouvrementRealiseDTO);
        }

        @PutMapping("/updateEtatRecouvrementRealise/{id}")
        public ResponseEntity<EtatRecouvrementRealiseDTO> updateEtatRecouvrementRealise(@PathVariable Long id, @RequestBody EtatRecouvrementRealiseDTO etatRecouvrementRealiseDTO) {
            EtatRecouvrementRealiseDTO updatedEtatRecouvrementRealiseDTO = etatRecouvrementRealiseService.updateEtatRecouvrementRealise(id, etatRecouvrementRealiseDTO);
            return ResponseEntity.ok(updatedEtatRecouvrementRealiseDTO);
        }

        @GetMapping("/getEtatRecouvrementRealiseById/{id}")
        public ResponseEntity<EtatRecouvrementRealiseDTO> getEtatRecouvrementRealiseById(@PathVariable Long id) {
            EtatRecouvrementRealiseDTO etatRecouvrementRealiseDTO = etatRecouvrementRealiseService.getEtatRecouvrementRealiseById(id);
            return ResponseEntity.ok(etatRecouvrementRealiseDTO);
        }

        @GetMapping("/getAllEtatRecouvrementRealise")
        public ResponseEntity<List<EtatRecouvrementRealiseDTO>> getAllEtatRecouvrementRealise() {
            List<EtatRecouvrementRealiseDTO> etatRecouvrementRealiseDTOList = etatRecouvrementRealiseService.getAllEtatRecouvrementRealise();
            return ResponseEntity.ok(etatRecouvrementRealiseDTOList);
        }

    @PutMapping("/{id}/deactivateEtatRecouvrementRealise")
    public ResponseEntity<Void> deactivateEtatRecouvrementRealise(@PathVariable Long id) {
        etatRecouvrementRealiseService.deactivateEtatRecouvrementRealise(id);
        return ResponseEntity.ok().build();
    }
}
