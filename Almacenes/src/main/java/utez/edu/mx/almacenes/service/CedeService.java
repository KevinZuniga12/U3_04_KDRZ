package utez.edu.mx.almacenes.service;

import utez.edu.mx.almacenes.model.Cede;
import utez.edu.mx.almacenes.repository.CedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CedeService {

    @Autowired
    private CedeRepository cedeRepository;

    public List<Cede> findAll() {
        return cedeRepository.findAll();
    }

    public Optional<Cede> findById(Long id) {
        return cedeRepository.findById(id);
    }

    public Cede save(Cede cede) {
        return cedeRepository.save(cede);
    }

    public Optional<Cede> update(Long id, Cede cedeDetails) {
        return cedeRepository.findById(id).map(cede -> {
            cede.setClave(cedeDetails.getClave());
            cede.setEstado(cedeDetails.getEstado());
            cede.setMunicipio(cedeDetails.getMunicipio());
            return cedeRepository.save(cede);
        });
    }

    public boolean delete(Long id) {
        return cedeRepository.findById(id).map(cede -> {
            cedeRepository.delete(cede);
            return true;
        }).orElse(false);
    }
}
