package tn.esprit.autoloc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.dto.MaintenanceDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MaintenanceMapper {

    @Mapping(target = "idVehicule", source = "vehicule.idVehicule")
    @Mapping(target = "immatriculationVehicule", source = "vehicule.immatriculation")
    MaintenanceDTO toDto(Maintenance maintenance);

    List<MaintenanceDTO> toDtoList(List<Maintenance> maintenances);

    @Mapping(target = "vehicule", ignore = true)
    Maintenance toEntity(MaintenanceDTO maintenanceDTO);

    List<Maintenance> toEntityList(List<MaintenanceDTO> maintenanceDTOs);
}
