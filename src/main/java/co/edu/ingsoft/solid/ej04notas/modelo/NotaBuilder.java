package co.edu.ingsoft.solid.ej04notas.modelo;

public final class NotaBuilder {

    private double valor;
    private double peso;

    public static NotaBuilder nuevaNota() {
        return new NotaBuilder();
    }

    public NotaBuilder conValor(double valor) {
        this.valor = valor;
        return this;
    }

    public NotaBuilder conPeso(double peso) {
        this.peso = peso;
        return this;
    }

    public INota construir() {
        return new Nota(valor, peso);
    }
}
