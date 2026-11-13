package co.edu.ingsoft.solid.ej01biblioteca;

import java.time.LocalDate;

public class Prestamo {

    private final String codigoUsuario;
    private final String tipoUsuario;
    private final String isbn;
    private final LocalDate fechaPrestamo;
    private final LocalDate fechaLimite;
    private LocalDate fechaDevolucion;

    public Prestamo(String codigoUsuario, String tipoUsuario, String isbn,
                    LocalDate fechaPrestamo, LocalDate fechaLimite) {
        this.codigoUsuario = codigoUsuario;
        this.tipoUsuario = tipoUsuario;
        this.isbn = isbn;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaLimite = fechaLimite;
    }

    public String getCodigoUsuario() { return codigoUsuario; }
    public String getTipoUsuario()   { return tipoUsuario; }
    public String getIsbn()          { return isbn; }
    public LocalDate getFechaPrestamo()   { return fechaPrestamo; }
    public LocalDate getFechaLimite()     { return fechaLimite; }
    public LocalDate getFechaDevolucion() { return fechaDevolucion; }

    public boolean estaActivo() {
        return fechaDevolucion == null;
    }

    public void marcarDevuelto(LocalDate fecha) {
        this.fechaDevolucion = fecha;
    }

    public String aCsv() {
        return String.join(";", codigoUsuario, tipoUsuario, isbn,
                fechaPrestamo.toString(), fechaLimite.toString(),
                fechaDevolucion == null ? "" : fechaDevolucion.toString());
    }

    public static Prestamo desdeCsv(String linea) {
        String[] c = linea.split(";", -1);
        Prestamo p = new Prestamo(c[0], c[1], c[2], LocalDate.parse(c[3]), LocalDate.parse(c[4]));
        if (!c[5].isEmpty()) {
            p.marcarDevuelto(LocalDate.parse(c[5]));
        }
        return p;
    }
}
