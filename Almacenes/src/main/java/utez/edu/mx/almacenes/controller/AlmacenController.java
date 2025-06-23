package utez.edu.mx.almacenes.controller;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import utez.edu.mx.almacenes.model.Almacen;
import utez.edu.mx.almacenes.service.AlmacenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/almacenes")
@CrossOrigin(origins = "*")
public class AlmacenController {

    @Autowired
    private AlmacenService service;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Almacen almacen, BindingResult result) {
        if (result.hasErrors()) {
            String errorMsg = result.getFieldErrors().stream()
                    .map(e -> e.getField() + ": " + e.getDefaultMessage())
                    .collect(Collectors.joining(" | "));
            return ResponseEntity.badRequest().body(Map.of(
                    "message", "Error de validación",
                    "details", errorMsg
            ));
        }

        try {
            Almacen resultSave = service.createAlmacen(almacen);
            return ResponseEntity.ok().body(Map.of(
                    "message", "Almacén registrado correctamente",
                    "data", resultSave
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "message", "Error al registrar almacén: " + e.getMessage()
            ));
        }
    }

    @GetMapping
    public List<Almacen> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Almacen> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Almacen updated, BindingResult result) {
        if (result.hasErrors()) {
            String errorMsg = result.getFieldErrors().stream()
                    .map(e -> e.getField() + ": " + e.getDefaultMessage())
                    .collect(Collectors.joining(" | "));
            return ResponseEntity.badRequest().body(Map.of(
                    "message", "Error de validación",
                    "details", errorMsg
            ));
        }

        try {
            Almacen almacen = service.getById(id);
            almacen.setPrecioVenta(updated.getPrecioVenta());
            almacen.setPrecioRenta(updated.getPrecioRenta());
            almacen.setTamano(updated.getTamano());
            almacen.setCede(updated.getCede());

            Almacen saved = service.updateAlmacen(almacen);
            return ResponseEntity.ok(Map.of(
                    "message", "Almacén actualizado correctamente",
                    "data", saved
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "message", "Error al actualizar almacén: " + e.getMessage()
            ));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok(Map.of(
                    "message", "Almacén eliminado correctamente"
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "message", "Error al eliminar almacén: " + e.getMessage()
            ));
        }
    }
}