package utez.edu.mx.almacenes.controller;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import utez.edu.mx.almacenes.model.Cede;
import utez.edu.mx.almacenes.service.CedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cedes")
@CrossOrigin(origins = "*")
public class CedeController {

    @Autowired
    private CedeService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Cede cede) {
        StringBuilder errores = new StringBuilder();

        // Validar que el estado no esté vacío y tenga solo letras y espacios (ej: "Morelos")
        if (cede.getEstado() == null || !cede.getEstado().matches("^[A-ZÁÉÍÓÚÑa-záéíóúñ\\s]{3,30}$")) {
            errores.append("Estado inválido. Solo letras y espacios, entre 3 y 30 caracteres. | ");
        }

        // Validar que el municipio no esté vacío y tenga solo letras y espacios (ej: "Cuernavaca")
        if (cede.getMunicipio() == null || !cede.getMunicipio().matches("^[A-ZÁÉÍÓÚÑa-záéíóúñ\\s]{3,30}$")) {
            errores.append("Municipio inválido. Solo letras y espacios, entre 3 y 30 caracteres. | ");
        }

        // Si hay errores, responder con 400
        if (!errores.toString().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of(
                    "message", "Error de validación",
                    "details", errores.toString()
            ));
        }

        try {
            Cede resultCede = service.createCede(cede);
            return ResponseEntity.ok(Map.of(
                    "message", "Cede registrada correctamente",
                    "data", resultCede
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "message", "Error al registrar cede: " + e.getMessage()
            ));
        }
    }


    @GetMapping
    public List<Cede> getAll() {
        return service.getAllCedes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cede> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCedeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Cede updated, BindingResult result) {
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
            Cede cede = service.getCedeById(id);
            cede.setEstado(updated.getEstado());
            cede.setMunicipio(updated.getMunicipio());
            Cede saved = service.updateCede(cede);
            return ResponseEntity.ok(Map.of(
                    "message", "Cede actualizada correctamente",
                    "data", saved
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "message", "Error al actualizar cede: " + e.getMessage()
            ));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.deleteCede(id);
            return ResponseEntity.ok(Map.of(
                    "message", "Cede eliminada correctamente"
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "message", "Error al eliminar cede: " + e.getMessage()
            ));
        }
    }
}