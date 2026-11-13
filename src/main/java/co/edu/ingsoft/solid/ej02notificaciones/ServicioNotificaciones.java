package co.edu.ingsoft.solid.ej02notificaciones;

import java.time.LocalDateTime;

public class ServicioNotificaciones {

    private final CanalCorreo correo = new CanalCorreo();
    private final CanalSms sms = new CanalSms();
    private final CanalPush push = new CanalPush();

    public void notificarConfirmacion(Pedido p) {
        switch (p.canalPreferido()) {
            case "EMAIL" -> correo.enviarHtml(p.email(), "Pedido " + p.id() + " confirmado",
                    "<h1>¡Gracias, " + p.cliente() + "!</h1><p>Total: $" + p.total() + "</p>");
            case "SMS" -> sms.enviarTexto(p.telefono(),
                    "DomiExpress: pedido " + p.id() + " confirmado. Total $" + p.total());
            case "PUSH" -> push.enviarTexto(p.tokenDispositivo(), "Tu pedido " + p.id() + " va en camino");
            default -> throw new IllegalArgumentException("Canal no soportado: " + p.canalPreferido());
        }
    }

    public void enviarFactura(Pedido p, byte[] pdf) {
        CanalNotificacion canal = canalDe(p);
        canal.enviarConAdjunto(destinoDe(p), "Factura del pedido " + p.id(),
                "factura-" + p.id() + ".pdf", pdf);
    }

    public void recordarCalificacion(Pedido p) {
        CanalNotificacion canal = canalDe(p);
        if (canal instanceof CanalSms) {
            canal.enviarTexto(destinoDe(p), "Califica tu pedido " + p.id() + " en domiexpress.co");
        } else {
            canal.programarEnvio(destinoDe(p), "¿Qué tal tu pedido " + p.id() + "? Califícalo",
                    LocalDateTime.now().plusHours(2));
        }
    }

    private CanalNotificacion canalDe(Pedido p) {
        return switch (p.canalPreferido()) {
            case "EMAIL" -> correo;
            case "SMS" -> sms;
            case "PUSH" -> push;
            default -> throw new IllegalArgumentException("Canal no soportado: " + p.canalPreferido());
        };
    }

    private String destinoDe(Pedido p) {
        return switch (p.canalPreferido()) {
            case "EMAIL" -> p.email();
            case "SMS" -> p.telefono();
            case "PUSH" -> p.tokenDispositivo();
            default -> throw new IllegalArgumentException("Canal no soportado: " + p.canalPreferido());
        };
    }
}
