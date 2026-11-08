package tn.esprit.autoloc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.dto.AgenceDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AgenceMapper {

    AgenceDTO toDto(Agence agence);

    List<AgenceDTO> toDtoList(List<Agence> agences);

    @Mapping(target = "employes", ignore = true)
    @Mapping(target = "vehicules", ignore = true)
    Agence toEntity(AgenceDTO agenceDTO);

    List<Agence> toEntityList(List<AgenceDTO> agenceDTOs);
}
