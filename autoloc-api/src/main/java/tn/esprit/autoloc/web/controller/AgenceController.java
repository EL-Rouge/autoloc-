package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.dto.AgenceDTO;
import tn.esprit.autoloc.service.InterfaceAgenceService;

import java.util.List;

@RestController
@RequestMapping("/agences")
@Tag(name = "Agences", description = "Gestion des agences avec DTOs et validation")
public class AgenceController {

    private final InterfaceAgenceService agenceService;

    public AgenceController(InterfaceAgenceService agenceService) {
        this.agenceService = agenceService;
    }

    @GetMapping
    @Operation(summary = "Afficher toutes les agences (DTO)")
    @ApiResponse(responseCode = "200", description = "Liste des agences recuperee")
    public List<AgenceDTO> retrieveAllAgences() {
        return agenceService.retrieveAllAgences();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Rechercher une agence par ID (DTO)")
    @ApiResponse(responseCode = "200", description = "Agence trouvee")
    @ApiResponse(responseCode = "404", description = "Agence introuvable")
    public ResponseEntity<AgenceDTO> retrieveAgence(@PathVariable Long id) {
        AgenceDTO dto = agenceService.retrieveAgence(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Operation(summary = "Ajouter une agence (DTO avec validation)")
    @ApiResponse(responseCode = "201", description = "Agence creee")
    @ApiResponse(responseCode = "400", description = "Donnees invalides")
    public ResponseEntity<AgenceDTO> addAgence(@Valid @RequestBody AgenceDTO agenceDTO) {
        AgenceDTO created = agenceService.addAgence(agenceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une agence (DTO avec validation)")
    @ApiResponse(responseCode = "200", description = "Agence modifiee")
    @ApiResponse(responseCode = "400", description = "Donnees invalides")
    @ApiResponse(responseCode = "404", description = "Agence introuvable")
    public ResponseEntity<AgenceDTO> updateAgence(@PathVariable Long id, @Valid @RequestBody AgenceDTO agenceDTO) {
        agenceDTO.setIdAgence(id);
        AgenceDTO updated = agenceService.updateAgence(agenceDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une agence")
    @ApiResponse(responseCode = "204", description = "Agence supprimee")
    @ApiResponse(responseCode = "404", description = "Agence introuvable")
    public ResponseEntity<Void> removeAgence(@PathVariable Long id) {
        agenceService.removeAgence(id);
        return ResponseEntity.noContent().build();
    }
}
