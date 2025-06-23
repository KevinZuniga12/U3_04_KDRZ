package utez.edu.mx.almacenes.controller;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import utez.edu.mx.almacenes.model.Cliente;
import utez.edu.mx.almacenes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public List<Cliente> getAll() {
        return service.getAllClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getClienteById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {
        if (result.hasErrors()) {
            String errorMsg = result.getFieldErrors().stream()
                    .map(e -> e.getField() + ": " + e.getDefaultMessage())
                    .collect(Collectors.joining(" | "));
            return ResponseEntity.badRequest().body(Map.of("message", "Error de validación", "details", errorMsg));
        }

        try {
            Cliente saved = service.createCliente(cliente);
            return ResponseEntity.ok(Map.of("message", "Cliente registrado correctamente", "data", saved));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Error al registrar cliente: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Cliente updated, BindingResult result) {
        if (result.hasErrors()) {
            String errorMsg = result.getFieldErrors().stream()
                    .map(e -> e.getField() + ": " + e.getDefaultMessage())
                    .collect(Collectors.joining(" | "));
            return ResponseEntity.badRequest().body(Map.of("message", "Error de validación", "details", errorMsg));
        }

        try {
            Cliente cliente = service.getClienteById(id);
            cliente.setNombreCompleto(updated.getNombreCompleto());
            cliente.setTelefono(updated.getTelefono());
            cliente.setCorreo(updated.getCorreo());
            Cliente saved = service.createCliente(cliente);
            return ResponseEntity.ok(Map.of("message", "Cliente actualizado correctamente", "data", saved));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Error al actualizar cliente: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.deleteCliente(id);
            return ResponseEntity.ok(Map.of("message", "Cliente eliminado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Error al eliminar cliente: " + e.getMessage()));
        }
    }

}