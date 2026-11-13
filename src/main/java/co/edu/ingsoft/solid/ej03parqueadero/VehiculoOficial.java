package co.edu.ingsoft.solid.ej03parqueadero;

import java.time.LocalDateTime;

public class VehiculoOficial extends Carro {

    public static final double EXENTO = -1;

    public VehiculoOficial(String placa, LocalDateTime ingreso) {
        super(placa, ingreso);
    }

    @Override
    public double calcularCobro(LocalDateTime salida) {
        return EXENTO;
    }
}
