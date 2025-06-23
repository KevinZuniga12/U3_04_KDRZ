package utez.edu.mx.almacenes.service;

import utez.edu.mx.almacenes.model.Cede;
import utez.edu.mx.almacenes.repository.CedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CedeService {
    Cede createCede(Cede cede);
    Cede updateCede(Cede cede);
    List<Cede> getAllCedes();
    Cede getCedeById(Long id);
    void deleteCede(Long id);
}
