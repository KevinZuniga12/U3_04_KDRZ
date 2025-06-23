package utez.edu.mx.almacenes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.mx.almacenes.model.Cliente;
import utez.edu.mx.almacenes.repository.ClienteRepository;
import utez.edu.mx.almacenes.service.ClienteService;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente createCliente(Cliente cliente) {
        if (cliente.getId() != null) {
            throw new IllegalArgumentException("No se debe asignar ID al crear un nuevo cliente.");
        }
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    }

    @Override
    public void deleteCliente(Long id) {
        Cliente cliente = getClienteById(id);
        clienteRepository.delete(cliente);
    }
}
