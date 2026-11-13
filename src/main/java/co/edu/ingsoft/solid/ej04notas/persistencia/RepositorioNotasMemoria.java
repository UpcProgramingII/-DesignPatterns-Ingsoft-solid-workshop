package co.edu.ingsoft.solid.ej04notas.persistencia;

import co.edu.ingsoft.solid.ej04notas.modelo.INota;
import java.util.ArrayList;
import java.util.List;

public class RepositorioNotasMemoria implements IRepositorioNotas {

    private final List<INota> notas = new ArrayList<>();

    @Override
    public void guardar(INota nota) {
        notas.add(nota);
    }

    @Override
    public List<INota> obtenerTodas() {
        return List.copyOf(notas);
    }

    @Override
    public void limpiar() {
        notas.clear();
    }
}
