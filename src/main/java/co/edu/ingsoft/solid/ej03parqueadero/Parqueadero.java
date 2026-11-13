package co.edu.ingsoft.solid.ej03parqueadero;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parqueadero {

    private final Map<String, Vehiculo> vehiculos = new HashMap<>();
    private final List<Bicicleta> bicicletas = new ArrayList<>();
    private double caja;

    public void registrarIngreso(Vehiculo v) {
        if (v instanceof Bicicleta b) {
            bicicletas.add(b);
            return;
        }
        if (vehiculos.containsKey(v.getPlaca())) {
            throw new IllegalStateException("La placa " + v.getPlaca() + " ya está dentro");
        }
        vehiculos.put(v.getPlaca(), v);
    }

    public double registrarSalida(String placa, LocalDateTime salida) {
        Vehiculo v = vehiculos.remove(placa);
        if (v == null) {
            throw new IllegalArgumentException("No hay un vehículo con placa " + placa);
        }
        double cobro = v.calcularCobro(salida);
        caja += cobro;
        return cobro;
    }

    public void liberarCicloparqueadero() {
        if (!bicicletas.isEmpty()) {
            bicicletas.remove(0);
        }
    }

    public double ingresoProyectado(LocalDateTime hasta) {
        return vehiculos.values().stream()
                .mapToDouble(v -> v.calcularCobro(hasta))
                .sum();
    }

    public List<String> estadoActual(LocalDateTime hasta) {
        List<Vehiculo> todos = new ArrayList<>(vehiculos.values());
        todos.addAll(bicicletas);
        List<String> lineas = new ArrayList<>();
        for (Vehiculo v : todos) {
            lineas.add(v.getClass().getSimpleName() + " " + v.getPlaca()
                    + " -> $" + String.format("%,.0f", v.calcularCobro(hasta)));
        }
        return lineas;
    }

    public double getCaja() {
        return caja;
    }
}
