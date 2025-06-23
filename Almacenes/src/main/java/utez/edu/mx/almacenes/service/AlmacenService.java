package utez.edu.mx.almacenes.service;

import utez.edu.mx.almacenes.model.Almacen;
import java.util.List;
import java.util.Optional;

public interface AlmacenService {
    List<Almacen> findAll();
    Optional<Almacen> findById(Long id);
    Almacen save(Almacen almacen);
    Optional<Almacen> update(Long id, Almacen detalles);

    Almacen createAlmacen(Almacen almacen);

    List<Almacen> getAll();

    Almacen getById(Long id);

    Almacen updateAlmacen(Almacen almacen);

    boolean delete(Long id);
}
