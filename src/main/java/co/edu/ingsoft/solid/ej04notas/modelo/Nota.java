package co.edu.ingsoft.solid.ej04notas.modelo;

public final class Nota implements INota {

    private final double valor;
    private final double peso;

    Nota(double valor, double peso) {
        this.valor = valor;
        this.peso = peso;
    }

    @Override
    public double getValor() {
        return valor;
    }

    @Override
    public double getPeso() {
        return peso;
    }
}
