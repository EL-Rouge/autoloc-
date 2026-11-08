package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.dto.MaintenanceDTO;
import tn.esprit.autoloc.service.InterfaceMaintenanceService;

import java.util.List;

@RestController
@RequestMapping("/maintenances")
@Tag(name = "Maintenances", description = "Gestion des maintenances avec DTOs et validation")
public class MaintenanceController {

    private final InterfaceMaintenanceService maintenanceService;

    public MaintenanceController(InterfaceMaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @GetMapping
    @Operation(summary = "Afficher toutes les maintenances (DTO)")
    @ApiResponse(responseCode = "200", description = "Liste des maintenances recuperee")
    public List<MaintenanceDTO> retrieveAllMaintenances() {
        return maintenanceService.retrieveAllMaintenances();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Rechercher une maintenance par ID (DTO)")
    @ApiResponse(responseCode = "200", description = "Maintenance trouvee")
    @ApiResponse(responseCode = "404", description = "Maintenance introuvable")
    public ResponseEntity<MaintenanceDTO> retrieveMaintenance(@PathVariable Long id) {
        MaintenanceDTO dto = maintenanceService.retrieveMaintenance(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Operation(summary = "Ajouter une maintenance (DTO avec validation)")
    @ApiResponse(responseCode = "201", description = "Maintenance creee")
    @ApiResponse(responseCode = "400", description = "Donnees invalides")
    public ResponseEntity<MaintenanceDTO> addMaintenance(@Valid @RequestBody MaintenanceDTO maintenanceDTO) {
        MaintenanceDTO created = maintenanceService.addMaintenance(maintenanceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une maintenance (DTO avec validation)")
    @ApiResponse(responseCode = "200", description = "Maintenance modifiee")
    @ApiResponse(responseCode = "400", description = "Donnees invalides")
    @ApiResponse(responseCode = "404", description = "Maintenance introuvable")
    public ResponseEntity<MaintenanceDTO> updateMaintenance(@PathVariable Long id, @Valid @RequestBody MaintenanceDTO maintenanceDTO) {
        maintenanceDTO.setIdMaintenance(id);
        MaintenanceDTO updated = maintenanceService.updateMaintenance(maintenanceDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une maintenance")
    @ApiResponse(responseCode = "204", description = "Maintenance supprimee")
    @ApiResponse(responseCode = "404", description = "Maintenance introuvable")
    public ResponseEntity<Void> removeMaintenance(@PathVariable Long id) {
        maintenanceService.removeMaintenance(id);
        return ResponseEntity.noContent().build();
    }
}
