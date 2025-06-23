package utez.edu.mx.almacenes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cede {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, unique = true)
    private String clave; // C[id]-[ddMMyyyy]-[aleatorio]

    @Column(nullable = false)
    @NotBlank(message = "El estado no puede estar vacío")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúñÑ ]{2,50}$", message = "El estado debe contener solo letras y tener entre 2 y 50 caracteres")
    private String estado;

    @Column(nullable = false)
    @NotBlank(message = "El municipio no puede estar vacío")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúñÑ ]{2,50}$", message = "El municipio debe contener solo letras y tener entre 2 y 50 caracteres")
    private String municipio;

}