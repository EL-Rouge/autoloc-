package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.dto.EmployeDTO;
import tn.esprit.autoloc.service.InterfaceEmployeService;

import java.util.List;

@RestController
@RequestMapping("/employes")
@Tag(name = "Employes", description = "Gestion des employés avec DTOs et validation")
public class EmployeController {

    private final InterfaceEmployeService employeService;

    public EmployeController(InterfaceEmployeService employeService) {
        this.employeService = employeService;
    }

    @GetMapping
    @Operation(summary = "Afficher tous les employes (DTO)")
    @ApiResponse(responseCode = "200", description = "Liste des employes recuperee")
    public List<EmployeDTO> retrieveAllEmployes() {
        return employeService.retrieveAllEmployes();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Rechercher un employe par ID (DTO)")
    @ApiResponse(responseCode = "200", description = "Employe trouve")
    @ApiResponse(responseCode = "404", description = "Employe introuvable")
    public ResponseEntity<EmployeDTO> retrieveEmploye(@PathVariable Long id) {
        EmployeDTO dto = employeService.retrieveEmploye(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Operation(summary = "Ajouter un employe (DTO avec validation)")
    @ApiResponse(responseCode = "201", description = "Employe cree")
    @ApiResponse(responseCode = "400", description = "Donnees invalides")
    public ResponseEntity<EmployeDTO> addEmploye(@Valid @RequestBody EmployeDTO employeDTO) {
        EmployeDTO created = employeService.addEmploye(employeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un employe (DTO avec validation)")
    @ApiResponse(responseCode = "200", description = "Employe modifie")
    @ApiResponse(responseCode = "400", description = "Donnees invalides")
    @ApiResponse(responseCode = "404", description = "Employe introuvable")
    public ResponseEntity<EmployeDTO> updateEmploye(@PathVariable Long id, @Valid @RequestBody EmployeDTO employeDTO) {
        employeDTO.setIdEmploye(id);
        EmployeDTO updated = employeService.updateEmploye(employeDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un employe")
    @ApiResponse(responseCode = "204", description = "Employe supprime")
    @ApiResponse(responseCode = "404", description = "Employe introuvable")
    public ResponseEntity<Void> removeEmploye(@PathVariable Long id) {
        employeService.removeEmploye(id);
        return ResponseEntity.noContent().build();
    }
}
