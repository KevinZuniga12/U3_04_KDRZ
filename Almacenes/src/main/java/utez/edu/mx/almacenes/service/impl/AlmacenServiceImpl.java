package utez.edu.mx.almacenes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.mx.almacenes.model.Almacen;
import utez.edu.mx.almacenes.model.Cede;
import utez.edu.mx.almacenes.repository.AlmacenRepository;
import utez.edu.mx.almacenes.repository.CedeRepository;
import utez.edu.mx.almacenes.service.AlmacenService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AlmacenServiceImpl implements AlmacenService {

    @Autowired
    private AlmacenRepository repository;

    @Autowired
    private CedeRepository cedeRepository;

    @Override
    public List<Almacen> findAll() {
        return List.of();
    }

    @Override
    public Optional<Almacen> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Almacen save(Almacen almacen) {
        return null;
    }

    @Override
    public Optional<Almacen> update(Long id, Almacen detalles) {
        return Optional.empty();
    }

    @Override
    public Almacen createAlmacen(Almacen almacen) {
        almacen.setFechaRegistro(LocalDate.now());

        Cede cede = cedeRepository.findById(almacen.getCede().getId())
                .orElseThrow(() -> new RuntimeException("Cede no encontrada"));

        almacen.setCede(cede);

        Almacen saved = repository.save(almacen); // primero se guarda para tener el id

        String clave = cede.getClave() + "-A" + saved.getId();
        saved.setClave(clave);

        return repository.save(saved); // segundo guardado con clave
    }

    @Override
    public List<Almacen> getAll() {
        return repository.findAll();
    }

    @Override
    public Almacen getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Almacén no encontrado"));
    }

    @Override
    public Almacen updateAlmacen(Almacen almacen) {
        // Solo actualizar precio, tamaño y cede
        Cede cede = cedeRepository.findById(almacen.getCede().getId())
                .orElseThrow(() -> new RuntimeException("Cede no encontrada"));
        almacen.setCede(cede); // actualiza la relación si cambió
        return repository.save(almacen); // no se toca clave ni fecha
    }

    @Override
    public boolean delete(Long id) {
        repository.deleteById(id);
        return false;
    }
}