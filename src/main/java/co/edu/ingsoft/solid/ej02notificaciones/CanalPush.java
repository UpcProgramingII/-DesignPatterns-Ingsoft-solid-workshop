package co.edu.ingsoft.solid.ej02notificaciones;

import java.time.LocalDateTime;
import java.util.UUID;

public class CanalPush implements CanalNotificacion {

    @Override
    public String enviarTexto(String destino, String mensaje) {
        System.out.println("[PUSH] a " + destino + ": " + mensaje);
        return UUID.randomUUID().toString();
    }

    @Override
    public String enviarConAdjunto(String destino, String mensaje, String nombreArchivo, byte[] contenido) {
        return enviarTexto(destino, mensaje);
    }

    @Override
    public String enviarHtml(String destino, String asunto, String html) {
        throw new UnsupportedOperationException("Las notificaciones push no admiten HTML");
    }

    @Override
    public void programarEnvio(String destino, String mensaje, LocalDateTime cuando) {
        System.out.println("[PUSH] programado para " + cuando + " a " + destino + ": " + mensaje);
    }

    @Override
    public EstadoEntrega consultarEstado(String idMensaje) {
        return EstadoEntrega.ENTREGADO;
    }
}
