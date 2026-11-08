package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.dto.EquipementDTO;
import tn.esprit.autoloc.service.InterfaceEquipementService;

import java.util.List;

@RestController
@RequestMapping("/equipements")
@Tag(name = "Equipements", description = "Gestion des equipements avec DTOs et validation")
public class EquipementController {

    private final InterfaceEquipementService equipementService;

    public EquipementController(InterfaceEquipementService equipementService) {
        this.equipementService = equipementService;
    }

    @GetMapping
    @Operation(summary = "Afficher tous les equipements (DTO)")
    @ApiResponse(responseCode = "200", description = "Liste des equipements recuperee")
    public List<EquipementDTO> retrieveAllEquipements() {
        return equipementService.retrieveAllEquipements();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Rechercher un equipement par ID (DTO)")
    @ApiResponse(responseCode = "200", description = "Equipement trouve")
    @ApiResponse(responseCode = "404", description = "Equipement introuvable")
    public ResponseEntity<EquipementDTO> retrieveEquipement(@PathVariable Long id) {
        EquipementDTO dto = equipementService.retrieveEquipement(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Operation(summary = "Ajouter un equipement (DTO avec validation)")
    @ApiResponse(responseCode = "201", description = "Equipement cree")
    @ApiResponse(responseCode = "400", description = "Donnees invalides")
    public ResponseEntity<EquipementDTO> addEquipement(@Valid @RequestBody EquipementDTO equipementDTO) {
        EquipementDTO created = equipementService.addEquipement(equipementDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un equipement (DTO avec validation)")
    @ApiResponse(responseCode = "200", description = "Equipement modifie")
    @ApiResponse(responseCode = "400", description = "Donnees invalides")
    @ApiResponse(responseCode = "404", description = "Equipement introuvable")
    public ResponseEntity<EquipementDTO> updateEquipement(@PathVariable Long id, @Valid @RequestBody EquipementDTO equipementDTO) {
        equipementDTO.setIdEquipement(id);
        EquipementDTO updated = equipementService.updateEquipement(equipementDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un equipement")
    @ApiResponse(responseCode = "204", description = "Equipement supprime")
    @ApiResponse(responseCode = "404", description = "Equipement introuvable")
    public ResponseEntity<Void> removeEquipement(@PathVariable Long id) {
        equipementService.removeEquipement(id);
        return ResponseEntity.noContent().build();
    }
}
