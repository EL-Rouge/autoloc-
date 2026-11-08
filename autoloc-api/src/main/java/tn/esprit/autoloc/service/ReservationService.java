package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.dto.ReservationDTO;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.mapper.ReservationMapper;
import tn.esprit.autoloc.repository.ReservationRepository;

import java.util.List;

@Service
public class ReservationService implements InterfaceReservationService {

    private static final String RESERVATION_NOT_FOUND = "Réservation non trouvée avec l'identifiant : ";

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    public ReservationService(ReservationRepository reservationRepository, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    @Override
    public List<ReservationDTO> retrieveAllReservations() {
        return reservationMapper.toDtoList(reservationRepository.findAll());
    }

    @Override
    public ReservationDTO addReservation(ReservationDTO reservationDTO) {
        Reservation entity = reservationMapper.toEntity(reservationDTO);
        Reservation saved = reservationRepository.save(entity);
        return reservationMapper.toDto(saved);
    }

    @Override
    public ReservationDTO updateReservation(ReservationDTO reservationDTO) {
        if (reservationDTO.getIdReservation() != null && !reservationRepository.existsById(reservationDTO.getIdReservation())) {
            throw new ResourceNotFoundException(RESERVATION_NOT_FOUND + reservationDTO.getIdReservation());
        }
        Reservation entity = reservationMapper.toEntity(reservationDTO);
        Reservation updated = reservationRepository.save(entity);
        return reservationMapper.toDto(updated);
    }

    @Override
    public ReservationDTO retrieveReservation(Long idReservation) {
        Reservation reservation = reservationRepository.findById(idReservation)
                .orElseThrow(() -> new ResourceNotFoundException(RESERVATION_NOT_FOUND + idReservation));
        return reservationMapper.toDto(reservation);
    }

    @Override
    public void removeReservation(Long idReservation) {
        if (!reservationRepository.existsById(idReservation)) {
            throw new ResourceNotFoundException(RESERVATION_NOT_FOUND + idReservation);
        }
        reservationRepository.deleteById(idReservation);
    }

    @Override
    public List<ReservationDTO> addReservations(List<ReservationDTO> reservationDTOs) {
        List<Reservation> entities = reservationMapper.toEntityList(reservationDTOs);
        List<Reservation> savedList = reservationRepository.saveAll(entities);
        return reservationMapper.toDtoList(savedList);
    }
}
