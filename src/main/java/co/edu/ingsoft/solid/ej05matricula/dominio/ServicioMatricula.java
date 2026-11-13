package co.edu.ingsoft.solid.ej05matricula.dominio;

import co.edu.ingsoft.solid.ej05matricula.infraestructura.PasarelaPagosBancoAndino;
import co.edu.ingsoft.solid.ej05matricula.infraestructura.RepositorioMatriculas;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServicioMatricula {

    private final RepositorioMatriculas repositorio;
    private final CalculadoraDescuento calculadora = new CalculadoraDescuento();
    private final PasarelaPagosBancoAndino pasarela =
            new PasarelaPagosBancoAndino("https://api.bancoandino.com.co/v2", "llave-produccion-2026");

    public ServicioMatricula(RepositorioMatriculas repositorio) {
        this.repositorio = repositorio;
    }

    public Matricula liquidar(IEstudiante estudiante, String periodo, double valorBase) {
        double descuento = calculadora.calcular(estudiante, valorBase);
        Matricula matricula = new Matricula(estudiante, periodo, valorBase, descuento);
        try {
            repositorio.guardar(matricula);
        } catch (SQLException e) {
            throw new IllegalStateException("No se pudo guardar la matrícula", e);
        }
        return matricula;
    }

    public String pagar(String codigoMatricula, String numeroTarjeta) {
        try (ResultSet rs = repositorio.buscarPorCodigo(codigoMatricula)) {
            if (!rs.next()) {
                throw new IllegalArgumentException("No existe la matrícula " + codigoMatricula);
            }
            if ("PAGADA".equals(rs.getString("estado"))) {
                throw new IllegalStateException("La matrícula ya fue pagada");
            }
            double valor = rs.getDouble("valor_final");
            String autorizacion = pasarela.cobrarConTarjeta(numeroTarjeta, valor, "MAT-" + codigoMatricula);
            repositorio.actualizarEstado(codigoMatricula, "PAGADA");
            return autorizacion;
        } catch (SQLException e) {
            throw new IllegalStateException("Error consultando la matrícula", e);
        }
    }
}
