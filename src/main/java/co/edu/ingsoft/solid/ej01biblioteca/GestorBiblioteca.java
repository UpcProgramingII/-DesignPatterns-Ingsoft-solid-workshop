package co.edu.ingsoft.solid.ej01biblioteca;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GestorBiblioteca {

    private static final Path ARCHIVO = Path.of("datos", "prestamos.csv");
    private static final String API_CORREO = "https://correo.universidad.edu.co/api/v1/enviar";

    private final HttpClient http = HttpClient.newHttpClient();

    public void registrarPrestamo(String codigoUsuario, String tipoUsuario, String isbn) throws IOException {
        List<Prestamo> prestamos = leerPrestamos();
        long activos = prestamos.stream()
                .filter(p -> p.getCodigoUsuario().equals(codigoUsuario) && p.estaActivo())
                .count();
        int maximo = tipoUsuario.equals("DOCENTE") ? 10 : 3;
        if (activos >= maximo) {
            throw new IllegalStateException("El usuario alcanzó el máximo de préstamos: " + maximo);
        }
        int dias = tipoUsuario.equals("DOCENTE") ? 30 : 8;
        LocalDate hoy = LocalDate.now();
        Prestamo nuevo = new Prestamo(codigoUsuario, tipoUsuario, isbn, hoy, hoy.plusDays(dias));
        prestamos.add(nuevo);
        escribirPrestamos(prestamos);
        enviarCorreo(codigoUsuario + "@universidad.edu.co", "Préstamo registrado",
                "Debe devolver el libro " + isbn + " a más tardar el " + nuevo.getFechaLimite());
    }

    public double registrarDevolucion(String codigoUsuario, String isbn, LocalDate fecha) throws IOException {
        List<Prestamo> prestamos = leerPrestamos();
        Prestamo prestamo = prestamos.stream()
                .filter(p -> p.getCodigoUsuario().equals(codigoUsuario)
                        && p.getIsbn().equals(isbn) && p.estaActivo())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No existe un préstamo activo"));
        prestamo.marcarDevuelto(fecha);
        escribirPrestamos(prestamos);

        double multa = calcularMulta(prestamo);
        if (multa > 0) {
            enviarCorreo(codigoUsuario + "@universidad.edu.co", "Multa por devolución tardía",
                    "Se generó una multa de $" + String.format("%,.0f", multa));
        }
        return multa;
    }

    public String generarReporteMensual(YearMonth mes) throws IOException {
        List<Prestamo> delMes = leerPrestamos().stream()
                .filter(p -> YearMonth.from(p.getFechaPrestamo()).equals(mes))
                .toList();
        double totalMultas = 0;
        int conRetraso = 0;
        for (Prestamo p : delMes) {
            double multa = calcularMulta(p);
            if (multa > 0) {
                conRetraso++;
                totalMultas += multa;
            }
        }
        StringBuilder html = new StringBuilder("<html><body>");
        html.append("<h1>Reporte de préstamos ").append(mes).append("</h1>");
        html.append("<p>Préstamos realizados: ").append(delMes.size()).append("</p>");
        html.append("<p>Préstamos con retraso: ").append(conRetraso).append("</p>");
        html.append("<p>Multas causadas: $").append(String.format("%,.0f", totalMultas)).append("</p>");
        html.append("</body></html>");
        return html.toString();
    }

    private double calcularMulta(Prestamo p) {
        if (p.getTipoUsuario().equals("DOCENTE")) {
            return 0;
        }
        LocalDate fin = p.getFechaDevolucion() != null ? p.getFechaDevolucion() : LocalDate.now();
        long diasRetraso = ChronoUnit.DAYS.between(p.getFechaLimite(), fin);
        return diasRetraso > 0 ? diasRetraso * 1_500 : 0;
    }

    private List<Prestamo> leerPrestamos() throws IOException {
        if (!Files.exists(ARCHIVO)) {
            return new ArrayList<>();
        }
        try (Stream<String> lineas = Files.lines(ARCHIVO)) {
            return lineas.filter(l -> !l.isBlank())
                    .map(Prestamo::desdeCsv)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
    }

    private void escribirPrestamos(List<Prestamo> prestamos) throws IOException {
        Files.createDirectories(ARCHIVO.getParent());
        Files.write(ARCHIVO, prestamos.stream().map(Prestamo::aCsv).toList());
    }

    private void enviarCorreo(String para, String asunto, String cuerpo) {
        String json = """
                {"para": "%s", "asunto": "%s", "cuerpo": "%s"}""".formatted(para, asunto, cuerpo);
        HttpRequest solicitud = HttpRequest.newBuilder(URI.create(API_CORREO))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        try {
            http.send(solicitud, HttpResponse.BodyHandlers.discarding());
        } catch (IOException | InterruptedException e) {
            System.err.println("No se pudo enviar el correo: " + e.getMessage());
        }
    }
}
