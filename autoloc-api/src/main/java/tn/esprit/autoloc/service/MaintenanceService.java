package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.dto.MaintenanceDTO;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.mapper.MaintenanceMapper;
import tn.esprit.autoloc.repository.MaintenanceRepository;

import java.util.List;

@Service
public class MaintenanceService implements InterfaceMaintenanceService {

    private static final String MAINTENANCE_NOT_FOUND = "Maintenance non trouvée avec l'identifiant : ";

    private final MaintenanceRepository maintenanceRepository;
    private final MaintenanceMapper maintenanceMapper;

    public MaintenanceService(MaintenanceRepository maintenanceRepository, MaintenanceMapper maintenanceMapper) {
        this.maintenanceRepository = maintenanceRepository;
        this.maintenanceMapper = maintenanceMapper;
    }

    @Override
    public List<MaintenanceDTO> retrieveAllMaintenances() {
        return maintenanceMapper.toDtoList(maintenanceRepository.findAll());
    }

    @Override
    public MaintenanceDTO addMaintenance(MaintenanceDTO maintenanceDTO) {
        Maintenance entity = maintenanceMapper.toEntity(maintenanceDTO);
        Maintenance saved = maintenanceRepository.save(entity);
        return maintenanceMapper.toDto(saved);
    }

    @Override
    public MaintenanceDTO updateMaintenance(MaintenanceDTO maintenanceDTO) {
        if (maintenanceDTO.getIdMaintenance() != null && !maintenanceRepository.existsById(maintenanceDTO.getIdMaintenance())) {
            throw new ResourceNotFoundException(MAINTENANCE_NOT_FOUND + maintenanceDTO.getIdMaintenance());
        }
        Maintenance entity = maintenanceMapper.toEntity(maintenanceDTO);
        Maintenance updated = maintenanceRepository.save(entity);
        return maintenanceMapper.toDto(updated);
    }

    @Override
    public MaintenanceDTO retrieveMaintenance(Long idMaintenance) {
        Maintenance maintenance = maintenanceRepository.findById(idMaintenance)
                .orElseThrow(() -> new ResourceNotFoundException(MAINTENANCE_NOT_FOUND + idMaintenance));
        return maintenanceMapper.toDto(maintenance);
    }

    @Override
    public void removeMaintenance(Long idMaintenance) {
        if (!maintenanceRepository.existsById(idMaintenance)) {
            throw new ResourceNotFoundException(MAINTENANCE_NOT_FOUND + idMaintenance);
        }
        maintenanceRepository.deleteById(idMaintenance);
    }

    @Override
    public List<MaintenanceDTO> addMaintenances(List<MaintenanceDTO> maintenanceDTOs) {
        List<Maintenance> entities = maintenanceMapper.toEntityList(maintenanceDTOs);
        List<Maintenance> savedList = maintenanceRepository.saveAll(entities);
        return maintenanceMapper.toDtoList(savedList);
    }
}
