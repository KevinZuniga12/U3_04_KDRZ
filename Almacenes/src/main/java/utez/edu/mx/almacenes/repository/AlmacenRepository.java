package utez.edu.mx.almacenes.repository;

import utez.edu.mx.almacenes.model.Almacen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlmacenRepository extends JpaRepository<Almacen, Long> {
}