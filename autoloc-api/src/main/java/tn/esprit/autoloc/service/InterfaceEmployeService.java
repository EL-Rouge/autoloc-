package tn.esprit.autoloc.service;

import tn.esprit.autoloc.dto.EmployeDTO;

import java.util.List;

public interface InterfaceEmployeService {
    List<EmployeDTO> retrieveAllEmployes();
    EmployeDTO addEmploye(EmployeDTO employeDTO);
    EmployeDTO updateEmploye(EmployeDTO employeDTO);
    EmployeDTO retrieveEmploye(Long idEmploye);
    void removeEmploye(Long idEmploye);
    List<EmployeDTO> addEmployes(List<EmployeDTO> employeDTOs);
}
