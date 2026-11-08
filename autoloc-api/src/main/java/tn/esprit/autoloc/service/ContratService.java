package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.dto.ContratDTO;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.mapper.ContratMapper;
import tn.esprit.autoloc.repository.ContratRepository;

import java.util.List;

@Service
public class ContratService implements InterfaceContratService {

    private static final String CONTRAT_NOT_FOUND = "Contrat non trouvé avec l'identifiant : ";

    private final ContratRepository contratRepository;
    private final ContratMapper contratMapper;

    public ContratService(ContratRepository contratRepository, ContratMapper contratMapper) {
        this.contratRepository = contratRepository;
        this.contratMapper = contratMapper;
    }

    @Override
    public List<ContratDTO> retrieveAllContrats() {
        return contratMapper.toDtoList(contratRepository.findAll());
    }

    @Override
    public ContratDTO addContrat(ContratDTO contratDTO) {
        Contrat entity = contratMapper.toEntity(contratDTO);
        Contrat saved = contratRepository.save(entity);
        return contratMapper.toDto(saved);
    }

    @Override
    public ContratDTO updateContrat(ContratDTO contratDTO) {
        if (contratDTO.getIdContrat() != null && !contratRepository.existsById(contratDTO.getIdContrat())) {
            throw new ResourceNotFoundException(CONTRAT_NOT_FOUND + contratDTO.getIdContrat());
        }
        Contrat entity = contratMapper.toEntity(contratDTO);
        Contrat updated = contratRepository.save(entity);
        return contratMapper.toDto(updated);
    }

    @Override
    public ContratDTO retrieveContrat(Long idContrat) {
        Contrat contrat = contratRepository.findById(idContrat)
                .orElseThrow(() -> new ResourceNotFoundException(CONTRAT_NOT_FOUND + idContrat));
        return contratMapper.toDto(contrat);
    }

    @Override
    public void removeContrat(Long idContrat) {
        if (!contratRepository.existsById(idContrat)) {
            throw new ResourceNotFoundException(CONTRAT_NOT_FOUND + idContrat);
        }
        contratRepository.deleteById(idContrat);
    }

    @Override
    public List<ContratDTO> addContrats(List<ContratDTO> contratDTOs) {
        List<Contrat> entities = contratMapper.toEntityList(contratDTOs);
        List<Contrat> savedList = contratRepository.saveAll(entities);
        return contratMapper.toDtoList(savedList);
    }
}
