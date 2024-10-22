package pe.edu.cibertec.app_circuitbreaker_ef.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.app_circuitbreaker_ef.model.Cliente;
import pe.edu.cibertec.app_circuitbreaker_ef.model.Reserva;
import pe.edu.cibertec.app_circuitbreaker_ef.service.IClienteService;
import pe.edu.cibertec.app_circuitbreaker_ef.service.IReservaService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class ReservaService implements IReservaService {

    private final IClienteService clienteService;

    private int cantidad_maxima = 100;
    private final List<Reserva> reservas = new ArrayList<>();

    public ReservaService(IClienteService clienteService) {
        this.clienteService = clienteService;
        reservas.add(new Reserva("1", 1,  convertirALocalDate("2024-10-21"), "19:00", 4));
        reservas.add(new Reserva("2", 1,  convertirALocalDate("2024-10-21"), "20:00", 2));
    }

    @Override
    public String registrarReserva(Reserva reserva){
        Cliente c1 = clienteService.obtenerClientePorId(reserva.getIdcliente()).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        int totalPersonas = obtenerTotalPersonasPorFecha(reserva.getFecha());
        if(totalPersonas==cantidad_maxima){
            throw new RuntimeException("No hay disponibilidad para la fecha seleccionada");
        }
        //Simulando un error en la generacion de la reserva para probar Circuit Breaker
        if(Math.random() >0.5){
            throw new RuntimeException("En estos momentos estamos tardando en generar la reserva, porfavor reintente en unos minutos");
        }
        reservas.add(reserva);
        cantidad_maxima -= reserva.getNumeroPersonas();

        return "Reserva con ID: "+reserva.getId() + " generada para el cliente: "+c1.getNombre() + " " +
                c1.getApellido() + " se ha generado correctamente para el dia: "+new SimpleDateFormat("dd-MM-yyyy").format(reserva.getFecha());
    }

    private Date convertirALocalDate(String fecha) {
        LocalDate localDate = LocalDate.parse(fecha);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public int obtenerTotalPersonasPorFecha(Date fecha) {
        return reservas.stream()
                .filter(reserva -> reserva.getFecha().equals(fecha))
                .mapToInt(Reserva::getNumeroPersonas)
                .sum();
    }


}
