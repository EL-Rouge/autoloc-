package tn.esprit.autoloc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.dto.PaiementDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaiementMapper {

    @Mapping(target = "idContrat", source = "contrat.idContrat")
    PaiementDTO toDto(Paiement paiement);

    List<PaiementDTO> toDtoList(List<Paiement> paiements);

    @Mapping(target = "contrat", ignore = true)
    Paiement toEntity(PaiementDTO paiementDTO);

    List<Paiement> toEntityList(List<PaiementDTO> paiementDTOs);
}
