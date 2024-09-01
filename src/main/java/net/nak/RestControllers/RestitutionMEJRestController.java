package net.nak.RestControllers;

import net.nak.DTO.RestitutionMEJDTO;
import net.nak.services.RestitutionMEJService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/annexes")
public class RestitutionMEJRestController {

        @Autowired
        private RestitutionMEJService restitutionMEJService;

        @PostMapping("/addRestitutionMEJ")
        public ResponseEntity<RestitutionMEJDTO> addRestitutionMEJ(@RequestBody RestitutionMEJDTO restitutionMEJDTO) {
            RestitutionMEJDTO savedRestitutionMEJDTO = restitutionMEJService.addRestitutionMEJ(restitutionMEJDTO);
            return ResponseEntity.ok(savedRestitutionMEJDTO);
        }

        @PutMapping("/updateRestitutionMEJ/{id}")
        public ResponseEntity<RestitutionMEJDTO> updateRestitutionMEJ(@PathVariable Long id, @RequestBody RestitutionMEJDTO restitutionMEJDTO) {
            RestitutionMEJDTO updatedRestitutionMEJDTO = restitutionMEJService.updateRestitutionMEJ(id, restitutionMEJDTO);
            return ResponseEntity.ok(updatedRestitutionMEJDTO);
        }

        @GetMapping("/getRestitutionMEJById/{id}")
        public ResponseEntity<RestitutionMEJDTO> getRestitutionMEJById(@PathVariable Long id) {
            RestitutionMEJDTO restitutionMEJDTO = restitutionMEJService.getRestitutionMEJById(id);
            return ResponseEntity.ok(restitutionMEJDTO);
        }

        @GetMapping("/getAllRestitutionMEJ")
        public ResponseEntity<List<RestitutionMEJDTO>> getAllRestitutionMEJ() {
            List<RestitutionMEJDTO> restitutionMEJDTOList = restitutionMEJService.getAllRestitutionMEJ();
            return ResponseEntity.ok(restitutionMEJDTOList);
        }

    @PutMapping("/{id}/deactivateRestitutionMEJ")
    public ResponseEntity<Void> deactivateRestitutionMEJ(@PathVariable Long id) {
        restitutionMEJService.deactivateRestitutionMEJ(id);
        return ResponseEntity.ok().build();
    }
}
