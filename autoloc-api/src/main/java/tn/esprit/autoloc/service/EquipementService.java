package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.dto.EquipementDTO;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.mapper.EquipementMapper;
import tn.esprit.autoloc.repository.EquipementRepository;

import java.util.List;

@Service
public class EquipementService implements InterfaceEquipementService {

    private static final String EQUIPEMENT_NOT_FOUND = "Équipement non trouvé avec l'identifiant : ";

    private final EquipementRepository equipementRepository;
    private final EquipementMapper equipementMapper;

    public EquipementService(EquipementRepository equipementRepository, EquipementMapper equipementMapper) {
        this.equipementRepository = equipementRepository;
        this.equipementMapper = equipementMapper;
    }

    @Override
    public List<EquipementDTO> retrieveAllEquipements() {
        return equipementMapper.toDtoList(equipementRepository.findAll());
    }

    @Override
    public EquipementDTO addEquipement(EquipementDTO equipementDTO) {
        Equipement entity = equipementMapper.toEntity(equipementDTO);
        Equipement saved = equipementRepository.save(entity);
        return equipementMapper.toDto(saved);
    }

    @Override
    public EquipementDTO updateEquipement(EquipementDTO equipementDTO) {
        if (equipementDTO.getIdEquipement() != null && !equipementRepository.existsById(equipementDTO.getIdEquipement())) {
            throw new ResourceNotFoundException(EQUIPEMENT_NOT_FOUND + equipementDTO.getIdEquipement());
        }
        Equipement entity = equipementMapper.toEntity(equipementDTO);
        Equipement updated = equipementRepository.save(entity);
        return equipementMapper.toDto(updated);
    }

    @Override
    public EquipementDTO retrieveEquipement(Long idEquipement) {
        Equipement equipement = equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new ResourceNotFoundException(EQUIPEMENT_NOT_FOUND + idEquipement));
        return equipementMapper.toDto(equipement);
    }

    @Override
    public void removeEquipement(Long idEquipement) {
        if (!equipementRepository.existsById(idEquipement)) {
            throw new ResourceNotFoundException(EQUIPEMENT_NOT_FOUND + idEquipement);
        }
        equipementRepository.deleteById(idEquipement);
    }

    @Override
    public List<EquipementDTO> addEquipements(List<EquipementDTO> equipementDTOs) {
        List<Equipement> entities = equipementMapper.toEntityList(equipementDTOs);
        List<Equipement> savedList = equipementRepository.saveAll(entities);
        return equipementMapper.toDtoList(savedList);
    }
}
