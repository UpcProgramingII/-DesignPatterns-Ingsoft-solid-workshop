package co.edu.ingsoft.solid.ej04notas.validacion;

import co.edu.ingsoft.solid.ej04notas.modelo.INota;

public class ValidadorPeso implements IValidadorNota {

    @Override
    public void validar(INota nota) {
        if (nota.getPeso() <= 0.0 || nota.getPeso() > 1.0) {
            throw new IllegalArgumentException("El peso debe estar entre 0 y 1");
        }
    }
}
