package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.dto.ClientDTO;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.mapper.ClientMapper;
import tn.esprit.autoloc.repository.ClientRepository;

import java.util.List;

@Service
public class ClientService implements InterfaceClientService {

    private static final String CLIENT_NOT_FOUND = "Client non trouvé avec l'identifiant : ";

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public List<ClientDTO> retrieveAllClients() {
        return clientMapper.toDtoList(clientRepository.findAll());
    }

    @Override
    public ClientDTO addClient(ClientDTO clientDTO) {
        Client entity = clientMapper.toEntity(clientDTO);
        Client saved = clientRepository.save(entity);
        return clientMapper.toDto(saved);
    }

    @Override
    public ClientDTO updateClient(ClientDTO clientDTO) {
        if (clientDTO.getIdClient() != null && !clientRepository.existsById(clientDTO.getIdClient())) {
            throw new ResourceNotFoundException(CLIENT_NOT_FOUND + clientDTO.getIdClient());
        }
        Client entity = clientMapper.toEntity(clientDTO);
        Client updated = clientRepository.save(entity);
        return clientMapper.toDto(updated);
    }

    @Override
    public ClientDTO retrieveClient(Long idClient) {
        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new ResourceNotFoundException(CLIENT_NOT_FOUND + idClient));
        return clientMapper.toDto(client);
    }

    @Override
    public void removeClient(Long idClient) {
        if (!clientRepository.existsById(idClient)) {
            throw new ResourceNotFoundException(CLIENT_NOT_FOUND + idClient);
        }
        clientRepository.deleteById(idClient);
    }

    @Override
    public List<ClientDTO> addClients(List<ClientDTO> clientDTOs) {
        List<Client> entities = clientMapper.toEntityList(clientDTOs);
        List<Client> savedList = clientRepository.saveAll(entities);
        return clientMapper.toDtoList(savedList);
    }
}
