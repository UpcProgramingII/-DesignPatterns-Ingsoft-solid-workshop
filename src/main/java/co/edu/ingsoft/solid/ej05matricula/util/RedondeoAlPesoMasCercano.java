package co.edu.ingsoft.solid.ej05matricula.util;

public class RedondeoAlPesoMasCercano implements IPoliticaRedondeo {

    @Override
    public double redondear(double valor) {
        return Math.round(valor);
    }
}
