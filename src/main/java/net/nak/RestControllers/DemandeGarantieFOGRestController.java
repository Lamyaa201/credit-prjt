package net.nak.RestControllers;

import net.nak.DTO.ChangementDebiteurDTO;
import net.nak.DTO.DemandeGarantieFOGDTO;
import net.nak.services.DemandeGarantieFOGService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/annexes")
public class DemandeGarantieFOGRestController {


        @Autowired
        private DemandeGarantieFOGService demandeGarantieFOGService;

        @PostMapping("/addDemandeGarantieFOG")
        public ResponseEntity<DemandeGarantieFOGDTO> addDemandeGarantieFOG(@RequestBody DemandeGarantieFOGDTO demandeGarantieFOGDTO) {
            DemandeGarantieFOGDTO savedDemandeGarantieFOGDTO = demandeGarantieFOGService.addDemandeGarantieFOG(demandeGarantieFOGDTO);
            return ResponseEntity.ok(savedDemandeGarantieFOGDTO);
        }

        @PutMapping("/updateDemandeGarantieFOG/{id}")
        public ResponseEntity<DemandeGarantieFOGDTO> updateDemandeGarantieFOG(@PathVariable Long id, @RequestBody DemandeGarantieFOGDTO demandeGarantieFOGDTO) {
            DemandeGarantieFOGDTO updatedDemandeGarantieFOGDTO = demandeGarantieFOGService.updateDemandeGarantieFOG(id, demandeGarantieFOGDTO);
            return ResponseEntity.ok(updatedDemandeGarantieFOGDTO);
        }

        @GetMapping("/getDemandeGarantieFOGById/{id}")
        public ResponseEntity<DemandeGarantieFOGDTO> getDemandeGarantieFOGById(@PathVariable Long id) {
            DemandeGarantieFOGDTO demandeGarantieFOGDTO = demandeGarantieFOGService.getDemandeGarantieFOGById(id);
            return ResponseEntity.ok(demandeGarantieFOGDTO);
        }

        @GetMapping("/getAllDemandeGarantieFOG")
        public ResponseEntity<List<DemandeGarantieFOGDTO>> getAllDemandeGarantieFOG() {
            List<DemandeGarantieFOGDTO> demandeGarantieFOGDTOList = demandeGarantieFOGService.getAllDemandeGarantieFOG();
            return ResponseEntity.ok(demandeGarantieFOGDTOList);
        }

    @PutMapping("/{id}/deactivateDemandeGarantieFOG")
    public ResponseEntity<Void> deactivateDemandeGarantieFOG(@PathVariable Long id) {
        demandeGarantieFOGService.deactivateDemandeGarantieFOG(id);
        return ResponseEntity.ok().build();
    }

    }


