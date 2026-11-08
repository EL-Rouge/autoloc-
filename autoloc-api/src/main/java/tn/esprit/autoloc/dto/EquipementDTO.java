package tn.esprit.autoloc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipementDTO {

    private Long idEquipement;

    @NotBlank(message = "Le libellé de l'équipement est obligatoire")
    @Size(min = 2, max = 100, message = "Le libellé doit comporter entre 2 et 100 caractères")
    private String libelle;
}
