package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.dto.ContratDTO;
import tn.esprit.autoloc.service.InterfaceContratService;

import java.util.List;

@RestController
@RequestMapping("/contrats")
@Tag(name = "Contrats", description = "Gestion des contrats avec DTOs et validation")
public class ContratController {

    private final InterfaceContratService contratService;

    public ContratController(InterfaceContratService contratService) {
        this.contratService = contratService;
    }

    @GetMapping
    @Operation(summary = "Afficher tous les contrats (DTO)")
    @ApiResponse(responseCode = "200", description = "Liste des contrats recuperee")
    public List<ContratDTO> retrieveAllContrats() {
        return contratService.retrieveAllContrats();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Rechercher un contrat par ID (DTO)")
    @ApiResponse(responseCode = "200", description = "Contrat trouve")
    @ApiResponse(responseCode = "404", description = "Contrat introuvable")
    public ResponseEntity<ContratDTO> retrieveContrat(@PathVariable Long id) {
        ContratDTO dto = contratService.retrieveContrat(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Operation(summary = "Ajouter un contrat (DTO avec validation)")
    @ApiResponse(responseCode = "201", description = "Contrat cree")
    @ApiResponse(responseCode = "400", description = "Donnees invalides")
    public ResponseEntity<ContratDTO> addContrat(@Valid @RequestBody ContratDTO contratDTO) {
        ContratDTO created = contratService.addContrat(contratDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un contrat (DTO avec validation)")
    @ApiResponse(responseCode = "200", description = "Contrat modifie")
    @ApiResponse(responseCode = "400", description = "Donnees invalides")
    @ApiResponse(responseCode = "404", description = "Contrat introuvable")
    public ResponseEntity<ContratDTO> updateContrat(@PathVariable Long id, @Valid @RequestBody ContratDTO contratDTO) {
        contratDTO.setIdContrat(id);
        ContratDTO updated = contratService.updateContrat(contratDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un contrat")
    @ApiResponse(responseCode = "204", description = "Contrat supprime")
    @ApiResponse(responseCode = "404", description = "Contrat introuvable")
    public ResponseEntity<Void> removeContrat(@PathVariable Long id) {
        contratService.removeContrat(id);
        return ResponseEntity.noContent().build();
    }
}
