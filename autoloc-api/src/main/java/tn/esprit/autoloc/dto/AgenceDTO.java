package tn.esprit.autoloc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgenceDTO {

    private Long idAgence;

    @NotBlank(message = "Le nom de l'agence est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom de l'agence doit comporter entre 2 et 100 caractères")
    private String nom;

    @NotBlank(message = "La ville de l'agence est obligatoire")
    @Size(min = 2, max = 50, message = "La ville de l'agence doit comporter entre 2 et 50 caractères")
    private String ville;

    @NotBlank(message = "L'adresse de l'agence est obligatoire")
    @Size(min = 5, max = 150, message = "L'adresse doit comporter entre 5 et 150 caractères")
    private String adresse;

    @NotBlank(message = "Le numéro de téléphone est obligatoire")
    @Pattern(regexp = "^(\\+216)?[0-9]{8}$", message = "Le numéro de téléphone doit être composé de 8 chiffres (ex: 71234567 ou +21671234567)")
    private String telephone;
}
