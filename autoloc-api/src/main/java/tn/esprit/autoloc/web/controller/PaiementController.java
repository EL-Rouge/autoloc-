package tn.esprit.autoloc.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.service.InterfacePaiementService;

import java.util.List;

@RestController
@RequestMapping("/paiements")
public class PaiementController {

    @Autowired
    private InterfacePaiementService paiementService;

    @GetMapping
    public List<Paiement> retrieveAllPaiements() {
        return paiementService.retrieveAllPaiements();
    }

    @GetMapping("/{id}")
    public Paiement retrievePaiement(@PathVariable("id") Long idPaiement) {
        return paiementService.retrievePaiement(idPaiement);
    }

    @PostMapping
    public Paiement addPaiement(@RequestBody Paiement paiement) {
        return paiementService.addPaiement(paiement);
    }

    @PostMapping("/batch")
    public List<Paiement> addPaiements(@RequestBody List<Paiement> paiements) {
        return paiementService.addPaiements(paiements);
    }

    @PutMapping
    public Paiement updatePaiement(@RequestBody Paiement paiement) {
        return paiementService.updatePaiement(paiement);
    }

    @DeleteMapping("/{id}")
    public void removePaiement(@PathVariable("id") Long idPaiement) {
        paiementService.removePaiement(idPaiement);
    }
}
