package utez.edu.mx.almacenes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.mx.almacenes.model.Cede;
import utez.edu.mx.almacenes.repository.CedeRepository;
import utez.edu.mx.almacenes.service.CedeService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class CedeServiceImpl implements CedeService {

    @Autowired
    private CedeRepository repository;

    @Override
    public Cede createCede(Cede cede) {
        // 1. Guardar sin clave (temporalmente)
        cede.setClave("TEMP"); // Evita errores por null
        Cede saved = repository.save(cede);

        // 2. Generar la clave con ID + fecha + aleatorio
        String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        String aleatorio = String.format("%04d", new Random().nextInt(10000)); // 0000 a 9999
        String claveFinal = "C" + saved.getId() + "-" + fecha + "-" + aleatorio;

        // 3. Actualizar el registro con la clave real
        saved.setClave(claveFinal);
        return repository.save(saved); // Segunda persistencia con la clave ya generada
    }

    @Override
    public Cede updateCede(Cede cede) {
        return repository.save(cede); // Aquí no se toca la clave
    }


    private String generarClaveCede() {
        String fecha = new SimpleDateFormat("ddMMyyyy").format(new Date());
        int aleatorio = new Random().nextInt(9000) + 1000;
        return "C" + System.currentTimeMillis() + "-" + fecha + "-" + aleatorio;
    }

    @Override
    public List<Cede> getAllCedes() {
        return repository.findAll();
    }

    @Override
    public Cede getCedeById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cede no encontrada"));
    }

    @Override
    public void deleteCede(Long id) {
        repository.deleteById(id);
    }
}