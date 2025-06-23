package utez.edu.mx.almacenes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.almacenes.model.Operacion;
import utez.edu.mx.almacenes.service.OperacionService;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/operaciones")
@CrossOrigin(origins = "*")
public class OperacionController {

    @Autowired
    private OperacionService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Operacion operacion) {
        StringBuilder errores = new StringBuilder();

        // Validar monto positivo
        if (operacion.getMonto() <= 0) {
            errores.append("El monto debe ser un número positivo. | ");
        }

        // Validar fecha no nula y no futura
        if (operacion.getFecha() == null) {
            errores.append("La fecha no puede estar vacía. | ");
        } else if (operacion.getFecha().isAfter(java.time.LocalDate.now())) {
            errores.append("La fecha no puede ser futura. | ");
        }

        // Validar tipo de operación
        if (operacion.getTipo() == null) {
            errores.append("El tipo de operación es obligatorio. | ");
        }

        // Validar que cliente y almacén no sean nulos
        if (operacion.getCliente() == null || operacion.getCliente().getId() == null) {
            errores.append("El cliente es obligatorio y debe tener un ID válido. | ");
        }

        if (operacion.getAlmacen() == null || operacion.getAlmacen().getId() == null) {
            errores.append("El almacén es obligatorio y debe tener un ID válido. | ");
        }

        if (!errores.toString().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of(
                    "message", "Error de validación",
                    "details", errores.toString()
            ));
        }

        try {
            Operacion result = service.registrarOperacion(operacion);
            return ResponseEntity.ok(Map.of(
                    "message", "Operación registrada correctamente",
                    "data", result
            ));
        } catch (RuntimeException e) {
            String msg = e.getMessage();
            Double expectedPrice = null;

            if (msg.contains("precio para")) {
                Pattern pattern = Pattern.compile("es (\\d+(\\.\\d{1,2})?)");
                Matcher matcher = pattern.matcher(msg);
                if (matcher.find()) {
                    expectedPrice = Double.valueOf(matcher.group(1));
                }
            }

            return ResponseEntity.badRequest().body(Map.of(
                    "message", msg,
                    "expectedPrice", expectedPrice
            ));
        }
    }


    @GetMapping
    public List<Operacion> obtenerTodas() {
        return service.obtenerTodas();
    }
}