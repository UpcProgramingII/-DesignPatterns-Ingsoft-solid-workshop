package co.edu.ingsoft.solid.ej05matricula.dominio;

public class Matricula {

    private final String codigo;
    private final IEstudiante estudiante;
    private final String periodo;
    private final double valorBase;
    private final double descuento;
    private String estado = "PENDIENTE";

    public Matricula(IEstudiante estudiante, String periodo, double valorBase, double descuento) {
        this.codigo = estudiante.getCodigo() + "-" + periodo;
        this.estudiante = estudiante;
        this.periodo = periodo;
        this.valorBase = valorBase;
        this.descuento = descuento;
    }

    public String getCodigo()          { return codigo; }
    public IEstudiante getEstudiante() { return estudiante; }
    public String getPeriodo()         { return periodo; }
    public double getValorBase()       { return valorBase; }
    public double getDescuento()       { return descuento; }
    public double getValorFinal()      { return valorBase - descuento; }
    public String getEstado()          { return estado; }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
