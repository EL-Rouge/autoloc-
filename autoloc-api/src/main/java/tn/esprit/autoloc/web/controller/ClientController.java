package tn.esprit.autoloc.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.InterfaceClientService;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private InterfaceClientService clientService;

    @GetMapping
    public List<Client> retrieveAllClients() {
        return clientService.retrieveAllClients();
    }

    @GetMapping("/{id}")
    public Client retrieveClient(@PathVariable("id") Long idClient) {
        return clientService.retrieveClient(idClient);
    }

    @PostMapping
    public Client addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }

    @PostMapping("/batch")
    public List<Client> addClients(@RequestBody List<Client> clients) {
        return clientService.addClients(clients);
    }

    @PutMapping
    public Client updateClient(@RequestBody Client client) {
        return clientService.updateClient(client);
    }

    @DeleteMapping("/{id}")
    public void removeClient(@PathVariable("id") Long idClient) {
        clientService.removeClient(idClient);
    }
}
