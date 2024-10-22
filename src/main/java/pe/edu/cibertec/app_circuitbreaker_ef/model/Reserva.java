package pe.edu.cibertec.app_circuitbreaker_ef.model;

import lombok.Data;

import java.util.Date;

@Data
public class Reserva {

    private String id;
    private int idcliente;
    private Date fecha;
    private String hora;
    private int numeroPersonas;

    public Reserva( String id, int idcliente,Date fecha, String hora, int numeroPersonas ) {
        this.numeroPersonas = numeroPersonas;
        this.hora = hora;
        this.idcliente = idcliente;
        this.id = id;
        this.fecha = fecha;
    }
}
