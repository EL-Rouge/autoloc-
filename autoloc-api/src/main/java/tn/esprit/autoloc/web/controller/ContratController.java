package tn.esprit.autoloc.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.service.InterfaceContratService;

import java.util.List;

@RestController
@RequestMapping("/contrats")
public class ContratController {

    @Autowired
    private InterfaceContratService contratService;

    @GetMapping
    public List<Contrat> retrieveAllContrats() {
        return contratService.retrieveAllContrats();
    }

    @GetMapping("/{id}")
    public Contrat retrieveContrat(@PathVariable("id") Long idContrat) {
        return contratService.retrieveContrat(idContrat);
    }

    @PostMapping
    public Contrat addContrat(@RequestBody Contrat contrat) {
        return contratService.addContrat(contrat);
    }

    @PostMapping("/batch")
    public List<Contrat> addContrats(@RequestBody List<Contrat> contrats) {
        return contratService.addContrats(contrats);
    }

    @PutMapping
    public Contrat updateContrat(@RequestBody Contrat contrat) {
        return contratService.updateContrat(contrat);
    }

    @DeleteMapping("/{id}")
    public void removeContrat(@PathVariable("id") Long idContrat) {
        contratService.removeContrat(idContrat);
    }
}
