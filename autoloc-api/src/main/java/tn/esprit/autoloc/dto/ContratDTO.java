package tn.esprit.autoloc.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContratDTO {

    private Long idContrat;

    @NotNull(message = "La date de signature est obligatoire")
    @PastOrPresent(message = "La date de signature ne peut pas être dans le futur")
    private LocalDate dateSignature;

    @NotNull(message = "Le montant total est obligatoire")
    @Positive(message = "Le montant total doit être strictement supérieur à zéro")
    private BigDecimal montantTotal;

    private boolean valide;

    @NotNull(message = "L'identifiant de la réservation associée est obligatoire")
    private Long idReservation;
}
