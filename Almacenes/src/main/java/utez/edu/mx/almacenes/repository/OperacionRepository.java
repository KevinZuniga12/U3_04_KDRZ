package utez.edu.mx.almacenes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.almacenes.model.Operacion;

import java.util.List;

public interface OperacionRepository extends JpaRepository<Operacion, Long> {
    List<Operacion> findByClienteId(Long clienteId);
}
