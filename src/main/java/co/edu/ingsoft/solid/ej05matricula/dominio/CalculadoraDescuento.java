package co.edu.ingsoft.solid.ej05matricula.dominio;

import co.edu.ingsoft.solid.ej05matricula.util.FabricaPoliticaRedondeo;
import co.edu.ingsoft.solid.ej05matricula.util.IPoliticaRedondeo;

public class CalculadoraDescuento {

    private static final double TOPE = 0.70;

    public double calcular(IEstudiante e, double valorBase) {
        double porcentaje = 0;
        if (e.getEstrato() <= 2) {
            porcentaje = 0.50;
        } else if (e.getEstrato() == 3) {
            porcentaje = 0.25;
        }
        if (e.tieneHermanoMatriculado()) {
            porcentaje += 0.10;
        }
        if ("ALCALDIA".equals(e.getConvenio())) {
            porcentaje += 0.20;
        } else if ("EMPRESA_ALIADA".equals(e.getConvenio())) {
            porcentaje += 0.15;
        }
        porcentaje = Math.min(porcentaje, TOPE);

        IPoliticaRedondeo redondeo = FabricaPoliticaRedondeo.obtenerInstancia().crear();
        return redondeo.redondear(valorBase * porcentaje);
    }
}
