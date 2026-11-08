package tn.esprit.autoloc.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.autoloc.domain.StatutReservation;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {

    private Long idReservation;

    @NotNull(message = "La date de début de réservation est obligatoire")
    private LocalDate dateDebut;

    @NotNull(message = "La date de fin de réservation est obligatoire")
    private LocalDate dateFin;

    @NotNull(message = "Le statut de la réservation est obligatoire")
    private StatutReservation statut;

    @NotNull(message = "L'identifiant du véhicule est obligatoire")
    private Long idVehicule;

    private String immatriculationVehicule;

    @NotNull(message = "L'identifiant du client est obligatoire")
    private Long idClient;

    private String nomClient;
    private String prenomClient;
    private Long idContrat;
}
