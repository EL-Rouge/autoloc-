package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.dto.PaiementDTO;
import tn.esprit.autoloc.service.InterfacePaiementService;

import java.util.List;

@RestController
@RequestMapping("/paiements")
@Tag(name = "Paiements", description = "Gestion des paiements avec DTOs et validation")
public class PaiementController {

    private final InterfacePaiementService paiementService;

    public PaiementController(InterfacePaiementService paiementService) {
        this.paiementService = paiementService;
    }

    @GetMapping
    @Operation(summary = "Afficher tous les paiements (DTO)")
    @ApiResponse(responseCode = "200", description = "Liste des paiements recuperee")
    public List<PaiementDTO> retrieveAllPaiements() {
        return paiementService.retrieveAllPaiements();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Rechercher un paiement par ID (DTO)")
    @ApiResponse(responseCode = "200", description = "Paiement trouve")
    @ApiResponse(responseCode = "404", description = "Paiement introuvable")
    public ResponseEntity<PaiementDTO> retrievePaiement(@PathVariable Long id) {
        PaiementDTO dto = paiementService.retrievePaiement(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Operation(summary = "Ajouter un paiement (DTO avec validation)")
    @ApiResponse(responseCode = "201", description = "Paiement cree")
    @ApiResponse(responseCode = "400", description = "Donnees invalides")
    public ResponseEntity<PaiementDTO> addPaiement(@Valid @RequestBody PaiementDTO paiementDTO) {
        PaiementDTO created = paiementService.addPaiement(paiementDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un paiement (DTO avec validation)")
    @ApiResponse(responseCode = "200", description = "Paiement modifie")
    @ApiResponse(responseCode = "400", description = "Donnees invalides")
    @ApiResponse(responseCode = "404", description = "Paiement introuvable")
    public ResponseEntity<PaiementDTO> updatePaiement(@PathVariable Long id, @Valid @RequestBody PaiementDTO paiementDTO) {
        paiementDTO.setIdPaiement(id);
        PaiementDTO updated = paiementService.updatePaiement(paiementDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un paiement")
    @ApiResponse(responseCode = "204", description = "Paiement supprime")
    @ApiResponse(responseCode = "404", description = "Paiement introuvable")
    public ResponseEntity<Void> removePaiement(@PathVariable Long id) {
        paiementService.removePaiement(id);
        return ResponseEntity.noContent().build();
    }
}
