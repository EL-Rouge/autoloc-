package tn.esprit.autoloc.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
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
public class ClientDTO {

    private Long idClient;

    @NotBlank(message = "Le nom du client est obligatoire")
    @Size(min = 2, max = 50, message = "Le nom doit comporter entre 2 et 50 caractères")
    private String nom;

    @NotBlank(message = "Le prénom du client est obligatoire")
    @Size(min = 2, max = 50, message = "Le prénom doit comporter entre 2 et 50 caractères")
    private String prenom;

    @NotBlank(message = "L'adresse email est obligatoire")
    @Email(message = "Le format de l'adresse email est invalide")
    @Size(max = 100, message = "L'email ne doit pas dépasser 100 caractères")
    private String email;

    @NotBlank(message = "Le numéro de téléphone est obligatoire")
    @Pattern(regexp = "^(\\+216)?[0-9]{8}$", message = "Le format du numéro de téléphone est invalide (8 chiffres)")
    private String telephone;

    @NotBlank(message = "Le numéro de permis est obligatoire")
    @Size(min = 4, max = 20, message = "Le numéro de permis doit comporter entre 4 et 20 caractères")
    private String numPermis;

    @NotNull(message = "La date d'inscription est obligatoire")
    @PastOrPresent(message = "La date d'inscription ne peut pas être dans le futur")
    private LocalDate dateInscription;
}
