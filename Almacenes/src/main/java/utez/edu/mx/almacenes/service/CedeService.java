package utez.edu.mx.almacenes.service;

import utez.edu.mx.almacenes.model.Cede;
import utez.edu.mx.almacenes.repository.CedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CedeService {

    private final CedeRepository cedeRepository;

    @Autowired
    public CedeService(CedeRepository cedeRepository) {
        this.cedeRepository = cedeRepository;
    }

    public List<Cede> findAll() {
        return cedeRepository.findAll();
    }

    public Optional<Cede> findById(Long id) {
        return cedeRepository.findById(id);
    }

    public Cede save(Cede cede) {
        return cedeRepository.save(cede);
    }

    public void deleteById(Long id) {
        cedeRepository.deleteById(id);
    }
}