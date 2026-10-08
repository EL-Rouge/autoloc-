package tn.esprit.autoloc.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.InterfaceAgenceService;

import java.util.List;

@RestController
@RequestMapping("/agences")
public class AgenceController {

    @Autowired
    private InterfaceAgenceService agenceService;

    @GetMapping
    public List<Agence> retrieveAllAgences() {
        return agenceService.retrieveAllAgences();
    }

    @GetMapping("/{id}")
    public Agence retrieveAgence(@PathVariable("id") Long idAgence) {
        return agenceService.retrieveAgence(idAgence);
    }

    @PostMapping
    public Agence addAgence(@RequestBody Agence agence) {
        return agenceService.addAgence(agence);
    }

    @PostMapping("/batch")
    public List<Agence> addAgences(@RequestBody List<Agence> agences) {
        return agenceService.addAgences(agences);
    }

    @PutMapping
    public Agence updateAgence(@RequestBody Agence agence) {
        return agenceService.updateAgence(agence);
    }

    @DeleteMapping("/{id}")
    public void removeAgence(@PathVariable("id") Long idAgence) {
        agenceService.removeAgence(idAgence);
    }
}
