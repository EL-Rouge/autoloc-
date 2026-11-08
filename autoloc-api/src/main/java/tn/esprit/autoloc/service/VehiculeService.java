package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.dto.VehiculeDTO;
import tn.esprit.autoloc.dto.VehiculeManuelDTO;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.mapper.VehiculeMapper;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.util.List;

@Service
public class VehiculeService implements InterfaceVehiculeService {

    private static final String VEHICULE_NOT_FOUND = "Véhicule non trouvé avec l'identifiant : ";

    private final VehiculeRepository vehiculeRepository;
    private final VehiculeMapper vehiculeMapper;

    public VehiculeService(VehiculeRepository vehiculeRepository, VehiculeMapper vehiculeMapper) {
        this.vehiculeRepository = vehiculeRepository;
        this.vehiculeMapper = vehiculeMapper;
    }

    // =========================================================
    // APPROCHE 2 : MAPSTRUCT (Slide 21)
    // =========================================================

    @Override
    public List<VehiculeDTO> retrieveAllVehicules() {
        return vehiculeMapper.toDtoList(vehiculeRepository.findAll());
    }

    @Override
    public VehiculeDTO retrieveVehicule(Long idVehicule) {
        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new ResourceNotFoundException(VEHICULE_NOT_FOUND + idVehicule));
        return vehiculeMapper.toDto(vehicule);
    }

    @Override
    public VehiculeDTO addVehicule(VehiculeDTO vehiculeDTO) {
        Vehicule entity = vehiculeMapper.toEntity(vehiculeDTO);
        Vehicule saved = vehiculeRepository.save(entity);
        return vehiculeMapper.toDto(saved);
    }

    @Override
    public VehiculeDTO updateVehicule(VehiculeDTO vehiculeDTO) {
        if (vehiculeDTO.getIdVehicule() != null && !vehiculeRepository.existsById(vehiculeDTO.getIdVehicule())) {
            throw new ResourceNotFoundException(VEHICULE_NOT_FOUND + vehiculeDTO.getIdVehicule());
        }
        Vehicule entity = vehiculeMapper.toEntity(vehiculeDTO);
        Vehicule updated = vehiculeRepository.save(entity);
        return vehiculeMapper.toDto(updated);
    }

    @Override
    public void removeVehicule(Long idVehicule) {
        if (!vehiculeRepository.existsById(idVehicule)) {
            throw new ResourceNotFoundException(VEHICULE_NOT_FOUND + idVehicule);
        }
        vehiculeRepository.deleteById(idVehicule);
    }

    @Override
    public List<VehiculeDTO> addVehicules(List<VehiculeDTO> vehiculeDTOs) {
        List<Vehicule> entities = vehiculeMapper.toEntityList(vehiculeDTOs);
        List<Vehicule> savedList = vehiculeRepository.saveAll(entities);
        return vehiculeMapper.toDtoList(savedList);
    }

    // =========================================================
    // APPROCHE 1 : MAPPING MANUEL (Slide 14)
    // =========================================================

    @Override
    public VehiculeManuelDTO retrieveVehiculeManuel(Long idVehicule) {
        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new ResourceNotFoundException(VEHICULE_NOT_FOUND + idVehicule));
        return convertToManuelDto(vehicule);
    }

    private VehiculeManuelDTO convertToManuelDto(Vehicule vehicule) {
        VehiculeManuelDTO dto = new VehiculeManuelDTO();
        dto.setIdVehicule(vehicule.getIdVehicule());
        dto.setImmatriculation(vehicule.getImmatriculation());
        dto.setMarque(vehicule.getMarque());
        dto.setModele(vehicule.getModele());
        dto.setCategorie(vehicule.getCategorie() != null ? vehicule.getCategorie().name() : null);
        dto.setTarifJournalier(vehicule.getTarifJournalier());
        dto.setStatut(vehicule.getStatut() != null ? vehicule.getStatut().name() : null);

        if (vehicule.getAgence() != null) {
            dto.setNomAgence(vehicule.getAgence().getNom());
            dto.setVilleAgence(vehicule.getAgence().getVille());
        }

        return dto;
    }
}
