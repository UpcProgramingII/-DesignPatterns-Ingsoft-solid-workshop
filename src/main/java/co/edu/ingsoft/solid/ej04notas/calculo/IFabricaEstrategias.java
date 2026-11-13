package co.edu.ingsoft.solid.ej04notas.calculo;

public interface IFabricaEstrategias {

    IEstrategiaCalculo crear(TipoCalculo tipo);
}
