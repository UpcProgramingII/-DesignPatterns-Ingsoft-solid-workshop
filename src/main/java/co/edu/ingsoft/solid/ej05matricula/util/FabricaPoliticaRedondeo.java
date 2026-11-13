package co.edu.ingsoft.solid.ej05matricula.util;

public final class FabricaPoliticaRedondeo {

    private static FabricaPoliticaRedondeo instancia;

    private FabricaPoliticaRedondeo() {
    }

    public static synchronized FabricaPoliticaRedondeo obtenerInstancia() {
        if (instancia == null) {
            instancia = new FabricaPoliticaRedondeo();
        }
        return instancia;
    }

    public IPoliticaRedondeo crear() {
        return new RedondeoAlPesoMasCercano();
    }
}
