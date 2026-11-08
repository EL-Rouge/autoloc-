package tn.esprit.autoloc.service;

import tn.esprit.autoloc.dto.ClientDTO;

import java.util.List;

public interface InterfaceClientService {
    List<ClientDTO> retrieveAllClients();
    ClientDTO addClient(ClientDTO clientDTO);
    ClientDTO updateClient(ClientDTO clientDTO);
    ClientDTO retrieveClient(Long idClient);
    void removeClient(Long idClient);
    List<ClientDTO> addClients(List<ClientDTO> clientDTOs);
}
