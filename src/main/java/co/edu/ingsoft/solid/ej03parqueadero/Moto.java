package co.edu.ingsoft.solid.ej03parqueadero;

import java.time.LocalDateTime;

public class Moto extends Vehiculo {

    public Moto(String placa, LocalDateTime ingreso) {
        super(placa, ingreso);
    }

    @Override
    protected double tarifaPorHora() {
        return 2_200;
    }
}
