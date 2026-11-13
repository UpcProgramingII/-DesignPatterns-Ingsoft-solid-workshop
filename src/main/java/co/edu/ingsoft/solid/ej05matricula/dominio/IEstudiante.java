package co.edu.ingsoft.solid.ej05matricula.dominio;

public interface IEstudiante {

    String getCodigo();

    String getNombre();

    int getEstrato();

    boolean tieneHermanoMatriculado();

    String getConvenio();
}
