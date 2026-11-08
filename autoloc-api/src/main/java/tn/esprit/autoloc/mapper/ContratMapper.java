package tn.esprit.autoloc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.dto.ContratDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContratMapper {

    @Mapping(target = "idReservation", source = "reservation.idReservation")
    ContratDTO toDto(Contrat contrat);

    List<ContratDTO> toDtoList(List<Contrat> contrats);

    @Mapping(target = "reservation", ignore = true)
    @Mapping(target = "paiements", ignore = true)
    Contrat toEntity(ContratDTO contratDTO);

    List<Contrat> toEntityList(List<ContratDTO> contratDTOs);
}
