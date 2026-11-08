package tn.esprit.autoloc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.autoloc.domain.RoleEmploye;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeDTO {

    private Long idEmploye;

    @NotBlank(message = "Le nom de l'employé est obligatoire")
    @Size(min = 2, max = 50, message = "Le nom doit comporter entre 2 et 50 caractères")
    private String nom;

    @NotBlank(message = "Le prénom de l'employé est obligatoire")
    @Size(min = 2, max = 50, message = "Le prénom doit comporter entre 2 et 50 caractères")
    private String prenom;

    @NotNull(message = "Le rôle de l'employé est obligatoire")
    private RoleEmploye role;

    private Long idAgence;
    private String nomAgence;
}
