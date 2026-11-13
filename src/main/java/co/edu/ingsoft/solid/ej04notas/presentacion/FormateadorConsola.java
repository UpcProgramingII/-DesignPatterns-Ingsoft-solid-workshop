package co.edu.ingsoft.solid.ej04notas.presentacion;

public class FormateadorConsola implements IFormateadorResultado {

    @Override
    public String formatear(double definitiva) {
        String estado = definitiva >= 3.0 ? "APROBÓ" : "REPROBÓ";
        return String.format("Nota definitiva: %.1f (%s)", definitiva, estado);
    }
}
