package pe.edu.cibertec.app_circuitbreaker_ef.service;

import pe.edu.cibertec.app_circuitbreaker_ef.model.Cliente;

import java.util.Optional;

public interface IClienteService {
    Optional<Cliente> obtenerClientePorId(int id);
}
