package tn.esprit.autoloc.service;

import tn.esprit.autoloc.dto.EquipementDTO;

import java.util.List;

public interface InterfaceEquipementService {
    List<EquipementDTO> retrieveAllEquipements();
    EquipementDTO addEquipement(EquipementDTO equipementDTO);
    EquipementDTO updateEquipement(EquipementDTO equipementDTO);
    EquipementDTO retrieveEquipement(Long idEquipement);
    void removeEquipement(Long idEquipement);
    List<EquipementDTO> addEquipements(List<EquipementDTO> equipementDTOs);
}
