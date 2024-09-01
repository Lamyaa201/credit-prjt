package net.nak.RestControllers;

import net.nak.DTO.DemandeMEJGarantieDTO;
import net.nak.services.DemandeMEJGarantieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/annexes")
public class DemandeMEJGarantieRestController {


        @Autowired
        private DemandeMEJGarantieService demandeMEJGarantieService;

        @PostMapping("/addDemandeMEJGarantie")
        public ResponseEntity<DemandeMEJGarantieDTO> addDemandeMEJGarantie(@RequestBody DemandeMEJGarantieDTO demandeMEJGarantieDTO) {
            DemandeMEJGarantieDTO savedDemandeMEJGarantieDTO = demandeMEJGarantieService.addDemandeMEJGarantie(demandeMEJGarantieDTO);
            return ResponseEntity.ok(savedDemandeMEJGarantieDTO);
        }

        @PutMapping("/updateDemandeMEJGarantie/{id}")
        public ResponseEntity<DemandeMEJGarantieDTO> updateDemandeMEJGarantie(@PathVariable Long id, @RequestBody DemandeMEJGarantieDTO demandeMEJGarantieDTO) {
            DemandeMEJGarantieDTO updatedDemandeMEJGarantieDTO = demandeMEJGarantieService.updateDemandeMEJGarantie(id, demandeMEJGarantieDTO);
            return ResponseEntity.ok(updatedDemandeMEJGarantieDTO);
        }

        @GetMapping("/getDemandeMEJGarantieById/{id}")
        public ResponseEntity<DemandeMEJGarantieDTO> getDemandeMEJGarantieById(@PathVariable Long id) {
            DemandeMEJGarantieDTO demandeMEJGarantieDTO = demandeMEJGarantieService.getDemandeMEJGarantieById(id);
            return ResponseEntity.ok(demandeMEJGarantieDTO);
        }

        @GetMapping("/getAllDemandeMEJGarantie")
        public ResponseEntity<List<DemandeMEJGarantieDTO>> getAllDemandeMEJGarantie() {
            List<DemandeMEJGarantieDTO> demandeMEJGarantieDTOList = demandeMEJGarantieService.getAllDemandeMEJGarantie();
            return ResponseEntity.ok(demandeMEJGarantieDTOList);
        }

    @PutMapping("/{id}/deactivateDemandeMEJGarantie")
    public ResponseEntity<Void> deactivateDemandeMEJGarantie(@PathVariable Long id) {
        demandeMEJGarantieService.deactivateDemandeMEJGarantie(id);
        return ResponseEntity.ok().build();
    }

}
