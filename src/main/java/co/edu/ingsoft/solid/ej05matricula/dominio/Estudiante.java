package co.edu.ingsoft.solid.ej05matricula.dominio;

public record Estudiante(String codigo, String nombre, int estrato, boolean hermanoMatriculado, String convenio)
        implements IEstudiante {

    @Override public String getCodigo() { return codigo; }
    @Override public String getNombre() { return nombre; }
    @Override public int getEstrato()   { return estrato; }
    @Override public boolean tieneHermanoMatriculado() { return hermanoMatriculado; }
    @Override public String getConvenio() { return convenio; }
}
