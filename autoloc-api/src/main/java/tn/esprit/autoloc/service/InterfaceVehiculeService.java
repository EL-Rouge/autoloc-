package tn.esprit.autoloc.service;

import tn.esprit.autoloc.dto.VehiculeDTO;
import tn.esprit.autoloc.dto.VehiculeManuelDTO;

import java.util.List;

public interface InterfaceVehiculeService {
    List<VehiculeDTO> retrieveAllVehicules();
    VehiculeDTO addVehicule(VehiculeDTO vehiculeDTO);
    VehiculeDTO updateVehicule(VehiculeDTO vehiculeDTO);
    VehiculeDTO retrieveVehicule(Long idVehicule);
    void removeVehicule(Long idVehicule);
    List<VehiculeDTO> addVehicules(List<VehiculeDTO> vehiculeDTOs);

    // Approche 1 : Mapping Manuel (Slide 14)
    VehiculeManuelDTO retrieveVehiculeManuel(Long idVehicule);
}
