package co.edu.ingsoft.solid.ej04notas.validacion;

import co.edu.ingsoft.solid.ej04notas.modelo.INota;

public class ValidadorRango implements IValidadorNota {

    @Override
    public void validar(INota nota) {
        if (nota.getValor() < 0.0 || nota.getValor() > 5.0) {
            throw new IllegalArgumentException("La nota debe estar entre 0.0 y 5.0");
        }
    }
}
