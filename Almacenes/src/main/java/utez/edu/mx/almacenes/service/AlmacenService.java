package utez.edu.mx.almacenes.service;

import utez.edu.mx.almacenes.model.Almacen;
import utez.edu.mx.almacenes.repository.AlmacenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlmacenService {

    @Autowired
    private AlmacenRepository almacenRepository;

    public List<Almacen> findAll() {
        return almacenRepository.findAll();
    }

    public Optional<Almacen> findById(Long id) {
        return almacenRepository.findById(id);
    }

    public Almacen save(Almacen almacen) {
        return almacenRepository.save(almacen);
    }

    public void deleteById(Long id) {
        almacenRepository.deleteById(id);
    }
}