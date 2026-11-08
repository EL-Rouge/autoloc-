package tn.esprit.autoloc.service;

import tn.esprit.autoloc.dto.PaiementDTO;

import java.util.List;

public interface InterfacePaiementService {
    List<PaiementDTO> retrieveAllPaiements();
    PaiementDTO addPaiement(PaiementDTO paiementDTO);
    PaiementDTO updatePaiement(PaiementDTO paiementDTO);
    PaiementDTO retrievePaiement(Long idPaiement);
    void removePaiement(Long idPaiement);
    List<PaiementDTO> addPaiements(List<PaiementDTO> paiementDTOs);
}
