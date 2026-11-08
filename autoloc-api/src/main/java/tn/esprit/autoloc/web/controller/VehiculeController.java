package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.dto.VehiculeDTO;
import tn.esprit.autoloc.dto.VehiculeManuelDTO;
import tn.esprit.autoloc.service.InterfaceVehiculeService;

import java.util.List;

@RestController
@RequestMapping("/vehicules")
@Tag(name = "Vehicules", description = "Gestion des véhicules avec DTOs et validation")
public class VehiculeController {

    private final InterfaceVehiculeService vehiculeService;

    public VehiculeController(InterfaceVehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    // --- APPROCHE 2 : MapStruct (Tous les véhicules) ---
    @GetMapping
    @Operation(summary = "Afficher tous les vehicules (MapStruct DTO)")
    @ApiResponse(responseCode = "200", description = "Liste des DTOs récupérée")
    public List<VehiculeDTO> retrieveAllVehicules() {
        return vehiculeService.retrieveAllVehicules();
    }

    // --- APPROCHE 2 : MapStruct (Rechercher un véhicule par ID) ---
    @GetMapping("/{id}")
    @Operation(summary = "Rechercher un vehicule par ID (MapStruct DTO)")
    @ApiResponse(responseCode = "200", description = "Vehicule trouve")
    @ApiResponse(responseCode = "404", description = "Vehicule introuvable")
    public ResponseEntity<VehiculeDTO> retrieveVehicule(@PathVariable Long id) {
        VehiculeDTO dto = vehiculeService.retrieveVehicule(id);
        return ResponseEntity.ok(dto);
    }

    // --- APPROCHE 1 : Mapping Manuel (Slide 15) ---
    @GetMapping("/{id}/manuel")
    @Operation(summary = "Rechercher un vehicule par ID (Mapping Manuel)")
    @ApiResponse(responseCode = "200", description = "Vehicule DTO manuel trouve")
    @ApiResponse(responseCode = "404", description = "Vehicule introuvable")
    public ResponseEntity<VehiculeManuelDTO> retrieveVehiculeManuel(@PathVariable Long id) {
        VehiculeManuelDTO dto = vehiculeService.retrieveVehiculeManuel(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Operation(summary = "Ajouter un vehicule (DTO avec validation)")
    @ApiResponse(responseCode = "201", description = "Vehicule cree")
    @ApiResponse(responseCode = "400", description = "Donnees invalides")
    public ResponseEntity<VehiculeDTO> addVehicule(@Valid @RequestBody VehiculeDTO vehiculeDTO) {
        VehiculeDTO created = vehiculeService.addVehicule(vehiculeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un vehicule (DTO avec validation)")
    @ApiResponse(responseCode = "200", description = "Vehicule modifie")
    @ApiResponse(responseCode = "400", description = "Donnees invalides")
    @ApiResponse(responseCode = "404", description = "Vehicule introuvable")
    public ResponseEntity<VehiculeDTO> updateVehicule(@PathVariable Long id, @Valid @RequestBody VehiculeDTO vehiculeDTO) {
        vehiculeDTO.setIdVehicule(id);
        VehiculeDTO updated = vehiculeService.updateVehicule(vehiculeDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un vehicule")
    @ApiResponse(responseCode = "204", description = "Vehicule supprime")
    @ApiResponse(responseCode = "404", description = "Vehicule introuvable")
    public ResponseEntity<Void> removeVehicule(@PathVariable Long id) {
        vehiculeService.removeVehicule(id);
        return ResponseEntity.noContent().build();
    }
}
