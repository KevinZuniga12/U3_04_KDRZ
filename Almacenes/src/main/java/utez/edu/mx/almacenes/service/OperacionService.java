package utez.edu.mx.almacenes.service;

import utez.edu.mx.almacenes.model.Operacion;

import java.util.List;


public interface OperacionService {
    Operacion registrarOperacion(Operacion operacion);
    List<Operacion> obtenerTodas();
}
