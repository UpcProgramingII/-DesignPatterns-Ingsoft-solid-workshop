package co.edu.ingsoft.solid.ej05matricula.infraestructura;

import co.edu.ingsoft.solid.ej05matricula.dominio.Matricula;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface RepositorioMatriculas {

    void guardar(Matricula matricula) throws SQLException;

    /** Devuelve las columnas codigo, estudiante, periodo, valor_final y estado. */
    ResultSet buscarPorCodigo(String codigo) throws SQLException;

    void actualizarEstado(String codigo, String estado) throws SQLException;

    Connection getConexion() throws SQLException;
}
