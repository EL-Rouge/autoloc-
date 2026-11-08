package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.dto.ClientDTO;
import tn.esprit.autoloc.service.InterfaceClientService;

import java.util.List;

@RestController
@RequestMapping("/clients")
@Tag(name = "Clients", description = "Gestion des clients avec DTOs et validation")
public class ClientController {

    private final InterfaceClientService clientService;

    public ClientController(InterfaceClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    @Operation(summary = "Afficher tous les clients (DTO)")
    @ApiResponse(responseCode = "200", description = "Liste des clients recuperee")
    public List<ClientDTO> retrieveAllClients() {
        return clientService.retrieveAllClients();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Rechercher un client par ID (DTO)")
    @ApiResponse(responseCode = "200", description = "Client trouve")
    @ApiResponse(responseCode = "404", description = "Client introuvable")
    public ResponseEntity<ClientDTO> retrieveClient(@PathVariable Long id) {
        ClientDTO dto = clientService.retrieveClient(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Operation(summary = "Ajouter un client (DTO avec validation)")
    @ApiResponse(responseCode = "201", description = "Client cree")
    @ApiResponse(responseCode = "400", description = "Donnees invalides")
    public ResponseEntity<ClientDTO> addClient(@Valid @RequestBody ClientDTO clientDTO) {
        ClientDTO created = clientService.addClient(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un client (DTO avec validation)")
    @ApiResponse(responseCode = "200", description = "Client modifie")
    @ApiResponse(responseCode = "400", description = "Donnees invalides")
    @ApiResponse(responseCode = "404", description = "Client introuvable")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @Valid @RequestBody ClientDTO clientDTO) {
        clientDTO.setIdClient(id);
        ClientDTO updated = clientService.updateClient(clientDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un client")
    @ApiResponse(responseCode = "204", description = "Client supprime")
    @ApiResponse(responseCode = "404", description = "Client introuvable")
    public ResponseEntity<Void> removeClient(@PathVariable Long id) {
        clientService.removeClient(id);
        return ResponseEntity.noContent().build();
    }
}
