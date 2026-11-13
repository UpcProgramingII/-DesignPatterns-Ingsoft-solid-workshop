package co.edu.ingsoft.solid.ej02notificaciones;

import java.time.LocalDateTime;

public interface CanalNotificacion {

    /** Envía un mensaje de texto y devuelve el identificador del mensaje. */
    String enviarTexto(String destino, String mensaje);

    /** Envía un mensaje con un archivo adjunto y devuelve el identificador del mensaje. */
    String enviarConAdjunto(String destino, String mensaje, String nombreArchivo, byte[] contenido);

    /** Envía contenido HTML con asunto y devuelve el identificador del mensaje. */
    String enviarHtml(String destino, String asunto, String html);

    /** Deja un mensaje programado para la fecha y hora indicadas. */
    void programarEnvio(String destino, String mensaje, LocalDateTime cuando);

    /** Estado de entrega de un mensaje enviado por este canal. */
    EstadoEntrega consultarEstado(String idMensaje);
}
