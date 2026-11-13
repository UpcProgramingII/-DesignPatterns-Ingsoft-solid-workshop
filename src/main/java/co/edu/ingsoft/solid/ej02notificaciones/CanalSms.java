package co.edu.ingsoft.solid.ej02notificaciones;

import java.time.LocalDateTime;
import java.util.UUID;

public class CanalSms implements CanalNotificacion {

    private static final int MAX_CARACTERES = 160;

    @Override
    public String enviarTexto(String destino, String mensaje) {
        String texto = mensaje.length() > MAX_CARACTERES ? mensaje.substring(0, MAX_CARACTERES) : mensaje;
        System.out.println("[SMS] a " + destino + ": " + texto);
        return UUID.randomUUID().toString();
    }

    @Override
    public String enviarConAdjunto(String destino, String mensaje, String nombreArchivo, byte[] contenido) {
        throw new UnsupportedOperationException("SMS no admite adjuntos");
    }

    @Override
    public String enviarHtml(String destino, String asunto, String html) {
        return enviarTexto(destino, html.replaceAll("<[^>]+>", ""));
    }

    @Override
    public void programarEnvio(String destino, String mensaje, LocalDateTime cuando) {
        throw new UnsupportedOperationException("El proveedor de SMS no permite programar envíos");
    }

    @Override
    public EstadoEntrega consultarEstado(String idMensaje) {
        return EstadoEntrega.DESCONOCIDO;
    }
}
