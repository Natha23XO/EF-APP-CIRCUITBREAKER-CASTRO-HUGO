package pe.edu.cibertec.app_circuitbreaker_ef.service.impl;

import org.springframework.stereotype.Service;
import pe.edu.cibertec.app_circuitbreaker_ef.model.Cliente;
import pe.edu.cibertec.app_circuitbreaker_ef.model.Reserva;
import pe.edu.cibertec.app_circuitbreaker_ef.service.IClienteService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements IClienteService {

    private final List<Cliente> clientes = new ArrayList<>();

    public ClienteService() {
        Cliente cliente1 = new Cliente(1, "Castro", "Hugo");
        Cliente cliente2 = new Cliente(2, "Leon", "Nathanael");

        clientes.add(cliente1);
        clientes.add(cliente2);
    }

    @Override
    public Optional<Cliente> obtenerClientePorId(int id) {
        return clientes.stream()
                .filter(cliente -> cliente.getId() == id)
                .findFirst();
    }
}
