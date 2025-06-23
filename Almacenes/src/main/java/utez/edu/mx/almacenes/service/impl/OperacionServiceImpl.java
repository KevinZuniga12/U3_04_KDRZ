package utez.edu.mx.almacenes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.mx.almacenes.model.Almacen;
import utez.edu.mx.almacenes.model.Cliente;
import utez.edu.mx.almacenes.model.Operacion;
import utez.edu.mx.almacenes.model.TipoOperacion;
import utez.edu.mx.almacenes.repository.AlmacenRepository;
import utez.edu.mx.almacenes.repository.ClienteRepository;
import utez.edu.mx.almacenes.repository.OperacionRepository;
import utez.edu.mx.almacenes.service.OperacionService;

import java.time.LocalDate;
import java.util.List;

@Service
public class OperacionServiceImpl implements OperacionService {

    @Autowired
    private OperacionRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AlmacenRepository almacenRepository;

    @Override
    public Operacion registrarOperacion(Operacion operacion) {
        Almacen almacen = almacenRepository.findById(operacion.getAlmacen().getId())
                .orElseThrow(() -> new RuntimeException("Almacén no encontrado"));

        if (!"DISPONIBLE".equalsIgnoreCase(almacen.getEstadoAlmacen())) {
            throw new RuntimeException("El almacén ya ha sido rentado o vendido");
        }

        double precioEsperado = operacion.getTipo() == TipoOperacion.RENTA
                ? almacen.getPrecioRenta()
                : almacen.getPrecioVenta();

        if (Double.compare(operacion.getMonto(), precioEsperado) != 0) {
            String tipo = operacion.getTipo().toString().toLowerCase(); // renta o venta
            throw new RuntimeException(String.format(
                    "El monto no es válido. El precio para %s del almacén es %.2f.",
                    tipo, precioEsperado
            ));
        }

        Cliente cliente = clienteRepository.findById(operacion.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        operacion.setCliente(cliente);
        operacion.setAlmacen(almacen);
        operacion.setFecha(LocalDate.now());

        if (operacion.getTipo() == TipoOperacion.RENTA) {
            almacen.setEstadoAlmacen("RENTADO");
        } else {
            almacen.setEstadoAlmacen("VENDIDO");
        }

        almacenRepository.save(almacen);
        return repository.save(operacion);
    }


    @Override
    public List<Operacion> obtenerTodas() {
        return repository.findAll();
    }
}