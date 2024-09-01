package net.nak.RestControllers;

import net.nak.DTO.EtatImpayesDTO;
import net.nak.DTO.ProduitDTO;
import net.nak.DTO.ProduitEntrepriseDTO;
import net.nak.services.EtatImpayesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/annexes")
public class EtatImpayesRestController {

        @Autowired
        private EtatImpayesService etatImpayesService;


    @PostMapping("/addEtatImpayes")
    public EtatImpayesDTO addEtatImpayes(@RequestBody EtatImpayesDTO etatImpayesDTO) {
        return etatImpayesService.addEtatImpayes(etatImpayesDTO);
    }


    @PutMapping("/updateEtatImpayes/{id}")
    public EtatImpayesDTO updateEtatImpayes(@PathVariable Long id, @RequestBody EtatImpayesDTO etatImpayesDTO) {
        return etatImpayesService.updateEtatImpayes(id, etatImpayesDTO);
    }


    @GetMapping("/getEtatImpayesById/{id}")
    public Optional<EtatImpayesDTO> getEtatImpayesById(@PathVariable Long id) {
        return etatImpayesService.getEtatImpayesById(id);
    }


    @GetMapping("/getAllEtatImpayes")
    public List<EtatImpayesDTO> getAllEtatImpayes() {
        return etatImpayesService.getAllEtatImpayes();
    }


    @PutMapping("/{id}/deactivateEtatImpayes")
    public ResponseEntity<Void> deactivateEtatImpayes(@PathVariable Long id) {
        etatImpayesService.deactivateEtatImpayes(id);
        return ResponseEntity.ok().build();
    }

}
