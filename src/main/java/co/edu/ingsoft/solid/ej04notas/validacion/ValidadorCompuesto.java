package co.edu.ingsoft.solid.ej04notas.validacion;

import co.edu.ingsoft.solid.ej04notas.modelo.INota;
import java.util.List;

public class ValidadorCompuesto implements IValidadorNota {

    private final List<IValidadorNota> validadores;

    public ValidadorCompuesto(List<IValidadorNota> validadores) {
        this.validadores = List.copyOf(validadores);
    }

    @Override
    public void validar(INota nota) {
        validadores.forEach(v -> v.validar(nota));
    }
}
