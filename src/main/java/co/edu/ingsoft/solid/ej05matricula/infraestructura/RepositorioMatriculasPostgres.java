package co.edu.ingsoft.solid.ej05matricula.infraestructura;

import co.edu.ingsoft.solid.ej05matricula.dominio.Matricula;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

public class RepositorioMatriculasPostgres implements RepositorioMatriculas {

    private final DataSource dataSource;

    public RepositorioMatriculasPostgres(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void guardar(Matricula m) throws SQLException {
        try (Connection c = getConexion();
             PreparedStatement ps = c.prepareStatement(
                     "INSERT INTO matricula(codigo, estudiante, periodo, valor_final, estado) "
                             + "VALUES (?, ?, ?, ?, ?)")) {
            ps.setString(1, m.getCodigo());
            ps.setString(2, m.getEstudiante().getCodigo());
            ps.setString(3, m.getPeriodo());
            ps.setDouble(4, m.getValorFinal());
            ps.setString(5, m.getEstado());
            ps.executeUpdate();
        }
    }

    @Override
    public ResultSet buscarPorCodigo(String codigo) throws SQLException {
        PreparedStatement ps = getConexion().prepareStatement(
                "SELECT codigo, estudiante, periodo, valor_final, estado FROM matricula WHERE codigo = ?");
        ps.setString(1, codigo);
        return ps.executeQuery();
    }

    @Override
    public void actualizarEstado(String codigo, String estado) throws SQLException {
        try (Connection c = getConexion();
             PreparedStatement ps = c.prepareStatement("UPDATE matricula SET estado = ? WHERE codigo = ?")) {
            ps.setString(1, estado);
            ps.setString(2, codigo);
            ps.executeUpdate();
        }
    }

    @Override
    public Connection getConexion() throws SQLException {
        return dataSource.getConnection();
    }
}
