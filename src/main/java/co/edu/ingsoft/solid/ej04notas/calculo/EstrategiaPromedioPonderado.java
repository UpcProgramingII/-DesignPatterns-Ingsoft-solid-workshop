package co.edu.ingsoft.solid.ej04notas.calculo;

import co.edu.ingsoft.solid.ej04notas.modelo.INota;
import java.util.List;

public class EstrategiaPromedioPonderado implements IEstrategiaCalculo {

    @Override
    public double calcular(List<INota> notas) {
        return notas.stream()
                .mapToDouble(n -> n.getValor() * n.getPeso())
                .sum();
    }
}
