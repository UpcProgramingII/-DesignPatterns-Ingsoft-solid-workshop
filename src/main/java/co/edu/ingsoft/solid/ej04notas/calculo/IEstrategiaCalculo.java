package co.edu.ingsoft.solid.ej04notas.calculo;

import co.edu.ingsoft.solid.ej04notas.modelo.INota;
import java.util.List;

public interface IEstrategiaCalculo {

    double calcular(List<INota> notas);
}
