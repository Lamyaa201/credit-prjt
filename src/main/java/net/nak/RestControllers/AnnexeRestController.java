package net.nak.RestControllers;

import net.nak.DTO.AnnexeDTO;
import net.nak.services.AnnexeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnnexeRestController {

    @Autowired
    private AnnexeService annexeService;

    @PostMapping("/addAnnexe")
    public ResponseEntity<AnnexeDTO> addAnnexe(@RequestBody AnnexeDTO annexeDTO) {
        AnnexeDTO savedAnnexeDTO = annexeService.addAnnexe(annexeDTO);
        return ResponseEntity.ok(savedAnnexeDTO);
    }

    @PutMapping("/updateAnnexe/{id}")
    public ResponseEntity<AnnexeDTO> updateAnnexe(@PathVariable Long id, @RequestBody AnnexeDTO annexeDTO) {
        AnnexeDTO updatedAnnexeDTO = annexeService.updateAnnexe(id, annexeDTO);
        return ResponseEntity.ok(updatedAnnexeDTO);
    }

    @GetMapping("/getAnnexeById/{id}")
    public ResponseEntity<AnnexeDTO> getAnnexeById(@PathVariable Long id) {
        AnnexeDTO annexeDTO = annexeService.getAnnexeById(id);
        return ResponseEntity.ok(annexeDTO);
    }

    @GetMapping("/getAllAnnexe")
    public ResponseEntity<List<AnnexeDTO>> getAllAnnexe() {
        List<AnnexeDTO> annexeDTOList = annexeService.getAllAnnexe();
        return ResponseEntity.ok(annexeDTOList);
    }
}
