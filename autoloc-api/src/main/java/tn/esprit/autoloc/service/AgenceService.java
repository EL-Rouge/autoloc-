package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.dto.AgenceDTO;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.mapper.AgenceMapper;
import tn.esprit.autoloc.repository.AgenceRepository;

import java.util.List;

@Service
public class AgenceService implements InterfaceAgenceService {

    private static final String AGENCE_NOT_FOUND = "Agence non trouvée avec l'identifiant : ";

    private final AgenceRepository agenceRepository;
    private final AgenceMapper agenceMapper;

    public AgenceService(AgenceRepository agenceRepository, AgenceMapper agenceMapper) {
        this.agenceRepository = agenceRepository;
        this.agenceMapper = agenceMapper;
    }

    @Override
    public List<AgenceDTO> retrieveAllAgences() {
        return agenceMapper.toDtoList(agenceRepository.findAll());
    }

    @Override
    public AgenceDTO addAgence(AgenceDTO agenceDTO) {
        Agence entity = agenceMapper.toEntity(agenceDTO);
        Agence saved = agenceRepository.save(entity);
        return agenceMapper.toDto(saved);
    }

    @Override
    public AgenceDTO updateAgence(AgenceDTO agenceDTO) {
        if (agenceDTO.getIdAgence() != null && !agenceRepository.existsById(agenceDTO.getIdAgence())) {
            throw new ResourceNotFoundException(AGENCE_NOT_FOUND + agenceDTO.getIdAgence());
        }
        Agence entity = agenceMapper.toEntity(agenceDTO);
        Agence updated = agenceRepository.save(entity);
        return agenceMapper.toDto(updated);
    }

    @Override
    public AgenceDTO retrieveAgence(Long idAgence) {
        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new ResourceNotFoundException(AGENCE_NOT_FOUND + idAgence));
        return agenceMapper.toDto(agence);
    }

    @Override
    public void removeAgence(Long idAgence) {
        if (!agenceRepository.existsById(idAgence)) {
            throw new ResourceNotFoundException(AGENCE_NOT_FOUND + idAgence);
        }
        agenceRepository.deleteById(idAgence);
    }

    @Override
    public List<AgenceDTO> addAgences(List<AgenceDTO> agenceDTOs) {
        List<Agence> entities = agenceMapper.toEntityList(agenceDTOs);
        List<Agence> savedList = agenceRepository.saveAll(entities);
        return agenceMapper.toDtoList(savedList);
    }
}
