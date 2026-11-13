package co.edu.ingsoft.solid.ej05matricula;

import co.edu.ingsoft.solid.ej05matricula.dominio.CalculadoraDescuento;
import co.edu.ingsoft.solid.ej05matricula.dominio.Estudiante;
import co.edu.ingsoft.solid.ej05matricula.dominio.IEstudiante;

public class DemoMatricula {

    public static void main(String[] args) {
        double valorBase = 6_850_000;
        CalculadoraDescuento calculadora = new CalculadoraDescuento();

        IEstudiante[] estudiantes = {
            new Estudiante("2026101", "Valentina", 2, true, "ALCALDIA"),
            new Estudiante("2026102", "Santiago", 3, false, "EMPRESA_ALIADA"),
            new Estudiante("2026103", "Camila", 5, true, null),
        };
        for (IEstudiante e : estudiantes) {
            double descuento = calculadora.calcular(e, valorBase);
            System.out.printf("%-10s estrato %d -> descuento $%,.0f | a pagar $%,.0f%n",
                    e.getNombre(), e.getEstrato(), descuento, valorBase - descuento);
        }
        System.out.println("\nPara probar ServicioMatricula.liquidar() o pagar() se requiere una base de datos"
                + " PostgreSQL y conexión a la pasarela del Banco Andino.");
    }
}
