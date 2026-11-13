package co.edu.ingsoft.solid.ej04notas.eventos;

import java.time.LocalDateTime;

public record EventoCalculoRealizado(double definitiva, int cantidadNotas, LocalDateTime momento) {
}
