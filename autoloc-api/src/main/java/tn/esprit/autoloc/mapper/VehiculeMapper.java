package tn.esprit.autoloc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.dto.VehiculeDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehiculeMapper {

    @Mapping(target = "nomAgence", source = "agence.nom")
    @Mapping(target = "villeAgence", source = "agence.ville")
    VehiculeDTO toDto(Vehicule vehicule);

    List<VehiculeDTO> toDtoList(List<Vehicule> vehicules);

    @Mapping(target = "agence", ignore = true)
    @Mapping(target = "reservations", ignore = true)
    @Mapping(target = "equipements", ignore = true)
    Vehicule toEntity(VehiculeDTO vehiculeDTO);

    List<Vehicule> toEntityList(List<VehiculeDTO> vehiculeDTOs);
}
