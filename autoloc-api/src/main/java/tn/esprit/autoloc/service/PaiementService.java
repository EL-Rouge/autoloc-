package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.dto.PaiementDTO;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.mapper.PaiementMapper;
import tn.esprit.autoloc.repository.PaiementRepository;

import java.util.List;

@Service
public class PaiementService implements InterfacePaiementService {

    private static final String PAIEMENT_NOT_FOUND = "Paiement non trouvé avec l'identifiant : ";

    private final PaiementRepository paiementRepository;
    private final PaiementMapper paiementMapper;

    public PaiementService(PaiementRepository paiementRepository, PaiementMapper paiementMapper) {
        this.paiementRepository = paiementRepository;
        this.paiementMapper = paiementMapper;
    }

    @Override
    public List<PaiementDTO> retrieveAllPaiements() {
        return paiementMapper.toDtoList(paiementRepository.findAll());
    }

    @Override
    public PaiementDTO addPaiement(PaiementDTO paiementDTO) {
        Paiement entity = paiementMapper.toEntity(paiementDTO);
        Paiement saved = paiementRepository.save(entity);
        return paiementMapper.toDto(saved);
    }

    @Override
    public PaiementDTO updatePaiement(PaiementDTO paiementDTO) {
        if (paiementDTO.getIdPaiement() != null && !paiementRepository.existsById(paiementDTO.getIdPaiement())) {
            throw new ResourceNotFoundException(PAIEMENT_NOT_FOUND + paiementDTO.getIdPaiement());
        }
        Paiement entity = paiementMapper.toEntity(paiementDTO);
        Paiement updated = paiementRepository.save(entity);
        return paiementMapper.toDto(updated);
    }

    @Override
    public PaiementDTO retrievePaiement(Long idPaiement) {
        Paiement paiement = paiementRepository.findById(idPaiement)
                .orElseThrow(() -> new ResourceNotFoundException(PAIEMENT_NOT_FOUND + idPaiement));
        return paiementMapper.toDto(paiement);
    }

    @Override
    public void removePaiement(Long idPaiement) {
        if (!paiementRepository.existsById(idPaiement)) {
            throw new ResourceNotFoundException(PAIEMENT_NOT_FOUND + idPaiement);
        }
        paiementRepository.deleteById(idPaiement);
    }

    @Override
    public List<PaiementDTO> addPaiements(List<PaiementDTO> paiementDTOs) {
        List<Paiement> entities = paiementMapper.toEntityList(paiementDTOs);
        List<Paiement> savedList = paiementRepository.saveAll(entities);
        return paiementMapper.toDtoList(savedList);
    }
}
