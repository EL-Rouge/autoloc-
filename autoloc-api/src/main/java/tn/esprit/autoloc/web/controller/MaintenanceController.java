package tn.esprit.autoloc.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.service.InterfaceMaintenanceService;

import java.util.List;

@RestController
@RequestMapping("/maintenances")
public class MaintenanceController {

    @Autowired
    private InterfaceMaintenanceService maintenanceService;

    @GetMapping
    public List<Maintenance> retrieveAllMaintenances() {
        return maintenanceService.retrieveAllMaintenances();
    }

    @GetMapping("/{id}")
    public Maintenance retrieveMaintenance(@PathVariable("id") Long idMaintenance) {
        return maintenanceService.retrieveMaintenance(idMaintenance);
    }

    @PostMapping
    public Maintenance addMaintenance(@RequestBody Maintenance maintenance) {
        return maintenanceService.addMaintenance(maintenance);
    }

    @PostMapping("/batch")
    public List<Maintenance> addMaintenances(@RequestBody List<Maintenance> maintenances) {
        return maintenanceService.addMaintenances(maintenances);
    }

    @PutMapping
    public Maintenance updateMaintenance(@RequestBody Maintenance maintenance) {
        return maintenanceService.updateMaintenance(maintenance);
    }

    @DeleteMapping("/{id}")
    public void removeMaintenance(@PathVariable("id") Long idMaintenance) {
        maintenanceService.removeMaintenance(idMaintenance);
    }
}
