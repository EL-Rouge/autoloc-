package tn.esprit.autoloc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.dto.EmployeDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeMapper {

    @Mapping(target = "idAgence", source = "agence.idAgence")
    @Mapping(target = "nomAgence", source = "agence.nom")
    EmployeDTO toDto(Employe employe);

    List<EmployeDTO> toDtoList(List<Employe> employes);

    @Mapping(target = "agence", ignore = true)
    Employe toEntity(EmployeDTO employeDTO);

    List<Employe> toEntityList(List<EmployeDTO> employeDTOs);
}
