package co.edu.ingsoft.solid.ej02notificaciones;

public record Pedido(String id, String cliente, String email, String telefono,
                     String tokenDispositivo, double total, String canalPreferido) {
}
