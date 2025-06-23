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
    private ClienteRepository repository;

    @Override
    public Cliente createCliente(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public List<Cliente> getAllClientes() {
        return repository.findAll();
    }

    @Override
    public Cliente getClienteById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    @Override
    public void deleteCliente(Long id) {
        repository.deleteById(id);
    }
}