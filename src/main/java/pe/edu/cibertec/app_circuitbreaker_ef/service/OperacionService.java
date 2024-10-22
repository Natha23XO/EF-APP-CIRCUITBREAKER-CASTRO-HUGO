package pe.edu.cibertec.app_circuitbreaker_ef.service;

import org.springframework.stereotype.Service;

@Service
public class OperacionService {

    //Service para provocar el error a proposito y probar la resilencia
    public String metodoRemoto(){
        if(Math.random() >0.5){
            throw new RuntimeException("Error en el llamada");
        }
        return "Repuesta Exitosa";
    }
}
