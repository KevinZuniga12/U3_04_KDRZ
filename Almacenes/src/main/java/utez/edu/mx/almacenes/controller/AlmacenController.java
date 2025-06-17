package utez.edu.mx.almacenes.controller;

import utez.edu.mx.almacenes.model.Almacen;
import utez.edu.mx.almacenes.service.AlmacenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/almacenes")
public class AlmacenController {

    @Autowired
    private AlmacenService almacenService;

    @GetMapping
    public List<Almacen> getAllAlmacenes() {
        return almacenService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Almacen> getAlmacenById(@PathVariable Long id) {
        return almacenService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Almacen createAlmacen(@RequestBody Almacen almacen) {
        return almacenService.save(almacen);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Almacen> updateAlmacen(@PathVariable Long id, @RequestBody Almacen almacenDetails) {
        return almacenService.update(id, almacenDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlmacen(@PathVariable Long id) {
        if (almacenService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}