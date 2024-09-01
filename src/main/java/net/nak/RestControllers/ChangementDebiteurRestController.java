package net.nak.RestControllers;

import net.nak.DTO.ChangementDebiteurDTO;
import net.nak.services.ChangementDebiteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/annexes")
public class ChangementDebiteurRestController {

    @Autowired
    private ChangementDebiteurService changementDebiteurService;

    @PostMapping("/addChangementDebiteur")
    public ChangementDebiteurDTO addChangementDebiteur(@RequestBody ChangementDebiteurDTO changementDebiteurDTO) {
        return changementDebiteurService.addChangementDebiteur(changementDebiteurDTO);
    }

    @PutMapping("/updateChangementDebiteur/{id}")
    public ChangementDebiteurDTO updateChangementDebiteur(@PathVariable Long id, @RequestBody ChangementDebiteurDTO changementDebiteurDTO) {
        return changementDebiteurService.updateChangementDebiteur(id, changementDebiteurDTO);
    }

    @GetMapping("/getChangementDebiteurById/{id}")
    public Optional<ChangementDebiteurDTO> getChangementDebiteurById(@PathVariable Long id) {
        return changementDebiteurService.getChangementDebiteurById(id);
    }

    @GetMapping("/getAllChangementDebiteur")
    public List<ChangementDebiteurDTO> getAllChangementDebiteur() {
        return changementDebiteurService.getAllChangementDebiteur();
    }

    @PutMapping("/{id}/deactivateChangementDebiteur")
    public ResponseEntity<Void> deactivateChangementDebiteur(@PathVariable Long id) {
        changementDebiteurService.deactivateChangementDebiteur(id);
        return ResponseEntity.ok().build();
    }

}
