package utez.edu.mx.almacenes.repository;

import utez.edu.mx.almacenes.model.Cede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CedeRepository extends JpaRepository<Cede, Long> {
}