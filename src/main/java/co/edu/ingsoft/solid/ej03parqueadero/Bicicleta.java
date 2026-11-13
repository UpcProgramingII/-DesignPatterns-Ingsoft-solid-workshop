package co.edu.ingsoft.solid.ej03parqueadero;

import java.time.LocalDateTime;

public class Bicicleta extends Vehiculo {

    public Bicicleta(LocalDateTime ingreso) {
        super(null, ingreso);
    }

    @Override
    protected double tarifaPorHora() {
        return 0;
    }

    @Override
    public double calcularCobro(LocalDateTime salida) {
        throw new IllegalStateException(
                "Las bicicletas no se cobran: use Parqueadero.liberarCicloparqueadero()");
    }
}
