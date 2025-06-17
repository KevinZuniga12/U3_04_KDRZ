package utez.edu.mx.almacenes.controller;

import utez.edu.mx.almacenes.model.Cede;
import utez.edu.mx.almacenes.service.CedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cedes")
public class CedeController {

    @Autowired
    private CedeService cedeService;

    @GetMapping
    public List<Cede> getAllCedes() {
        return cedeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cede> getCedeById(@PathVariable Long id) {
        return cedeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cede createCede(@RequestBody Cede cede) {
        return cedeService.save(cede);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cede> updateCede(@PathVariable Long id, @RequestBody Cede cedeDetails) {
        return cedeService.update(id, cedeDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCede(@PathVariable Long id) {
        if (cedeService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}