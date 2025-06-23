package utez.edu.mx.almacenes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.mx.almacenes.model.Cede;
import utez.edu.mx.almacenes.repository.CedeRepository;
import utez.edu.mx.almacenes.service.CedeService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class CedeServiceImpl implements CedeService {

    @Autowired
    private CedeRepository repository;

    @Override
    public Cede createCede(Cede cede) {
        // Primero se guarda sin la clave (clave será null)
        Cede saved = repository.save(cede);

        // Ahora que ya tiene ID, generamos la clave con el formato C[id]-[ddMMyyyy]-[4 dígitos aleatorios]
        String fecha = new SimpleDateFormat("ddMMyyyy").format(new Date());
        int aleatorio = new Random().nextInt(9000) + 1000;
        String clave = "C" + saved.getId() + "-" + fecha + "-" + aleatorio;

        saved.setClave(clave);
        return repository.save(saved); // Actualizamos la clave en BD
    }

    private String generarClaveCede(Long id) {
        String fecha = new SimpleDateFormat("ddMMyyyy").format(new Date());
        int aleatorio = new Random().nextInt(9000) + 1000;
        return "C" + id + "-" + fecha + "-" + aleatorio;
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