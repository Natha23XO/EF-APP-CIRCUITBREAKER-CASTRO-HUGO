package pe.edu.cibertec.app_circuitbreaker_ef.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.app_circuitbreaker_ef.service.OperacionService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/operacion")
public class OperacionController {
    private static final Logger logger = LoggerFactory.getLogger(OperacionController.class);

    private final OperacionService operacionService;

    @GetMapping()
    @CircuitBreaker(name = "myService", fallbackMethod = "fallback")
    public String metodoRemoto(){
        return operacionService.metodoRemoto();
    }

    public String fallback(Throwable t){
        logger.error("Fallback method called due to: ", t);
        return "Servicio temporalmente no disponible. Intente más tarde";
    }
}
