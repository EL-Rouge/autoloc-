package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Paiement;

import java.util.List;

public interface InterfacePaiementService {
    List<Paiement> retrieveAllPaiements();
    Paiement addPaiement(Paiement paiement);
    Paiement updatePaiement(Paiement paiement);
    Paiement retrievePaiement(Long idPaiement);
    void removePaiement(Long idPaiement);
    List<Paiement> addPaiements(List<Paiement> paiements);
}
