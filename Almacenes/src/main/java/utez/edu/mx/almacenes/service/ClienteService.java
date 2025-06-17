package utez.edu.mx.almacenes.service;

import utez.edu.mx.almacenes.model.Cliente;
import utez.edu.mx.almacenes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> update(Long id, Cliente clienteDetails) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setNombreCompleto(clienteDetails.getNombreCompleto());
            cliente.setNumeroTelefono(clienteDetails.getNumeroTelefono());
            cliente.setCorreoElectronico(clienteDetails.getCorreoElectronico());
            return clienteRepository.save(cliente);
        });
    }

    public boolean delete(Long id) {
        return clienteRepository.findById(id).map(cliente -> {
            clienteRepository.delete(cliente);
            return true;
        }).orElse(false);
    }
}
