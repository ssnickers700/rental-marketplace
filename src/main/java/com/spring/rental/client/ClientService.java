package com.spring.rental.client;

import com.spring.rental.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("client", id));
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(Long id, Client updatedClient) {
        if (!clientRepository.existsById(id)) {
            throw new NotFoundException("client", id);
        }
        updatedClient.setId(id);
        clientRepository.save(updatedClient);
        return updatedClient;
    }

    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new NotFoundException("client", id);
        }
        clientRepository.deleteById(id);
    }

}
