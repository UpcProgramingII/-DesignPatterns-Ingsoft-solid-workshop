package co.edu.ingsoft.solid.ej02notificaciones;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CanalCorreo implements CanalNotificacion {

    private final Map<String, EstadoEntrega> enviados = new HashMap<>();

    @Override
    public String enviarTexto(String destino, String mensaje) {
        return registrar("[CORREO] a " + destino + ": " + mensaje);
    }

    @Override
    public String enviarConAdjunto(String destino, String mensaje, String nombreArchivo, byte[] contenido) {
        return registrar("[CORREO] a " + destino + ": " + mensaje
                + " (adjunto " + nombreArchivo + ", " + contenido.length + " bytes)");
    }

    @Override
    public String enviarHtml(String destino, String asunto, String html) {
        return registrar("[CORREO] a " + destino + " | " + asunto + " | " + html);
    }

    @Override
    public void programarEnvio(String destino, String mensaje, LocalDateTime cuando) {
        System.out.println("[CORREO] programado para " + cuando + " a " + destino + ": " + mensaje);
    }

    @Override
    public EstadoEntrega consultarEstado(String idMensaje) {
        return enviados.getOrDefault(idMensaje, EstadoEntrega.DESCONOCIDO);
    }

    private String registrar(String traza) {
        System.out.println(traza);
        String id = UUID.randomUUID().toString();
        enviados.put(id, EstadoEntrega.ENTREGADO);
        return id;
    }
}
