package tn.esprit.autoloc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehiculeManuelDTO {

    private Long idVehicule;

    @NotBlank(message = "L'immatriculation est obligatoire")
    @Size(min = 3, max = 20, message = "L'immatriculation doit comporter entre 3 et 20 caractères")
    private String immatriculation;

    @NotBlank(message = "La marque est obligatoire")
    @Size(min = 2, max = 50, message = "La marque doit comporter entre 2 et 50 caractères")
    private String marque;

    @NotBlank(message = "Le modèle est obligatoire")
    @Size(min = 1, max = 50, message = "Le modèle doit comporter entre 1 et 50 caractères")
    private String modele;

    @NotBlank(message = "La catégorie du véhicule est obligatoire")
    private String categorie;

    @NotNull(message = "Le tarif journalier est obligatoire")
    @Positive(message = "Le tarif journalier doit être strictement supérieur à zéro")
    private BigDecimal tarifJournalier;

    @NotBlank(message = "Le statut du véhicule est obligatoire")
    private String statut;

    private String nomAgence;
    private String villeAgence;
}
