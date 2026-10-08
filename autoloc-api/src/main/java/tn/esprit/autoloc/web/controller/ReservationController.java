package tn.esprit.autoloc.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.service.InterfaceReservationService;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private InterfaceReservationService reservationService;

    @GetMapping
    public List<Reservation> retrieveAllReservations() {
        return reservationService.retrieveAllReservations();
    }

    @GetMapping("/{id}")
    public Reservation retrieveReservation(@PathVariable("id") Long idReservation) {
        return reservationService.retrieveReservation(idReservation);
    }

    @PostMapping
    public Reservation addReservation(@RequestBody Reservation reservation) {
        return reservationService.addReservation(reservation);
    }

    @PostMapping("/batch")
    public List<Reservation> addReservations(@RequestBody List<Reservation> reservations) {
        return reservationService.addReservations(reservations);
    }

    @PutMapping
    public Reservation updateReservation(@RequestBody Reservation reservation) {
        return reservationService.updateReservation(reservation);
    }

    @DeleteMapping("/{id}")
    public void removeReservation(@PathVariable("id") Long idReservation) {
        reservationService.removeReservation(idReservation);
    }
}
