package tn.esprit.autoloc.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.autoloc.domain.ModePaiement;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaiementDTO {

    private Long idPaiement;

    @NotNull(message = "Le montant du paiement est obligatoire")
    @Positive(message = "Le montant du paiement doit être strictement supérieur à zéro")
    private BigDecimal montant;

    @NotNull(message = "La date du paiement est obligatoire")
    @PastOrPresent(message = "La date du paiement ne peut pas être dans le futur")
    private LocalDate datePaiement;

    @NotNull(message = "Le mode de paiement est obligatoire")
    private ModePaiement modePaiement;

    @NotNull(message = "L'identifiant du contrat associé est obligatoire")
    private Long idContrat;
}
