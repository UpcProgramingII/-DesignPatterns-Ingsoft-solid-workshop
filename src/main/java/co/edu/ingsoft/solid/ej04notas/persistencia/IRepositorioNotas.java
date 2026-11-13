package co.edu.ingsoft.solid.ej04notas.persistencia;

import co.edu.ingsoft.solid.ej04notas.modelo.INota;
import java.util.List;

public interface IRepositorioNotas {

    void guardar(INota nota);

    List<INota> obtenerTodas();

    void limpiar();
}
