package tn.esprit.autoloc.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.service.InterfaceEquipementService;

import java.util.List;

@RestController
@RequestMapping("/equipements")
public class EquipementController {

    @Autowired
    private InterfaceEquipementService equipementService;

    @GetMapping
    public List<Equipement> retrieveAllEquipements() {
        return equipementService.retrieveAllEquipements();
    }

    @GetMapping("/{id}")
    public Equipement retrieveEquipement(@PathVariable("id") Long idEquipement) {
        return equipementService.retrieveEquipement(idEquipement);
    }

    @PostMapping
    public Equipement addEquipement(@RequestBody Equipement equipement) {
        return equipementService.addEquipement(equipement);
    }

    @PostMapping("/batch")
    public List<Equipement> addEquipements(@RequestBody List<Equipement> equipements) {
        return equipementService.addEquipements(equipements);
    }

    @PutMapping
    public Equipement updateEquipement(@RequestBody Equipement equipement) {
        return equipementService.updateEquipement(equipement);
    }

    @DeleteMapping("/{id}")
    public void removeEquipement(@PathVariable("id") Long idEquipement) {
        equipementService.removeEquipement(idEquipement);
    }
}
