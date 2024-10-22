package pe.edu.cibertec.app_circuitbreaker_ef.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cliente {
    private int id;
    private String nombre;
    private String apellido;

    public Cliente(int id, String apellido, String nombre) {
        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
    }
}
