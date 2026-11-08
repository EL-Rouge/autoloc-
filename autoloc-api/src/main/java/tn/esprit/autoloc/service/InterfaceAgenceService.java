package tn.esprit.autoloc.service;

import tn.esprit.autoloc.dto.AgenceDTO;

import java.util.List;

public interface InterfaceAgenceService {
    List<AgenceDTO> retrieveAllAgences();
    AgenceDTO addAgence(AgenceDTO agenceDTO);
    AgenceDTO updateAgence(AgenceDTO agenceDTO);
    AgenceDTO retrieveAgence(Long idAgence);
    void removeAgence(Long idAgence);
    List<AgenceDTO> addAgences(List<AgenceDTO> agenceDTOs);
}
