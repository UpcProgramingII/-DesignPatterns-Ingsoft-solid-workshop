package co.edu.ingsoft.solid.ej03parqueadero;

import java.time.LocalDateTime;

public class DemoParqueadero {

    public static void main(String[] args) {
        LocalDateTime ocho = LocalDateTime.of(2026, 11, 13, 8, 0);
        LocalDateTime once = ocho.plusHours(3);

        Parqueadero parqueadero = new Parqueadero();
        parqueadero.registrarIngreso(new Carro("ABC123", ocho));
        parqueadero.registrarIngreso(new Moto("XYZ45F", ocho));
        parqueadero.registrarIngreso(new VehiculoOficial("OFI001", ocho));
        CarroElectrico electrico = new CarroElectrico("ELE777", ocho);
        electrico.registrarCarga(12.5);
        parqueadero.registrarIngreso(electrico);
        parqueadero.registrarIngreso(new Bicicleta(ocho));
        parqueadero.registrarIngreso(new Bicicleta(ocho));

        System.out.printf("Ingreso proyectado a las 11:00: $%,.0f%n", parqueadero.ingresoProyectado(once));

        for (String placa : new String[] {"ABC123", "XYZ45F", "OFI001", "ELE777"}) {
            double cobro = parqueadero.registrarSalida(placa, once);
            System.out.printf("Salida %s: cobro $%,.0f | caja $%,.0f%n", placa, cobro, parqueadero.getCaja());
        }

        System.out.println("\nEstado actual del parqueadero:");
        try {
            parqueadero.estadoActual(once).forEach(l -> System.out.println("  " + l));
        } catch (RuntimeException e) {
            System.out.println("  !! " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }
}
