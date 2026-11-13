package co.edu.ingsoft.solid.ej03parqueadero;

import java.time.Duration;
import java.time.LocalDateTime;

public abstract class Vehiculo {

    private final String placa;
    private final LocalDateTime ingreso;

    protected Vehiculo(String placa, LocalDateTime ingreso) {
        this.placa = placa;
        this.ingreso = ingreso;
    }

    public String getPlaca()          { return placa; }
    public LocalDateTime getIngreso() { return ingreso; }

    protected abstract double tarifaPorHora();

    /**
     * Valor a cobrar por la permanencia hasta {@code salida}.
     * Se cobra por hora o fracción, con un mínimo de una hora.
     * El resultado nunca es negativo.
     */
    public double calcularCobro(LocalDateTime salida) {
        long minutos = Duration.between(ingreso, salida).toMinutes();
        long horas = Math.max(1, (minutos + 59) / 60);
        return horas * tarifaPorHora();
    }
}
