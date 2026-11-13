package co.edu.ingsoft.solid.ej02notificaciones;

public class DemoNotificaciones {

    public static void main(String[] args) {
        ServicioNotificaciones servicio = new ServicioNotificaciones();
        byte[] pdf = new byte[2048];

        Pedido ana = new Pedido("P-101", "Ana", "ana@correo.co", "3001234567", "tok-ana", 58_900, "EMAIL");
        Pedido luis = new Pedido("P-102", "Luis", "luis@correo.co", "3109876543", "tok-luis", 23_500, "PUSH");
        Pedido marta = new Pedido("P-103", "Marta", "marta@correo.co", "3205557788", "tok-marta", 41_000, "SMS");

        for (Pedido p : new Pedido[] {ana, luis, marta}) {
            System.out.println("\n=== Pedido " + p.id() + " (canal " + p.canalPreferido() + ") ===");
            ejecutar("Confirmación", () -> servicio.notificarConfirmacion(p));
            ejecutar("Factura", () -> servicio.enviarFactura(p, pdf));
            ejecutar("Recordatorio", () -> servicio.recordarCalificacion(p));
        }
    }

    private static void ejecutar(String paso, Runnable accion) {
        try {
            accion.run();
        } catch (RuntimeException e) {
            System.out.println("  !! " + paso + " falló: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }
}
