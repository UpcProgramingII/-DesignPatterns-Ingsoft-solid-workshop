package co.edu.ingsoft.solid.ej04notas;

import co.edu.ingsoft.solid.ej04notas.servicio.IServicioDefinitiva;
import co.edu.ingsoft.solid.ej04notas.servicio.ServicioDefinitivaImpl;

public class AplicacionCalculadora {

    private static final double[] PESOS = {0.30, 0.30, 0.40};

    public static void main(String[] args) {
        double[] notas = args.length == 3
                ? new double[] {Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2])}
                : new double[] {3.2, 2.8, 3.5};

        IServicioDefinitiva servicio = new ServicioDefinitivaImpl();
        for (int i = 0; i < notas.length; i++) {
            servicio.registrarNota(notas[i], PESOS[i]);
        }
        System.out.println(servicio.calcularDefinitiva());
    }
}
