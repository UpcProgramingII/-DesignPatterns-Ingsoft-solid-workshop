package co.edu.ingsoft.solid.ej03parqueadero;

import java.time.LocalDateTime;

public class CarroElectrico extends Carro {

    private static final double VALOR_KWH = 900;

    private double kwhCargados;

    public CarroElectrico(String placa, LocalDateTime ingreso) {
        super(placa, ingreso);
    }

    public void registrarCarga(double kwh) {
        if (kwh <= 0) {
            throw new IllegalArgumentException("La carga debe ser positiva");
        }
        kwhCargados += kwh;
    }

    @Override
    protected double tarifaPorHora() {
        return 3_000;
    }

    @Override
    public double calcularCobro(LocalDateTime salida) {
        return super.calcularCobro(salida) + kwhCargados * VALOR_KWH;
    }
}
