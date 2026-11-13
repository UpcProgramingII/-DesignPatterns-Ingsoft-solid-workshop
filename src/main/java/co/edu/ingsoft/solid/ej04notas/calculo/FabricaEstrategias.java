package co.edu.ingsoft.solid.ej04notas.calculo;

public class FabricaEstrategias implements IFabricaEstrategias {

    @Override
    public IEstrategiaCalculo crear(TipoCalculo tipo) {
        return switch (tipo) {
            case PROMEDIO_PONDERADO -> new EstrategiaPromedioPonderado();
        };
    }
}
