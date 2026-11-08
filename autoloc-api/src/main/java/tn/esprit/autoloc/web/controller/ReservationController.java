package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.dto.ReservationDTO;
import tn.esprit.autoloc.service.InterfaceReservationService;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@Tag(name = "Reservations", description = "Gestion des réservations avec DTOs et validation")
public class ReservationController {

    private final InterfaceReservationService reservationService;

    public ReservationController(InterfaceReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    @Operation(summary = "Afficher toutes les reservations (DTO)")
    @ApiResponse(responseCode = "200", description = "Liste des reservations recuperee")
    public List<ReservationDTO> retrieveAllReservations() {
        return reservationService.retrieveAllReservations();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Rechercher une reservation par ID (DTO)")
    @ApiResponse(responseCode = "200", description = "Reservation trouvee")
    @ApiResponse(responseCode = "404", description = "Reservation introuvable")
    public ResponseEntity<ReservationDTO> retrieveReservation(@PathVariable Long id) {
        ReservationDTO dto = reservationService.retrieveReservation(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Operation(summary = "Ajouter une reservation (DTO avec validation)")
    @ApiResponse(responseCode = "201", description = "Reservation creee")
    @ApiResponse(responseCode = "400", description = "Donnees invalides")
    public ResponseEntity<ReservationDTO> addReservation(@Valid @RequestBody ReservationDTO reservationDTO) {
        ReservationDTO created = reservationService.addReservation(reservationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une reservation (DTO avec validation)")
    @ApiResponse(responseCode = "200", description = "Reservation modifiee")
    @ApiResponse(responseCode = "400", description = "Donnees invalides")
    @ApiResponse(responseCode = "404", description = "Reservation introuvable")
    public ResponseEntity<ReservationDTO> updateReservation(@PathVariable Long id, @Valid @RequestBody ReservationDTO reservationDTO) {
        reservationDTO.setIdReservation(id);
        ReservationDTO updated = reservationService.updateReservation(reservationDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une reservation")
    @ApiResponse(responseCode = "204", description = "Reservation supprimee")
    @ApiResponse(responseCode = "404", description = "Reservation introuvable")
    public ResponseEntity<Void> removeReservation(@PathVariable Long id) {
        reservationService.removeReservation(id);
        return ResponseEntity.noContent().build();
    }
}
