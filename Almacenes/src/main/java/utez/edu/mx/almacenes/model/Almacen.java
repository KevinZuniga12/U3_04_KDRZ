package utez.edu.mx.almacenes.model;

import jakarta.persistence.*; // Usar jakarta en Spring Boot 3+
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Almacen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, unique = true)
    private String clave; // Formato: [clave de la cede]-A[id]

    @Column(nullable = false)
    private LocalDate fechaRegistro;

    @Column(nullable = false)
    @Min(value = 1, message = "El precio de venta debe ser mayor a 0")
    private double precioVenta;

    @Column(nullable = false)
    @Min(value = 1, message = "El precio de renta debe ser mayor a 0")
    private double precioRenta;

    @Column(nullable = false)
    private String tamano; // G, M, P

    @ManyToOne(optional = false)
    @JoinColumn(name = "cede_id", nullable = false)
    private Cede cede;

    @Column(nullable = false)
    private String estadoAlmacen; // DISPONIBLE, RENTADO, VENDIDO
}