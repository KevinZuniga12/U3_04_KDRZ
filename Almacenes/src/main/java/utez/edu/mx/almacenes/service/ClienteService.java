package utez.edu.mx.almacenes.service;

import utez.edu.mx.almacenes.model.Cliente;
import utez.edu.mx.almacenes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    Cliente createCliente(Cliente cliente);
    List<Cliente> getAllClientes();
    Cliente getClienteById(Long id);
    void deleteCliente(Long id);
}
