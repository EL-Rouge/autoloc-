package tn.esprit.autoloc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.dto.ReservationDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(target = "idVehicule", source = "vehicule.idVehicule")
    @Mapping(target = "immatriculationVehicule", source = "vehicule.immatriculation")
    @Mapping(target = "idClient", source = "client.idClient")
    @Mapping(target = "nomClient", source = "client.nom")
    @Mapping(target = "prenomClient", source = "client.prenom")
    @Mapping(target = "idContrat", source = "contrat.idContrat")
    ReservationDTO toDto(Reservation reservation);

    List<ReservationDTO> toDtoList(List<Reservation> reservations);

    @Mapping(target = "vehicule", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "contrat", ignore = true)
    Reservation toEntity(ReservationDTO reservationDTO);

    List<Reservation> toEntityList(List<ReservationDTO> reservationDTOs);
}
