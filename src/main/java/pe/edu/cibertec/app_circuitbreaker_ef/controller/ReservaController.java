package pe.edu.cibertec.app_circuitbreaker_ef.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.app_circuitbreaker_ef.model.Reserva;
import pe.edu.cibertec.app_circuitbreaker_ef.service.impl.ReservaService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/reserva")
public class ReservaController {
    private final ReservaService reservaService;

    @PostMapping()
    @CircuitBreaker(name = "myService", fallbackMethod = "errorGenerarReserva")
    public String registrarReserva(@RequestBody Reserva reserva){
        return reservaService.registrarReserva(reserva);
    }

    public String errorGenerarReserva(Throwable t){
        return "En estos momentos estamos tardando en generar la reserva, porfavor reintente en unos minutos.";
    }
}
