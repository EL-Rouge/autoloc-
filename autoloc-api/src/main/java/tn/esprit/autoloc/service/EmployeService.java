package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.dto.EmployeDTO;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.mapper.EmployeMapper;
import tn.esprit.autoloc.repository.EmployeRepository;

import java.util.List;

@Service
public class EmployeService implements InterfaceEmployeService {

    private static final String EMPLOYE_NOT_FOUND = "Employé non trouvé avec l'identifiant : ";

    private final EmployeRepository employeRepository;
    private final EmployeMapper employeMapper;

    public EmployeService(EmployeRepository employeRepository, EmployeMapper employeMapper) {
        this.employeRepository = employeRepository;
        this.employeMapper = employeMapper;
    }

    @Override
    public List<EmployeDTO> retrieveAllEmployes() {
        return employeMapper.toDtoList(employeRepository.findAll());
    }

    @Override
    public EmployeDTO addEmploye(EmployeDTO employeDTO) {
        Employe entity = employeMapper.toEntity(employeDTO);
        Employe saved = employeRepository.save(entity);
        return employeMapper.toDto(saved);
    }

    @Override
    public EmployeDTO updateEmploye(EmployeDTO employeDTO) {
        if (employeDTO.getIdEmploye() != null && !employeRepository.existsById(employeDTO.getIdEmploye())) {
            throw new ResourceNotFoundException(EMPLOYE_NOT_FOUND + employeDTO.getIdEmploye());
        }
        Employe entity = employeMapper.toEntity(employeDTO);
        Employe updated = employeRepository.save(entity);
        return employeMapper.toDto(updated);
    }

    @Override
    public EmployeDTO retrieveEmploye(Long idEmploye) {
        Employe employe = employeRepository.findById(idEmploye)
                .orElseThrow(() -> new ResourceNotFoundException(EMPLOYE_NOT_FOUND + idEmploye));
        return employeMapper.toDto(employe);
    }

    @Override
    public void removeEmploye(Long idEmploye) {
        if (!employeRepository.existsById(idEmploye)) {
            throw new ResourceNotFoundException(EMPLOYE_NOT_FOUND + idEmploye);
        }
        employeRepository.deleteById(idEmploye);
    }

    @Override
    public List<EmployeDTO> addEmployes(List<EmployeDTO> employeDTOs) {
        List<Employe> entities = employeMapper.toEntityList(employeDTOs);
        List<Employe> savedList = employeRepository.saveAll(entities);
        return employeMapper.toDtoList(savedList);
    }
}
