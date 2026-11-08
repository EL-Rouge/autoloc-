package tn.esprit.autoloc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceDTO {

    private Long idMaintenance;

    @NotNull(message = "La date de début de maintenance est obligatoire")
    private LocalDate dateDebut;

    @NotNull(message = "La date de fin de maintenance est obligatoire")
    private LocalDate dateFin;

    @NotBlank(message = "La description de la maintenance est obligatoire")
    @Size(min = 3, max = 255, message = "La description doit comporter entre 3 et 255 caractères")
    private String description;

    @NotNull(message = "L'identifiant du véhicule concerné est obligatoire")
    private Long idVehicule;

    private String immatriculationVehicule;
}
