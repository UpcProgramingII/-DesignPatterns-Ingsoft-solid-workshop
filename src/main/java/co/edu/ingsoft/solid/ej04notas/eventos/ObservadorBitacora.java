package co.edu.ingsoft.solid.ej04notas.eventos;

public class ObservadorBitacora implements IObservadorCalculo {

    @Override
    public void alCalcular(EventoCalculoRealizado evento) {
        System.out.println("[bitácora] " + evento.momento() + " - cálculo con "
                + evento.cantidadNotas() + " notas");
    }
}
